<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<ul class="nav nav-tabs">
  <li class="active"><a href="#tab1" data-toggle="tab">Todas as tarefas</a></li>
  <li><a href="#tab2" data-toggle="tab">Tarefas fora de prazo!</a></li>
</ul>

<div class="tab-content">
  <div class="tab-pane active" id="tab1">
	<table id="tabela" class="table-bordered table-hover span12" >
	  <thead>
		<tr align="center" height="45" style="font-size: 14px; background-color:threedlightshadow;">
		  <th width="100">Status</th>
		  <th width="260">Tarefa</th>
		  <th width="100">Prazo</th>
		  <th width="100">Responsável</th>
		  <th width="130">Reunião</th>
		</tr>
	  <tr style="background-color:highlight;" valign="bottom" height="50"> 
	  	<th ><input style="width: 100px" type="text" id="txtColuna1" ></th>
	  	<th ><input style="width: 260px" type="text" id="txtColuna2" ></th>
	  	<th ><input style="width: 100px" type="text" id="txtColuna3" ></th>
	  	<th ><input style="width: 100px" type="text" id="txtColuna4" ></th>
	  	<th ><input style="width: 135px" type="text" id="txtColuna5" ></th>
	  </tr>
	  </thead>
	  
	  
	  <c:forEach items="${tarefas}" var="tarefa">
		<tr align="center" height="40" style="background-color:buttonhighlight; padding: 10px">
		  <td>
		    <c:if test="${tarefa.status eq false}">
		    	<button class="btn btn-warning btn-small" disabled>PENDENTE</button>
		    </c:if>
		    <c:if test="${tarefa.status eq true}">
		    	<button class="btn btn-info btn-small" disabled>CONCLUÍDA</button>
		    </c:if>
		  </td>
		  <td><a href="/sis-gr/tarefas/${tarefa.contato.id}" data-toggle="tooltip" title="Clique para editar a tarefa.">${tarefa.descricao}</a></td>
		  <fmt:formatDate pattern="dd-MM-yyyy" value="${tarefa.prazo}" var="prazo"/>
		  <td><strong>${prazo}</strong></td>
		  <td>${tarefa.contato.nome}</td>
		  <td>${tarefa.reuniao.nome}</td>
		</tr>
	  </c:forEach>
	</table>
  </div>
  <div class="tab-pane" id="tab2">
  <table id="tabela" class="table-bordered table-hover span12" >
	  <thead>
		<tr align="center" height="45" style="font-size: 14px; background-color:threedlightshadow;">
		  <th width="100">Status</th>
		  <th width="260">Tarefa</th>
		  <th width="100">Prazo</th>
		  <th width="100">Responsável</th>
		  <th width="130">Reunião</th>
		</tr>
	  <tr style="background-color:highlight;" valign="bottom" height="50"> 
	  	<th ><input style="width: 100px" type="text" id="txtColuna1" ></th>
	  	<th ><input style="width: 260px" type="text" id="txtColuna2" ></th>
	  	<th ><input style="width: 100px" type="text" id="txtColuna3" ></th>
	  	<th ><input style="width: 100px" type="text" id="txtColuna4" ></th>
	  	<th ><input style="width: 135px" type="text" id="txtColuna5" ></th>
	  </tr>
	  </thead>
	  
	  
	  <c:forEach items="${tarefas2}" var="tarefa">
		<tr align="center" height="40" style="background-color:buttonhighlight; padding: 10px">
		  <td>
		    <button class="btn btn-danger btn-small" disabled>PENDENTE</button>
		  </td>
		  <td><a href="/sis-gr/tarefas/${tarefa.contato.id}" data-toggle="tooltip" title="Clique para editar a tarefa.">${tarefa.descricao}</a></td>
		  <fmt:formatDate pattern="dd-MM-yyyy" value="${tarefa.prazo}" var="prazo"/>
		  <td><strong>${prazo}</strong></td>
		  <td style="color:red;">${tarefa.contato.nome}</td>
		  <td>${tarefa.reuniao.nome}</td>
		</tr>
	  </c:forEach>
	</table>
  </div>
</div>