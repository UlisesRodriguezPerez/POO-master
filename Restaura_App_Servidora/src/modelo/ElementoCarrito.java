package modelo;

public class ElementoCarrito {

	Plato platoAsociado;
	int cantidadPorPlato;
	
	public ElementoCarrito(Plato platoAsociado, int cantidad) {
		this.platoAsociado = platoAsociado;
		cantidadPorPlato = cantidad;
	}
	
	public Plato getPlatoAsociado() {
		return platoAsociado;
	}

	public void setPlatoAsociado(Plato platoAsociado) {
		this.platoAsociado = platoAsociado;
	}

	public int getCantidadPorPlato() {
		return cantidadPorPlato;
	}

	public void setCantidadPorPlato(int cantidadPorPlato) {
		this.cantidadPorPlato = cantidadPorPlato;
	}

	public float sumaPorPlato() {
		return platoAsociado.getPrecio() * cantidadPorPlato;
	}
	
	public void aumentar() {
		this.cantidadPorPlato++;
	}
	
	public void disminuir() {
		this.cantidadPorPlato--;
	}

	@Override
	public String toString() {
		return "ElementoCarrito [platoAsociado=" + platoAsociado + ", cantidadPorPlato=" + cantidadPorPlato + ", sumaPorPlato=" +
				sumaPorPlato()+ "]";
	}

	
}
