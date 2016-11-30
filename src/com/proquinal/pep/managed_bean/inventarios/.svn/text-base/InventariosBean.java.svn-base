package com.proquinal.pep.managed_bean.inventarios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.proquinal.pep.entidades.inventario_pedidos.Bodega;
import com.proquinal.pep.fwk.exception.BusinessException;
import com.proquinal.pep.negocioServicio.inventarios.IServicioInventarios;

public class InventariosBean {
	
	@EJB(mappedName="ejb/ServicioInventarios")
	private IServicioInventarios servicioInventarios;
	

	public InventariosBean(){
		logger.info("Ingreso InventariosBean");
	}
	
	private String pais;
	
	private String ciudad;
	
	private String zona;
	
	private String bodega;
	
	private String referencia;
	
	private String producto;
	
	private String codigo;
	
	private String codigoStock;
	
	private String nombreTecnico;
	
	private String nombreComercial;
	
	
	/**
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * @param pais the pais to set
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}
/**
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	/**
	 * @return the zona
	 */
	public String getZona() {
		return zona;
	}
	/**
	 * @param zona the zona to set
	 */
	public void setZona(String zona) {
		this.zona = zona;
	}
	/**
	 * @return the bodega
	 */
	public String getBodega() {
		return bodega;
	}
	/**
	 * @param bodega the bodega to set
	 */
	public void setBodega(String bodega) {
		this.bodega = bodega;
	}
	/**
	 * @return the referencia
	 */
	public String getReferencia() {
		return referencia;
	}
	/**
	 * @param referencia the referencia to set
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	/**
	 * @return the producto
	 */
	public String getProducto() {
		return producto;
	}
/**
	 * @param producto the producto to set
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the codigoStock
	 */
	public String getCodigoStock() {
		return codigoStock;
	}
	/**
	 * @param codigoStock the codigoStock to set
	 */
	public void setCodigoStock(String codigoStock) {
		this.codigoStock = codigoStock;
	}
	/**
	 * @return the nombreTecnico
	 */
	public String getNombreTecnico() {
		return nombreTecnico;
	}
	/**
	 * @param nombreTecnico the nombreTecnico to set
	 */
	public void setNombreTecnico(String nombreTecnico) {
		this.nombreTecnico = nombreTecnico;
	}
	/**
	 * @return the nombreComercial
	 */
	public String getNombreComercial() {
		return nombreComercial;
	}

	/**
	 * @param nombreComercial the nombreComercial to set
	 */
	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}
	
	/**
	 * @return the lProductos
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<SelectItem> getlBodegas() {
		
		lBodegas = new ArrayList <SelectItem> ();
		try {
			List<Bodega> bodegas = servicioInventarios.obtenerTodasBodegas();
			for (Iterator iterator =  bodegas.iterator(); iterator.hasNext();) {
				Bodega b = (Bodega) iterator.next();
				lBodegas.add(new SelectItem( String.valueOf( b.getCodBodega() ), String.valueOf( b.getDesBodega() ) ));
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return lBodegas;
	}
	

	//private List<Bodega> lBodegas = new ArrayList<Bodega>();
	private ArrayList <SelectItem> lBodegas = null;
	private static final Logger logger = Logger.getLogger(InventariosBean.class);
 
}
