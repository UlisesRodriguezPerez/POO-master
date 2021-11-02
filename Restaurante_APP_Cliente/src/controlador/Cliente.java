package controlador;

import modelo.Carrito;
import modelo.Catalogo;
import modelo.Pedido_;
import modelo.ReceptorDeCatalogo;

public class Cliente {

	ReceptorDeCatalogo receptorCatalogo;
	Carrito carrito;
	Catalogo catalogo;
	Pedido_ pedidoActual;
	
	public Cliente(Carrito carrito, Catalogo catalogo, ReceptorDeCatalogo receptorCatalogo) {
		this.carrito = carrito;
		this.catalogo = catalogo;
		this.receptorCatalogo = receptorCatalogo;
	}

	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}

	public Catalogo getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	public ReceptorDeCatalogo getReceptorCatalogo() {
		return receptorCatalogo;
	}

	public void setReceptorCatalogo(ReceptorDeCatalogo receptorCatalogo) {
		this.receptorCatalogo = receptorCatalogo;
	}

	public Pedido_ getPedidoActual() {
		return pedidoActual;
	}

	public void setPedidoActual(Pedido_ pedidoActual) {
		this.pedidoActual = pedidoActual;
	}

}
