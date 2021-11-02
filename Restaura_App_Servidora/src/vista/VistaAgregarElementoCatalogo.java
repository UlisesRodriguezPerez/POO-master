package vista;

import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.ElementoCatalogo;

import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

public class VistaAgregarElementoCatalogo extends JFrame {

	public enum MODO {
		AGREGAR, EDICION
	};
	
	private final String encabezadosCombo[] = {
			"Entradas", "Principal", "Postres", "Bebidas"
	};
	
	public MODO modo;
	public int ultimoIndice;
	private JPanel contentPane;
	public JTextField txtAlimento;
	public JTextField txtRacion;
	public JTextField txtCalorias;
	public JButton btnCancelar;
	public JButton btnAceptar;
	public JTextArea txaDescripcion;
	public JLabel lblCodigo;
	
	public JLabel lblCodigoPlato ;
	public JButton btnCargarImagen ;
	public JCheckBox chbxDisponible;
	public JComboBox<String> cbxTipoPlatillo;
	public JTextField txtPrecio;
	public JLabel lblImagen;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaAgregarElementoCatalogo frame = new VistaAgregarElementoCatalogo();
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
	public VistaAgregarElementoCatalogo() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 686, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(553, 300, 119, 21);
		contentPane.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(421, 300, 118, 21);
		contentPane.add(btnCancelar);
		
		chbxDisponible = new JCheckBox("Disponible");
		chbxDisponible.setBounds(532, 243, 140, 38);
		chbxDisponible.setEnabled(true);
		contentPane.add(chbxDisponible);
		
		lblImagen = new JLabel("");
		lblImagen.setBounds(442, 12, 230, 162);
		contentPane.add(lblImagen);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 421, 195);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtCalorias = new JTextField();
		txtCalorias.setBounds(152, 121, 249, 25);
		panel.add(txtCalorias);
		txtCalorias.setColumns(10);
		
		txtRacion = new JTextField();
		txtRacion.setBounds(152, 83, 249, 25);
		panel.add(txtRacion);
		txtRacion.setColumns(10);
		
		txtAlimento = new JTextField();
		txtAlimento.setBounds(152, 46, 249, 25);
		panel.add(txtAlimento);
		txtAlimento.setColumns(10);
		
		cbxTipoPlatillo = new JComboBox<String>();
		cbxTipoPlatillo.setBounds(152, 12, 249, 24);
		panel.add(cbxTipoPlatillo);
		
		JLabel lblNewLabel = new JLabel("Tipo de platillo");
		lblNewLabel.setBounds(12, 12, 122, 25);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Alimento");
		lblNewLabel_1.setBounds(12, 49, 85, 18);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Racion");
		lblNewLabel_2.setBounds(12, 86, 45, 18);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Calorias ");
		lblNewLabel_3.setBounds(12, 127, 85, 13);
		panel.add(lblNewLabel_3);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(12, 164, 85, 13);
		panel.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(152, 158, 249, 25);
		panel.add(txtPrecio);
		
		btnCargarImagen = new JButton("Cargar Imagen");
		btnCargarImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCargarImagen.setBounds(478, 186, 172, 21);
		contentPane.add(btnCargarImagen);
		
		txaDescripcion = new JTextArea();
		txaDescripcion.setBounds(124, 243, 237, 78);
		contentPane.add(txaDescripcion);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(36, 279, 85, 13);
		contentPane.add(lblDescripcion);
		
		lblCodigo = new JLabel("");
		lblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigo.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCodigo.setBackground(SystemColor.menu);
		lblCodigo.setBounds(373, 261, 105, 27);
		contentPane.add(lblCodigo);
		
		lblCodigoPlato = new JLabel("Codigo:");
		lblCodigoPlato.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigoPlato.setBounds(373, 228, 101, 21);
		contentPane.add(lblCodigoPlato);
		
		cbxTipoPlatillo.addItem("Entradas");
		cbxTipoPlatillo.addItem("Principal");
		cbxTipoPlatillo.addItem("Postres");
		cbxTipoPlatillo.addItem("Bebidas");
	}
	
	public void reset() {
		this.txtAlimento.setText("");
		this.txtCalorias.setText("");
		this.txtRacion.setText("");
		this.cbxTipoPlatillo.setSelectedIndex(0);
	}
	
	public boolean noHayEspaciosEnBlanco() {
		return !this.txtAlimento.equals("") && !this.txtCalorias.equals("")
		&& !this.txtRacion.equals("") && !this.txtPrecio.getText().equals("");
	}
	
	public boolean validarFormatoNumero() {
		try {
			Integer.parseInt(this.txtCalorias.getText());
			Integer.parseInt(this.txtPrecio.getText());
			return true;
		} catch (NumberFormatException n) {
			JOptionPane.showMessageDialog(null, "Ingrese un numero valido");
			return false;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ingrese un numero valido");
			return false;
		}
	}
	
	public void enlazarEventos(ActionListener a) {
		this.btnAceptar.addActionListener(a);
		this.btnCancelar.addActionListener(a);
		this.btnCargarImagen.addActionListener(a);
		this.cbxTipoPlatillo.addActionListener(a);
	}

	public void cargarElementoCatalogo(ElementoCatalogo obj) {
		if (obj != null) {
			this.txtAlimento.setText(obj.getPlatoAsociado().getAlimento());
			this.txaDescripcion.setText(obj.getDescripcion());
			this.txtCalorias.setText(String.valueOf(obj.getPlatoAsociado().getCalorias()));
			this.txtPrecio.setText(String.valueOf(obj.getPlatoAsociado().getPrecio()));
			this.txtRacion.setText(obj.getPlatoAsociado().getRacion());
			this.chbxDisponible.setEnabled(obj.isVisibilidad());
			
		}
	}
	
	public int cargarComboboxEdicion(String s) {
		for (int i=0; i< encabezadosCombo.length; i++) {
			if (s.equals(encabezadosCombo[i])) 
				return i;
		}
		
		return -1;
	}

}
