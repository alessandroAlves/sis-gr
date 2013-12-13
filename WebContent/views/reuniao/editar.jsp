<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<form:form method="put" name="form" action="/sis-gr/editarReuniao" class="form-horizontal" commandName="reuniao"> 
  <input type="hidden" name="id" value="${reuniao.id}"/>
  
  <ul class="nav nav-tabs">
    <li class="active"><a href="#tab1" data-toggle="tab">Reunião</a></li>
    <li><a href="#tab2" data-toggle="tab">Participantes</a></li>
    <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown" href="#">Ações<b class="caret"></b></a>
      <ul class="dropdown-menu">
        <li><a href="/sis-gr/enviarReuniao/${reuniao.id}"><i class="icon-envelope"></i> Enviar convites</a></li>
        <li><a href="/sis-gr/listaTarefas/${reuniao.id}"><i class="icon-list"></i> Lista de tarefas</a></li>
        <li><a href="/sis-gr/downloadPDF/${reuniao.id}"><i class="icon-print"></i> Imprimir</a></li>
        <li class="divider" />
        <li><a href="/sis-gr/removerReuniao/${reuniao.id}"><i class="icon-trash"></i> Remover reunião</a></li>
      </ul>
    </li>
    <div class="btn-group offset4">
      <button type="submit" class="btn btn-info">Salvar Alterações</button>
      <a href="/sis-gr/agenda" class="btn btn-warning">Cancelar</a>    
    </div>
  </ul>
  
  <div class="tab-content">
    
    <div class="tab-pane active" id="tab1">
      <div class="span7" style="background-color:buttonhighlight; border-radius: 10px;">  	
	    <table style="margin: 15px" class="table-condensed">
	      <tr>
	        <td width="83px"><label><strong>Título</strong></label></td>
	     	<td><input name="nome" type="text" placeholder="título da reunião ..." class="input-xlarge" value="${reuniao.nome}" required/><br /></td>
	      </tr>
	      <tr>
	      	<fmt:formatDate pattern="yyyy-MM-dd" value="${reuniao.dia}" var="dia"/>
	     	<td><label><strong>Dia</strong></label></td>
	     	<td><input name="dia" type="date" class="input-large" value="${dia}" required/></td>
	      </tr>
	      <tr>
	        <fmt:formatDate pattern="HH:mm" value="${reuniao.inicio}" var="inicio"/>
	     	<td><label><strong>Início</strong></label></td>
	     	<td><input name="inicio" type="time" class="input-medium" value="${inicio}" required/></td>
	      </tr>
	      <tr>
	        <fmt:formatDate pattern="HH:mm" value="${reuniao.fim}" var="fim"/>
	        <td><label><strong>Fim</strong></label></td>
	     	<td><input name="fim" type="time" class="input-medium" value="${fim}" required/><br /></td>
	      </tr>
	      <tr>
	        <td><label><strong>Local</strong></label></td>
	     	<td><input name="local" type="text" class="input-xlarge" value="${reuniao.local}" required/><br /></td>
	      </tr>
	      <tr>
	     	<td valign="top"><label><strong>Descrição</strong></label></td>
	     	<td><textarea name="descricao" placeholder="Descreva os detalhes da sua reunião ...	" rows="15" class="span12" >${reuniao.descricao}</textarea></td> 
	      </tr>
	    </table>
	  </div>
	   
	  <div class="span5" style="background-color:buttonhighlight; border-radius: 10px;">
	    <table style="margin: 10px">
	        <tr height="40px" style="padding: 10px">
	          <th valign="top" align="left" width="225px"><p><i class="icon-user"></i> Participante da Reunião</p></th>
	          <th valign="top" width="75px">Tarefas</th>
	        </tr>
	      <c:forEach items="${reuniao.contatos}" var="contato">
	        <tr height="40px">
	          <td>
	            <p>
	              <c:if test="${contato.tarefas.size() eq 0}">
	                <span class="badge badge-info">0</span>
	              </c:if>
	              <c:if test="${contato.tarefas.size() != 0}">
	                <span class="badge badge-info">${contato.tarefas.size()}</span>
	              </c:if>
	              ${contato.nome} ${contato.sobrenome}
	            </p>
	          </td>
	          <td align="center"><a class="btn btn-info" href="/sis-gr/tarefas/${contato.id}" data-toggle="tooltip" title="Clique para adicionar tarefas."><i class="icon-pencil"></i></a></td>
	        </tr>
	      </c:forEach>
	    </table>
	  </div> 
   </div>
	
    <div class="tab-pane" id="tab2">    
      <table class="table table-bordered table-condensed">
      	<tr class="info">
      	  <td width="240px"><strong>Nome</strong></td>
      	  <td width="240px"><strong>Sobrenome</strong></td>
          <td width="240px"><strong>Email</strong></td>		 	      	
      	</tr>
      </table>	
      <table class="table-condensed" rel="list">
        <c:forEach items="${reuniao.contatos}" var="contato" varStatus="status"> 
          <tr>
            <td width="250px"><input type="text" placeholder="Nome" name="contatos[${status.index}].nome" value="${contato.nome}" required/></td>
            <td width="250px"><input type="text" placeholder="Sobrenome" name="contatos[${status.index}].sobrenome" value="${contato.sobrenome}" required/></td>
            <td width="250px"><input type="email" placeholder="Email" name="contatos[${status.index}].email" value="${contato.email}" required/></td>		      	
          </tr>
        </c:forEach>
      </table><hr>
      <a href="#" class="addMore btn btn-success offset4"><i class="icon-plus"></i> Adicionar outro</a>
    </div>      
  </div> 

</form:form>



