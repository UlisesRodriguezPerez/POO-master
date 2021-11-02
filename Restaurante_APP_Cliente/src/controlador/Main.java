package controlador;

import modelo.Carrito;
import modelo.Catalogo;
import modelo.ElementoCatalogo;
import modelo.Plato;
import modelo.ReceptorDeCatalogo;
import modelo.VisorDeCostos;
import vista.VistaCliente;

public class Main {

	public static void main(String args[]) {
		
		VistaCliente vistaCliente = new VistaCliente();
		Carrito carrito = new Carrito(vistaCliente.tblCarrito);
		Catalogo catalogo = new Catalogo();
		ReceptorDeCatalogo receptorCatalogo = new ReceptorDeCatalogo(null);
		Cliente cliente = new Cliente(carrito, catalogo, receptorCatalogo);
		receptorCatalogo.setCliente(cliente);
		
		// SINGLETON
		VisorDeCostos visor = VisorDeCostos.obtenerInstancia();
		
		Controlador c = new Controlador(vistaCliente, cliente, visor, catalogo, carrito, receptorCatalogo);
	}
	
	// cargar ejemplo /////////////////////////////////////////////////
	public static Catalogo cargarCatalogoEjemplo() {
		Catalogo catalogo = new Catalogo();
		Plato plato = new Plato("Postre", "Helado", "c", 10, 1000);
		catalogo.add(new ElementoCatalogo(plato, "S", "S", true));
		
		Plato plato1 = new Plato("Bebida", "Coca Cola", "c", 12, 2000);
		catalogo.add(new ElementoCatalogo(plato1, "S", "S", true));
		
		Plato plato2 = new Plato("Principal", "Casado", "c", 14, 3000);
		catalogo.add(new ElementoCatalogo(plato2, "S", "S", true));
		
		Plato plato3 = new Plato("Postre", "Tres Leches", "c",  15, 77777);
		catalogo.add(new ElementoCatalogo(plato3, "S", "S", true));
		
		Plato plato4 = new Plato("Bebida", "Leche", "1",  19, 5000);
		catalogo.add(new ElementoCatalogo(plato4, "S", "S", true));
		
		Plato plato5 = new Plato("Postre", "Arroz con leche", "1", 12, 155);
		catalogo.add(new ElementoCatalogo(plato5, "S", "S", true));
		
		Plato plato6 = new Plato("Bebida", "Cafe", "1",  8, 44);
		catalogo.add(new ElementoCatalogo(plato6, "S", "S", true));
		
		Plato plato7 = new Plato("Principal", "Camaron", "1", 64, 11400);
		catalogo.add(new ElementoCatalogo(plato7, "S", "S", true));
		
		Plato plato8 = new Plato("Postre", "Dos leches", "1",  15, 4000);
		catalogo.add(new ElementoCatalogo(plato8, "S", "S", true));
		
		Plato plato9 = new Plato("Bebida", "Horchata", "1", 19, 99999);
		catalogo.add(new ElementoCatalogo(plato9, "S", "S", true));
		
		Plato plato10 = new Plato("Bebida", "Chocolate", "1",  11, 555);
		catalogo.add(new ElementoCatalogo(plato10, "S", "S", true));
		
		Plato plato11 = new Plato("Bebida", "Lizano", "1",  12, 6666);
		catalogo.add(new ElementoCatalogo(plato11, "S", "S", true));
		
		Plato plato12 = new Plato("Bebida", "Cacique", "1",  14, 4757);
		catalogo.add(new ElementoCatalogo(plato12, "S", "S", true));
		
		Plato plato13 = new Plato("Bebida", "Agua", "1", 15, 1111);
		catalogo.add(new ElementoCatalogo(plato13, "S", "S", true));
		
		Plato plato14 = new Plato("Bebida", "Jugo de piña", "1",  19, 596);
		catalogo.add(new ElementoCatalogo(plato14, "S", "S", true));
//		for (int i=0; i<15; i++) {
//			Plato plato = new Plato("a"+i, "b"+i, "c", "d", 10+i, 1000+i);
//			catalogo.add(new ElementoCatalogo(plato, "S"+i, "S"+i, true));
//		}
		return catalogo;
	}
	///////////////////////////////////////////////////////////////////
	
}
