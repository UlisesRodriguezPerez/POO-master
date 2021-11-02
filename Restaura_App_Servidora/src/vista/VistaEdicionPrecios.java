package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class VistaEdicionPrecios extends JDialog {

	public enum MODO {
		EXPRESS, RECOGER
	};
	public static MODO modo;
	private final JPanel contentPanel = new JPanel();
	public JTextField txtCantidad;
	public int valor;
	public JButton btnOk;
	public JButton btnCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VistaEdicionPrecios dialog = new VistaEdicionPrecios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VistaEdicionPrecios() {
		this.setUndecorated(true);
		this.valor = -1;
		setBackground(Color.WHITE);
		setBounds(100, 100, 288, 165);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 255, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblCantidad = new JLabel("Digite la cantidad:");
			lblCantidad.setFont(new Font("DejaVu Sans Light", Font.BOLD, 14));
			lblCantidad.setBackground(new Color(0, 204, 51));
			lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);
			lblCantidad.setBounds(33, 12, 217, 28);
			contentPanel.add(lblCantidad);
		}
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(33, 52, 217, 37);
		contentPanel.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnOk = new JButton("OK");
				btnOk.setBackground(Color.WHITE);
				//btnOk.setActionCommand("Confirmar");
				buttonPane.add(btnOk);
				getRootPane().setDefaultButton(btnOk);
				btnOk.setMnemonic(KeyEvent.VK_ENTER);
			}
			{
				btnCancelar = new JButton("Cancel");
				btnCancelar.setBackground(Color.WHITE);
				//btnCancelar.setActionCommand("Cancelar");
				buttonPane.add(btnCancelar);
			}
		}
	}
	
	public void setTitulo(String titulo) {
		this.setTitle(titulo);
	}
	
	public void mostrar() {
		// TODO: anteponerse
		this.setVisible(true);
		reset();
	}
	
	public void reset() {
		this.valor = -1;
		this.txtCantidad.setText("");
		//this.dispose();
	}

	public void enlazarEventos(ActionListener a) {
		this.btnOk.addActionListener(a);
		this.btnCancelar.addActionListener(a);
	}
	
}