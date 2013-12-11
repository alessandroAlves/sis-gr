<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<table class="table-bordered table-hover" >
  <thead>
	<tr align="center" height="45" style="font-size: 14px; background-color:threedlightshadow;">
	  <th width="100">Status</th>
	  <th width="260">Tarefa</th>
	  <th width="100">Prazo</th>
	  <th width="150">Responsável</th>
	  <th width="150">Reunião</th>
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
	  <td>${tarefa.descricao}</td>
	  <fmt:formatDate pattern="dd-MM-yyyy" value="${tarefa.prazo}" var="prazo"/>
	  
	  <td><strong>${prazo}</strong></td>
	  <td>${tarefa.contato.nome}</td>
	  <td>${tarefa.reuniao.nome}</td>
	</tr>
  </c:forEach>
</table>