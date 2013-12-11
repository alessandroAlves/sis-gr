<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table class="table table-ordered">
  
<c:forEach items="${reuniao.contatos}" var="contato">
  <tr>
	<td><button class="btn btn-block btn-default" disabled><strong><i class="icon-user"></i> ${contato.nome} ${contato.sobrenome}</strong></button>
	  <table class="table table-bordered table-ordered table-condensed table-hover" style="background-color:buttonhighlight">
	    <c:forEach items="${contato.tarefas}" var="tarefa">
	     <tr class="info">	
	       <td valign="bottom" class="span6"><a href="#" data-toggle="tooltip" title="Clique para editar a tarefa.">${tarefa.descricao}</a></td>
	       <td width="80px"><strong><fmt:formatDate pattern="dd-MM-yyyy" value="${tarefa.prazo}"/></strong></td>
	       <c:if test="${tarefa.status eq false}">
	       	<td width="78px"><button class="btn btn-warning btn-small" disabled>PENDENTE</button></td>
			<td width="40px"><a href="/sis-gr/finalizaTarefa/${tarefa.id}" class="btn btn-success btn-small" data-toggle="tooltip" 
				title="Clique para finalizar a tarefa."><i class="icon-ok"></i></a></td>		       
	       </c:if>
	       <c:if test="${tarefa.status eq true}">
	       	<td width="78px"><button class="btn btn-primary btn-small" disabled>CONCLUÍDA</button></td>
	       	<td width="40px"><a href="#" class="btn btn-success btn-small disabled"><i class="icon-ok"></i></a></td>
	       </c:if>
	        <td width="40px"><a href="/sis-gr/removerTarefa/${tarefa.id}" class="btn btn-danger btn-small" data-toggle="tooltip" 
				title="Clique para remover a tarefa."><i class="icon-trash"></i></a></td>
	     </tr>
	    </c:forEach>
	  </table>
	</td>  
  </tr>
</c:forEach>  
</table>