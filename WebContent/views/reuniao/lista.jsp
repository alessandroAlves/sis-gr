<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<table id="tabela" class="table-bordered table-hover">
  <thead>
	<tr align="center" height="45px" style="font-size: 14px; background-color:threedlightshadow;">
	  <th width="260px"><strong>Reunião</strong></th>
	  <th width="160px"><strong>Data</strong></th>
	  <th width="160px"><strong>Hora de início</strong></th>
	  <th width="210px"><strong>Local</strong></th>
	</tr>
	 <tr style="background-color:highlight;" valign="bottom" height="50"> 
	  	<th ><input style="width: 260px" type="text" id="txtColuna1" ></th>
	  	<th ><input style="width: 120px" type="text" id="txtColuna2" ></th>
	  	<th ><input style="width: 120px" type="text" id="txtColuna3" ></th>
	  	<th ><input style="width: 210px" type="text" id="txtColuna4" ></th>

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