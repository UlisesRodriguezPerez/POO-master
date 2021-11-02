package modelo;

import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class VisorDeCostos implements Observador {

	static VisorDeCostos visor = null;
	
	private float precioExpress;
	private float precioRecoger;
	private float precioSitio;
	
	private Carrito carrito;
	
	private JLabel etiquetaExpress;
	private JLabel etiquetaRecoger;
	private JLabel etiquetaSitio;
	
	private static VisorDeCostos instancia() {
		return new VisorDeCostos();
	}
	
	public static VisorDeCostos obtenerInstancia() {
		if (visor == null)
			visor = instancia();
		return visor;
	}	
	
	@Override
	public void actualizar() {
		
	}

	public float getPrecioExpress() {
		return precioExpress;
	}

	public void setPrecioExpress(float precioExpress) {
		this.precioExpress = precioExpress;
	}

	public float getPrecioRecoger() {
		return precioRecoger;
	}

	public void setPrecioRecoger(float precioRecoger) {
		this.precioRecoger = precioRecoger;
	}

	public float getPrecioSitio() {
		return precioSitio;
	}

	public void setPrecioSitio(float precioSitio) {
		this.precioSitio = precioSitio;
	}

	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}

	public JLabel getEtiquetaExpress() {
		return etiquetaExpress;
	}

	public void setEtiquetaExpress(JLabel etiquetaExpress) {
		this.etiquetaExpress = etiquetaExpress;
	}

	public JLabel getEtiquetaRecoger() {
		return etiquetaRecoger;
	}

	public void setEtiquetaRecoger(JLabel etiquetaRecoger) {
		this.etiquetaRecoger = etiquetaRecoger;
	}

	public JLabel getEtiquetaSitio() {
		return etiquetaSitio;
	}

	public void setEtiquetaSitio(JLabel etiquetaSitio) {
		this.etiquetaSitio = etiquetaSitio;
	}	
	
	
}
