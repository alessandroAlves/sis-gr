<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="container">
		<div class="hero-unit">
	<div class="row-fluid">
		<div class="span3"></div>
			<div class="span6" align="center">
				<div class="area">
					<c:if test="${not empty editar}">
					<form:form commandName="Cadastro" class="form-horizontal" action="editar"
						method="POST">
						<div class="heading">
							<h2>Editar!</h2>
							<hr>
						</div>					
						<td><input type="hidden" value="${editar.id}" name="id" id="id"/></td>
						<div class="control-group">
							<label class="control-label" for="nome">Nome</label>
							<div class="controls">
								<input type="text" name="nome" id="nome" value="${editar.nome}"></input> 
							</div>
						</div>
						<form:errors class="alert alert-error" path="nome"></form:errors>
						<div class="control-group">
						<br />
							<label class="control-label" for="nome">Sobrenome</label>
							<div class="controls">
								<input type="text" name="sobrenome" id="sobrenome"
									value="${editar.sobrenome}">
							</div>
						</div>
						<form:errors class="alert alert-error" path="sobrenome"></form:errors>
						<div class="control-group">
						<br />
							<label class="control-label" for="email">Email</label>
							<div class="controls">
								<input type="text" name="email" id="email"
									value="${editar.email}">
							</div>
						</div>
						<form:errors class="alert alert-error" path="email"></form:errors>
						<div class="control-group">
						<br />
							<label class="control-label" for="senha">Senha</label>
							<div class="controls">
								<input type="hidden" name="senha" id="senha"
									value="${editar.senha}">
							</div>
						</div>									
						<form:errors class="alert alert-error" path="senha"></form:errors>
            			<hr>
            			<table>
            				<tr>
            					<td>
									<button type="submit" class="btn btn-info">Editar</button>
								</td>
								<td style="width: 100px;" align="right">
									<a href="/sis-gr/agenda"><input type="button" class="btn btn-danger" name="cancelar" value="Cancelar" /></a>
								</td>
							</tr>
						</table>
						</form:form>
						</c:if>
				</div>
				<div class="span3"></div>
			</div>
		</div>
	</div>

</div>