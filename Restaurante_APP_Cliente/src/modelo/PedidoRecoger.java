package modelo;

import java.io.Serializable;

/**
 * Clase PedidoRecoger es para los pedidos que se 
 * llevan a retirar nada más
 * @author Josue Rojas Vega 
 * @author Kevin Fallas Alvarado
 * @author Ulises Rodriguez Perez	
 * @since 2019-10-22
 */
public class PedidoRecoger extends Pedido implements Serializable {
	
	//Atributos
	private static float costoEmpaque = 600;
	private String nombreRetirador;
	private String numero;
	
	//Contructor
	public PedidoRecoger(int idUnico, String fecha, float costo, Carrito listaPedidos, 
			String nombreRetirador, String numero) {
		super(idUnico, fecha, costo, listaPedidos);
		this.nombreRetirador = nombreRetirador;
	}

	//Metodos
	public String getNombreRetirador() {
		return nombreRetirador;
	}

	public void setNombreRetirador(String nombreRetirador) {
		this.nombreRetirador = nombreRetirador;
	}
	
	public static float getCostoEmpaque() {
		return costoEmpaque;
	}

	public static void setCostoEmpaque(float costoEmpaque) {
		PedidoRecoger.costoEmpaque = costoEmpaque;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public float calcularPrecio() {
		return this.getCarritoCompra().sumaOrdenCompra() + this.costoEmpaque;
	}

	@Override
	public String generarPedido() {
		// cuadro de la suma de totales segun el tipo de pedido
		
		
		return null;
	}
	
	
	
}
