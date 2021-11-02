package modelo;

import java.io.Serializable;

import javax.swing.Icon;

/**
 * Representa el plato del menu
 * @author Ulises Rodriguez Perez
 * @author Kevin Fallas Alvarado
 * @author Josue Rojas Vega
 * @since 2019-09-14
 *
 */
public class Plato implements Serializable {
	//Atributos
		
		public static final String codigos[] = {
				"ENT", "PRN", "PTR", "BEB"
		};
	
		private String tipoAlimento; //Fuerte, Entrada ...
		private String alimento; //Nombre del alimento
		private String racion; //porcion
		private int calorias; //VER ESTO ESPE **************
		private float precio;
		private Icon imagen;
		
		//Constructor
		public Plato(Icon imagen, String tipoAlimento, String alimento, String racion, int calorias, float precio) {
			this.tipoAlimento = tipoAlimento;
			this.alimento = alimento;
			this.racion = racion;
			this.calorias = calorias;
			this.precio = precio;
		}
		
		//Metodos
		public String getTipoAlimento() {
			return tipoAlimento;
		}

		public void setTipoAlimento(String tipoAlimento) {
			this.tipoAlimento = tipoAlimento;
		}
		
		public Icon getImagen() {
			return imagen;
		}

		public void setImagen(Icon imagen) {
			this.imagen = imagen;
		}

		public static String[] getCodigos() {
			return codigos;
		}

		public String getAlimento() {
			return alimento;
		}

		public void setAlimento(String alimento) {
			this.alimento = alimento;
		}

		public String getRacion() {
			return racion;
		}

		public void setRacion(String racion) {
			this.racion = racion;
		}

		public int getCalorias() {
			return calorias;
		}

		public void setCalorias(int calorias) {
			this.calorias = calorias;
		}

		public float getPrecio() {
			return precio;
		}

		public void setPrecio(float precio) {
			this.precio = precio;
		}
		
		public boolean equals(Plato p) {
			return this.alimento.equals(p.alimento) && this.calorias == p.calorias
					 && this.precio == p.precio
					&& this.racion.equals(p.racion) && this.tipoAlimento.equals(p.tipoAlimento);
		}

		@Override
		public String toString() {
			return "Plato [tipoAlimento=" + tipoAlimento + ", alimento=" + alimento + ", racion=" + racion + ", calorias=" + calorias + ", precio=" + precio + "]";
		}
	
	
}
