package com.proquinal.pep.managed_bean.internet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.proquinal.pep.entidades.internet.catalogo.ListarCategorizacionDTO;
import com.proquinal.pep.entidades.internet.catalogo.ListarFichaTecnicaDTO;
import com.proquinal.pep.entidades.internet.catalogo.ListarNormasDTO;
import com.proquinal.pep.entidades.ycontent.dto.ParametricaDTO;
import com.proquinal.pep.fwk.constantes.ConstantesPEPParametricas;
import com.proquinal.pep.fwk.exception.BusinessException;
import com.proquinal.pep.negocioServicio.internet.IServicioInternet;
import com.proquinal.pep.negocioServicio.ycontent.IServicioYcontent;

public class InternetBean {
	
	@EJB(mappedName="ejb/ServicioInternet")
	private IServicioInternet servicioInternet;
	
	@EJB(mappedName="ejb/ServicioYcontent")
	private IServicioYcontent servicioYcontent;
	
	/** K_TIPO de la vista materializada V_GLB_PROD_CATEGORIZACION, dato consultado de la tabla parametrica*/
	private String tipoProducto;
	
	/** Key de la Categoría */
	private String kCategoria;
	
	/** Nombre de la Categoría */
	private String dCategoria;
	
	/** Key del Segmento */
	private String kSegmento;
	
	/** Nombre del Segmento */
	private String dSegmento;
	
	/** Key del Subsegmento */
	private String kSubsegmento;
	
	/** Nombre del Subsegmento */
	private String dSubsegmento;	
	
	/** Key de la Referencia */
	private String kReferencia;
	
	/** Nombre de la Referencia */
	private String dReferencia;
	
	/** Key del Producto */
	private String kProducto;
	
	/** Nombre del Producto */
	private String dProducto;
	
	/** K_CODIGO del producto */
	private String imagenMediana;
	
	/** K_NOMBRE del producto */
	private String nombreImagen;
	
	/** NOMBRE de la imagen por segmento */
	private String nombreImagenSegmento;
	
	/** Listado de las Categorías */
	private List<ListarCategorizacionDTO> listaCategorias;
	
	/** Listado de los Segmentos */
	private List<ListarCategorizacionDTO> listaSegmentos;
	
	/** Listado de los Subsegmentos */
	private List<ListarCategorizacionDTO> listaSubsegmentos;
	
	/** Listado de las Referencias */
	private List<ListarCategorizacionDTO> listaReferencias;
	
	/** Listado de los Productos */
	private List<ListarCategorizacionDTO> listaProductos;
	
	/** Listado de las Normas que cumple un producto */
	private List<ListarNormasDTO> listaNormas;
	
	/** Listado de la Ficha técnica de un producto */
	private ListarFichaTecnicaDTO listaFichaTecnica;
	
	/** variables del buscador*/
	private String categoria;
	
	private String segmento;
	
	private String subsegmento;
	
	private String referencia;
	
	private String producto;
	
	private ArrayList <SelectItem> elemCategorias = null;
	
	private ArrayList <SelectItem> elemSegmentos = null;
	
	private ArrayList <SelectItem> elemSubsegmentos = null;
	
	private ArrayList <SelectItem> elemReferencias = null;
	
	private ArrayList <SelectItem> elemProductos = null;
	
	int swCategoria = 0;
	
	int swSegmento = 0;
	
	int swSubsegmento = 0;
	
	int swReferencia = 0;
	
	/**
	 * Contructor
	 */
	public InternetBean() {
		
	}
	
