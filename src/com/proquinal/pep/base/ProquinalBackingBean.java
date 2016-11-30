package com.proquinal.pep.base;

import java.io.Serializable;
import java.security.Principal;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.security.auth.Subject;

import com.proquinal.pep.entidades.seguridad.Usuario;
import com.proquinal.pep.entidades.seguridad.dto.UsuarioVO;
import com.proquinal.pep.negocioServicio.seguridad.IServicioSeguridad;

import weblogic.security.Security;
import weblogic.security.principal.WLSUserImpl;

public class ProquinalBackingBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB(mappedName="ejb/ServicioSeguridad")
	private IServicioSeguridad servicioSeguridad; 

	public ProquinalBackingBean(){
		
	}
	
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
   
   
    private Usuario obtenerUsuarioVOSecurity()
    {
    	String username = obtenerUserName();
    	Usuario u = servicioSeguridad.conocerInfoUsuario(username);
    	return u;
    }
   
    @PostConstruct
    public void obtenerUsuarioVO()
    {
        Usuario usuario;
        usuario = (Usuario)getSession().get("UsuarioVo");
        if (usuario == null)
        {
            usuario = obtenerUsuarioVOSecurity();
            //Meterlo a la session.
            getSession().put("UsuarioVo", usuario);
        }
        System.out.println( usuario.getUsuario() + usuario.getTipoUsuario() + usuario.getContrasena());
    }
   
    public Usuario getUsuarioAutenticado() {
        if (getSession().get("UsuarioVo") != null) {
            return (Usuario) getSession().get("UsuarioVo");
        }
        return null;
    }
   
    public Map getSession()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        //Map requestMap = facesContext.getExternalContext().getRequestMap();
        Map sessionMap = facesContext.getExternalContext().getSessionMap();
        return sessionMap;
    }
}
