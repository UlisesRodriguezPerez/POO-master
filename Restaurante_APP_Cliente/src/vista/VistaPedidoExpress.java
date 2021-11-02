package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
/*
 * VistaPedidoExpress esta clase es la que muestra la ventana cuando el pedido es solo para recoger
 */
import javax.swing.border.EmptyBorder;
 
public class VistaPedidoExpress extends JFrame {

	public JPanel contentPane;
	public JTextField txtTelefono;
	public JTextField txtDireccion;
	public JLabel lblTextoFecha; 
	public JLabel lblTotalPedido;
	public JLabel lblTotalNumero;
	public JLabel lblTelefono;
	public JLabel lblDireccionExacta;
	public JButton btnConfirmarPedido;
	public JButton btnCancelar;
	public JLabel lblFecha;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaPedidoExpress frame = new VistaPedidoExpress();
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
	public VistaPedidoExpress() {
		setUndecorated(true);
		setBackground(Color.WHITE);
		setTitle("Pedido Express");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 544, 263);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTextoFecha = new JLabel("Fecha:");
		lblTextoFecha.setBounds(41, 5, 59, 15);
		contentPane.add(lblTextoFecha);
		
		lblTotalPedido = new JLabel("Total:");
		lblTotalPedido.setBounds(41, 32, 66, 15);
		contentPane.add(lblTotalPedido);
		
		lblTotalNumero = new JLabel("0");
		lblTotalNumero.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalNumero.setBounds(403, 32, 90, 15);
		contentPane.add(lblTotalNumero);
		
		lblTelefono = new JLabel("Numero de telefono:");
		lblTelefono.setBounds(41, 59, 152, 15);
		contentPane.add(lblTelefono);
		
		lblDireccionExacta = new JLabel("Direccion exacta:");
		lblDireccionExacta.setBounds(38, 117, 127, 15);
		contentPane.add(lblDireccionExacta);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(41, 86, 452, 19);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(38, 144, 455, 19);
		contentPane.add(txtDireccion);
		
		btnConfirmarPedido = new JButton("Confirmar pedido");
		btnConfirmarPedido.setBounds(41, 184, 192, 25);
		contentPane.add(btnConfirmarPedido);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(379, 184, 114, 25);
		contentPane.add(btnCancelar);
		
		lblFecha = new JLabel("dd/mm/aaaa");
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFecha.setBounds(403, 5, 90, 15);
		contentPane.add(lblFecha);
	}
	
	public void reset() { 
		this.txtDireccion.setText("");
		this.txtTelefono.setText("");
	}
	
	public void enlazarEventos(ActionListener a) {
		this.btnCancelar.addActionListener(a);
		this.btnConfirmarPedido.addActionListener(a);
	}
}
