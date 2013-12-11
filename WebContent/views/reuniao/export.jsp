<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div>
	<form data-ajax method="POST" id="form"
		action="/sis-gr/export/download">

		<table class="table">
			<thead>Deseja Exportar sua agenda
			</thead>

			<input type="hidden" value="${usuario.id}" name="id" id="id" />
			<hr>
			<div align="center">
			<td align="center"><button class='btn btn-info' type="submit"
					value="Export">Confirmar</button> <a href="/sis-gr/agenda">
					<input align="center" type="button" class="btn btn-danger" name="voltar"
					value="Voltar" /></a></td>
			</div>
		</table>

	</form>
</div>