<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<table class="table-bordered table-hover">
  <thead>
	<tr align="center" height="45px" style="font-size: 14px; background-color:threedlightshadow;">
	  <td width="260px"><strong>Reunião</strong></td>
	  <td width="160px"><strong>Data</strong></td>
	  <td width="160px"><strong>Hora de início</strong></td>
	  <td width="210px"><strong>Local</strong></td>
	</tr>
  </thead>
  <c:forEach items="${reunioes}" var="reuniao">
  <tr align="center" height="40px" style="background-color:buttonhighlight;">
  	<td><a href="/sis-gr/editarReuniao/${reuniao.id}"><h5>${reuniao.nome}</h5></a></td>
    <fmt:formatDate pattern="dd-MM-yyyy" value="${reuniao.dia}" var="dia"/>
    <td><strong>${dia}</strong></td>
    <fmt:formatDate pattern="HH:mm" value="${reuniao.inicio}" var="inicio"/>
    <td>${inicio}</td>
    <td>${reuniao.local}</td>
  </tr>
  </c:forEach>
</table>