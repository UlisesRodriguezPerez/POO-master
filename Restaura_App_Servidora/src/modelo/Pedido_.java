package modelo;

import java.io.Serializable;

public class Pedido_ implements Serializable {

	public enum MODO {
		EXPRESS, SITIO, RECOGER
	};
	
	MODO modo;
	public PedidoExpress express;
	public PedidoSitio sitio;
	public PedidoRecoger recoger;

}
