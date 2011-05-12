<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <title>Identificación</title>
</head>
<body>
      <h2><s:property value="mensaje" /></h2>
<div>
Por favor, identifíquese.
</div>
<s:form action="LoginAction">
  <s:textfield    label="Identificador"   name="usuario.nombre"/>
  <s:password     label="Contraseña"           name="usuario.password" />
  <s:submit       value="Login"                align="center"/>
  <a href="jsp/registracion.jsp">registrese</a>
</s:form>

</body>
</html>