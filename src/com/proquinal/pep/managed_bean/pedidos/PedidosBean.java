package com.proquinal.pep.managed_bean.pedidos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.proquinal.pep.entidades.inventario_pedidos.Ciudade;
import com.proquinal.pep.entidades.inventario_pedidos.Despacho;
import com.proquinal.pep.entidades.inventario_pedidos.DetallePedido;
import com.proquinal.pep.entidades.inventario_pedidos.Pais;
import com.proquinal.pep.entidades.inventario_pedidos.Pedido;
import com.proquinal.pep.entidades.inventario_pedidos.Sucursale;
import com.proquinal.pep.fwk.exception.BusinessException;
import com.proquinal.pep.negocioServicio.pedidos.IServicioPedidos;

public class PedidosBean {
	
	@EJB(mappedName="ejb/ServicioPedidos")
	private IServicioPedidos servicioPedidos;
	
	public PedidosBean() {
		logger.info("Ingreso PedidosBean");
		List<Pedido> l = new ArrayList<Pedido>();
		Pedido pedido = new Pedido();
		Sucursale s = new Sucursale();
		pedido.setOrdenCompra("E37066");
		pedido.setNumeroPedido("1298");
//		pedido.setFechaPedido( new Date(2010, 5, 23));
//		pedido.setFechaRegistro(new Date(2010, 5, 24));
		pedido.setCantidadItems( new BigDecimal(3));
		s.setNombreSucursal("PELHAM");
		pedido.setSucursale(s);
		l.add(pedido);
		Pedido pedido1 = new Pedido();
		Sucursale s1 = new Sucursale();
		pedido1.setOrdenCompra("160328");
		pedido1.setNumeroPedido("1345");
//		pedido1.setFechaPedido( new Date(2010, 5, 29));
//		pedido1.setFechaRegistro(new Date(2010, 5, 29)); 
		pedido1.setCantidadItems( new BigDecimal(3));
		s1.setNombreSucursal("VEVAY");
		pedido1.setSucursale(s1);
		
		l.add(pedido1);
		
		setlPedidos(l);
	}
	
	// parametros o filtros de entrada para el modelo de seguimiento de pedidos
	/**
	 * numero de pedido cliente
	 */
	private String numeroPedido;
	/**
	 * numero de orden de compra
	 */
	private String ordenCompra;
	/**
	 * numero de Stock producto
	 */
	private String numeroStock;
	/**
	 * Nombre comercial producto
	 */
	private String nombreComercial;
	/**
	 * Codigo o identificador del producto en la BD
	 */
	private String codigoProducto;
	/**
	 * Fecha especifica a partir de la cual se desean consultar ordenes de pedido de un cliente
	 */
	private Date fechaEspecifica;
	/**
	 * Nombre del canal o comercializadora
	 */
	private String nombreComercializadora;
	/**
	 * identificador o codigo del cliente
	 */
	private String codigoCliente;
	/**
	 * Nombre del cliente
	 */
	private String nombreCliente;
	/**
	 * Ciudad
	 */
	private String ciudad;
	/**
	 * Pais
	 */
	private String pais;
	/**
	 * listado de pedidos de un cliente especifico
	 */
	private List<Pedido> lPedidos;
	/**
	 * listado de productos dado un Id de cliente y una orden de pedido
	 */
	private List<DetallePedido> lDetallePedidos;
	/**
	 * listado de despachos para un producto dado un Id de cliente y una orden de pedido
	 */
	private List<Despacho> lDespachos;

	// ------------------------------------------------------------------------------------
	// Filtros pedidos areas Internas
	// ------------------------------------------------------------------------------------

	private String canal;
	
	private String cliente;
	
	/**
	 * @return the numeroPedido
	 */
	public String getNumeroPedido() {
		return numeroPedido;
	}

	/**
	 * @param numeroPedido the numeroPedido to set
	 */
	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	/**
	 * @return the ordenCompra
	 */
	public String getOrdenCompra() {
		return ordenCompra;
	}
	/**
	 * @param ordenCompra the ordenCompra to set
	 */
	public void setOrdenCompra(String ordenCompra) {
		this.ordenCompra = ordenCompra;
	}

