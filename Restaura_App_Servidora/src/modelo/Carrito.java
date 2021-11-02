package modelo;

import java.util.ArrayList;
import javax.swing.JTable;

public class Carrito implements Observable {

	private ArrayList<ElementoCarrito> elementosCarrito;
	private ArrayList<Observador> observadores;
	private JTable tablaAsociada;
	
	public float sumaOrdenCompra() {
		float sumaCompra = 0;
		// obtiene la suma de los elementos del carrito
		for (ElementoCarrito e: elementosCarrito)
			sumaCompra += e.sumaPorPlato();
		return sumaCompra;
	}
	
	public void vacearCarritoCompra() {
		// limpiar la lista lateral, lo pedido hasta ahora
		this.elementosCarrito.clear();
		notificar();
	}
	
	
	public void cambiarCantidadElemento() {
		// cambiar cantidad		
		int cantidadNueva = 0;
		this.obtenerElementoCarrito(this.tablaAsociada.getSelectedColumn()).cantidadPorPlato = cantidadNueva;
		
		notificar();
	}
	
	public void eliminarElementoCarrito() {
		this.elementosCarrito.remove(this.tablaAsociada.getSelectedColumn());
		notificar();
	}
	
	public void agregarElementoCarrito() {
		
		// toma el valor seleccionado y lo agrega a la lista, si no esta
		// si ya existe aumenta el contador
		
		notificar();
	}
	
	public ElementoCarrito obtenerElementoCarrito(int indice) {
		return this.getElementosCarrito().get(indice);
	}

	// GETTERS AND SETTERS
	public ArrayList<ElementoCarrito> getElementosCarrito() {
		return elementosCarrito;
	}

	public void setElementosCarrito(ArrayList<ElementoCarrito> elementosCarrito) {
		this.elementosCarrito = elementosCarrito;
	}
	
	public void enlazarTabla(JTable tablaAsociada) {
		this.tablaAsociada = tablaAsociada;
	}
	
	@Override
	public void notificar() {
		for (Observador o: observadores)
			o.actualizar();
	}

	@Override
	public void enlazarObservador(Observador o) {
		this.observadores.add(o);
	}

}
