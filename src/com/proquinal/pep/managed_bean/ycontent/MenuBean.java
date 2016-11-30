/**
 * 
 */
package com.proquinal.pep.managed_bean.ycontent;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import org.apache.log4j.Logger;

import com.proquinal.pep.entidades.ycontent.dto.MenusDTO;
import com.proquinal.pep.fwk.exception.BusinessException;
import com.proquinal.pep.negocioServicio.ycontent.IServicioMenu;

/**
 * @author Administrador
 *
 */
public class MenuBean {

	private static final Logger logger = Logger.getLogger(MenuBean.class);


	
	@EJB(mappedName="ejb/ServicioMenu")
	private IServicioMenu servicioMenu;
	
	public MenuBean() {
		logger.info("Ingreso a la clase MenuBean");
		setIdHeader("1");
		setIdSide("2");
	}
	
	/**
	 * Postcontructor
	 */
	 @PostConstruct
	public void postConstructor(){
		System.out.println("Ingreso al m�todo: postConstructor()");
		servicioMenu.MenuHeaderInternet(MENU_HORIZONTAL);
	}
	
	
	/**
	 * Id del menu
	 */
	private String id;
	private String idTipo;
	private String idHeader;
	private String idSide;
	private List<MenusDTO> menusDTO;
	private List<MenusDTO> menuHeader;
	private static String MENU_VERTICAL = "2";
	private static String MENU_HORIZONTAL = "1";
	
	
	/**
	 * Este metodo obtiene el id del menu
	 * @return
	 */
	public String getId() {
		return id;
	}
	/**
	 * Este metodo permite establecer el id del menu
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	public String getIdTipo() {
		return idTipo;
	}
	/**
	 * Este metodo permite establecer el idTipoMenu 
	 * @param id
	 */
	public void setIdTipo(String idTipo) {
		this.idTipo= idTipo;
	}

	public String getIdHeader() {
		return idHeader;
	}
	public void setIdHeader(String idHeader) {
		this.idHeader = idHeader;
	}
	public String getIdSide() {
		return idSide;
	}
	public void setIdSide(String idSide) {
		this.idSide = idSide;
	}
	public String obtenerMenu() throws BusinessException{
		logger.info("MenuBean:obtenerMenu");
		List<MenusDTO> mDTO=servicioMenu.obtenerMenu(MENU_VERTICAL,"0");
		if ( mDTO != null ) {
			setMenusDTO( mDTO );
		}
		return "success";
		
	}
	
	public String obtenerMenuHeader() throws BusinessException{
		logger.info("MenuBean:obtenerMenu");
		List<MenusDTO> mDTO=servicioMenu.obtenerMenu(MENU_HORIZONTAL,"0");
		//List<MenusDTO> mDTO=servicioMenu.obtenerMenu(id);
		if ( mDTO != null ) {
			setMenuHeader( mDTO );
		}
	
		return "Informaci�n corporativa"; 
		
	}
	
	public List<MenusDTO> getMenuHeader() {
		return menuHeader;
	}

	public void setMenuHeader(List<MenusDTO> menuHeader) {
		this.menuHeader = menuHeader;
	}


	
	/**
	 * 
	 * @return
	 */
	public List<MenusDTO> getMenusDTO() {
		return menusDTO;
	}

	public void setMenusDTO(List<MenusDTO> mDTO) {
		this.menusDTO = mDTO;
	}
	
	/**Muestra los padres en el cabezote de la vista Internet*/
	
	
	public boolean getMenuHeaderInternet() {
		logger.info("MenuBean:MenuHeaderInternet");
		List<MenusDTO> menu = servicioMenu.MenuHeaderInternet(MENU_HORIZONTAL);
		//String nombre= mHI.getNombre();
		//S/ystem.out.println(mHI);
		if ( menu != null ) {
			setMenuHeader( menu );
		}
	
		return true; 	
	}
	
	
}
