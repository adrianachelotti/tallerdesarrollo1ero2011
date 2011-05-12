<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css"  rel="stylesheet" href="estilo.css">
</head>
<body>
	<table width="1000" >
		<tr>
			<td>
		      <ul id="navigation">
			      <li><a href="#">Registracion</a></li>
			  </ul>
			</td>
		</tr>
	</table>
	<form>
		<table>
			<tr height="25"><td>Nombre</td><td><input type="text" name="nombre"></td>	</tr>		
			<tr><td>Apellido</td><td><input type="text" name="apellido"></td>	</tr>		
			<tr><td>CUIT</td><td><input type="text" name="cuit"></td>	</tr>		
			<tr><td>Direccion</td><td><input type="text" name="direccion"></td>	</tr>
			<tr><td>Localidad</td><td><input type="text" name="localidad"></td>	</tr>
			<tr><td>Partido</td><td><input type="text" name="partido"></td>	</tr>
			<tr><td>Provincia</td><td><input type="text" name="provincia"></td>	</tr>
			<tr><td><a href="contrato.jsp" style="color: red;">Next</a></td><td ><a href="jsp/login.jsp" style="color: red;">Cancelar</a></td>	</tr>		
		</table>
		
	</form>
</body>
</html>