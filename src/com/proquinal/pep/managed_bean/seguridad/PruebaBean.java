package com.proquinal.pep.managed_bean.seguridad;

import javax.ejb.EJB;

import com.proquinal.pep.entidades.seguridad.Usuario;
import com.proquinal.pep.negocioServicio.seguridad.IServicioSeguridad;


public class PruebaBean {
	
	@EJB(mappedName="ejb/ServicioSeguridad")
	private IServicioSeguridad servicioSeguridad; 
	
	public PruebaBean(){
		System.out.println("ingreso");
		//seguridad = (ISeguridad)EjbLocator.locate("ejb/Seguridad","com.proquinal.pep.negocioServicio.seguridad.ISeguridad");
	}
	
	private String usuario;

	public String getUsuario() {
		System.out.println("**** GetUsuario Before Using Service");
		// usuario = "810584"
		Usuario u = servicioSeguridad.conocerInfoUsuario(usuario);
		System.out.println("**** GetUsuario After Using Service");
		return u.getIdentificacion();
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
}
