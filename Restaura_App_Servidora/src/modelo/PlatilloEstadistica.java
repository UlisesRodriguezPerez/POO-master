package modelo;

public class PlatilloEstadistica {
	//Atributos
	private String nombrePlatillo;
	private int cantidad;
	//Constructor
	public PlatilloEstadistica(String nombrePlatillo, int cantidad) {
		this.nombrePlatillo = nombrePlatillo;
		this.cantidad = cantidad;
	}
	//Metodos
	public String getNombrePlatillo() {
		return nombrePlatillo;
	}
	public void setNombrePlatillo(String nombrePlatillo) {
		this.nombrePlatillo = nombrePlatillo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}
