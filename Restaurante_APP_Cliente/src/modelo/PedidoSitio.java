package modelo;

import java.io.Serializable;

public class PedidoSitio extends Pedido implements Serializable {

	public PedidoSitio(int idUnico, String fecha, float costo, Carrito listaPedidos) {
		super(idUnico, fecha, costo, listaPedidos);
	}
	
	@Override
	public float calcularPrecio() {
		return this.calcularPrecio();
	}

	@Override
	public String generarPedido() {
		// cuadro de la suma de totales segun el tipo de pedido
		
		return null;
	}

}
