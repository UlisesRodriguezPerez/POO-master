package modelo;

import java.io.Serializable;

/**
 * Clase PedidoExpress representa las entregas a domicilio
 * @author Josue Rojas Vega 
 * @author Kevin Fallas Alvarado
 * @author Ulises Rodriguez Perez	
 * @since 2019-10-21
 */
public class PedidoExpress extends Pedido implements Serializable {
	//Atributos
	private static float costoExpress = 1000;
	private String direccion;
	private String numeroCelular;
	
	//Contructor
	public PedidoExpress(int idUnico, String fecha, float costo, Carrito listaPedidos, 
			String direccion, String numeroCelular) {
		super(idUnico, fecha, costo, listaPedidos);
		this.direccion = direccion;
		this.numeroCelular = numeroCelular;
	}
	
	//Metodos
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNumeroCelular() {
		return numeroCelular;
	}

	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
	}

	public static float getCostoExpress() {
		return costoExpress;
	}

	public static void setCostoExpress(float costoExpress) {
		PedidoExpress.costoExpress = costoExpress;
	}

	@Override
	public float calcularPrecio() {
		return this.getCarritoCompra().sumaOrdenCompra() + this.costoExpress;
	}

	@Override
	public String generarPedido() {
		// cuadro de la suma de totales segun el tipo de pedido

		
		
		return null;
	}
	
	
}
