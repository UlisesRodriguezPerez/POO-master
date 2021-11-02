package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import modelo.PedidoExpress;
import modelo.PedidoRecoger;
import modelo.PedidoSitio;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class VistaConsultaRegistros extends JFrame {
	public JTable table;
	public JTable table_1;
	public JTabbedPane tabbedPane;
	public JPanel tabMasSolicitados;
	public JLabel lblMsSolicitados;
	public JScrollPane scrollPane;
	public JPanel tabNuncaSolicitados;
	public JLabel lblNuncaSolicitados;
	public JPanel tabEstadistica;
	public JPanel pnlTabla;
	public JScrollPane scrollPane_1;
	public JLabel lblSitio;
	public JLabel lblRecoger;
	public JLabel lblExpress;
	public JLabel lblCantidadSitio;
	public JLabel lblCantidadRecoger;
	public JLabel lblCantidadExpress;
	public JButton btnRegresar;
	public JFreeChart grafica;
	public ChartPanel pnlGrafica;
	public JPanel pnlGraficaLugar;
	public PedidoSitio pedidoSitio;
	public PedidoRecoger pedidoRecoger;
	public PedidoExpress pedidoExpress;
	public DefaultCategoryDataset datos;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaConsultaRegistros frame = new VistaConsultaRegistros();
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
	public VistaConsultaRegistros() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		getContentPane().setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(28, 23, 1134, 572);
		getContentPane().add(tabbedPane);
		
		tabMasSolicitados = new JPanel();
		tabbedPane.addTab("10 mas solicitados", null, tabMasSolicitados, null);
		tabMasSolicitados.setLayout(null);
		
		lblMsSolicitados = new JLabel("10 mas solicitados");
		lblMsSolicitados.setBounds(488, 24, 309, 31);
		tabMasSolicitados.add(lblMsSolicitados);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 67, 1067, 450);
		tabMasSolicitados.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Nombre", "Descripcion", "Categoria"
			}
		));
		scrollPane.setViewportView(table);
		
		tabNuncaSolicitados = new JPanel();
		tabbedPane.addTab("Nunca solicitados", null, tabNuncaSolicitados, null);
		tabNuncaSolicitados.setLayout(null);
		
		lblNuncaSolicitados = new JLabel("Nunca solicitados");
		lblNuncaSolicitados.setBounds(436, 12, 270, 43);
		tabNuncaSolicitados.add(lblNuncaSolicitados);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(35, 67, 1046, 437);
		tabNuncaSolicitados.add(scrollPane_1);
		
		
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Nombre", "Categoria"
			}
		));
		scrollPane_1.setViewportView(table_1);
		
		tabEstadistica = new JPanel();
		tabbedPane.addTab("Estadistica", null, tabEstadistica, null);
		tabEstadistica.setLayout(null);
		
		pnlTabla = new JPanel();
		pnlTabla.setBounds(23, 142, 400, 271);
		tabEstadistica.add(pnlTabla);
		pnlTabla.setLayout(null);
		
		lblSitio = new JLabel("Sitio");
		lblSitio.setBounds(10, 35, 86, 26);
		pnlTabla.add(lblSitio);
		
		lblRecoger = new JLabel("Recoger");
		lblRecoger.setBounds(10, 123, 86, 26);
		pnlTabla.add(lblRecoger);
		
		lblExpress = new JLabel("Express");
		lblExpress.setBounds(10, 201, 86, 26);
		pnlTabla.add(lblExpress);
		
		lblCantidadSitio = new JLabel("0");
		lblCantidadSitio.setBounds(300, 42, 45, 13);
		pnlTabla.add(lblCantidadSitio);
		
		lblCantidadRecoger = new JLabel("0");
		lblCantidadRecoger.setBounds(300, 130, 45, 13);
		pnlTabla.add(lblCantidadRecoger);
		
		lblCantidadExpress = new JLabel("0");
		lblCantidadExpress.setBounds(300, 208, 45, 13);
		pnlTabla.add(lblCantidadExpress);
		
		pnlGraficaLugar = new JPanel();
		pnlGraficaLugar.setBounds(450, 36, 645, 425);
		tabEstadistica.add(pnlGraficaLugar);
		pnlGraficaLugar.setLayout(null);
		
		btnRegresar = new JButton("Regresar");
		btnRegresar.setBounds(1029, 605, 114, 21);
		getContentPane().add(btnRegresar);
		
		/*
		 * Grafica con relacion porcentual de los pedidos
		 * 
		 * */
	cargarGrafica();
		
		
		
	}
	
	public void cargarGrafica() {
		datos = new DefaultCategoryDataset();
		
		datos.addValue(PedidoSitio.cantidad, "Pedidos", "Sitio");
		datos.addValue(PedidoRecoger.cantidad, "Pedidos", "Recoger");
		datos.addValue(PedidoExpress.cantidad, "Pedidos", "Express");
	
		grafica = ChartFactory.createBarChart("Relación porcentual entre pedidos",
		"", "Relacion", datos,
		PlotOrientation.HORIZONTAL, true, true, false);
		pnlGrafica = new ChartPanel(grafica);
		pnlGrafica.setBounds(12, 12, 621, 401);
		pnlGraficaLugar.add(pnlGrafica);
	}
}
