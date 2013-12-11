<div class="span12 hero-unit">
  <p class="text-success offset4">${sucesso}</p>	
  <div class="span4"></div>
  <div class="span4">
	<form action="/sis-gr/login" method="post">		
	<h3 class="offset3">Fazer Login</h3><hr>     
	<p class="text-error offset2">${msg}</p> 
	     
	<label>Email</label>
	<input class="input-xlarge" type="text" name="email" required/><br/>
	   	 
	<label>Senha</label>
	<input class="input-xlarge" type="password" name="senha" required><hr/>
	   	 
	<input type="submit" class="btn btn-large btn-primary offset4" value="Entrar"/>
	</form>
  </div>
</div>