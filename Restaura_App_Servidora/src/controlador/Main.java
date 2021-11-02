package controlador;

import javax.swing.JTable;

import modelo.Catalogo;
import modelo.ElementoCatalogo;
import modelo.Plato;
import modelo.ReceptorDePedidos;
import vista.VistaAdmin;

public class Main {

	public static void main(String args[]) {
//		Catalogo catalogo = cargarCatalogoEjemplo();
		Catalogo catalogo = new Catalogo();
		Administrador admin = new Administrador(catalogo, null);
		ReceptorDePedidos receptorPedidos = new ReceptorDePedidos(admin.getPedidos());
		VistaAdmin vistaAdmin = new VistaAdmin();
		admin.setReceptorPedidos(receptorPedidos);
		Controlador controlador = new Controlador(admin, vistaAdmin, receptorPedidos);
	}
	
	// cargar ejemplo /////////////////////////////////////////////////
	public static Catalogo cargarCatalogoEjemplo() {
		Catalogo catalogo = new Catalogo();
		Plato plato = new Plato(null, "Postres", "Helado", "c", 10, 1000);
		catalogo.add(new ElementoCatalogo(plato, "S", "S", true));
		
		Plato plato1 = new Plato(null, "Bebidas", "Coca Cola", "c", 12, 2000);
		catalogo.add(new ElementoCatalogo(plato1, "S", "S", true));
		
		Plato plato2 = new Plato(null, "Principales", "Casado", "c", 14, 3000);
		catalogo.add(new ElementoCatalogo(plato2, "S", "S", true));
		
		Plato plato3 = new Plato(null, "Postres", "Tres Leches", "c", 15, 77777);
		catalogo.add(new ElementoCatalogo(plato3, "S", "S", true));
		
		Plato plato4 = new Plato(null, "Bebidas", "Leche", "1",  19, 5000);
		catalogo.add(new ElementoCatalogo(plato4, "S", "S", true));
		
		Plato plato5 = new Plato(null, "Postres", "Arroz con leche", "1",  12, 155);
		catalogo.add(new ElementoCatalogo(plato5, "S", "S", true));
		
		Plato plato6 = new Plato(null, "Bebidas", "Cafe", "1",  8, 44);
		catalogo.add(new ElementoCatalogo(plato6, "S", "S", true));
		
		Plato plato7 = new Plato(null, "Principales", "Camaron", "1",  64, 11400);
		catalogo.add(new ElementoCatalogo(plato7, "S", "S", true));
		
		Plato plato8 = new Plato(null, "Postres", "Dos leches", "1",  15, 4000);
		catalogo.add(new ElementoCatalogo(plato8, "S", "S", true));
		
		Plato plato9 = new Plato(null, "Bebidas", "Horchata", "1", 19, 99999);
		catalogo.add(new ElementoCatalogo(plato9, "S", "S", true));
		
		Plato plato10 = new Plato(null, "Bebidas", "Chocolate", "1",  11, 555);
		catalogo.add(new ElementoCatalogo(plato10, "S", "S", true));
		
		Plato plato11 = new Plato(null, "Bebidas", "Lizano", "1", 12, 6666);
		catalogo.add(new ElementoCatalogo(plato11, "S", "S", true));
		
		Plato plato12 = new Plato(null, "Bebidas", "Cacique", "1", 14, 4757);
		catalogo.add(new ElementoCatalogo(plato12, "S", "S", true));
		
		Plato plato13 = new Plato(null, "Bebidas", "Agua", "1",  15, 1111);
		catalogo.add(new ElementoCatalogo(plato13, "S", "S", true));
		
		Plato plato14 = new Plato(null, "Bebidas", "Jugo de piña", "1", 19, 596);
		catalogo.add(new ElementoCatalogo(plato14, "S", "S", true));
//		for (int i=0; i<15; i++) {
//			Plato plato = new Plato("a"+i, "b"+i, "c", "d", 10+i, 1000+i);
//			catalogo.add(new ElementoCatalogo(plato, "S"+i, "S"+i, true));
//		}
		return catalogo;
	}
	///////////////////////////////////////////////////////////////////
}
