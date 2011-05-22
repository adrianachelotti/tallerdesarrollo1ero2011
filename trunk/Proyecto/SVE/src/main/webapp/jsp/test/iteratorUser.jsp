<%@ taglib prefix="s" uri="/struts-tags" %>
 
<html>
<head>
</head>
 
<body>
<h1>Struts 2 Iterator tag example</h1>
Obtiene las personas que se encuentran en la tabla PERSONS. 
<h3>Simple Iterator</h3>
<ol>
<table>
<s:iterator value="usuarios">
     <tr>
      <td><s:property value="username"/></td>
      <td><s:property value="password"/></td>
      <td><s:property value="authorities"/></td>
   </tr>
</s:iterator>
</table>
</ol>
 
<h3>Iterator with IteratorStatus</h3>

<table>
<s:iterator value="auorizaciones" status="usuariosStatus">
   <tr>
      <s:property value="authority"/>
      
   </tr>
</s:iterator>
</table>
 
</body>
</html>