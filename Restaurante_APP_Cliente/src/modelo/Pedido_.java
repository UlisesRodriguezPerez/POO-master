
package modelo;

import java.io.Serializable;

public class Pedido_ implements Serializable {

	public enum MODO {
		EXPRESS, SITIO, RECOGER
	};
	
	public MODO modo;
	public PedidoExpress express;
	public PedidoSitio sitio;
	public PedidoRecoger recoger;

}
