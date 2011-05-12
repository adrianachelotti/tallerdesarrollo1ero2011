<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%String contrato = "Este es un contrato legal. Usted esta entregando toda la plata a esta entidad....." ;%>
<head>
<link type="text/css"  rel="stylesheet" href="estilo.css">
</head>
<body id="fondo">
	<table width="1000" >
		<tr>
			<td>
		      <ul id="navigation">
			      <li><a href="#">Contratación de Servicios</a></li>
			  </ul>
			</td>
		</tr>
	</table>
	<form>
		<table>
			<tr><td><input type="radio" value="1" name="aceptacion">Servicio basico </td></tr>
			<tr><td><input type="radio" value="2" name="aceptacion">Servicio Personalizado </td></tr>
			<tr><td><input type="radio" value="3" name="aceptacion" >Servicio Premiun</td></tr>

			<tr><td><a href="contrato_servicio.jsp" style="color: red;"><span na>Next</span></a></td><td ><a href="jsp/login.jsp" style="color: red;">Cancelar</a></td>	</tr>		
		</table>
		
	</form>
</body>
</html>