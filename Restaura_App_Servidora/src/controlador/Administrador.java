package controlador;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import modelo.Catalogo;
import modelo.ElementoCarrito;
import modelo.ElementoCatalogo;
import modelo.Observable;
import modelo.Observador;
import modelo.Pedido;
import modelo.Plato;
import modelo.ReceptorDePedidos;

public class Administrador implements Observable {

	ArrayList<Observador> observadores;
	private Catalogo catalogo;
	private ArrayList<Pedido> pedidos;
	private ReceptorDePedidos receptorPedidos;
	private ArrayList<Socket> conexiones;
	public JTable tabla;
	public static float precioExpress = 1000;
	public static float precioRecoger = 600;
	
	public Administrador(Catalogo catalogo, ReceptorDePedidos receptorPedidos) {
		this.receptorPedidos = receptorPedidos;
		this.catalogo = catalogo;
		this.pedidos = new ArrayList<Pedido>();
		this.conexiones = new ArrayList<Socket>();
	}
	
	public void cargarPedidos() {
		if (tabla != null) {
			DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
			// Cliente, Platillo, Cantidad
			for (Pedido p:this.pedidos) {
				for (ElementoCarrito e: p.getCarritoCompra().getElementosCarrito())
					modelo.addRow(new Object[] {
							String.valueOf(p.getIdUnico()),
							e.getPlatoAsociado().getAlimento(),
							e.getCantidadPorPlato()
					});
			}
		}
	}
	
	public void limpiarPedidos() {
		
		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
		// Cliente, Platillo, Cantidad
		int cantidadfilas = modelo.getRowCount();
		for (int i=0; i<cantidadfilas; i++) {
			modelo.removeRow(0);
		}
	}
	
	public void generarXML(Catalogo catalogo) throws ClassNotFoundException {
	    
		try{ // a partir de la clase obtenida por la vista, se genera el documento
			// genera la instancia para el documento .xml
	    	DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance(); // se crea el documento
	        DocumentBuilder docBuilder = docFactory.newDocumentBuilder(); // se crea un builder, para formar el documento
	        
	        //se agrega el elemento principal del archivo
	        Document documento = docBuilder.newDocument(); // se crea un builder, para formar el documento
	        Element raiz =  documento.createElement("catalogo"); 
	        documento.appendChild(raiz);	// se agrega la raiz principal del .xml	        	        
	        // se agregan los elementos generados a partir de la informacion de la clase
	        
	        for(ElementoCatalogo e : catalogo) {
	        	Element elementoCatalogo =  documento.createElement("item");
	        	Attr codigo = documento.createAttribute("codigo");
	        	codigo.setValue(e.getCodigo());
	        	
	        	Attr descripcion = documento.createAttribute("descripcion");
	        	descripcion.setValue(e.getDescripcion());
	        	
	        	Attr visibilidad = documento.createAttribute("visibilidad");
	        	visibilidad.setValue(new String(Boolean.toString(e.isVisibilidad())));
	        
	        	Element plato = documento.createElement("plato");
	        	Attr nombre = documento.createAttribute("nombre");
	        	nombre.setValue(e.getPlatoAsociado().getAlimento());
	        	
	        	Attr tipoAlimento = documento.createAttribute("tipo");
	        	tipoAlimento.setValue(e.getPlatoAsociado().getTipoAlimento());
	        	
	        	Attr racion = documento.createAttribute("racion");
	        	racion.setValue(e.getPlatoAsociado().getRacion());
	        
	        	
	        	Attr calorias = documento.createAttribute("caloria");
	        	calorias.setValue(String.valueOf((e.getPlatoAsociado().getCalorias())));
	        	
	        	Attr precio = documento.createAttribute("precio");
	        	precio.setValue(String.valueOf((e.getPlatoAsociado().getPrecio())));
	        	
	        	plato.setAttribute("nombre", nombre.getValue());
	        	plato.setAttribute("tipoAlimento", tipoAlimento.getValue());
	        	plato.setAttribute("racion", racion.getValue());
	        	plato.setAttribute("calorias", calorias.getValue());
	        	plato.setAttribute("precio", precio.getValue());

	        	
	        	elementoCatalogo.setAttribute("codigo", codigo.getValue());
	        	elementoCatalogo.setAttribute("descripcion", descripcion.getValue());
	        	elementoCatalogo.setAttribute("visibilidad", visibilidad.getValue());
	        	elementoCatalogo.appendChild(plato);
	        	
	        	raiz.appendChild(elementoCatalogo);
	        }
	        
	        TransformerFactory instanciaTransformador = TransformerFactory.newInstance();
	        Transformer transformador = instanciaTransformador.newTransformer();
	        DOMSource fuente = new DOMSource(documento);
	        File archivoXML = new File("Catalogo.xml"); // asignamos el nombre del archivo y su extension
	        StreamResult flujoDeArchivo = new StreamResult(archivoXML); // se instancia el flujo para el DOM del documento
	        transformador.transform(fuente, flujoDeArchivo); // segun la fuente y el flujo de resulltado, se trandforma y se guarda el documento
	        JOptionPane.showMessageDialog(null, "El Catalogo se ha generado en "+archivoXML.getAbsolutePath());
	        // se muestra el mensaje con respecto a la ubicacion del archivo en el sistema
	      
		} catch (ParserConfigurationException | TransformerException e) {
	        e.printStackTrace();
	      } 
	}
	
