<?xml version="1.0" encoding="utf-8" ?>
<jsp:root version="1.2" 
  xmlns:jsp="http://java.sun.com/JSP/Page"
   xmlns:f="http://java.sun.com/jsf/core"
  xmlns:h="http://java.sun.com/jsf/html"
  xmlns:ice="http://www.icesoft.com/icefaces/component">
<jsp:directive.page contentType="text/html;charset=utf-8" />
<f:view>
  <ice:outputDeclaration doctypeRoot="HTML" doctypePublic="-//W3C//DTD HTML 4.01 Transitional//EN" doctypeSystem="http://www.w3.org/TR/html4/loose.dtd"/>
  <html>
    <head>
     <title><ice:outputText value="ICEfaces, Ajax for Java EE" /></title>
     <ice:outputStyle href="./xmlhttp/css/rime/rime.css" />
    </head>
    <body>

     <ice:outputText value="Thank you for using ICEfaces." />
     <ice:form>
     	<table>
     		<tr>
     			<td>
     				<ice:outputText value="Correo electronico (*):" />
     			</td>
     			<td>
     				<ice:inputText value="#{formulariosBean.email}" />
     			</td>
     		</tr>
     		<tr>
     			<td>
     				<ice:outputText value="Adjunte aqui su hoja de vida (*):" />
     			</td>
     			<td>
     				<ice:inputFile id="inputFileComponent" actionListener="#{formulariosBean.uploadActionListener}" />
        				<ice:message for="inputFileComponent" />
        
        			<ice:outputText value="#{formulariosBean.archivoActual.physicalPath}" /> 	
     			</td>
     		</tr>
     	
     	</table>
         

     </ice:form>

    </body>
  </html>
</f:view>
</jsp:root>