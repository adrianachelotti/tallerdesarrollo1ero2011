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
			      <li><a href="#">Contrato</a></li>
			  </ul>
			</td>
		</tr>
	</table>
	<form>
		<table>
			<tr height="25"><td>Contrato de servicio</td></tr>		
			<tr height="25"><td><textarea cols="80" rows="10"><%=contrato %></textarea></td></tr>
			<tr><td><input type="radio" value="si" name="aceptacion">Acepto </td></tr>
			<tr><td><input type="radio" value="on" name="aceptacion" onclick="">No Acepto </td></tr>

			<tr><td><a href="contrato_servicios.jsp" style="color: red;"><span na>Next</span></a></td><td ><a href="jsp/login.jsp" style="color: red;">Cancelar</a></td>	</tr>		
		</table>
		
	</form>
</body>
</html>