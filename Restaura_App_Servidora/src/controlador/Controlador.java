package controlador;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import modelo.Catalogo;
import modelo.ElementoCatalogo;
import modelo.Observable;
import modelo.Observador;
import modelo.Plato;
import modelo.ReceptorDePedidos;
import vista.VistaAdmin;
import vista.VistaAgregarElementoCatalogo;
import vista.VistaConsultaRegistros;
import vista.VistaEdicionPrecios;

public class Controlador implements Observador, ActionListener, Observable {

	public final String HOST = "192.168.1.32";
	public final int PUERTO = 7777;
	public static int contadorId = 0;
	
	public Administrador admin;
	public VistaAdmin vistaAdmin;
	public VistaConsultaRegistros vistaRegistros;
	public VistaAgregarElementoCatalogo vistaNuevoElemento;
	public ArrayList<Observador> observadores;
	public VistaEdicionPrecios vistaEdicionPrecios;
	
	public Controlador(Administrador administrador, VistaAdmin vistaAdministrador, 
			ReceptorDePedidos receptorPedidos) {
		
		this.admin = administrador;
		this.vistaEdicionPrecios = new VistaEdicionPrecios();
		this.vistaAdmin = vistaAdministrador;
		this.observadores = new ArrayList<Observador>();
		this.vistaAdmin.catalogo = this.admin.getCatalogo();
		this.vistaAdmin.pedidos = this.admin.getPedidos();
		this.vistaEdicionPrecios.enlazarEventos(this);
		this.vistaRegistros = new VistaConsultaRegistros();
		this.vistaNuevoElemento = new VistaAgregarElementoCatalogo();
		this.vistaNuevoElemento.enlazarEventos(this);
		this.admin.tabla = this.vistaAdmin.tblPedidos;
		
		cargarPreciosServicios();
		this.vistaAdmin.tblCatalogo.addMouseListener(new MouseListener( ) {
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
		
		this.vistaAdmin.admin = this.admin;
		enlazarObservador(this);
		enlazarObservador(this.vistaAdmin);
		this.vistaAdmin.enlazarEventos(this);
		this.vistaEdicionPrecios.enlazarEventos(this);
		notificar();
		actualizar();
		this.vistaAdmin.setVisible(true);
		this.admin.getReceptorPedidos().start();
	}

	public void enviarCatalogo () throws IOException {
		System.out.println("ENVIAR");
		Socket socket = new Socket(HOST, PUERTO);
        System.out.println("...conectado...");
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        
        // obtenemos el catalogo a enviar
        Catalogo catalogoPorEnviar = this.admin.getCatalogo(); 
        objectOutputStream.writeObject(catalogoPorEnviar);
        socket.close();
	}

	
	public void cargarImagen() { 
	    File fichero;
	    JFrame ventana = new JFrame();
	    ventana.setUndecorated(true);
	    JFileChooser ventanaEleccionImagen = new JFileChooser();
	    ventana.add(ventanaEleccionImagen);
	    JLabel etiquetaACambiar = this.vistaNuevoElemento.lblImagen;
	    FileNameExtensionFilter filtro =
	            new FileNameExtensionFilter("JPG y PNG", "jpg", "png");
	    ventanaEleccionImagen.setFileFilter(filtro);
	    
	    // guardamos el retorno de la ventana
	    int resultado = ventanaEleccionImagen.showOpenDialog(ventana);
	    
	    if(JFileChooser.APPROVE_OPTION == resultado) {
	        fichero= ventanaEleccionImagen.getSelectedFile();
	        try {
	            ImageIcon icon= new ImageIcon(fichero.toString());   
	            Icon icono = new ImageIcon(icon.getImage().
	                    getScaledInstance(etiquetaACambiar.getWidth(),
	                    		etiquetaACambiar.getHeight(), 
	                    		Image.SCALE_DEFAULT));
	            this.vistaNuevoElemento.lblImagen.setIcon(icono);
	           
	            	
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, "Error al abrir la imagen", "Aviso", JOptionPane.INFORMATION_MESSAGE);
	        }
	    } else {
	    	JOptionPane.showMessageDialog(null, "No se ha cargado la imagen", "Aviso", JOptionPane.INFORMATION_MESSAGE);
	    }
	}
	
	public void visualizarInfo() {
		int indiceSeleccionado = this.vistaAdmin.tblCatalogo.getSelectedRow();
		if (indiceSeleccionado != -1) {
			String infoBuscar[] = {(String)this.vistaAdmin.tblCatalogo.getValueAt(indiceSeleccionado, 0), 
									(String) this.vistaAdmin.tblCatalogo.getValueAt(indiceSeleccionado, 1)};
			ElementoCatalogo e = this.admin.getCatalogo().getObjetoAsociado(infoBuscar);
			if (e != null) {
				// cargar informacion del platillo
				this.vistaAdmin.lblTotalCalorias.setText(e.getPlatoAsociado().getCalorias() + " kcal");
				this.vistaAdmin.lblTotalPorciones.setText(e.getPlatoAsociado().getRacion());
				this.vistaAdmin.lblTotalPrecio.setText("¢" + e.getPlatoAsociado().getPrecio());
				if (e.getImagen() != null)
					this.vistaAdmin.lblImg.setIcon(e.getImagen());
				else
					this.vistaAdmin.lblImg.setText("");
			} else  {
				this.vistaAdmin.lblImg.setIcon(null);
				this.vistaAdmin.lblTotalCalorias.setText("-");
				this.vistaAdmin.lblTotalPorciones.setText("-");
				this.vistaAdmin.lblTotalPrecio.setText("-");
				this.vistaAdmin.lblImg.setText("");
			}
		}	
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		int indiceSeleccionado = this.vistaAdmin.tblCatalogo.getSelectedRow();
		
		if (ae.getSource() == this.vistaAdmin.btnAgregar) {
			this.vistaNuevoElemento.reset();
			this.vistaNuevoElemento.setVisible(true);
			this.vistaNuevoElemento.lblCodigo.setText(Plato.codigos[0]);
			
		} else if (ae.getSource() == this.vistaAdmin.btnEliminar) {
			if (indiceSeleccionado != -1) {
				String[] infoTabla = {(String)this.vistaAdmin.tblCatalogo.getValueAt(indiceSeleccionado, 0),
						(String)this.vistaAdmin.tblCatalogo.getValueAt(indiceSeleccionado, 1)};
				ElementoCatalogo e = this.admin.getCatalogo().getObjetoAsociado(infoTabla);
				this.admin.getCatalogo().remove(e);
			}
			
		} else if (ae.getSource() == this.vistaAdmin.btnEditarExpress) {
			this.vistaEdicionPrecios.reset();
			this.vistaEdicionPrecios.setVisible(true);
			this.vistaEdicionPrecios.modo = VistaEdicionPrecios.MODO.EXPRESS;
			this.vistaEdicionPrecios.setTitulo("Nuevo monto de express");
			
			
		} else if (ae.getSource() == this.vistaAdmin.btnEditarParaLlevar) {
			this.vistaEdicionPrecios.reset();
			this.vistaEdicionPrecios.setVisible(true);
			this.vistaEdicionPrecios.modo = VistaEdicionPrecios.MODO.RECOGER;
			this.vistaEdicionPrecios.setTitulo("Nuevo monto de empaque para recoger");
			
	    } else if (ae.getSource() == this.vistaAdmin.btnEditar) {
	    	// enlazar ventanaEdicion 
	    	if (this.vistaAdmin.tblCatalogo.getSelectedRow() != -1) {
		    	this.vistaNuevoElemento.modo = VistaAgregarElementoCatalogo.MODO.EDICION;
		    	this.vistaNuevoElemento.ultimoIndice = this.vistaAdmin.tblCatalogo.getSelectedRow();
	    		
		    	String infoTabla[] = {(String) this.vistaAdmin.tblCatalogo.getValueAt(this.vistaAdmin.tblCatalogo.getSelectedRow(), 0),
		    			(String) this.vistaAdmin.tblCatalogo.getValueAt(this.vistaAdmin.tblCatalogo.getSelectedRow(), 1) };
		    	this.vistaNuevoElemento.cargarElementoCatalogo(this.admin.getCatalogo().getObjetoAsociado(infoTabla));
		    	
		    	if (this.vistaNuevoElemento.modo == VistaAgregarElementoCatalogo.MODO.EDICION) {
					this.vistaNuevoElemento.cargarElementoCatalogo(
							this.admin.getCatalogo().getObjetoAsociado(infoTabla));
		    	}
		    	
		    	this.vistaNuevoElemento.setVisible(true);
		    	
	    	} else {
	    		JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento del catalogo para ser editado");
	    	}
	    	
	    	
			
		} else if (ae.getSource() == this.vistaAdmin.btnEstadstica) {
			// enlazar estadistica
			this.vistaRegistros.setVisible(true);
	    	// actualizar estadistica
			
		} else if (ae.getSource() == this.vistaRegistros.btnRegresar) {
			
		} else if (ae.getSource() == this.vistaNuevoElemento.btnAceptar) {
				
				// agregar elemento
				if (this.vistaNuevoElemento.noHayEspaciosEnBlanco()) {
						// si todo esta validado, entonces...
						
						// PLATO: String tipoAlimento, String alimento, String racion, int calorias, 
						// float precio
						// ELEMENTO: Plato platoAsociado, String codigo, String descripcion, boolean visibilidad
						if (this.vistaNuevoElemento.validarFormatoNumero()) {
							Icon icono = this.vistaNuevoElemento.lblImagen.getIcon();
							Plato nuevoPlato = new Plato(icono, (String)this.vistaNuevoElemento.cbxTipoPlatillo.getSelectedItem(),
									this.vistaNuevoElemento.txtAlimento.getText(),
									this.vistaNuevoElemento.txtRacion.getText(),
									Integer.parseInt(this.vistaNuevoElemento.txtCalorias.getText()),
									Float.parseFloat(this.vistaNuevoElemento.txtPrecio.getText())
							);
												
							ElementoCatalogo nuevoElemento = new ElementoCatalogo(nuevoPlato, 
									obtenerCodigo(this.vistaNuevoElemento.cbxTipoPlatillo.getSelectedIndex()), 
									this.vistaNuevoElemento.txaDescripcion.getText(),
									this.vistaNuevoElemento.chbxDisponible.isEnabled()
							);
							
							this.admin.getCatalogo().add(nuevoElemento);
							this.vistaNuevoElemento.dispose();
							// TODO: notificar par ser enviado por el hilo
						
						} else {
							JOptionPane.showMessageDialog(null, "Debe ingresar un valor numerico");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
					}
				

		} else if (ae.getSource() == this.vistaNuevoElemento.btnCancelar) {
			this.vistaNuevoElemento.reset();
			this.vistaNuevoElemento.dispose();
			
			
		} else if (ae.getSource() == this.vistaEdicionPrecios.btnOk) {
			int nuevoValor;
			try {
				nuevoValor = Integer.parseInt(this.vistaEdicionPrecios.txtCantidad.getText());
				if (nuevoValor < 0)
					throw new NumberFormatException();
			} catch (NumberFormatException e) {
				nuevoValor = -1;
			}

			if (nuevoValor != -1)
				if (VistaEdicionPrecios.modo == VistaEdicionPrecios.MODO.EXPRESS) {
					this.admin.getCatalogo().valorExpress = nuevoValor;
				} else if (VistaEdicionPrecios.modo == VistaEdicionPrecios.MODO.RECOGER){
					this.admin.getCatalogo().valorRecoger = nuevoValor;
				}
			this.vistaEdicionPrecios.dispose();
			this.vistaEdicionPrecios.reset();
			
		} else if (ae.getSource() == this.vistaEdicionPrecios.btnCancelar) {
			// salir de ventana edicion de numero
			this.vistaEdicionPrecios.reset();
			this.vistaEdicionPrecios.dispose();
		
		}  else if (ae.getSource() == this.vistaNuevoElemento.btnCancelar) {
			this.vistaNuevoElemento.reset();
			this.vistaNuevoElemento.dispose();
			
		} else if (ae.getSource() == this.vistaAdmin.btnGenerarXML) {
			if (!this.admin.getCatalogo().isEmpty())
				try {
					this.admin.generarXML(this.admin.getCatalogo());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			
		} else if (ae.getSource() == this.vistaAdmin.btnCargarXML) {
			Catalogo catalogo = null;
			try {
				catalogo = this.admin.leerXML();
			} catch (IOException e) {
				e.printStackTrace();
				catalogo = null;
			}
			
			if (catalogo != null) {
				for (ElementoCatalogo e:catalogo)
					this.admin.getCatalogo().add(e);
			}
			
		} else if (ae.getSource() == this.vistaAdmin.btnActualizarClientes) {
			try {
				this.enviarCatalogo();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (ae.getSource() == this.vistaNuevoElemento.btnCargarImagen) {
			cargarImagen();
		} else if (ae.getSource() == this.vistaNuevoElemento.cbxTipoPlatillo) {
			this.vistaNuevoElemento.lblCodigo.setText(obtenerCodigo(this.vistaNuevoElemento.cbxTipoPlatillo.getSelectedIndex()));		
		} else if (this.vistaAdmin.rbtPrincipales.isEnabled()) {
			DefaultTableModel modelo = (DefaultTableModel) this.vistaAdmin.tblCatalogo.getModel();
			for (ElementoCatalogo e: this.admin.getCatalogo()) {
				if (e.getPlatoAsociado().getTipoAlimento().equals("Principales"))
				modelo.addRow(new Object[] {
						e.getPlatoAsociado().getAlimento(), e.getPlatoAsociado().getTipoAlimento()
				});
			}
		} else if (this.vistaAdmin.rbtEntradas.isEnabled()) {
			DefaultTableModel modelo = (DefaultTableModel) this.vistaAdmin.tblCatalogo.getModel();
			for (ElementoCatalogo e: this.admin.getCatalogo()) {
			if (e.getPlatoAsociado().getTipoAlimento().equals("Entradas"))
				modelo.addRow(new Object[] {
						e.getPlatoAsociado().getAlimento(), e.getPlatoAsociado().getTipoAlimento()
				});
			} 
		}else if (this.vistaAdmin.rbtPostres.isEnabled()) {
			DefaultTableModel modelo = (DefaultTableModel) this.vistaAdmin.tblCatalogo.getModel();
			for (ElementoCatalogo e: this.admin.getCatalogo()) {
		
				if (e.getPlatoAsociado().getTipoAlimento().equals("Postres"))
					modelo.addRow(new Object[] {
						e.getPlatoAsociado().getAlimento(), e.getPlatoAsociado().getTipoAlimento()
				});
			} 
		}else if (this.vistaAdmin.rbtBebidas.isEnabled()) {
			DefaultTableModel modelo = (DefaultTableModel) this.vistaAdmin.tblCatalogo.getModel();
			for (ElementoCatalogo e: this.admin.getCatalogo()) {
		
			if (e.getPlatoAsociado().getTipoAlimento().equals("Bebidas"))
				modelo.addRow(new Object[] {
						e.getPlatoAsociado().getAlimento(), e.getPlatoAsociado().getTipoAlimento()
				});
		
			}
		}
		
		visualizarInfo();
		notificar();
	}
	
	private String obtenerCodigo(int indice) {
		String numero = "";
		if (contadorId < 10)
			numero += "00"+contadorId;
		else
			numero += "0"+contadorId;
		this.contadorId++;
		return Plato.codigos[indice] + numero;
	}

	public void cargarPreciosServicios() {
		this.vistaAdmin.lblPreciosDeExpress.setText(String.valueOf(this.admin.getCatalogo().valorExpress));
		this.vistaAdmin.lblPreciosParaLlevar.setText(String.valueOf(this.admin.getCatalogo().valorRecoger));
	}
	
	@Override
	public void actualizar() {
		visualizarInfo();
		cargarPreciosServicios();
	}

	@Override
	public void notificar() {
		this.observadores.forEach((o) -> o.actualizar());
	}

	@Override
	public void enlazarObservador(Observador o) {
		this.observadores.add(o);
	}
	
	
}
