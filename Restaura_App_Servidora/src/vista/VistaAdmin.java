package vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.JFreeChart;

import controlador.Administrador;
import modelo.Catalogo;
import modelo.ElementoCarrito;
import modelo.Observador;
import modelo.Pedido;

public class VistaAdmin extends JFrame implements Observador {

	public JPanel contentPane;
	public final ButtonGroup buttonGroup = new ButtonGroup();
	public JTable tblCatalogo;
	public JTable tblPedidos;
	public JLabel lblImg;
	public JRadioButton rbtTodos;
	public JRadioButton rbtEntradas;
	public JRadioButton rbtPrincipales;
	public JRadioButton rbtPostres;
	public JRadioButton rbtBebidas;
	
	public JScrollPane tblListaCatalogo;
	public JLabel lblListaCatalogo;
	public JLabel lblPedidosGenerados;
	public JScrollPane tblListaPedidos;
	public JButton btnEliminar;
	public JButton btnEditar;
	public JButton btnAgregar;
	public JLabel lblInfo;
	public JLabel lblPreciosDeExpress;
	public JLabel textoPresioExpress;
	public JLabel presioLlevar;
	public JLabel lblPreciosParaLlevar;
	public JButton btnEditarExpress;
	public JButton btnEditarParaLlevar;
	public JButton btnEstadstica;
	public JPanel pnlInfoPlato;
	public JLabel lblPorciones;
	public JLabel lblTotalPorciones;
	public JLabel lblCalorias;
	public JLabel lblTotalCalorias;
	public JLabel lblCatalogo;

	public JButton btnProcesarPedido;
	public JButton btnDisminuye;
	public JButton btnAumenta;
	public JLabel lblInformacion;
	public JFreeChart grafica;
	public JLabel lblTotalPrecio;
	public JButton btnGenerarXML;
	public JButton btnCargarXML;

	public Catalogo catalogo;
	public ArrayList<Pedido> pedidos;
	public JButton btnActualizarClientes;
	public Administrador admin;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaAdmin frame = new VistaAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VistaAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		rbtTodos = new JRadioButton("Todos");
		rbtTodos.setSelected(true);
		rbtTodos.setFont(new Font("DejaVu Sans Light", Font.BOLD, 14));
		buttonGroup.add(rbtTodos);
		rbtTodos.setBounds(370, 62, 68, 21);
		contentPane.add(rbtTodos);
		
		rbtEntradas = new JRadioButton("Entradas");
		rbtEntradas.setFont(new Font("DejaVu Sans Light", Font.BOLD, 14));
		buttonGroup.add(rbtEntradas);
		rbtEntradas.setBounds(444, 66, 95, 13);
		contentPane.add(rbtEntradas);
		
		rbtPrincipales = new JRadioButton("Principales");
		rbtPrincipales.setFont(new Font("DejaVu Sans Light", Font.BOLD, 14));
		buttonGroup.add(rbtPrincipales);
		rbtPrincipales.setBounds(544, 62, 103, 21);
		contentPane.add(rbtPrincipales);
		
		rbtPostres = new JRadioButton("Postres");
		rbtPostres.setFont(new Font("DejaVu Sans Light", Font.BOLD, 14));
		buttonGroup.add(rbtPostres);
		rbtPostres.setBounds(651, 62, 85, 21);
		contentPane.add(rbtPostres);
		
		rbtBebidas = new JRadioButton("Bebidas");
		rbtBebidas.setFont(new Font("DejaVu Sans Light", Font.BOLD, 14));
		buttonGroup.add(rbtBebidas);
		rbtBebidas.setBounds(740, 62, 85, 21);
		contentPane.add(rbtBebidas);
		
		tblListaCatalogo = new JScrollPane();
		tblListaCatalogo.setBounds(357, 106, 453, 468);
		contentPane.add(tblListaCatalogo);
		
