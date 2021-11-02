package modelo;

import java.util.ArrayList;

public interface Observable {

	ArrayList<Observador> observadores = new ArrayList<Observador>();
	void notificar();
	void enlazarObservador(Observador o);
}
