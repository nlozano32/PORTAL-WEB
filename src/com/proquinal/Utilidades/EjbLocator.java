package com.proquinal.Utilidades;

import java.io.Serializable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Clase que permite localizar los EJB
 * @author <a href="mailto:nolbertoj@gmail.com">Nolberto Jaimes</a>
 * @version 1.0
 * @FechaCreacion DEC 17, 2010
 * @FechaModificacion ${date}
 */

public class EjbLocator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * Metodo se encarga de localizar el EJB
	 * @param name EJB a buscar
	 * @return Object
	 */
	public static Object locate(String name, String fullclassname) 
	{
		System.out.println("Ingreso locator");
		try 
			{
				Context context = new InitialContext();
				System.out.println("Obtiene contexto ");
				Object o = context.lookup(name + "#" + fullclassname);
				System.out.println( o.toString() );
				return o;
			} 
		catch (NamingException e) 
		{
			 e.printStackTrace() ;
			//log.info("Error inicializando el ejb " + name);
		}
		System.out.println("Salio locator");
		return null;
		
	}
	
}