		tblCatalogo = new JTable();
		tblCatalogo.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Platillo", "Tipo de Platillo"
			}
		));
		tblCatalogo.getColumnModel().getColumn(0).setPreferredWidth(125);
		tblCatalogo.getColumnModel().getColumn(1).setPreferredWidth(121);
		tblListaCatalogo.setViewportView(tblCatalogo);
		
		lblListaCatalogo = new JLabel("Cat\u00E1logo");
		lblListaCatalogo.setFont(new Font("DejaVu Sans Light", Font.BOLD, 14));
		lblListaCatalogo.setBounds(544, 12, 103, 26);
		contentPane.add(lblListaCatalogo);
		
		lblPedidosGenerados = new JLabel("Pedidos Generados");
		lblPedidosGenerados.setFont(new Font("DejaVu Sans Light", Font.BOLD, 14));
		lblPedidosGenerados.setBounds(90, 12, 146, 13);
		contentPane.add(lblPedidosGenerados);
		
		tblListaPedidos = new JScrollPane();
		tblListaPedidos.setBounds(12, 37, 323, 622);
		contentPane.add(tblListaPedidos);
		
		tblPedidos = new JTable();
		tblPedidos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Cliente", "Platillo", "Cantidad"
			}
		));
		tblListaPedidos.setViewportView(tblPedidos);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminar.setBounds(525, 601, 103, 21);
		contentPane.add(btnEliminar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(451, 601, 75, 21);
		contentPane.add(btnEditar);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(357, 601, 95, 21);
		contentPane.add(btnAgregar);
		
		pnlInfoPlato = new JPanel();
		pnlInfoPlato.setBounds(833, 0, 353, 659);
		contentPane.add(pnlInfoPlato);
		pnlInfoPlato.setLayout(null);
		
		lblImg = new JLabel("Img");
		lblImg.setBounds(23, 35, 298, 184);
		pnlInfoPlato.add(lblImg);
		lblImg.setFont(new Font("DejaVu Sans Light", Font.BOLD, 12));
		
		JPanel panel = new JPanel();
		panel.setBounds(23, 247, 298, 106);
		pnlInfoPlato.add(panel);
		panel.setLayout(null);
		
		lblPorciones = new JLabel("Porciones");
		lblPorciones.setBounds(12, 12, 94, 15);
		panel.add(lblPorciones);
		lblPorciones.setFont(new Font("DejaVu Sans Light", Font.BOLD, 14));
		
		lblTotalPorciones = new JLabel("0");
		lblTotalPorciones.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalPorciones.setBounds(220, 12, 66, 15);
		panel.add(lblTotalPorciones);
		
		lblTotalCalorias = new JLabel("0");
		lblTotalCalorias.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalCalorias.setBounds(220, 39, 66, 24);
		panel.add(lblTotalCalorias);
		
		lblTotalPrecio = new JLabel("0");
		lblTotalPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalPrecio.setBounds(220, 75, 66, 24);
		panel.add(lblTotalPrecio);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(12, 75, 89, 24);
		panel.add(lblPrecio);
		lblPrecio.setFont(new Font("DejaVu Sans Light", Font.BOLD, 14));
		
		lblCalorias = new JLabel("Calorias");
		lblCalorias.setBounds(12, 39, 89, 24);
		panel.add(lblCalorias);
		lblCalorias.setFont(new Font("DejaVu Sans Light", Font.BOLD, 14));
		
		btnEstadstica = new JButton("Estad\u00EDstica");
		btnEstadstica.setBounds(88, 626, 141, 21);
		pnlInfoPlato.add(btnEstadstica);
		
		btnEditarExpress = new JButton("Editar ");
		btnEditarExpress.setBounds(113, 439, 85, 21);
		pnlInfoPlato.add(btnEditarExpress);
		
		lblPreciosDeExpress = new JLabel("0");
		lblPreciosDeExpress.setBounds(205, 414, 116, 13);
		pnlInfoPlato.add(lblPreciosDeExpress);
		lblPreciosDeExpress.setHorizontalAlignment(SwingConstants.LEFT);
		
		textoPresioExpress = new JLabel("Precios de express");
		textoPresioExpress.setBounds(38, 414, 158, 13);
		pnlInfoPlato.add(textoPresioExpress);
		
		presioLlevar = new JLabel("Precios para llevar");
		presioLlevar.setBounds(38, 491, 126, 13);
		pnlInfoPlato.add(presioLlevar);
		
		lblPreciosParaLlevar = new JLabel("0");
		lblPreciosParaLlevar.setBounds(205, 491, 116, 13);
		pnlInfoPlato.add(lblPreciosParaLlevar);
		lblPreciosParaLlevar.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnEditarParaLlevar = new JButton("Editar ");
		btnEditarParaLlevar.setBounds(113, 517, 85, 21);
		pnlInfoPlato.add(btnEditarParaLlevar);
		
		btnGenerarXML = new JButton("Generar XML");
		btnGenerarXML.setBounds(357, 634, 210, 21);
		contentPane.add(btnGenerarXML);
		
		btnCargarXML = new JButton("Cargar desde XML");
		btnCargarXML.setBounds(579, 634, 231, 21);
		contentPane.add(btnCargarXML);
		
		btnActualizarClientes = new JButton("Actualizar clientes");
		btnActualizarClientes.setBounds(651, 601, 159, 21);
		contentPane.add(btnActualizarClientes);
		
	}
	
	public void limpiarTabla(JTable tabla) {
		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
		int cantidadFilas = modelo.getRowCount();
		for (int i=cantidadFilas; i!=0; i-- )
			modelo.removeRow(0);
	}


	private void cargarPedidos() {
		// "Cliente", "Platillo", "Cantidad"
		limpiarTabla(this.tblPedidos);
		
		DefaultTableModel modelo = (DefaultTableModel) this.tblPedidos.getModel();
		for (Pedido p: this.pedidos) 
			for (ElementoCarrito pl: p.getCarritoCompra().getElementosCarrito()) 
				modelo.addRow(new Object[] {
					String.valueOf(p.getIdUnico()), 
					String.valueOf(pl.getPlatoAsociado().getAlimento()),
					String.valueOf(pl.getCantidadPorPlato())
				});
	}
	
	private void cargarCatalogo() {
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
		this.lblPreciosDeExpress.setText(String.valueOf(this.admin.precioExpress));
		this.lblPreciosParaLlevar.setText(String.valueOf(this.admin.precioRecoger));
		cargarCatalogo();
		cargarPedidos();
	}

	public void enlazarEventos(ActionListener a) {
		this.btnAgregar.addActionListener(a);
		this.btnEditar.addActionListener(a);
		this.btnEditarExpress.addActionListener(a);
		this.btnEditarParaLlevar.addActionListener(a);
		this.btnEliminar.addActionListener(a);
		this.btnEstadstica.addActionListener(a);
		this.btnGenerarXML.addActionListener(a);
		this.btnCargarXML.addActionListener(a);
		this.btnActualizarClientes.addActionListener(a);
		
		this.rbtBebidas.addActionListener(a);
		this.rbtEntradas.addActionListener(a);
		this.rbtPostres.addActionListener(a);
		this.rbtPrincipales.addActionListener(a);
		this.rbtTodos.addActionListener(a);
	}
}