	/**
	 * @return the numeroStock
	 */
	public String getNumeroStock() {
		return numeroStock;
	}

	/**
	 * @param numeroStock the numeroStock to set
	 */
	public void setNumeroStock(String numeroStock) {
		this.numeroStock = numeroStock;
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
	 * @return the codigoProducto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * @param codigoProducto the codigoProducto to set
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	/**
	 * @return the fechaEspecifica
	 */
	public Date getFechaEspecifica() {
		return fechaEspecifica;
	}

	/**
	 * @param fechaEspecifica the fechaEspecifica to set
	 */
	public void setFechaEspecifica(Date fechaEspecifica) {
		this.fechaEspecifica = fechaEspecifica;
	}

	/**
	 * @return the nombreComercializadora
	 */
	public String getNombreComercializadora() {
		return nombreComercializadora;
	}

	/**
	 * @param nombreComercializadora the nombreComercializadora to set
	 */
	public void setNombreComercializadora(String nombreComercializadora) {
		this.nombreComercializadora = nombreComercializadora;
	}

	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return the nombreCliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * @param nombreCliente the nombreCliente to set
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
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
	 * @return the lPedidos
	 */
	public List<Pedido> getlPedidos() {
		return lPedidos;
	}

	/**
	 * @param lPedidos the lPedidos to set
	 */
	public void setlPedidos(List<Pedido> lPedidos) {
		this.lPedidos = lPedidos;
	}
	
	/**
	 * @return the lDetallePedidos
	 */
	public List<DetallePedido> getlDetallePedidos() {
		return lDetallePedidos;
	}

	/**
	 * @param lDetallePedidos the lDetallePedidos to set
	 */
	public void setlDetallePedidos(List<DetallePedido> lDetallePedidos) {
		this.lDetallePedidos = lDetallePedidos;
	}

	/**
	 * @return the lDespachos
	 */
	public List<Despacho> getlDespachos() {
		return lDespachos;
	}

	/**
	 * @param lDespachos the lDespachos to set
	 */
	public void setlDespachos(List<Despacho> lDespachos) {
		this.lDespachos = lDespachos;
	}
	
	
	
	/**
	 * @return the canal
	 */
	public String getCanal() {
		return canal;
	}

	/**
	 * @param canal the canal to set
	 */
	public void setCanal(String canal) {
		this.canal = canal;
	}

	/**
	 * @return the cliente
	 */
	public String getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String obtenerPedidosPorFecha(){
		
		logger.info("Ingreso obtenerPedidosPorFecha");
		try {
			servicioPedidos.obtenerPedidosClienteFecha(codigoCliente, fechaEspecifica);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	/**
	 * @return the listaCiudades
	 */
	public List<SelectItem> getListaCiudades() {
		
		List<Ciudade> lCiudades = new ArrayList<Ciudade>();
		for (Ciudade ciudade : lCiudades) {
			listaCiudades.add( new SelectItem( String.valueOf( ciudade.getIdCiudad() ), ciudade.getNombreCiudad() ));
		}
		return listaCiudades;
	}

	/**
	 * @param listaCiudades the listaCiudades to set
	 */
	public void setListaCiudades(List<SelectItem> listaCiudades) {
		this.listaCiudades = listaCiudades;
	}

	/**
	 * @return the listaPaises
	 */
	public List<SelectItem> getListaPaises() {
		List<Pais> lPais = new ArrayList<Pais>();
		for (Pais pais : lPais) {
			listaCiudades.add( new SelectItem( String.valueOf( pais.getIdPais() ), pais.getDesPais() ));
		}
		return listaPaises;
	}

	/**
	 * @param listaPaises the listaPaises to set
	 */
	public void setListaPaises(List<SelectItem> listaPaises) {
		this.listaPaises = listaPaises;
	}
	

	private List<SelectItem> listaCiudades = new ArrayList<SelectItem>();
	private List<SelectItem> listaPaises = new ArrayList<SelectItem>();


	private static final Logger logger = Logger.getLogger(PedidosBean.class);
	
}
