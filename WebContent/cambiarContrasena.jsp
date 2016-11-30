<?xml version="1.0" encoding="utf-8" ?>
<jsp:root version="1.2" 
    xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:ice="http://www.icesoft.com/icefaces/component">
	
    <jsp:directive.page contentType="text/html;charset=utf-8" />

<f:view>
    <ice:outputDeclaration doctypeRoot="HTML"
			doctypePublic="-//W3C//DTD HTML 4.01 Transitional//EN"
			doctypeSystem="http://www.w3.org/TR/html4/loose.dtd" />
    <html>
	    <head>
		   <title>Cambiar contrasena</title>
           <link rel="stylesheet" type="text/css" href="./xmlhttp/css/rime/rime.css" />
		</head>
		<body>
		    <ice:form>
			
				
<table>
	<tr>
		<td>
			<ice:inputText value="#{seguridadBean.usuario}">  
			</ice:inputText>
		</td>
	</tr>
	<tr>
		<td>
			<ice:inputText value="#{seguridadBean.contrasena}">
			</ice:inputText>
		</td>
	</tr>
			<tr>
		<td>
			<ice:inputText value="#{seguridadBean.contrasenaNueva}">
			</ice:inputText>
		</td>
	</tr>
		<tr>
		<td>
		
		</td>
	</tr>
		<tr>
		<td>
			<ice:commandButton type="submit" value="Check Out" action="#{seguridadBean.cambiarContrasena}" /> 
		</td>
	</tr>
</table>
			
		    </ice:form>
		</body>
    </html>
</f:view>
</jsp:root>

	