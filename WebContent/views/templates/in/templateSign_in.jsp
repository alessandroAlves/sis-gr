<%@ page contentType="text/html;charset=UTF-8"%><%@ page pageEncoding="UTF-8"%><%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%><%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!doctype html>
<html>
<head>
<link rel="stylesheet" type="text/css" media="screen" href="/sis-gr/resources/css/bootstrap.min.css" />
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src='/sis-gr/resources/js/bootstrap.min.js'></script>
<script src='/sis-gr/resources/js/jquery-ui.custom.min.js'></script>
<link href='/sis-gr/resources/fullcalendar/fullcalendar.css' rel='stylesheet' />
<link href='/sis-gr/resources/fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />
<script src='/sis-gr/resources/fullcalendar/fullcalendar.min.js'></script>

<script>
$(document).ready(function() 
{
	
	$.getJSON('/sis-gr/listaJson', function (data){
	
		var calendar = $('#calendar').fullCalendar
		({
			
			header : {
				left : 'prev,next today',
				center : 'title',
				right : 'month,agendaWeek,agendaDay'
			},
			dayClick : function(date, allDay, jsEvent, view){
				$('#myModal').modal('show');
			},
			selectable : true,
			selectHelper : true,
			select : function(start, end, allDay) 
			{
				var title = $('#myModal').modal('show') ;
					if (title) 
					{
						calendar.fullCalendar('renderEvent', {
							title : title,
							start : start,
							end : end,
							allDay : allDay
						}
						);
					}
				calendar.fullCalendar('unselect');
			},
			monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
			monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
			dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
			dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb'],
			buttonText: 
			{
				prev: '&nbsp;&#9668;&nbsp;',
				next: '&nbsp;&#9658;&nbsp;',
				prevYear: '&nbsp;&lt;&lt;&nbsp;',
				nextYear: '&nbsp;&gt;&gt;&nbsp;',
				today: 'hoje',
				month: 'mês',
				week: 'semana',
				day: 'dia'
			},		
			titleFormat: 
			{
				month: 'MMMM yyyy',
				week: "d [ yyyy]{ '&#8212;'[ MMM] d MMM yyyy}",
				day: 'dddd, d MMM, yyyy'
			},
			columnFormat: 
			{
				month: 'ddd',
				week: 'ddd d/M',
				day: 'dddd d/M'
			},
			allDayText: 'dia todo',
			axisFormat: 'H:mm',
			timeFormat: 
			{
				'': 'H(:mm)',
				agenda: 'H:mm{ - H:mm}'
			},
			editable : false,
		 	events:data
			
		});
	
	});

});
		
</script>
<!-- // -->
<style>
#calendar {
	width: 735px;
	margin: 0 auto;
}

a.tooltip{
  position:relative; 
  font-size:12px; 
  color:#039;
  text-decoration:none;
  }

  a.tooltip:hover{
  background:transparent;
  color:#f00;
  z-index:25; 

  }
  a.tooltip span{display: none
  }

  a.tooltip:hover span{
  display:block;
  position:absolute;
  width:110px;
  top:20px;
  left:0;
  font-size: 12px;
  padding:5px;
  border:1px solid #999;
  border-radius: 10px;
  font-family: Lucida, sans-serif;
  background:white; 
  color:#000;
  }

</style>
<title>SisGR</title>
</head>

<body>
<script src='/sis-gr/resources/js/wz_tooltip.js'></script>

	<div class="header">
		<div class="container">
			<tiles:insertAttribute name="header" ignore="true" />  
		</div>
	</div>

	<div class="container">
		<div class="row-fluid">
			<div class="span2">
				<tiles:insertAttribute name="menu" />
			</div>
			<div class="span10">
				<tiles:insertAttribute name="body" />
			</div>
		</div>
	</div>
	<footer>
		<tiles:insertAttribute name="footer" ignore="true" />
	</footer>
</body>
</html>