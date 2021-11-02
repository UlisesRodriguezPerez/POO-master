package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.Carrito;
import modelo.Catalogo;
import modelo.Observador;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.SystemColor;
/*
 * VistaCliente esta clase
 */
public class VistaCliente extends JFrame implements Observador {

	public Controlador controlador;
	public Catalogo catalogo;
	public Carrito carrito;
	public int ultimoIndiceSeleccionado;
	
	private JPanel contentPane;
	public final ButtonGroup filtro = new ButtonGroup();
	public JTable tblCatalogo;
	public JTable tblCarrito;
	public JRadioButton rbtTodos;
	public JRadioButton rbtEntradas;
	public JRadioButton rbtPrincipales;
	public JRadioButton rbtPostres;
	public JRadioButton rbtBebidas;
	public JButton btnEliminarCarrito;
	public JButton btnAgregarAlCarrito;
	public JPanel pnlInfoPlato;
	public JLabel lblImagen;
	public JLabel lblPorciones;
	public JLabel lblTotalPorciones;
	public JLabel lblCalorias;
	public JLabel lblTotalCalorias;
	public JPanel pnlTotalPedido;
	public JLabel lblTotalDePedido;
	public JLabel lblPedidoEnSitio;
	public JLabel lblPedidoParaLlevar;
	public JLabel lblPedidoExpress;
	public JLabel lblTotalSitio;
	public JLabel lblCatalogo;
	public JLabel lblTotalRecoger;
	public JLabel lblTotalExpress;
	public JLabel lblPrecio;
	public JLabel lblTotalPrecio;
	public JButton btnProcesarPedido;
	public JButton btnDisminuye;
	public JButton btnAumenta;

	public final ButtonGroup pedido = new ButtonGroup();
	public JRadioButton rbtSitio;
	public JRadioButton rbtRecoger;
	public JRadioButton rbtExpress;
	public JLabel lblCliente;
	public JTextField txtNombre;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaCliente frame = new VistaCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VistaCliente() {
		
		setTitle("Restaurante / Cliente");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		rbtTodos = new JRadioButton("Todos");
		rbtTodos.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		rbtTodos.setBackground(Color.WHITE);
		filtro.add(rbtTodos);
		rbtTodos.setBounds(327, 78, 75, 21);
		contentPane.add(rbtTodos);
		
		rbtEntradas = new JRadioButton("Entradas");
		rbtEntradas.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		rbtEntradas.setBackground(Color.WHITE);
		filtro.add(rbtEntradas);
		rbtEntradas.setBounds(404, 78, 86, 21);
		contentPane.add(rbtEntradas);
		
		rbtPrincipales = new JRadioButton("Principales");
		rbtPrincipales.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		rbtPrincipales.setBackground(Color.WHITE);
		filtro.add(rbtPrincipales);
		rbtPrincipales.setBounds(500, 78, 103, 21);
		contentPane.add(rbtPrincipales);
		
		rbtPostres = new JRadioButton("Postres");
		rbtPostres.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		rbtPostres.setBackground(Color.WHITE);
		filtro.add(rbtPostres);
		rbtPostres.setBounds(615, 78, 76, 21);
		contentPane.add(rbtPostres);
		
		rbtBebidas = new JRadioButton("Bebidas");
		rbtBebidas.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		rbtBebidas.setBackground(Color.WHITE);
		filtro.add(rbtBebidas);
		rbtBebidas.setBounds(705, 78, 86, 21);
		contentPane.add(rbtBebidas);		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(327, 105, 470, 486);
		contentPane.add(scrollPane);
		
		tblCatalogo = new JTable();
		tblCatalogo.setFont(new Font("DejaVu Sans Light", Font.BOLD, 14));
		tblCatalogo.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Platillo", "Tipo Platillo"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblCatalogo.getColumnModel().getColumn(0).setPreferredWidth(322);
		tblCatalogo.getColumnModel().getColumn(1).setPreferredWidth(402);
		scrollPane.setViewportView(tblCatalogo);
		
		lblCatalogo = new JLabel("Catalogo de platillos");
		lblCatalogo.setFont(new Font("DejaVu Sans Light", Font.BOLD, 18));
		lblCatalogo.setBounds(453, 42, 193, 21);
		contentPane.add(lblCatalogo);
		
		JLabel lblCarritoDeCompras = new JLabel("Carrito de compras");
		lblCarritoDeCompras.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		lblCarritoDeCompras.setBounds(103, 80, 170, 15);
		contentPane.add(lblCarritoDeCompras);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 105, 290, 486);
		contentPane.add(scrollPane_1);
		
