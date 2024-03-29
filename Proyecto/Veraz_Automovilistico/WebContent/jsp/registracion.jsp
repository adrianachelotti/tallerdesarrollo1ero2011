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
		function openPopupCenter(pageURL) {	

			var left = (screen.width/2)-(700/2);

			var top = (screen.height/2)-(600/2);

			settings='height=600,width=700,top='+top+',left='+left+',scrollbars=yes,toolbar=no';	

			var popup = window.open(pageURL,'BNA',settings);

		} 

		 

	$(document).ready(function(){

	



		$("select#localidad").attr('disabled', 'disabled');

		

		$("select#provincia").change(function(){

			var id_provincia = $("select#provincia").val();

					

			var xmlhttp;

			if (window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari

	  			xmlhttp=new XMLHttpRequest();

	  		}else{// code for IE6, IE5

	  			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");

	  		}

			

			xmlhttp.onreadystatechange=function(){

	  			if (xmlhttp.readyState==4 && xmlhttp.status==200){  			

		  			if(xmlhttp.responseText!=""){

		  				$("select#localidad").html("");

		    			$("select#localidad").append(xmlhttp.responseText);

						$("select#localidad").removeAttr('disabled');

					}else{

		  				$("select#localidad").html("");

		    			$("select#localidad").append('<option value="">seleccionar</option>');

						$("select#localidad").attr('disabled', 'disabled');				

					}

	    		}

	  		}

			

			xmlhttp.open("GET","consultaLocalidades.do?id_provincia="+id_provincia,true);

			xmlhttp.send();

			

		});

	

		$("#acepto_rubros").change(function(){

			if (!$('#acepto_rubros').is(':checked')) {

				$("input[name=rubro]").each(function() {

					$(this).attr('disabled', 'disabled');

					$(this).attr('checked', false);			

				});				

			}else{

				$("input[name=rubro]").each(function() {

					$(this).removeAttr('disabled');

								

				});				

			}

		});

	

		$('.error').css('visibility', 'hidden');

		

	    $(".btnSubmit").click(function(){

	      // validate and process form here

			var result = true;

	

			$('.error').css('visibility', 'hidden');

	

	  		var dni = $("input#dni").val();

	  		if(!isNumeroEntero(dni)||dni==""){

	  			$("span#dni_error").text('Ingrese N�mero de Documento (s�lo n�meros)');

				$("span#dni_error").css('visibility', 'visible');

			    result = false;				  		

	  		}else{

				if(!(dni.length==8||dni.length==7)){

					$("span#dni_error").text('N�mero de Documento inv�lido');

					$("span#dni_error").css('visibility', 'visible');

			    	result = false;						  							

				}

	  		}	

	

	  		var mail = $("input#mail").val();

			if(mail!=""){

				if(!isMail(mail)){

					$("span#mail_error").css('visibility', 'visible');

			        result = false;						

				}

			}

			

			if (!$("input:checked[name=sexo]").is(':checked')){

				$("span#sexo_error").css('visibility', 'visible');

		        result = false;						

			}

			

	  		var fecha_nac_dia = $("input#fecha_nac_dia").val();

	  		var fecha_nac_mes = $("input#fecha_nac_mes").val();		

	  		var fecha_nac_anio = $("input#fecha_nac_anio").val();

	

			if(fecha_nac_dia!=""||fecha_nac_mes!=""||fecha_nac_anio!=""){

				if(!isDateSeparatedFields(fecha_nac_dia,fecha_nac_mes,fecha_nac_anio)){

					$("span#date_error").text('La fecha ingresada no es v�lida');				

					$("span#date_error").css('visibility', 'visible');

					result = false;

				}

			}else{

				$("span#date_error").text('Ingrese su fecha de nacimiento');			

				$("span#date_error").css('visibility', 'visible');

				result = false;

			}

	

			var prov = $("select#provincia").val();

			if (prov == "") {

				$("span#provincia_error").css('visibility', 'visible');

		        $("select#provincia").focus();

		        result = false;

			}

	

			var loc = $("select#localidad").val();

			if (loc == "") {

				$("span#localidad_error").css('visibility', 'visible');

		        $("select#localidad").focus();

		        result = false;

			}

	

			if ($('#acepto_rubros').is(':checked')) {

				var selectedRubros = new Array();

				$("input:checked[name=rubro]").each(function() {selectedRubros.push($(this).val());});

				if (selectedRubros.length == 0) {

					$("span#rubro_error").css('visibility', 'visible');

			        result = false;

				}	

			}

	

			var selectedTarjetas = new Array();

			$("input:checked[name=tarjeta]").each(function() {selectedTarjetas.push($(this).val());});

			if (selectedTarjetas.length == 0) {

				$("span#tarjeta_error").css('visibility', 'visible');

		        result = false;

			}

	

			if (!$('#acepto').is(':checked')) {

				$("span#acepto_error").css('visibility', 'visible');

				result = false;

			}

	

			return result;

	

	    });

	  });

	</script>

	
	<BODY onLoad="clearForms()" onUnload="clearForms()">
	
		

		<table border="0" cellpadding="0" cellspacing="0" align="center" width="780">
			<tr>
				<td>
					<div id="contenido">
						<div id="tit">Registro Web (nuevos usuarios)</div>
						<div id="form">
							<form name="frm_reclutamiento" action="" method="post">
								<table border="0" cellpadding="0" cellspacing="0" width="780" class="table_form">
									<tr>
										<td colspan="4" style="border:0px;"><br/>
										Complete el formulario  .<br/><br/>						
										</td>
									</tr>
									<tr>
										<td colspan="4" >
											<span class="alert">*</span> Dato Obligatorio
										</td>
									</tr>									
									<tr>
										<td>
											Nombre 
										</td>
										<td colspan="2">
											<input type="text" name="nombre" maxlength="100" autocomplete="off" size="26"/>
										</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>
											Apellido
										</td>
										<td colspan="2">
											<input type="text" name="apellido" maxlength="100" autocomplete="off" size="26"/>
										</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>
											Tipo y N� de Documento <span class="alert">*</span>
										</td>
										<td colspan="2">
											<select name="tipo_doc" id="tipo_doc">
											  <option value="D" selected="selected">DNI</option>
											  <option value="V">Libreta C�vica</option>
											  <option value="E">Libreta de Enrolamiento</option>
											  <option value="P">Pasaporte</option>
											  <option value="C">C�dula de Identidad</option>
											</select>&nbsp;<input type="text" name="dni" id="dni" maxlength="8" autocomplete="off" size="26"/>
										</td>
										<td><span class="error" id="dni_error"></span></td>
									</tr>
									<tr>
										<td>
											E-mail
										</td>
										<td colspan="2">
											<input type="text" name="mail" id="mail" maxlength="50" autocomplete="off" size="26"/>
										</td>
										<td><span class="error" id="mail_error">La direcci�n de correo no es v�lida</span></td>
									</tr>
									<tr>
										<td>
											Sexo <span class="alert">*</span>
										</td>
										<td colspan="2">
											<label><input type="radio" name="sexo" value="M" autocomplete="off">Masculino</label><br/>
											<label><input type="radio" name="sexo" value="F" autocomplete="off">Femenino</label>
										</td>
										<td><span class="error" id="sexo_error">Indique su sexo</span></td>
									</tr>
									<tr>
										<td>
											Fecha de nacimiento <span class="alert">*</span>
										</td>
										<td colspan="2">
											<input type="text" name="fecha_nac_dia" id="fecha_nac_dia"  autocomplete="off" maxlength="2" size="1"/>&nbsp;/&nbsp;<input type="text" autocomplete="off" name="fecha_nac_mes" id="fecha_nac_mes" maxlength="2" size="1"/>&nbsp;/&nbsp;<input autocomplete="off" type="text" name="fecha_nac_anio" id="fecha_nac_anio" maxlength="4" size="2"/>
										&nbsp;dd/mm/aaaa (Ej: 02/08/1973)</td>
										<td><span class="error"id="date_error"></span></td>
									</tr>
									<tr>
										<td>
											Provincia (de residencia actual)<span class="alert">*</span>
										</td>
										<td colspan="2">
											<select name="provincia" id="provincia" autocomplete="off">
												<option value="">seleccionar</option>
												
													<option value="BUENOS AIRES">BUENOS AIRES</option>
												
													<option value="CAPITAL FEDERAL">CAPITAL FEDERAL</option>
												
													<option value="CATAMARCA">CATAMARCA</option>
												
													<option value="CHACO">CHACO</option>
												
													<option value="CHUBUT">CHUBUT</option>
												
													<option value="CORDOBA">CORDOBA</option>
												
													<option value="CORRIENTES">CORRIENTES</option>
												
													<option value="ENTRE RIOS">ENTRE RIOS</option>
												
													<option value="FORMOSA">FORMOSA</option>
												
													<option value="JUJUY">JUJUY</option>
												
													<option value="LA PAMPA">LA PAMPA</option>
												
													<option value="LA RIOJA">LA RIOJA</option>
												
													<option value="MENDOZA">MENDOZA</option>
												
													<option value="MISIONES">MISIONES</option>
												
													<option value="NEUQUEN">NEUQUEN</option>
												
													<option value="RIO NEGRO">RIO NEGRO</option>
												
													<option value="SALTA">SALTA</option>
												
													<option value="SAN JUAN">SAN JUAN</option>
												
													<option value="SAN LUIS">SAN LUIS</option>
												
													<option value="SANTA CRUZ">SANTA CRUZ</option>
												
													<option value="SANTA FE">SANTA FE</option>
												
													<option value="SANTIAGO DEL ESTERO">SANTIAGO DEL ESTERO</option>
												
													<option value="TIERRA DEL FUEGO">TIERRA DEL FUEGO</option>
												
													<option value="TUCUMAN">TUCUMAN</option>
												
											</select>
										</td>
										<td><span class="error" id="provincia_error">Seleccione su provincia</span></td>
									</tr>
									<tr>
										<td>
											Localidad (de residencia actual)<span class="alert">*</span>
										</td>
										<td colspan="2">
											<select name="localidad" id="localidad" autocomplete="off">
												<option value="">seleccionar</option>
											</select>
										</td>
										<td><span class="error" id="localidad_error">Seleccione su localidad</span></td>
									</tr>
									<tr>
										<td colspan="4" style="border:0px;">
											Seleccione el servicio que desea contratar:<span class="alert">*</span>
										</td>
									</tr>
									<tr>
										<td valign="top" width="220">&nbsp;</td>
										<td valign="top" >
											<label><input type="checkbox" name="tarjeta" value="1" autocomplete="off"/> Plan Premium</label><br/>
											<label><input type="checkbox" name="tarjeta" value="2" autocomplete="off"/> Consultas Puntuales</label><br/>
											<label><input type="checkbox" name="tarjeta" value="3" autocomplete="off"/> Plan Mixto</label><br/>											
										</td>
										
										<td width="130"><span class="error" id="tarjeta_error">Seleccione al menos un servicio</span></td>
									</tr>				
									<tr>
										<td colspan="4" style="border:0px;">
											<label><input type="checkbox" autocomplete="off" name="acepto_rubros" id="acepto_rubros" checked="checked"/>Indique de que rubro es su empresa :</label><br/>
										</td>
									</tr>				
									<tr>
										<td valign="top">
											&nbsp;
										</td>
										<td valign="top">
										
												<label id="label_rubro"><input type="checkbox" autocomplete="off" name="rubro" value="1"/> Aseguradora</label><br/>
										
												<label id="label_rubro"><input type="checkbox" autocomplete="off" name="rubro" value="2"/> Empresa de Logistica</label><br/>
										
												<label id="label_rubro"><input type="checkbox" autocomplete="off" name="rubro" value="3"/> Empresa de transporte</label><br/>
										
											
										
										</td>
										<td valign="top">
											
												<label id="label_rubro"><input type="checkbox" autocomplete="off" name="rubro" value="6"/> Empresa de finanzas</label><br/>
										
												<label id="label_rubro"><input type="checkbox" autocomplete="off" name="rubro" value="7"/> Prestadora</label><br/>
										
												<label id="label_rubro"><input type="checkbox" autocomplete="off" name="rubro" value="8"/> Otros</label><br/>										
											
										
										</td>
										<td><span class="error" id="rubro_error">Debe seleccionar al menos un rubro</span></td>
									</tr>
									<tr>
										<td colspan="3">
											<label><input type="checkbox" autocomplete="off" name="acepto" id="acepto"/> Acepto los t�rminos y condiciones  <a href="#" onClick='javascript:openPopupCenter("contrato.htm");'>(ver t�rminos y condiciones)</a></label><br/>
										</td>
										<td><span class="error" id="acepto_error">Debe aceptar los t�rminos y condiciones para continuar</span><br/></td>
									</tr>
									<tr>
										<td colspan="4" align="center">
											<input class="btnSubmit" type="submit" value="Aceptar"/>
										</td>
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