	public Catalogo leerXML() throws IOException {
		Catalogo catalogo = new Catalogo();
        File archivoXML;
        JFrame ventana = new JFrame();
        JFileChooser ventanaEleccionImagen = new JFileChooser();
        ventana.add(ventanaEleccionImagen);
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("XML", "xml");
        ventanaEleccionImagen.setFileFilter(filtro);
        int resultado = ventanaEleccionImagen.showOpenDialog(ventana);
        if(JFileChooser.APPROVE_OPTION == resultado) {
        	archivoXML = ventanaEleccionImagen.getSelectedFile();
        	try {
        		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        		Document documento = documentBuilder.parse(archivoXML);
        		documento.getDocumentElement().normalize();
        		NodeList listaElementos = documento.getElementsByTagName("item");
        		
        		for(int i = 0; i < listaElementos.getLength(); i++) {
        			Node elemento = listaElementos.item(i);
        			if(elemento.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) elemento;
                        //LAS DEMAS ACCIONES
                        Attr codigo = element.getAttributeNode("codigo");
                        Attr descripcion = element.getAttributeNode("descripcion");
                        Attr visibilidad = element.getAttributeNode("visibilidad");
                        
                        NodeList platoNodeL = (NodeList) element.getElementsByTagName("plato");
                        
                        Node platoN = (Element) platoNodeL.item(0);
                        Element plato = (Element) platoN;
                        
                        Attr nombre = plato.getAttributeNode("nombre");
                        Attr tipo = plato.getAttributeNode("tipoAlimento");
                        Attr racion = plato.getAttributeNode("racion");
                        Attr caloria = plato.getAttributeNode("calorias");
                        Attr precio = plato.getAttributeNode("precio");
                        
                        Plato nuevoPlato = new Plato(null, tipo.getValue(), nombre.getValue(), racion.getValue(), 
                        							 Integer.parseInt(caloria.getValue()), Float.parseFloat(precio.getValue()));
                        
                        ElementoCatalogo nuevoElemento = new ElementoCatalogo(nuevoPlato, codigo.getValue(), descripcion.getValue(), 
                        													  Boolean.parseBoolean(visibilidad.getValue()));
                        catalogo.add(nuevoElemento);
         			}
        			
        		}
        	} catch (Exception e) {
                e.printStackTrace();
                catalogo = null;
        	}
        } else if(JFileChooser.ERROR_OPTION == resultado) {	
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error durante la carga de archivo", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			catalogo = null;
        }else {
			JOptionPane.showMessageDialog(null, "No se ha podido cargar el archivo", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			catalogo = null;
		}
        return catalogo;
    }
	
	public ArrayList<Socket> getConexiones() {
		return conexiones;
	}

	public void setConexiones(ArrayList<Socket> conexiones) {
		this.conexiones = conexiones;
	}

	public Administrador() {
		this.observadores = new ArrayList<Observador>();
	}
	
	public Administrador(Catalogo catalogo) {
		this.catalogo = catalogo;
		this.observadores = new ArrayList<Observador>();
	}

	public Catalogo getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	@Override
	public void notificar() {
		for (Observador o: observadores)
			o.actualizar();
	}

	public ArrayList<Observador> getObservadores() {
		return observadores;
	}

	public void setObservadores(ArrayList<Observador> observadores) {
		this.observadores = observadores;
	}

	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public ReceptorDePedidos getReceptorPedidos() {
		return receptorPedidos;
	}

	public void setReceptorPedidos(ReceptorDePedidos receptorPedidos) {
		this.receptorPedidos = receptorPedidos;
	}

	@Override
	public void enlazarObservador(Observador o) {
		this.observadores.add(o);
	}
	
}
