package com.proquinal.pep.base;

import java.io.Serializable;
import java.security.Principal;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.security.auth.Subject;

import weblogic.security.Security;
import weblogic.security.principal.WLSUserImpl;

import com.proquinal.pep.entidades.seguridad.dto.UsuarioVO;
import com.proquinal.pep.fwk.exception.BusinessException;
import com.proquinal.pep.negocioServicio.seguridad.IServicioSeguridad;

/**
 * Clase utilizada para obtener la informacion del usuario sacada el contexto luego de haberse autenticado el usuario
* @author <a href="mailto:luismartinez@btech.com.co">Luis Gabriel Martinez Lopez</a>
* @version 1.0
* @FechaCreacion 26/03/2011
*/
public class UsuarioProquinalBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB(mappedName="ejb/ServicioSeguridad")
	private IServicioSeguridad servicioSeguridad;
	
	public UsuarioProquinalBean() {
		
	}
	/**
	 * Este metodo obtiene el nombre del usuario sacado a traves del subject principal
	 * @return String nombre del usuario
	 */
	private String obtenerUserName()
    {
        String username = "";
        Subject subject = Security.getCurrentSubject();
        Set<Principal> allPrincipals = subject.getPrincipals();
        for (Principal principal : allPrincipals)
        {
            if (principal instanceof WLSUserImpl)
            {
                username = principal.getName();
            }
        }   
        return username;
    }
   
   /**
    * Este metodo obtiene la informacion basica del usuario autenticado 
    * @return objeto de tipo UsuarioVO 
    */
    private UsuarioVO obtenerUsuarioVOSecurity()
    {
    	String username = obtenerUserName();
    	UsuarioVO u = null ;
		try {
			u = servicioSeguridad.obtenerInfoUsuario( username );
		} catch (BusinessException e) {
			e.printStackTrace();
		}
    	return u;
    }
   
    @PostConstruct
    public void obtenerUsuarioVO()
    {
    	UsuarioVO usuario;
        usuario = (UsuarioVO)getSession().get("UsuarioVo");
        if (usuario == null)
        {
            usuario = obtenerUsuarioVOSecurity();
            //Meterlo a la session.
            getSession().put("UsuarioVo", usuario);
        }
        System.out.println( usuario.getUsuario() + usuario.getTipoUsuario() + usuario.getContrasena());
    }
   
    /**
     * Este metodo obtiene la informacion basica del usuario desde la sesion
     * @return un objeto de UsuarioVO
     */
    public UsuarioVO getUsuarioAutenticado() {
        if (getSession().get("UsuarioVo") != null) {
            return (UsuarioVO) getSession().get("UsuarioVo");
        }
        return null;
    }
   /**
    * Este metodo obtiene la sesion del contexto actual
    * @return un map con la sesion obtenida dentro del contexto actual
    */
    public Map getSession()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Map sessionMap = facesContext.getExternalContext().getSessionMap();
        return sessionMap;
    }

}
