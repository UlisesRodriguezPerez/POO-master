package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

/*
 * VistaPedidoRecoger esta clase es la que muestra la ventana cuando el pedido es solo para recoger
 */
public class VistaPedidoRecoger extends JFrame {
	public JPanel contentPane;
	public JTextField txtRetirante;
	public JTextField txtNumeroTelefono;
	public JLabel lblFecha; 
	public JLabel lblTotalPedido;
	public JLabel lblTotalNumero;
	public JLabel lblRetirante;
	public JLabel lblDireccionExacta;
	public JButton btnConfirmarPedido;
	public JButton btnCancelar;
	public JLabel lblFechaActual;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaPedidoRecoger frame = new VistaPedidoRecoger();
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
	public VistaPedidoRecoger() {
		setUndecorated(true);
		setBackground(Color.WHITE);
		setTitle("Pedido Para Recoger");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 511, 257);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblFecha = new JLabel("Fecha: ");
		lblFecha.setBounds(50, 25, 66, 15);
		contentPane.add(lblFecha);
		
		lblTotalPedido = new JLabel("Total");
		lblTotalPedido.setBounds(50, 52, 66, 15);
		contentPane.add(lblTotalPedido);
		
		lblTotalNumero = new JLabel("0");
		lblTotalNumero.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalNumero.setBounds(348, 52, 117, 15);
		contentPane.add(lblTotalNumero);
		
		lblRetirante = new JLabel("Nombre del retirante: ");
		lblRetirante.setBounds(50, 74, 184, 15);
		contentPane.add(lblRetirante);
		
		lblDireccionExacta = new JLabel("Numero telefonico");
		lblDireccionExacta.setBounds(50, 123, 127, 15);
		contentPane.add(lblDireccionExacta);
		
		txtRetirante = new JTextField();
		txtRetirante.setBounds(50, 96, 415, 19);
		contentPane.add(txtRetirante);
		txtRetirante.setColumns(10);
		
		
		txtNumeroTelefono = new JTextField();
		txtNumeroTelefono.setColumns(10);
		txtNumeroTelefono.setBounds(50, 146, 415, 19);
		contentPane.add(txtNumeroTelefono);
		
		btnConfirmarPedido = new JButton("Confirmar pedido");
		btnConfirmarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnConfirmarPedido.setBounds(50, 177, 192, 25);
		contentPane.add(btnConfirmarPedido);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(332, 177, 114, 25);
		contentPane.add(btnCancelar);
		
		lblFechaActual = new JLabel("dd/mm/aaaa");
		lblFechaActual.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaActual.setBounds(338, 25, 127, 15);
		contentPane.add(lblFechaActual);
		
	}
	
	public void reset() { 
		this.txtNumeroTelefono.setText("");
		this.txtRetirante.setText("");
	}
	
	public void enlazarEventos(ActionListener a) {
		this.btnCancelar.addActionListener(a);
		this.btnConfirmarPedido.addActionListener(a);
	}
}
