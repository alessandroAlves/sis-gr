<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div class="row-fluid hero-unit span12">
  <div class="span1"></div>
  <div class="span5">
	<form:form method="post" action="/sis-gr/registro" commandName="usuario"> 
	<input type="hidden" name="id" value="${usuario.id}"/>
	
	  <p class="lead offset3">Casdastre-se</p><hr>
	  
	  <label>Nome</label>
	  <input name="nome" type="text" class="input-xlarge" required/><br>
	 		    
	  <label>Sobrenome</label>
	  <input name="sobrenome" type="text" class="input-xlarge" required/><br>
	  	
	  <label>Email</label>
	  <input name="email" type="email" class="input-xlarge" required/><br>			
	  	
	  <label>Senha</label>
	  <input name="senha" type="password" class="input-xlarge" required/><hr>    
	  
	  <input type="submit" class="btn btn-large btn-primary offset1" value="Confirmar Cadastro" />		
	</form:form>
  </div>
  <div class="span1"></div>
  	<div class="span4">
  	  <p class="lead offset1">Agilize seu cadastro</p><hr>
  	  <br/>
  	  <p>Importe os dados da sua conta do google e faça seu cadastro sem complicações!</p>
  	  <br />
      <hr>
      <a href="google" class="btn btn-large btn-primary">Cadastrar com o Google!</a>
    </div> 
  </div>