package modelo;


import java.io.Serializable;

import javax.swing.Icon;

public class ElementoCatalogo implements Serializable {

	private String codigo;
	private String descripcion;
	private Icon imagen;
	private boolean visibilidad;
	private Plato platoAsociado;
	

	public ElementoCatalogo(Plato platoAsociado, String codigo, String descripcion, boolean visibilidad) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.visibilidad = visibilidad;
		this.platoAsociado = platoAsociado;
	}


	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isVisibilidad() {
		return visibilidad;
	}

	public void setVisibilidad(boolean visibilidad) {
		this.visibilidad = visibilidad;
	}

	public Icon getImagen() {
		return imagen;
	}

	public void setImagen(Icon imagen) {
		this.imagen = imagen;
	}
	
	public Plato getPlatoAsociado() {
		return platoAsociado;
	}

	public void setPlatoAsociado(Plato platoAsociado) {
		this.platoAsociado = platoAsociado;
	}
	
	public boolean equals(ElementoCatalogo e) {
		return this.codigo.equals(e.getCodigo()) && this.visibilidad == e.visibilidad &&
				this.descripcion.equals(e.descripcion) && 
				this.imagen.equals(e.imagen) && this.platoAsociado.equals(e.platoAsociado);
	}

	@Override
	public String toString() {
		return "ElementoCatalogo [codigo=" + codigo + ", descripcion=" + descripcion + ", imagen=" + imagen
				+ ", visibilidad=" + visibilidad + "]";
	}

	
	
}

