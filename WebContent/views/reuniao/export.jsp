<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="hero-unit">
  <form data-ajax method="POST" id="form" action="/sis-gr/download">
   	<input type="hidden" value="${usuario.id}" name="id" id="id" />
      
    <h3>Deseja exportar a sua agenda?</h3>
    <p class="lead">O Sis-Gr irá disponilibizar um arquivo  CSV para download.</p>
    <p>Clique <a href="http://pt.wikipedia.org/wiki/Comma-separated_values">aqui</a> para saber mais sobre a extensão CSV.</p>
    	
    <button class='btn btn-info' type="submit" value="Export">Confirmar</button>
    <a href="/sis-gr/agenda">
	<input align="center" type="button" class="btn btn-danger" name="voltar" value="Voltar" /></a>
   
	</form>
</div>