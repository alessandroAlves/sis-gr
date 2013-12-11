
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:url var="editarImg" value="/resources/img/editar.png" />
<c:url var="deletarImg" value="/resources/img/deletar.png" />
<form data-ajax method="POST" id="form"
	action="/sis-gr/contato/adicionar">
	<c:if test="${not empty editar}">
		<table class="table">
			<thead>Editar contato</thead>
			
			<tr>
				<td><input type="hidden" value="${editar.id}" name="id" id="id"/></td>
				
				<td><input placeholder="Nome" type='text' name="nome" id="nome"
					value="${editar.nome}" /></td>
				<td><input type='text' placeholder="Sobrenome" name="sobrenome"
					id="sobrenome" value="${editar.sobrenome}" /></td>
				<td><input type='text' placeholder="E-mail" name="email"
					id="email" value="${editar.email}" /></td>
				<td align="right"><button class='btn btn-info' type="submit"
						value="Salvar">Salvar</button></td>
			</tr>
		</table>
	</c:if>
	<c:if test="${empty editar}">
		<table class="table">
			<thead>Cadastrar novo contato</thead>
			<tr>
				<td><input placeholder="Nome" type='text' name="nome" id="nome" /></td>
				<td><input type='text' placeholder="Sobrenome" name="sobrenome"
					id="sobrenome" /></td>
				<td><input type='text' placeholder="E-mail" name="email"
					id="email" /></td>
				<td align="right"><button class='btn btn-info' type="submit"
						value="Salvar">Salvar</button></td>
			</tr>
		</table>
	</c:if>
</form>
<hr />
<table class="table">
	<tr>
		<td><b>Nome</b></td>
		<td><b>E-mail</b></td>
		<td width="50"><b>Editar</b></td>
		<td width="50"><b>Excluir</b></td>
	</tr>

	<c:forEach items="${contatos}" var="contato">
		<c:url var="
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:url var="editarImg" value="/resources/img/editar.png" />
<c:url var="deletarImg" value="/resources/img/deletar.png" />
<form data-ajax method="POST" id="form"
	action="/sis-gr/contato/adicionar">
	<c:if test="${not empty editar}">
		<table class="table">
			<thead>Editar contato</thead>
			
			<tr>
				<td><input type="hidden" value="${editar.id}" name="id" id="id"/></td>
				
				<td><input placeholder="Nome" type='text' name="nome" id="nome"
					value="${editar.nome}" /></td>
				<td><input type='text' placeholder="Sobrenome" name="sobrenome"
					id="sobrenome" value="${editar.sobrenome}" /></td>
				<td><input type='text' placeholder="E-mail" name="email"
					id="email" value="${editar.email}" /></td>
				<td align="right"><button class='btn btn-info' type="submit"
						value="Salvar">Salvar</button></td>
			</tr>
		</table>
	</c:if>
	<c:if test="${empty editar}">
		<table class="table">
			<thead>Cadastrar novo contato</thead>
			<tr>
				<td><input placeholder="Nome" type='text' name="nome" id="nome" /></td>
				<td><input type='text' placeholder="Sobrenome" name="sobrenome"
					id="sobrenome" /></td>
				<td><input type='text' placeholder="E-mail" name="email"
					id="email" /></td>
				<td align="right"><button class='btn btn-info' type="submit"
						value="Salvar">Salvar</button></td>
			</tr>
		</table>
	</c:if>
</form>
<hr />
<table class="table">
	<tr>
		<td><b>Nome</b></td>
		<td><b>E-mail</b></td>
		<td width="50"><b>Editar</b></td>
		<td width="50"><b>Excluir</b></td>
	</tr>

	<c:forEach items="${contatos}" var="contato">
		<c:url var="editarUrl" value="/contato/editar?editar=${contato.id}" />
		<c:url var="deletarUrl" value="deletar?id=${contato.id}" />
		<tr>
			<td>${contato.nome}</td>
			<td>${contato.email}</td>
			<td><a href="${editarUrl}"><img src="${editarImg}"></img></a></td>
			<td><a href="${deletarUrl}"><img src="${deletarImg}"></img></a></td>
		</tr>
	</c:forEach>
</table>editarUrl" value="/contato/editar?editar=${contato.id}" />
		<c:url var="deletarUrl" value="deletar?id=${contato.id}" />
		<tr>
			<td>${contato.nome}</td>
			<td>${contato.email}</td>
			<td><a href="${editarUrl}"><img src="${editarImg}"></img></a></td>
			<td><a href="${deletarUrl}"><img src="${deletarImg}"></img></a></td>
		</tr>
	</c:forEach>
</table>