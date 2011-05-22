<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page import="org.springframework.security.ui.AbstractProcessingFilter" %>
<%@ page import="org.springframework.security.ui.webapp.AuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.AuthenticationException" %>


<HTML>
	<HEAD>
		<TITLE>Nuevos Rumbos</TITLE>
		<META NAME="Generator" CONTENT="EditPlus">
		<META NAME="Author" CONTENT="">
		<META NAME="Keywords" CONTENT="">
		<META NAME="Description" CONTENT="">
		<meta http-equiv="X-UA-Compatible" content="IE=5">		
		<link rel="stylesheet" type="text/css" href="style.css" /> 
	</HEAD>

	<div id="header">
		<img width="380" height="220" src="img/logonr.jpg"/>
	</div>



	<script>

		function clearForms(){
		  var i;
		  for (i = 0; (i < document.forms.length); i++) {
		    document.forms[i].reset();
		  }
		}

	

	</script>

	
	<BODY onLoad="clearForms()" onUnload="clearForms()">
	
		<table border="0" cellpadding="0" cellspacing="0" align="center" width="780">
			<tr>
				<td>
					<div id="contenido">
						<div id="tit">Inicio de Session</div>


<div class="section">
    <table class="search">
        <tr><th>USERNAME</th><th>PASS</th><th>ROLE</th></tr>
        <tr><td>david</td><td>newyork</td><td>ROLE_USER,ROLE_ADMIN</td></tr>
        <tr><td>alex</td><td>newjersey</td><td>ROLE_USER</td></tr>
        <tr><td>tim</td><td>illinois</td><td>ROLE_USER</td></tr>
    </table>        
</div>

<div class="section">
	<c:if test="${not empty param.login_error}">
		<div class="errors">
			ERROR: <%= ((AuthenticationException) session.getAttribute(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY)).getMessage() %>
		</div>
	</c:if>
</div>

<div id="contenido" class="section">
	<form name="f" action="<c:url value="/loginProcess" />" method="post">
		<fieldset>
			<div class="field">
				<div class="label"><label for="j_username">User:</label></div>
				<div class="output">
					<input type="text" name="j_username" id="j_username" <c:if test="${not empty param.login_error}">value="<%= session.getAttribute(AuthenticationProcessingFilter.SPRING_SECURITY_LAST_USERNAME_KEY) %>"</c:if> />
				</div>
			</div>
			<div class="field">
				<div class="label"><label for="j_password">Pass:</label></div>
				<div class="output">
					<input type="password" name="j_password" id="j_password" />
				</div>
			</div>
			<div class="field">
				<div class="label"><label for="remember_me">Remember me </label></div>
				<div class="output">
					<input type="checkbox" name="_spring_security_remember_me" id="remember_me" />
				</div>
			</div>
            <div class="form-buttons">
                <div class="button">
                    <input name="submit" id="submit" type="submit" value="Login" />
                </div>
            </div>
		</fieldset>
	</form>
	
	
	
</div>
</BODY>
