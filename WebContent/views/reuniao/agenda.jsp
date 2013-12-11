<div class="well" id="calendar" ></div>
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="advancedSort" aria-hidden="true">
	<div class="modal-header">
		<!-- NAVIGATION -->
		<ul class="nav nav-tabs">
			<li class="active"><a href="#tab1" data-toggle="tab">Geral</a></li>
			<li><a href="#tab2" data-toggle="tab">Detalhes</a></li>
		</ul>
	</div>
	<form data-ajax method="POST" id="form" action="adicionarReuniao">
		<div class="modal-body">
			<div class="tabbable">
				<div class="tab-content">
					<!-- SESSION 1 -->			
					<div class="tab-pane active" id="tab1">
						<div class="row-fluid">
							<div class="span6">
								<label>Título da Reunião</label> <input id="nome" name="nome" type="text"
									placeholder="Titulo" required> 
									<label class="control-label" for="">Data</label>
								<input type='date' placeholder="Dia" name="dia" id="dia"
									value="${editar.dia}" required/>
							</div>
							<div class="span6">								
									<label class="control-label" for="nome">Hora de inicio</label>
									<input type='time' placeholder="Inicio" name="inicio" id="inicio" value="${editar.inicio}" required/>
							
									<label class="control-label" for="nome">Hora de termino</label>
									<input type='time' placeholder="fim" name="fim" id="fim" value="${editar.fim}" required />
							</div>
						</div>
						<label class="control-label" for="nome">Local da reunião</label>
									<input class="span12" type='text' placeholder="Onde vai ser?" name="local" id="local" value="${editar.local}" required/>
							
					</div>
					<!-- SESSION 2  -->
					<div class="tab-pane" id="tab2">
						<div class="row-fluid">
							<div class="span12">
								<label>Detalhes</label>
								<textarea id="descricao" name="descricao" class="span12" rows="17"
									placeholder="Descreva os detalhes da sua reunião aqui ..."></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	
	<!-- MODAL FOOTER -->
	<div class="modal-footer">
		<button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Cancelar</button>
		<button class="btn btn-primary" type="submit">Salvar Reunião</button>
	</div>
</form>
</div> 