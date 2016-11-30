package com.proquinal.pep.managed_bean.ycontent;

import java.util.Map;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.proquinal.pep.entidades.ycontent.dto.ContenidosDTO;
import com.proquinal.pep.fwk.exception.BusinessException;
import com.proquinal.pep.negocioServicio.ycontent.IServicioYcontent;

public class YcontentBean {

	private static final Logger logger = Logger.getLogger(YcontentBean.class);
	
	@EJB(mappedName="ejb/ServicioYcontent")
	private IServicioYcontent servicioYcontent;
	
	/**
	 * Id del contenido
	 */
	private String id;
	
	private ContenidosDTO  contenidosDTO;
	
	public YcontentBean() {
		logger.info("Ingreso a la clase YcontentBean");
		setId("9");
	}
	
	/**
	 * Este metodo obtiene el id del contenido
	 * @return
	 */
	public String getId() {
		return id;
	}
	/**
	 * Este metodo permite establecer el id del contenido
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}	
	
	public String test(){
		logger.info("YcontentBean:test");
		servicioYcontent.test();
		return null;
		
	}

	public String obtenerContenido(){
		logger.info("YcontentBean:obtenerContenido");
		
		try {
			ContenidosDTO cDTO = servicioYcontent.obtenerContenido(id);
			if ( cDTO != null ) {
				setContenidosDTO( cDTO );
			}
				
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "success";
		
	}
	
	@SuppressWarnings("unchecked")
	public String actionObtenerContenido(){
		logger.info( ">>> actionObtenerContenido created!" );
		String action = null;
    	try {
    		FacesContext context = FacesContext.getCurrentInstance(); 
	    	Map map = context.getExternalContext().getRequestParameterMap();
	    	String idContenido = (String) map.get("idContenido");
	    	System.out.println(" idContenido " + idContenido );
		// invocar metodo que itera
		 ContenidosDTO contenidosDTO = servicioYcontent.obtenerContenido(idContenido);
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
	    	
    	return action;
	}	
	/**
	 * 
	 * @return
	 */
	public ContenidosDTO getContenidosDTO() {
		return contenidosDTO;
	}

	public void setContenidosDTO(ContenidosDTO contenidosDTO) {
		this.contenidosDTO = contenidosDTO;
	}
	
}
