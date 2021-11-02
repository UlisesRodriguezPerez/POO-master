package modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Catalogo extends ArrayList<ElementoCatalogo> implements Serializable {
	
	public float valorExpress;
	public float valorRecoger;
	
	public ElementoCatalogo getObjetoAsociado(String infoTabla[]) {
		for (ElementoCatalogo e : this)
			if (e.getPlatoAsociado().getAlimento().equals(infoTabla[0]) && e.getPlatoAsociado().getTipoAlimento().equals(infoTabla[1])) 
				return e;
		return null;
	}

	// TODO: obtener la lista de los 10 objetos mas solicitados

	// TODO: obtener la relacion porcentual
	
	// TODO: obtener las menos vendidas
	
	// TODO: filtrado segun categoria
	//		 	- Bebida
	//		 	- Fuerte
	//		 	- Postre.....

}