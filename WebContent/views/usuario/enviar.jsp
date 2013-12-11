<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="container">
	<div class="hero-unit">
		<div class="row-fluid">
			<div class="span3"></div>
			<div class="span6" align="center">
				<div class="area">
					<form class="form-horizontal" action="senha"
						method="POST">
						<div class="heading">
							<h2>Esqueci minha senha</h2>
							<hr>
						</div>
						<div class="control-group">
							<label class="control-label" for="email">Email</label>
							<div class="controls">
								<input type="text" name="email" id="email" placeholder="email" value="${email}">
							</div>
						</div>
						<hr>
						<div>
								<button type="submit" class="btn btn-success">Enviar por email</button>
							
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>