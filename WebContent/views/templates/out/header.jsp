<div class="navbar">

	<div class="row-fluid">
		<div class="span12">
			<div class="span3">
				<h1 class="muted">SisGR</h1>
			</div>
			<div class="span8">
				<h2 class="muted">Gerenciamento de Reuniões</h2>
			</div>
		</div>
	</div>

	<div class="navbar-inner">
		<div class="container-fluid">
			<a class="btn btn-navbar" data-toggle="collapse"
				data-target=".nav-collapse"> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
			</a> 
			<div class="nav-collapse collapse">
				<ul class="nav">
					<li><a href="/sis-gr/inicio"><i class="icon-home"></i>Home</a></li>
					<li class="divider-vertical"></li>
				</ul>
				<ul class="nav pull-right">
					<li><a href="/sis-gr/registro">Cadastre-se</a></li>
					<li class="divider-vertical"></li>
					<li class="dropdown"><a class="dropdown-toggle" href="#"
						data-toggle="dropdown">Logar<strong class="caret"></strong></a>
						
						<div class="dropdown-menu"	style="padding: 15px; padding-bottom: 0px;">
							
							
							<!-- FORM LOGIN -->
							<form method="post" action="/sis-gr/login" accept-charset="UTF-8">
								<input style="margin-bottom: 15px;" type="email" placeholder="email" id="email" name="email" required>
								 
									<input style="margin-bottom: 15px;" type="password" placeholder="senha" id="senha" name="senha" required> 
									
									<input class="btn btn-primary btn-block" type="submit" id="email" value="Logar">
									
									<br />
									<a href="enviarEmail/senha"><p align="center">Esqueceu sua senha?</p></a>
									<hr>
							</form>
							<a href="google" class="btn btn-primary btn-block" >Faça login com o Google</a>
							<br>
							
							
						</div></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
		<!--/.container-fluid -->
	</div>
	<!--/.navbar-inner -->
</div>
<!--/.navbar -->

<script>
	$(document).ready(function() {
		//Handles menu drop down
		$('.dropdown-menu').find('form').click(function(e) {
			e.stopPropagation();
		});
	});
</script>

