<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	
<form:form method="put" action="/sis-gr/editarContato" class="form-horizontal" commandName="contato"> 
  <form:hidden path="id" />
  <form:hidden path="nome" />
  <form:hidden path="sobrenome" />
  <form:hidden path="email" />
  
    <ul class="nav nav-tabs">
    <li class="active"><a href="#" data-toggle="tab">Adicionar tarefas para ${contato.nome}</a></li>
    <div class="btn-group offset4">
      <button type="submit" class="btn btn-info">Salvar Alterações</button>
      <a href="/sis-gr/editarReuniao/${reuniao}" class="btn btn-warning">Cancelar</a>    
    </div>
  </ul>
  
  <table class="table table-bordered">
	<tr class="info">
		<td class="span10"><strong>Descrição</strong></td>
		<td class="span4"><strong>Prazo</strong></td>
	</tr>
  </table>
  <table class="table-condensed" rel="list">
	<c:forEach items="${contato.tarefas}" var="tarefa" varStatus="status">
		<input type="hidden" name = "tarefas[${status.index}].status" value="${tarefa.status}"/>
		<tr>
			<td class="span" ><input type="text" class="input-xxlarge" placeholder="Descreva a tarefa ..." name="tarefas[${status.index}].descricao" value="${tarefa.descricao}" required/></td>
			<td align="right" class="span4">
			  <fmt:formatDate pattern="yyyy-MM-dd" value="${tarefa.prazo}" var="date" />	
			  <input type='date' class="input-medium" name="tarefas[${status.index}].prazo" value="${date}" required>
			</td>
		</tr>
	</c:forEach>
  </table>
  <hr>
  <a href="#" style="width: 300px" class="addMore btn btn-block btn-success offset3"><i class="icon-plus"></i> Adicionar nova tarefa</a> 
</form:form>	



  