	/**
	 * Postcontructor
	 */
	@PostConstruct
	public void postConstructor(){
		// System.out.println("Ingreso al método: postConstructor()");
		try {
			ParametricaDTO tipo = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.TIPO_DE_PRODUCTO_CATALOGO );
			tipoProducto = tipo.getDescripcion1().trim();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getkCategoria() {
		return kCategoria;
	}

	public void setkCategoria(String kCategoria) {
		this.kCategoria = kCategoria;
	}

	public String getdCategoria() {
		return dCategoria;
	}

	public void setdCategoria(String dCategoria) {
		this.dCategoria = dCategoria;
	}

	public String getkSegmento() {
		return kSegmento;
	}

	public void setkSegmento(String kSegmento) {
		this.kSegmento = kSegmento;
	}

	public String getdSegmento() {
		return dSegmento;
	}

	public void setdSegmento(String dSegmento) {
		this.dSegmento = dSegmento;
	}

	public String getkSubsegmento() {
		return kSubsegmento;
	}

	public void setkSubsegmento(String kSubsegmento) {
		this.kSubsegmento = kSubsegmento;
	}

	public String getdSubsegmento() {
		return dSubsegmento;
	}

	public void setdSubsegmento(String dSubsegmento) {
		this.dSubsegmento = dSubsegmento;
	}

	public String getkReferencia() {
		return kReferencia;
	}

	public void setkReferencia(String kReferencia) {
		this.kReferencia = kReferencia;
	}

	public String getdReferencia() {
		return dReferencia;
	}

	public void setdReferencia(String dReferencia) {
		this.dReferencia = dReferencia;
	}
	
	public String getkProducto() {
		return kProducto;
	}

	public void setkProducto(String kProducto) {
		this.kProducto = kProducto;
	}

	public String getdProducto() {
		return dProducto;
	}

	public void setdProducto(String dProducto) {
		this.dProducto = dProducto;
	}

	public String getImagenMediana() {
		return imagenMediana;
	}

	public void setImagenMediana(String imagenMediana) {
		this.imagenMediana = imagenMediana;
	}

	public String getNombreImagen() {
		return nombreImagen;
	}

	public void setNombreImagen(String nombreImagen) {
		this.nombreImagen = nombreImagen;
	}
	
	public List<ListarCategorizacionDTO> getListaCategorias() {
		return listaCategorias;
	}
	
	public void setListaCategorias(
			List<ListarCategorizacionDTO> listaCategorias) {
		this.listaCategorias = listaCategorias;
	}
	
	public List<ListarCategorizacionDTO> getListaSegmentos() {
		return listaSegmentos;
	}
	
	public void setListaSegmentos(
			List<ListarCategorizacionDTO> listaSegmentos) {
		this.listaSegmentos = listaSegmentos;
	}
	
	public List<ListarCategorizacionDTO> getListaSubsegmentos() {
		return listaSubsegmentos;
	}

	public void setListaSubsegmentos(List<ListarCategorizacionDTO> listaSubsegmentos) {
		this.listaSubsegmentos = listaSubsegmentos;
	}

	public List<ListarCategorizacionDTO> getListaReferencias() {
		return listaReferencias;
	}

	public void setListaReferencias(List<ListarCategorizacionDTO> listaReferencias) {
		this.listaReferencias = listaReferencias;
	}

	public List<ListarCategorizacionDTO> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<ListarCategorizacionDTO> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public List<ListarNormasDTO> getListaNormas() {
		return listaNormas;
	}

	public void setListaNormas(List<ListarNormasDTO> listaNormas) {
		this.listaNormas = listaNormas;
	}
	
	public ListarFichaTecnicaDTO getListaFichaTecnica() {
		return listaFichaTecnica;
	}

	public void setListaFichaTecnica(ListarFichaTecnicaDTO listaFichaTecnica) {
		this.listaFichaTecnica = listaFichaTecnica;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getSegmento() {
		return segmento;
	}
	
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	public String getSubsegmento() {
		return subsegmento;
	}

	public void setSubsegmento(String subsegmento) {
		this.subsegmento = subsegmento;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getNombreImagenSegmento() {
		return nombreImagenSegmento;
	}

	public void setNombreImagenSegmento(String nombreImagenSegmento) {
		this.nombreImagenSegmento = nombreImagenSegmento;
	}

	/**
	 * regla de navegación para listar las categorías
	 */
	public String actionCategorias(){
		
		try {
			
			List<ListarCategorizacionDTO> lCategorias;
			
			lCategorias = servicioInternet.obtenerListadoCategorias(tipoProducto);
			setListaCategorias(lCategorias);
			
			/** categorías */
			System.out.println( "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println( "CATEGORIAS");
			for (Iterator<?> iterator = lCategorias.iterator(); iterator.hasNext();) {
				ListarCategorizacionDTO listaCategoriasDTO = (ListarCategorizacionDTO) iterator.next();
				System.out.println( "k_categoria : " + listaCategoriasDTO.getK_nombre() + " || d_categoria : " + listaCategoriasDTO.getD_nombre() );
			}
			System.out.println( "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			
		} catch (Exception ex) { //BusinessException e
			// TODO Auto-generated catch block
			
		}
		
		return  "success";
	}
	
	/**
	 * regla de navegación para listar los segmentos
	 */
	@SuppressWarnings("unchecked")
	public String actionSegmentos(){
		
		try {
			FacesContext context = FacesContext.getCurrentInstance(); 
	    	Map map = context.getExternalContext().getRequestParameterMap();
	    	String kCategoria = (String) map.get("kCategoria");
	    	String dCategoria = (String) map.get("dCategoria");
	    	
	    	setkCategoria(kCategoria);
	    	setdCategoria(dCategoria);
	    	
			List<ListarCategorizacionDTO> lSegmentos;
			
			lSegmentos = servicioInternet.obtenerListadoSegmentos(tipoProducto, kCategoria);
			setListaSegmentos(lSegmentos);
			
			/** segmentos */
			System.out.println( "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println( "SEGMENTOS DE LA CATEGORIA : " + dCategoria + "(" + kCategoria + ")");
			for (Iterator<?> iterator = lSegmentos.iterator(); iterator.hasNext();) {
				ListarCategorizacionDTO listaSegmentosDTO = (ListarCategorizacionDTO) iterator.next();
				System.out.println( "k_segmento : " + listaSegmentosDTO.getK_nombre() + " || d_segmento : " + listaSegmentosDTO.getD_nombre() );
			}
			System.out.println( "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			
		} catch (Exception e) { //BusinessException e
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return  "success";
	}
	
	/**
	 * regla de navegación para listar los subsegmentos
	 */
	@SuppressWarnings("unchecked")
	public String actionSubsegmentos(){
		
		try {
			FacesContext context = FacesContext.getCurrentInstance(); 
	    	Map map = context.getExternalContext().getRequestParameterMap();
	    	String kCategoria = (String) map.get("kCategoria");
	    	String dCategoria = (String) map.get("dCategoria");
	    	String kSegmento = (String) map.get("kSegmento");
	    	String dSegmento = (String) map.get("dSegmento");
	    	setkCategoria(kCategoria);
	    	setdCategoria(dCategoria);
	    	setkSegmento(kSegmento);
	    	setdSegmento(dSegmento);
			
			List<ListarCategorizacionDTO> lSubsegmentos;
			
			lSubsegmentos = servicioInternet.obtenerListadoSubsegmentos(tipoProducto, kCategoria, kSegmento);
			setListaSubsegmentos(lSubsegmentos);
			
			/** subsegmentos */
			System.out.println( "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println( "SUBEGMENTOS DE LA CATEGORIA : " + dCategoria + "(" + kCategoria + ")" + " Y DEL SEGMENTO : " + dSegmento + "(" + kSegmento + ")");
			for (Iterator<?> iterator = lSubsegmentos.iterator(); iterator.hasNext();) {
				ListarCategorizacionDTO listaSubsegmentosDTO = (ListarCategorizacionDTO) iterator.next();
				System.out.println( "k_segmento : " + listaSubsegmentosDTO.getK_nombre() + " || d_segmento : " + listaSubsegmentosDTO.getD_nombre() );
			}
			System.out.println( "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			
		} catch (Exception e) { //BusinessException e
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return  "success";
	}
	
	/**
	 * regla de navegación para listar las referencias
	 */
	@SuppressWarnings("unchecked")
	public String actionReferencias(){
		
		try {
			FacesContext context = FacesContext.getCurrentInstance(); 
	    	Map map = context.getExternalContext().getRequestParameterMap();
	    	String kCategoria = (String) map.get("kCategoria");
	    	String dCategoria = (String) map.get("dCategoria");
	    	String kSegmento = (String) map.get("kSegmento");
	    	String dSegmento = (String) map.get("dSegmento");
	    	String kSubsegmento = (String) map.get("kSubsegmento");
	    	String dSubsegmento = (String) map.get("dSubsegmento");
	    	String kReferencia = (String) map.get("kReferencia");
	    	String dReferencia = (String) map.get("dReferencia");
	    	setkCategoria(kCategoria);
	    	setdCategoria(dCategoria);
	    	setkSegmento(kSegmento);
	    	setdSegmento(dSegmento);
	    	setkSubsegmento(kSubsegmento);
	    	setdSubsegmento(dSubsegmento);
	    	setkReferencia(kReferencia);
	    	setdReferencia(dReferencia);
			
			List<ListarCategorizacionDTO> lReferencias;
			
			lReferencias = servicioInternet.obtenerListadoReferencias(tipoProducto, kCategoria, kSegmento, kSubsegmento);
			setListaReferencias(lReferencias);
			
			/** subsegmentos */
			System.out.println( "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println( "REFERENCIAS DE LA CATEGORIA : " + dCategoria + "(" + kCategoria + ")" + ", DEL SEGMENTO : " + dSegmento + "(" + kSegmento + ")" + " Y DEL SUBEGMENTO : " + dSubsegmento + "(" + kSubsegmento + ")");
			for (Iterator<?> iterator = lReferencias.iterator(); iterator.hasNext();) {
				ListarCategorizacionDTO listaReferenciasDTO = (ListarCategorizacionDTO) iterator.next();
				System.out.println( "k_refencia : " + listaReferenciasDTO.getK_nombre() + " || d_refencia : " + listaReferenciasDTO.getD_nombre() );
			}
			System.out.println( "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			
		} catch (Exception e) { //BusinessException e
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return  "success";
	}
	
	/**
	 * regla de navegación para listar los productos
	 */
	@SuppressWarnings("unchecked")
	public String actionProductos(){
		
		try {
			FacesContext context = FacesContext.getCurrentInstance(); 
	    	Map map = context.getExternalContext().getRequestParameterMap();
	    	String kCategoria = (String) map.get("kCategoria");
	    	String dCategoria = (String) map.get("dCategoria");
	    	String kSegmento = (String) map.get("kSegmento");
	    	String dSegmento = (String) map.get("dSegmento");
	    	String kSubsegmento = (String) map.get("kSubsegmento");
	    	String dSubsegmento = (String) map.get("dSubsegmento");
	    	String kReferencia = (String) map.get("kReferencia");
	    	String dReferencia = (String) map.get("dReferencia");
	    	String kProducto = (String) map.get("kProducto");
	    	String dProducto = (String) map.get("dProducto");
	    	setkCategoria(kCategoria);
	    	setdCategoria(dCategoria);
	    	setkSegmento(kSegmento);
	    	setdSegmento(dSegmento);
	    	setkSubsegmento(kSubsegmento);
	    	setdSubsegmento(dSubsegmento);
	    	setkReferencia(kReferencia);
	    	setdReferencia(dReferencia);
	    	setkProducto(kProducto);
	    	setdProducto(dProducto);
			
	    	List<ListarCategorizacionDTO> lProductos;
			
			lProductos = servicioInternet.obtenerListadoProductos(tipoProducto, kCategoria, kSegmento, kSubsegmento, kReferencia);
			setListaProductos(lProductos);
			
			int sw = 0;
			/** productos */
			System.out.println( "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println( "PRODUCTOS DE LA CATEGORIA : " + dCategoria + "(" + kCategoria + ")" + ", DEL SEGMENTO : " + dSegmento + "(" + kSegmento + ")" + ", DEL SUBSEGMENTO : " + dSubsegmento + "(" + kSubsegmento + ")"+ " Y DE LA REFENCIA : " + dReferencia + "(" + kReferencia + ")");
			for (Iterator<?> iterator = lProductos.iterator(); iterator.hasNext();) {
				ListarCategorizacionDTO listaProductosDTO = (ListarCategorizacionDTO) iterator.next();
				System.out.println( "k_producto : " + listaProductosDTO.getK_nombre() + " || k_nombre : " + listaProductosDTO.getD_nombre());
				
				if ( sw == 0 ){
					setImagenMediana(listaProductosDTO.getK_nombre());
					setNombreImagen(listaProductosDTO.getD_nombre());
					setNombreImagenSegmento((kCategoria + kSegmento).trim().toLowerCase());
					setListaNormas(servicioInternet.obtenerNormas(listaProductosDTO.getK_nombre()));
					setListaFichaTecnica(servicioInternet.obtenerFichaTecnica(listaProductosDTO.getK_nombre()));
					sw = 1;
				}
			}
			System.out.println( "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			
		} catch (Exception e) { //BusinessException e
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return  "success";
	}
	
	/**
	 * regla de navegación para listar los productos con su paleta de colores
	 */
	@SuppressWarnings("unchecked")
	public String actionProductosPareto(){
		
		try {
			FacesContext context = FacesContext.getCurrentInstance(); 
	    	Map map = context.getExternalContext().getRequestParameterMap();
	    	String kCategoria = (String) map.get("kCategoria");
	    	String dCategoria = (String) map.get("dCategoria");
	    	String kSegmento = (String) map.get("kSegmento");
	    	String dSegmento = (String) map.get("dSegmento");
	    	String kSubsegmento = (String) map.get("kSubsegmento");
	    	String dSubsegmento = (String) map.get("dSubsegmento");
	    	String kReferencia = (String) map.get("kReferencia");
	    	String dReferencia = (String) map.get("dReferencia");
	    	String kProducto = (String) map.get("kProducto");
	    	String dProducto = (String) map.get("dProducto");
	    	setkCategoria(kCategoria);
	    	setdCategoria(dCategoria);
	    	setkSegmento(kSegmento);
	    	setdSegmento(dSegmento);
	    	setkSubsegmento(kSubsegmento);
	    	setdSubsegmento(dSubsegmento);
	    	setkReferencia(kReferencia);
	    	setdReferencia(dReferencia);
	    	setkProducto(kProducto);
	    	setdProducto(dProducto);
			
	    	List<ListarCategorizacionDTO> lProductos;
			
			lProductos = servicioInternet.obtenerListadoProductos(tipoProducto, kCategoria, kSegmento, kSubsegmento, kReferencia);
			setListaProductos(lProductos);
			
			int sw = 0;
			/** productos */
			System.out.println( "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println( "PRODUCTOS DE LA CATEGORIA : " + dCategoria + "(" + kCategoria + ")" + ", DEL SEGMENTO : " + dSegmento + "(" + kSegmento + ")" + ", DEL SUBSEGMENTO : " + dSubsegmento + "(" + kSubsegmento + ")"+ " Y DE LA REFENCIA : " + dReferencia + "(" + kReferencia + ")");
			for (Iterator<?> iterator = lProductos.iterator(); iterator.hasNext();) {
				ListarCategorizacionDTO listaProductosDTO = (ListarCategorizacionDTO) iterator.next();
				System.out.println( "k_producto : " + listaProductosDTO.getK_nombre() + " || k_nombre : " + listaProductosDTO.getD_nombre());
				
				if (kProducto.equals(listaProductosDTO.getK_nombre().toString()) && sw == 0 ){
					setImagenMediana(listaProductosDTO.getK_nombre());
					setNombreImagen(listaProductosDTO.getD_nombre());
					setNombreImagenSegmento((kCategoria + kSegmento).trim().toLowerCase());
					setListaNormas(servicioInternet.obtenerNormas(listaProductosDTO.getK_nombre()));
					setListaFichaTecnica(servicioInternet.obtenerFichaTecnica(listaProductosDTO.getK_nombre()));
					sw = 1;
				}
			}
			System.out.println( "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			
		} catch (Exception e) { //BusinessException e
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return  null;
	}
	
	/**
	 * regla de navegación para listar los productos
	 */
	@SuppressWarnings("unchecked")
	public String busquedaCatalogo(){
		System.out.println("categoria: " + categoria);
		System.out.println("segmento: " + segmento);
		System.out.println("subsegmento: " + subsegmento);
		System.out.println("referencia: " + referencia);
		
		/*try {
			/*FacesContext context = FacesContext.getCurrentInstance(); 
	    	Map map = context.getExternalContext().getRequestParameterMap();
	    	String kCategoria = (String) map.get("kCategoria");
	    	String dCategoria = (String) map.get("dCategoria");
	    	String kSegmento = (String) map.get("kSegmento");
	    	String dSegmento = (String) map.get("dSegmento");
	    	String kSubsegmento = (String) map.get("kSubsegmento");
	    	String dSubsegmento = (String) map.get("dSubsegmento");
	    	String kReferencia = (String) map.get("kReferencia");
	    	String dReferencia = (String) map.get("dReferencia");
	    	String kProducto = (String) map.get("kProducto");
	    	String dProducto = (String) map.get("dProducto");
	    	setkCategoria(kCategoria);
	    	setdCategoria(dCategoria);
	    	setkSegmento(kSegmento);
	    	setdSegmento(dSegmento);
	    	setkSubsegmento(kSubsegmento);
	    	setdSubsegmento(dSubsegmento);
	    	setkReferencia(kReferencia);
	    	setdReferencia(dReferencia);
	    	setkProducto(kProducto);
	    	setdProducto(dProducto);*/
			
	    	/*List<ListarCategorizacionDTO> lProductos;
			
			lProductos = servicioInternet.obtenerListadoProductos(tipoProducto, categoria, segmento, subsegmento, referencia);
			setListaProductos(lProductos);
			
			int sw = 0;
			/** productos */
			/*System.out.println( "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println( "PRODUCTOS DE LA CATEGORIA : " + dCategoria + "(" + kCategoria + ")" + ", DEL SEGMENTO : " + dSegmento + "(" + kSegmento + ")" + ", DEL SUBSEGMENTO : " + dSubsegmento + "(" + kSubsegmento + ")"+ " Y DE LA REFENCIA : " + dReferencia + "(" + kReferencia + ")");
			for (Iterator<?> iterator = lProductos.iterator(); iterator.hasNext();) {
				ListarCategorizacionDTO listaProductosDTO = (ListarCategorizacionDTO) iterator.next();
				System.out.println( "k_producto : " + listaProductosDTO.getK_nombre() + " || k_nombre : " + listaProductosDTO.getD_nombre());
				
				if ( sw == 0 ){
					setImagenMediana(listaProductosDTO.getK_nombre());
					setNombreImagen(listaProductosDTO.getD_nombre());
					setNombreImagenSegmento((kCategoria + kSegmento).trim().toLowerCase());
					setListaNormas(servicioInternet.obtenerNormas(listaProductosDTO.getK_nombre()));
					setListaFichaTecnica(servicioInternet.obtenerFichaTecnica(listaProductosDTO.getK_nombre()));
					sw = 1;
				}
			}
			System.out.println( "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			
		} catch (Exception e) { //BusinessException e
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return  "buscador";*/return null;
	}
	
	/**
	 * Método que carga las categorías en el buscador
	 */
	public Collection <SelectItem> getCargarCategorias() {

		String value = "[seleccione la categoría]";
		String label = "[seleccione la categoría]";
		
		try {
			
			if( elemCategorias == null ){
				
				elemCategorias = new ArrayList <SelectItem> ();
				elemCategorias.add( new SelectItem(value, label) );
				
				List<ListarCategorizacionDTO> lCategorias;
				lCategorias = servicioInternet.obtenerListadoCategorias(tipoProducto);
				
				for (Iterator<?> iterator = lCategorias.iterator(); iterator.hasNext();) {
					
					ListarCategorizacionDTO listaCategoriasDTO = (ListarCategorizacionDTO) iterator.next();
					value = listaCategoriasDTO.getK_nombre();
					label = listaCategoriasDTO.getD_nombre();
					elemCategorias.add( new SelectItem(value, label) );
				}
			}
			
		} catch (Exception e) { //BusinessException e
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return elemCategorias;
	}
	
	/**
	 * Método que carga los segmentos en el buscador
	 */
	public Collection <SelectItem> getCargarSegmentos() {

		String value = null;
		String label = null;
		
		try {
			
			if( elemSegmentos == null){
				
				elemSegmentos = new ArrayList <SelectItem> ();
				
				if(swCategoria == 1){
					
					value = "[seleccione el segmento]";
					label = "[seleccione el segmento]";
					elemSegmentos.add( new SelectItem(value, label) );
					
					List<ListarCategorizacionDTO> lSegmentos;
					lSegmentos = servicioInternet.obtenerListadoSegmentos(tipoProducto, categoria);
					
					for (Iterator<?> iterator = lSegmentos.iterator(); iterator.hasNext();) {
						
						ListarCategorizacionDTO listaSegmentosDTO = (ListarCategorizacionDTO) iterator.next();
						value = listaSegmentosDTO.getK_nombre();
						label = listaSegmentosDTO.getD_nombre();
						elemSegmentos.add( new SelectItem(value, label) );
						
					}
				}else{
					value = "seleccione 1° la categoria";
					label = "seleccione 1° la categoria";
					elemSegmentos.add( new SelectItem(value, label) );
				}
			}
		} catch (Exception e) { //BusinessException e
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return elemSegmentos;
	}
	
	/**
	 * Método que carga los subsegmentos en el buscador
	 */
	public Collection <SelectItem> getCargarSubsegmentos() {

		String value = null;
		String label = null;
		
		try {
			
			if( elemSubsegmentos == null){
				
				elemSubsegmentos = new ArrayList <SelectItem> ();
				
				if(swSegmento == 1){
					
					value = "[seleccione el subsegmento]";
					label = "[seleccione el subsegmento]";
					elemSubsegmentos.add( new SelectItem(value, label) );
					
					List<ListarCategorizacionDTO> lSubsegmentos;
					lSubsegmentos = servicioInternet.obtenerListadoSubsegmentos(tipoProducto, categoria, segmento);
					
					for (Iterator<?> iterator = lSubsegmentos.iterator(); iterator.hasNext();) {
						
						ListarCategorizacionDTO listaSubsegmentosDTO = (ListarCategorizacionDTO) iterator.next();
						value = listaSubsegmentosDTO.getK_nombre();
						label = listaSubsegmentosDTO.getD_nombre();
						elemSubsegmentos.add( new SelectItem(value, label) );
						
					}
				}else{
					value = "seleccione 1° el segmento";
					label = "seleccione 1° el segmento";
					elemSubsegmentos.add( new SelectItem(value, label) );
				}
			}
		} catch (Exception e) { //BusinessException e
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return elemSubsegmentos;
	}
	
	/**
	 * Método que carga las referencias en el buscador
	 */
	public Collection <SelectItem> getCargarReferencias() {

		String value = null;
		String label = null;
		
		try {
			
			if( elemReferencias == null){
				
				elemReferencias = new ArrayList <SelectItem> ();
				
				if(swSubsegmento == 1){
					
					value = "[seleccione la referencia]";
					label = "[seleccione la referencia]";
					elemReferencias.add( new SelectItem(value, label) );
					
					List<ListarCategorizacionDTO> lReferencias;
					lReferencias = servicioInternet.obtenerListadoReferencias(tipoProducto, categoria, segmento, subsegmento);
					
					for (Iterator<?> iterator = lReferencias.iterator(); iterator.hasNext();) {
						
						ListarCategorizacionDTO listaReferenciasDTO = (ListarCategorizacionDTO) iterator.next();
						value = listaReferenciasDTO.getK_nombre();
						label = listaReferenciasDTO.getD_nombre();
						elemReferencias.add( new SelectItem(value, label) );
						
					}
				}else{
					value = "seleccione 1° el subsegmento";
					label = "seleccione 1° el subsegmento";
					elemReferencias.add( new SelectItem(value, label) );
				}
			}
		} catch (Exception e) { //BusinessException e
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return elemReferencias;
	}
	
	/**
	 * Método que carga los productos en el buscador
	 */
	public Collection <SelectItem> getCargarProductos() {

		String value = null;
		String label = null;
		
		try {
			
			if( elemProductos == null){
				
				elemProductos = new ArrayList <SelectItem> ();
				
				if(swReferencia == 1){
					
					value = "[seleccione el producto]";
					label = "[seleccione el producto]";
					elemProductos.add( new SelectItem(value, label) );
					
					List<ListarCategorizacionDTO> lProductos;
					lProductos = servicioInternet.obtenerListadoProductos(tipoProducto, categoria, segmento, subsegmento, referencia);
					
					for (Iterator<?> iterator = lProductos.iterator(); iterator.hasNext();) {
						
						ListarCategorizacionDTO listaProductosDTO = (ListarCategorizacionDTO) iterator.next();
						value = listaProductosDTO.getK_nombre();
						label = listaProductosDTO.getD_nombre();
						elemProductos.add( new SelectItem(value, label) );
						
					}
				}else{
					value = "seleccione 1° la referencia";
					label = "seleccione 1° la referencia";
					elemProductos.add( new SelectItem(value, label) );
				}
			}
		} catch (Exception e) { //BusinessException e
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return elemProductos;
	}
	
	/**
	 * Método del evento onchange del select de las categorias
	 * metodo encargado de cambiar el select de los segmentos de acuerdo a la categoría seleccionada
	 */
	public void cambiarSegmentos(ValueChangeEvent value1) throws AbortProcessingException {
		
		/** se recibe el valor por pantalla de la categoría */	
		String selectedValue = (String) value1.getNewValue();
		
		System.out.println("Del metodo cambiarSegmentos  - categoría:" + selectedValue);
		
		/** se setea la variable categoría por el valor recibido */
		setCategoria(selectedValue);
		
		/** se setea el array para crear nuevamente el arreglo de segmentos */
		elemSegmentos = null;
		
		/** se setea el array para crear nuevamente el arreglo de subsegmentos */
		elemSubsegmentos = null;
		
		/** se setea el array para crear nuevamente el arreglo de referencias */
		elemReferencias = null;
		
		/** se setea el array para crear nuevamente el arreglo de productos */
		elemProductos = null;
		
		if(!selectedValue.contains("[") && !selectedValue.contains("1°")){
			/** se le indica a la aplicación que puede llamar el servicio para cargar los segmentos */
			swCategoria = 1;
		}
		else{
			/** se le indica a la aplicación que no puede llamar el servicio para cargar los segmentos */
			swCategoria = 0;
			/** se le indica a la aplicación que no puede llamar el servicio para cargar los subsegmentos */
			swSegmento = 0;
			/** se le indica a la aplicación que no puede llamar el servicio para cargar las refencias */
			swSubsegmento = 0;
			/** se le indica a la aplicación que no puede llamar el servicio para cargar los productos */
			swReferencia = 0;			
		}
		
		/** se invoca nuevamente el metodo para cargar los segmentos */
		getCargarSegmentos();
		/** se invoca nuevamente el metodo para cargar los subsegmentos */
		getCargarSubsegmentos();
		/** se invoca nuevamente el metodo para cargar las referencias */
		getCargarReferencias();
		/** se invoca nuevamente el metodo para cargar los productos */
		getCargarProductos();
	}
	
	/**
	 * Método del evento onchange del select de los segmentos
	 * metodo encargado de cambiar el select de los subsegmentos de acuerdo al segmento seleccionado
	 */
	public void cambiarSubsegmentos(ValueChangeEvent value1) throws AbortProcessingException {
		/** se recibe el valor por pantalla del segmento */	
		String selectedValue = (String) value1.getNewValue();
		
		System.out.println("Del metodo cambiarSubsegmentos  - segmento:" + selectedValue);
		
		/** se setea la variable segmento por el valor recibido */
		setSegmento(selectedValue);
		
		/** se setea el array para crear nuevamente el arreglo de subsegmentos */
		elemSubsegmentos = null;
		
		/** se setea el array para crear nuevamente el arreglo de referencias */
		elemReferencias = null;
		
		/** se setea el array para crear nuevamente el arreglo de productos */
		elemProductos = null;
		
		/** se le indica a la aplicación que no puede llamar el servicio para cargar los subsegmentos */
		swSegmento = 0;
		
		/** se le indica a la aplicación que no puede llamar el servicio para cargar las refencias */
		swSubsegmento = 0;
		
		/** se le indica a la aplicación que no puede llamar el servicio para cargar los productos */
		swReferencia = 0;	
		
		if(!selectedValue.contains("[") && !selectedValue.contains("1°")){
			/** se le indica a la aplicación que puede llamar el servicio para cargar los segmentos */
			swSegmento = 1;
		}
				
		/** se invoca nuevamente el metodo para cargar los subsegmentos */
		getCargarSubsegmentos();
		/** se invoca nuevamente el metodo para cargar las referencias */
		getCargarReferencias();
		/** se invoca nuevamente el metodo para cargar los productos */
		getCargarProductos();
	}
	
	/**
	 * Método del evento onchange del select de los subsegmentos
	 * metodo encargado de cambiar el select de las referencias de acuerdo al subsegmento seleccionado
	 */
	public void cambiarReferencias(ValueChangeEvent value1) throws AbortProcessingException {
		/** se recibe el valor por pantalla del subsegmento */	
		String selectedValue = (String) value1.getNewValue();
		
		System.out.println("Del metodo cambiarReferencias  - subsegmento:" + selectedValue);
		
		/** se setea la variable subsegmento por el valor recibido */
		setSubsegmento(selectedValue);
		
		/** se setea el array para crear nuevamente el arreglo de referencias */
		elemReferencias = null;
		
		/** se setea el array para crear nuevamente el arreglo de productos */
		elemProductos = null;
		
		/** se le indica a la aplicación que no puede llamar el servicio para cargar las refencias */
		swSubsegmento = 0;
		
		/** se le indica a la aplicación que no puede llamar el servicio para cargar los productos */
		swReferencia = 0;
		
		if(!selectedValue.contains("[") && !selectedValue.contains("1°")){
			/** se le indica a la aplicación que puede llamar el servicio para cargar los segmentos */
			swSubsegmento = 1;
		}
				
		/** se invoca nuevamente el metodo para cargar las referencias */
		getCargarReferencias();
		/** se invoca nuevamente el metodo para cargar los productos */
		getCargarProductos();
	}
	
	/**
	 * Método del evento onchange del select de las referencias
	 * metodo encargado de cambiar el select de los productos de acuerdo a la referencia seleccionada
	 */
	public void cambiarProductos(ValueChangeEvent value1) throws AbortProcessingException {
		/** se recibe el valor por pantalla de la referencia */	
		String selectedValue = (String) value1.getNewValue();
		
		System.out.println("Del metodo cambiarProductos  - referencia:" + selectedValue);
		
		/** se setea la variable referencia por el valor recibido */
		setReferencia(selectedValue);
		
		/** se setea el array para crear nuevamente el arreglo de productos */
		elemProductos = null;
		
		/** se le indica a la aplicación que no puede llamar el servicio para cargar los productos */
		swReferencia = 0;
		
		if(!selectedValue.contains("[") && !selectedValue.contains("1°")){
			/** se le indica a la aplicación que puede llamar el servicio para cargar los productos */
			swReferencia = 1;
		}
				
		/** se invoca nuevamente el metodo para cargar los productos */
		getCargarProductos();
	}
	
	
	/*
	public String obtenerImagen(String kCodigo){
		String servidor          = "172.16.1.91";
		String usuario           = "catalogo";
		String password          = "portal";
		String directorioOrigen  = "/portal/catalogoportal/";
		String directorioDestino = "C:/Oracle/Middleware/user_projects/domains/myPortalDomain/autodeploy/pep_documentos.ear/pep_documentos.war/imagenesCatalogo/";
		String imgBig            = "_grande.gif";
		String imgMedium         = "_mediano.gif";
		String imgSmall          = "_pequeno.gif";
		
		FtpConnect cn = FtpConnect.newConnect("ftp://" + servidor + directorioOrigen);
		cn.setUserName(usuario);
		cn.setPassWord(password);
		
		Ftp cl = new Ftp();

        try {
        	cl.connect(cn);
        	
        	CoFile file = new FtpFile(directorioOrigen + kCodigo + imgSmall, cl);
            //CoFile fileMedium = new FtpFile("/portal/catalogoportal079500_grande.gif", cl);
            //CoFile fileSmall = new FtpFile("/portal/catalogoportal079500_grande.gif", cl);
            System.out.println("From: " + file.toString());

            /* destination LocalFile home dir */
          /*CoFile to =
              new LocalFile(directorioDestino + kCodigo + imgSmall);
          System.out.println("To:   " + to.toString());

            /* download /Welcome file to home dir*/
           /* System.out.println("Load: " + CoLoad.copy(to, file));
        } catch (IOException e) {
            System.out.println(e);
        } finally { /* disconnect from server 
               	  * this must be always run */
            /*cl.disconnect();
        }
        
        return "hola";
	}*/
	
	/*public String probarLista(){
		List<String> lCategorias = new ArrayList<String>();
		try {
			lCategorias = servicioInternet.obtenerCategoriasSegmentos();
			for (Iterator iterator = lCategorias.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				System.out.println("***************   " + string );
				
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}*/
	
}
