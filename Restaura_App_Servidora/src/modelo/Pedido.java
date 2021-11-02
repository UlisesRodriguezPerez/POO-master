package modelo;

import java.io.Serializable;
import java.util.Date;

public abstract class Pedido implements Serializable {
	//Atributos
	public static int cantidad = 0;
	private int idUnico;
	private String fecha;
	private float costo;
	private Carrito carritoCompra;
	
	//Constructor
	public Pedido(int idUnico, String fecha, float costo, Carrito listaPedidos) {
		super();
		this.idUnico = idUnico;
		this.fecha = fecha;
		this.costo = costo;
		this.carritoCompra = listaPedidos;
	}

	public Pedido() {
		// POR AHORA
	}

	//Metodos
	public int getIdUnico() {
		return idUnico;
	}

	public void setIdUnico(int idUnico) {
		this.idUnico = idUnico;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}
	
	public Carrito getCarritoCompra() {
		return carritoCompra;
	}

	public void setCarritoCompra(Carrito carritoCompra) {
		this.carritoCompra = carritoCompra;
	}
	
	public static int nuevoId() {
		return cantidad++;
	}

	@Override
	public String toString() {
		return "Pedido [idUnico=" + idUnico + ", fecha=" + fecha + ", costo=" + costo + ", carritoCompra="
				+ carritoCompra + "]";
	}

	public abstract float calcularPrecio();
	public abstract String generarPedido();
	
	
}
