package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import modelo.Carrito;
import modelo.Catalogo;
import modelo.ElementoCarrito;
import modelo.ElementoCatalogo;
import modelo.Observable;
import modelo.Observador;
import modelo.Pedido;
import modelo.PedidoExpress;
import modelo.PedidoRecoger;
import modelo.PedidoSitio;
import modelo.Pedido_;
import modelo.Pedido_.MODO;
import modelo.Plato;
import modelo.ReceptorDeCatalogo;
import modelo.VisorDeCostos;
import vista.CantidadDialog;
import vista.VistaCliente;
import vista.VistaPedidoExpress;
import vista.VistaPedidoRecoger;

public class Controlador implements Observable, Observador, ActionListener {
	
	public static final int PUERTO = 7777;
	public static final String HOST = "";
	
	Cliente cliente;
	VistaCliente vistaCliente;
	CantidadDialog ventanaIngresoNumero;
	VisorDeCostos visorDeCostos;
	ReceptorDeCatalogo receptorCatalogo;
	VistaPedidoExpress vistaPedidoExpress;
	VistaPedidoRecoger vistaPedidoRecoger;
	
	ArrayList<Observador> observadores = new ArrayList<Observador>();
	
	public Controlador (VistaCliente vistaCliente, Cliente cliente, 
			VisorDeCostos visorDeCostos, Catalogo catalogo, Carrito carrito, 
			ReceptorDeCatalogo receptorDeCatalogo) {
		
		this.vistaCliente = vistaCliente;
		this.cliente = cliente;
		this.cliente.receptorCatalogo = this.receptorCatalogo = receptorDeCatalogo;
		this.visorDeCostos = visorDeCostos;
		this.vistaPedidoExpress = new VistaPedidoExpress();
		this.vistaPedidoRecoger = new VistaPedidoRecoger();
		this.receptorCatalogo.vista = this.vistaCliente;
		
		this.vistaCliente.controlador = this;
		
		enlazarObservador(this);
		enlazarObservador(this.vistaCliente);
		this.vistaCliente.enlazarEventos(this);
		this.vistaPedidoExpress.enlazarEventos(this);
		this.vistaPedidoRecoger.enlazarEventos(this);
		this.ventanaIngresoNumero = new CantidadDialog();
		
		this.cliente.setCatalogo(catalogo);
		this.vistaCliente.catalogo = this.cliente.catalogo;
		this.vistaCliente.carrito = this.cliente.carrito;
		
		enlazarInformacionLateral();
		this.vistaCliente.tblCatalogo.addMouseListener(new MouseListener( ) {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				visualizarInfo();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				visualizarInfo();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				visualizarInfo();
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				visualizarInfo();	
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				visualizarInfo();				
			}
		});
		this.ventanaIngresoNumero.enlazarEventos(this);
		notificar();
		this.vistaCliente.setVisible(true);
		this.cliente.receptorCatalogo.run();
	}
	
	public void enviarPedido (Pedido_ pedido, MODO modo) throws IOException {
		System.out.println("ENVIAR PEDIDO");
		Socket socket = new Socket(HOST, PUERTO);
        System.out.println("...conectado...");
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        // obtenemos el pedido a enviar
        Pedido_ pedidoAEnviar = pedido;
        pedido.modo = modo;
        objectOutputStream.writeObject(pedidoAEnviar);
        socket.close();
	}

	
	public void enlazarInformacionLateral() {
		// enlazar etiquetas de precios sumados
		this.visorDeCostos.setCarrito(this.cliente.carrito);
		this.visorDeCostos.setEtiquetaExpress(this.vistaCliente.lblTotalExpress);
		this.visorDeCostos.setEtiquetaRecoger(this.vistaCliente.lblTotalRecoger);
		this.visorDeCostos.setEtiquetaSitio(this.vistaCliente.lblTotalSitio);
	}
	
	private boolean comprobarRangoCarrito(int indice) {
		return indice >= 0 && indice < this.vistaCliente.tblCarrito.getRowCount();
	}
	
	private boolean comprobarRangoCatalogo(int indice) {
		return indice >= 0 && indice < this.vistaCliente.tblCatalogo.getRowCount();
	}
	
	public String obtenerFecha() {
		Calendar calendario = new GregorianCalendar();
		int anio = calendario.get(Calendar.YEAR);
		int mes = calendario.get(Calendar.MONTH);
		int dia = calendario.get(Calendar.DAY_OF_MONTH);
		return anio + "/" + mes + "/" + dia;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		// obtenemos las columnas seleccionadas
		int indiceCarrito = this.vistaCliente.tblCarrito.getSelectedRow();
		int indiceCatalogo = this.vistaCliente.ultimoIndiceSeleccionado;
		
		if (ae.getSource() == this.vistaCliente.btnProcesarPedido) {
			if (!this.cliente.carrito.getElementosCarrito().isEmpty()) {
				if (!this.vistaCliente.txtNombre.getText().equals("")) {
					if (this.vistaCliente.rbtSitio.isSelected()) { // pago normal
						
						PedidoSitio pedidoS = new PedidoSitio(Pedido.nuevoId(), obtenerFecha(),
								this.cliente.carrito.sumaOrdenCompra(), this.cliente.carrito);
						
						Pedido_ pedido = new Pedido_();
						pedido.sitio = pedidoS;
				
						try {
							this.enviarPedido(pedido, Pedido_.MODO.EXPRESS);
							this.cliente.pedidoActual = pedido;
						} catch (IOException e) {
							JOptionPane.showMessageDialog(null, "No se ha podido completar la transaccion");
							this.cliente.pedidoActual = null;
						}
						
					} else if (this.vistaCliente.rbtRecoger.isSelected()) { // pago para recoger
						this.vistaPedidoRecoger.lblFechaActual.setText(obtenerFecha());
						this.vistaPedidoRecoger.lblTotalPedido.setText(this.vistaCliente.lblPedidoParaLlevar.getText());
						this.vistaPedidoRecoger.setVisible(true);
					} else if (this.vistaCliente.rbtExpress.isSelected()) {
						this.vistaPedidoExpress.lblFecha.setText(obtenerFecha());
						this.vistaPedidoExpress.lblTotalPedido.setText(this.vistaCliente.lblPedidoExpress.getText());
						this.vistaPedidoExpress.setVisible(true);
					}
		
				} else 
					JOptionPane.showMessageDialog(null, "Debe ingresar su nombre");
			} else 
				JOptionPane.showMessageDialog(null, "El carrito de compras está vacio");
			
		} else if (ae.getSource() == this.vistaCliente.btnEliminarCarrito) {
			// eliminar indice seleccionado del carrito
			if (comprobarRangoCarrito(indiceCarrito)) 
				this.cliente.carrito.getElementosCarrito().remove(indiceCarrito);	
		} else if (ae.getSource() == this.ventanaIngresoNumero.btnOk) {
			int cantidad = 0;
			try {
				cantidad = Integer.parseInt(this.ventanaIngresoNumero.txtCantidad.getText());
				if (cantidad <= 0)
					cantidad = -1;
			} catch (Exception e) {
				cantidad = -1;
			}
			if (comprobarRangoCatalogo(indiceCatalogo) && cantidad != -1) {
				Plato platoSeleccionado = this.cliente.catalogo.get(indiceCatalogo).getPlatoAsociado();
				// se ingreso un valor valido, entonces .....
				ElementoCarrito elementoCarrito = new ElementoCarrito(platoSeleccionado, cantidad);
				this.cliente.carrito.getElementosCarrito().add(elementoCarrito); // se agrega el elemento
				this.ventanaIngresoNumero.dispose();
				this.ventanaIngresoNumero.reset();
				
			} else {
				JOptionPane.showMessageDialog(null, "Debe digitar un valor numérico");
				this.ventanaIngresoNumero.reset();
			}
		} else if (ae.getSource() == this.vistaCliente.btnAumenta) {
			if (this.vistaCliente.tblCarrito.getSelectedRow() != -1)
			if (comprobarRangoCarrito(indiceCarrito))
				this.cliente.carrito.getElementosCarrito().get(indiceCarrito).aumentar();
			
		} else if (ae.getSource() == this.vistaCliente.btnDisminuye) {
			if (this.vistaCliente.tblCarrito.getSelectedRow() != -1) {
			if (comprobarRangoCarrito(indiceCarrito))
				this.cliente.carrito.getElementosCarrito().get(indiceCarrito).disminuir();
			if (this.cliente.carrito.getElementosCarrito().get(indiceCarrito).getCantidadPorPlato() <= 0)
				this.cliente.carrito.getElementosCarrito().remove(indiceCarrito);
			}
			
		} else if (ae.getSource() == this.vistaCliente.btnAgregarAlCarrito) {
			if (this.vistaCliente.tblCatalogo.getSelectedRow() != -1) {
				this.ventanaIngresoNumero.setVisible(true);
				this.ventanaIngresoNumero.reset();
			} else {
				JOptionPane.showMessageDialog(null, "Debe seleccionar algún platillo");
			}
			
			this.vistaCliente.ultimoIndiceSeleccionado = this.vistaCliente.tblCatalogo.getSelectedRow();
		} else if (ae.getSource() == this.ventanaIngresoNumero.btnCancelar) {
			this.ventanaIngresoNumero.setVisible(false);
			this.ventanaIngresoNumero.reset();
			this.ventanaIngresoNumero.dispose();
			
			
		} else if (ae.getSource() == this.vistaCliente.tblCatalogo) {
			if (this.vistaCliente.tblCatalogo.getSelectedRow() != -1) {
				ElementoCatalogo e = this.cliente.catalogo.getObjetoAsociado(
						new String[] {(String)this.vistaCliente.tblCatalogo.getValueAt(this.vistaCliente.tblCatalogo.getSelectedRow(), 0),
								(String)this.vistaCliente.tblCatalogo.getValueAt(this.vistaCliente.tblCatalogo.getSelectedRow(), 1)
						});
			}
		} else if (ae.getSource() == this.vistaPedidoExpress.btnConfirmarPedido) {
			// int idUnico, String fecha, float costo, Carrito listaPedidos, 
			// String direccion, String numeroCelular, String nombreEntregar
			PedidoExpress pedidoExpress = null;
			if (!this.vistaPedidoExpress.txtDireccion.getText().equals("") && 
				!this.vistaPedidoExpress.txtTelefono.getText().equals("")) {
			        pedidoExpress = new PedidoExpress(Pedido.nuevoId(), obtenerFecha(),
					this.cliente.carrito.sumaOrdenCompra(), this.cliente.carrito, 
					this.vistaPedidoExpress.txtDireccion.getText(), this.vistaPedidoExpress.txtTelefono.getText());
			        	   
			        Pedido_ pedido = new Pedido_();
			        pedido.express = pedidoExpress;
			        try {
						this.enviarPedido(pedido, Pedido_.MODO.EXPRESS);
						this.cliente.pedidoActual = pedido;
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "No se ha podido completar la transaccion");
						this.cliente.pedidoActual = null;
					}
			        
			} else {
				JOptionPane.showMessageDialog(null, "No pueden haber campos vacios");
			}
		} else if (ae.getSource() == this.vistaPedidoExpress.btnCancelar) {
			this.vistaPedidoExpress.reset();
			this.vistaPedidoExpress.dispose();
		} else if (ae.getSource() == this.vistaPedidoRecoger.btnConfirmarPedido) {
			// int idUnico, String fecha, float costo, Carrito listaPedidos, String nombreRetirador
			PedidoRecoger pedidoRecoger = null;
			if (!this.vistaPedidoRecoger.txtRetirante.getText().equals("") && 
				!this.vistaPedidoRecoger.txtNumeroTelefono.getText().equals("")) {
				
			        pedidoRecoger = new PedidoRecoger(Pedido.nuevoId(), obtenerFecha(),
					this.cliente.carrito.sumaOrdenCompra() + PedidoExpress.getCostoExpress(), 
					this.cliente.carrito, this.vistaPedidoRecoger.txtRetirante.getText(),
					this.vistaPedidoRecoger.txtNumeroTelefono.getText());

			        Pedido_ pedido = new Pedido_();
			        pedido.recoger = pedidoRecoger;
			        try {
						this.enviarPedido(pedido, Pedido_.MODO.RECOGER);
						this.cliente.pedidoActual = pedido;
			        } catch (IOException e) {
						JOptionPane.showMessageDialog(null, "No se ha podido completar la transaccion");
						this.cliente.pedidoActual = null;
					}
			        
			} else {
				JOptionPane.showMessageDialog(null, "No pueden haber campos vacios");
			}	
		} else if (ae.getSource() == this.vistaPedidoRecoger.btnCancelar) {
			this.vistaPedidoRecoger.reset();
			this.vistaPedidoRecoger.dispose();
		} else if (ae.getSource() == this.vistaPedidoExpress.btnCancelar) {
			this.vistaPedidoExpress.reset();
			this.vistaPedidoExpress.dispose();
		}
		
		// alertar mediante el patron observador
		notificar();
		calcularCostos();
	}

	public void calcularCostos() {
		// calcular costo express
		this.vistaCliente.lblTotalExpress.setText( String.valueOf(this.cliente.carrito.sumaOrdenCompra() 
				+ PedidoExpress.getCostoExpress()));
		
		// calcular costo por recoger
		this.vistaCliente.lblTotalRecoger.setText( String.valueOf(
					this.cliente.carrito.sumaOrdenCompra() + PedidoRecoger.getCostoEmpaque())
		);
				
		// calcular costo normal
		this.vistaCliente.lblTotalSitio.setText( String.valueOf(this.cliente.carrito.sumaOrdenCompra()));
	}

	public void visualizarInfo() {
		int indiceSeleccionado = this.vistaCliente.tblCatalogo.getSelectedRow();
		if (indiceSeleccionado != -1) {
			String infoBuscar[] = {(String)this.vistaCliente.tblCatalogo.getValueAt(indiceSeleccionado, 0), 
									(String) this.vistaCliente.tblCatalogo.getValueAt(indiceSeleccionado, 1)};
			ElementoCatalogo e = this.cliente.catalogo.getObjetoAsociado(infoBuscar);
			if (e != null) {
				// cargar informacion del platillo
				this.vistaCliente.lblTotalCalorias.setText(e.getPlatoAsociado().getCalorias() + " kcal");
				this.vistaCliente.lblTotalPorciones.setText(e.getPlatoAsociado().getRacion());
				this.vistaCliente.lblTotalPrecio.setText("¢" + e.getPlatoAsociado().getPrecio());
				if (e.getImagen() != null)
					this.vistaCliente.lblImagen.setIcon(e.getImagen());
				else
					this.vistaCliente.lblImagen.setText("");
			} else  {
				this.vistaCliente.lblTotalCalorias.setText("-");
				this.vistaCliente.lblTotalPorciones.setText("-");
				this.vistaCliente.lblTotalPrecio.setText("-");
				this.vistaCliente.lblImagen.setText("");
			}
		}	
	}
	
	@Override
	public void notificar() {
		this.observadores.forEach((o)->o.actualizar());
	}

	@Override
	public void enlazarObservador(Observador o) {
		this.observadores.add(o);
	}

	@Override
	public void actualizar() {
		this.vistaCliente.actualizar();
	}

	
	

}
