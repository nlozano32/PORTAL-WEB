package com.proquinal.pep.managed_bean.contabilidad;

public class ContabilidadAction {
	/**
	 * Muestra un mensaje en la interfaces
	 */
	public static final String ERROR_VALIDACION_BIMESTRES 		= "El Bimestre Final seleccionado no debe ser inferior al Bimestre Inicial";
	/**
	 * Mensaje que indica que cuando se genere el certificado ReteFuente no existe informacion asociada en la BD
	 */
	public static final String NO_EXISTE_CERTIFICADO_RETEFUENTE = "No se pudo generar el certificado ReteFuente, no existe informacion asociado en el sistema";
	/**
	 * Mensaje que indica que cuando se genere el certificado ReteIca no existe informacion asociada en la BD
	 */
	public static final String NO_EXISTE_CERTIFICADO_RETEICA 	= "No se pudo generar el certificado ReteIca, no existe informacion asociado en el sistema";
	/**
	 * Mensaje que indica que cuando se genere el certificado ReteIva no existe informacion asociada en la BD
	 */
	public static final String NO_EXISTE_CERTIFICADO_RETEIVA 	= "No se pudo generar el certificado ReteIva, no existe informacion asociado en el sistema";
	/**
	 * Mensaje que indica que cuando se genere el certificado ReteIva o ReteIca no esta activa para su generacion.
	 */
	public static final String ERROR_FECHA_EMISION_CERTIFICADO	= "El certificado no se puede generar porque la fecha para este bimestre no esta activa para su generacion";
}