		tblCarrito = new JTable();
		tblCarrito.setFont(new Font("DejaVu Sans Light", Font.BOLD, 14));
		tblCarrito.setBackground(UIManager.getColor("Desktop.background"));
		tblCarrito.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Platillo", "Cantidad"
			}
		));
		scrollPane_1.setViewportView(tblCarrito);
		
		btnEliminarCarrito = new JButton("Eliminar del carrito");
		btnEliminarCarrito.addActionListener(this.controlador);
		btnEliminarCarrito.setBounds(12, 628, 170, 25);
		contentPane.add(btnEliminarCarrito);
		
		btnAgregarAlCarrito = new JButton("Agregar al carrito");
		btnAgregarAlCarrito.setBounds(488, 628, 176, 25);
		btnAgregarAlCarrito.addActionListener(this.controlador);
		contentPane.add(btnAgregarAlCarrito);
		
		pnlInfoPlato = new JPanel();
		pnlInfoPlato.setBounds(839, -31, 351, 718);
		contentPane.add(pnlInfoPlato);
		pnlInfoPlato.setLayout(null);
		
		lblImagen = new JLabel("img");
		lblImagen.setBounds(62, 50, 209, 161);
		pnlInfoPlato.add(lblImagen);
		
		pnlTotalPedido = new JPanel();
		pnlTotalPedido.setBounds(23, 340, 298, 175);
		pnlInfoPlato.add(pnlTotalPedido);
		pnlTotalPedido.setLayout(null);
		
		lblTotalDePedido = new JLabel("Total de pedido");
		lblTotalDePedido.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		lblTotalDePedido.setBounds(90, 25, 126, 13);
		pnlTotalPedido.add(lblTotalDePedido);
		
		lblPedidoEnSitio = new JLabel("Pedido en sitio");
		lblPedidoEnSitio.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		lblPedidoEnSitio.setBounds(25, 50, 118, 13);
		pnlTotalPedido.add(lblPedidoEnSitio);
		
		lblPedidoParaLlevar = new JLabel("Pedido para llevar");
		lblPedidoParaLlevar.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		lblPedidoParaLlevar.setBounds(25, 86, 137, 13);
		pnlTotalPedido.add(lblPedidoParaLlevar);
		
		lblPedidoExpress = new JLabel("Pedido express");
		lblPedidoExpress.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		lblPedidoExpress.setBounds(25, 124, 126, 13);
		pnlTotalPedido.add(lblPedidoExpress);
		
		lblTotalSitio = new JLabel("0");
		lblTotalSitio.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		lblTotalSitio.setBounds(200, 50, 86, 13);
		pnlTotalPedido.add(lblTotalSitio);
		
		lblTotalRecoger = new JLabel("0");
		lblTotalRecoger.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		lblTotalRecoger.setBounds(200, 86, 86, 13);
		pnlTotalPedido.add(lblTotalRecoger);
		
		lblTotalExpress = new JLabel("0");
		lblTotalExpress.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		lblTotalExpress.setBounds(200, 124, 86, 13);
		pnlTotalPedido.add(lblTotalExpress);
		
		rbtExpress = new JRadioButton("Express");
		rbtExpress.setBounds(235, 534, 86, 24);
		pnlInfoPlato.add(rbtExpress);
		rbtExpress.setBackground(SystemColor.controlHighlight);
		pedido.add(rbtExpress);
		
		rbtRecoger = new JRadioButton("Recoger");
		rbtRecoger.setBounds(127, 534, 86, 24);
		pnlInfoPlato.add(rbtRecoger);
		rbtRecoger.setBackground(SystemColor.controlHighlight);
		pedido.add(rbtRecoger);
		
		rbtSitio = new JRadioButton("Sitio");
		rbtSitio.setSelected(true);
		rbtSitio.setBounds(26, 534, 86, 24);
		pnlInfoPlato.add(rbtSitio);
		rbtSitio.setBackground(SystemColor.controlHighlight);
		pedido.add(rbtSitio);
		
		lblCliente = new JLabel("Nombre del cliente:");
		lblCliente.setBounds(99, 581, 152, 15);
		pnlInfoPlato.add(lblCliente);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(39, 598, 275, 19);
		pnlInfoPlato.add(txtNombre);
		txtNombre.setColumns(10);
		
		btnProcesarPedido = new JButton("Procesar Pedido");
		btnProcesarPedido.setBounds(95, 644, 197, 25);
		pnlInfoPlato.add(btnProcesarPedido);
		
		JPanel panel = new JPanel();
		panel.setBounds(23, 223, 298, 106);
		pnlInfoPlato.add(panel);
		panel.setLayout(null);
		
		lblPorciones = new JLabel("Porciones");
		lblPorciones.setBounds(12, 12, 94, 15);
		panel.add(lblPorciones);
		lblPorciones.setFont(new Font("DejaVu Sans Light", Font.BOLD, 14));
		
		lblTotalPorciones = new JLabel("0");
		lblTotalPorciones.setBounds(166, 12, 66, 15);
		panel.add(lblTotalPorciones);
		
		lblTotalCalorias = new JLabel("0");
		lblTotalCalorias.setBounds(166, 39, 66, 24);
		panel.add(lblTotalCalorias);
		
		lblTotalPrecio = new JLabel("0");
		lblTotalPrecio.setBounds(166, 75, 66, 24);
		panel.add(lblTotalPrecio);
		
		lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(12, 75, 89, 24);
		panel.add(lblPrecio);
		lblPrecio.setFont(new Font("DejaVu Sans Light", Font.BOLD, 14));
		
		lblCalorias = new JLabel("Calorias");
		lblCalorias.setBounds(12, 39, 89, 24);
		panel.add(lblCalorias);
		lblCalorias.setFont(new Font("DejaVu Sans Light", Font.BOLD, 14));
		btnProcesarPedido.addActionListener(this.controlador);
		
		btnDisminuye = new JButton("-");
		btnDisminuye.addActionListener(this.controlador);
		btnDisminuye.setBounds(194, 628, 51, 25);
		contentPane.add(btnDisminuye);
		
		btnAumenta = new JButton("+");
		btnAumenta.addActionListener(this.controlador);
		btnAumenta.setBounds(251, 628, 51, 25);
		contentPane.add(btnAumenta);

	}
	
	public void limpiarTabla(JTable tabla) {
		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
		int cantidadFilas = modelo.getRowCount();
		for (int i=cantidadFilas; i!=0; i-- )
			modelo.removeRow(0);
	}
	
	public void cargarCarrito() {
		limpiarTabla(this.tblCarrito);
		// volver a cargar la informacion
		// HEADER: "Platillo", "Cantidad"
		if (carrito != null) {
			DefaultTableModel modelo = (DefaultTableModel) this.tblCarrito.getModel();
			for (int i=0; i<carrito.getElementosCarrito().size(); i++) {
				modelo.addRow(new Object[] {
						carrito.getElementosCarrito().get(i).getPlatoAsociado().getAlimento(),
						carrito.getElementosCarrito().get(i).getCantidadPorPlato()
				});
			}
		}
		
	}
	
	public void cargarCatalogo() {
		limpiarTabla(this.tblCatalogo);
		// volver a cargar la informacion
		// HEADER : "Platillo", "Tipo Platillo"
		if (catalogo != null) {
			DefaultTableModel modelo = (DefaultTableModel) this.tblCatalogo.getModel();
			for (int i=0; i<catalogo.size(); i++)
				modelo.addRow(new Object[] {
						catalogo.get(i).getPlatoAsociado().getAlimento(),
						catalogo.get(i).getPlatoAsociado().getTipoAlimento()
				});
		}
	}

	@Override
	public void actualizar() {
		cargarCatalogo();
		cargarCarrito();
	}
	
	public void enlazarEventos(ActionListener a) {
		this.btnAgregarAlCarrito.addActionListener(a);
		this.btnAumenta.addActionListener(a);
		this.btnDisminuye.addActionListener(a);
		this.btnEliminarCarrito.addActionListener(a);
		this.btnProcesarPedido.addActionListener(a);
	}
	
	public void resetInfoPlatillo() {
		
		this.lblTotalCalorias.setText("0");
		this.lblTotalPorciones.setText("0");
		this.lblTotalPrecio.setText("0");
		this.lblImagen.setText("");
	}
}
