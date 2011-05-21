		<!--header.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
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
						<div id="form">
							<form name="frm_reclutamiento" action="" method="post">
								<table border="0" cellpadding="0" cellspacing="0" width="780" class="table_form">
									<tr>
										<td colspan="4" style="border:0px;"><br/>
										Ingrese Usuario y password  .<br/><br/>						
										</td>
									</tr>
									<tr>
										<td colspan="4" >
											<span class="alert">*</span> Dato Obligatorio
										</td>
									</tr>									
									<tr>
										<td>
											Usuario 
										</td>
										<td colspan="2">
											<input type="text" name="nombre" maxlength="100" autocomplete="off" size="26"/>
										</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>
											Password
										</td>
										<td colspan="2">
											<input type="text" name="apellido" maxlength="100" autocomplete="off" size="26"/>
										</td>
										<td>&nbsp;</td>
									</tr>
								
									<tr>
										<td colspan="4" align="center">
											<input class="btnSubmit" type="submit" value="Aceptar"/>
										</td>
									</tr>
											<tr>
										<td>
											Si usted no tiene usuario,
										</td>
										<td colspan="2">
											<a href="registracion.jsp">Registrese</a>
										</td>
										<td>&nbsp;</td>
									</tr>									
								</table>
								<br/>
							</form>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</BODY>
</HTML>
