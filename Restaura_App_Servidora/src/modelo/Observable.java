package modelo;

import java.util.ArrayList;

public interface Observable {

	void notificar();
	void enlazarObservador(Observador o);
	ArrayList<Observador> observadores = new ArrayList<Observador>();
}

