<%-- Pagina define o template da aplicacao, no Tiles. --%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />

<spring:url value="/resources/css/bootstrap.min.css" var="bootstrap_url" />
<spring:url value="/resources/css/springmvc_gae.css" var="css_url" />

<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapjs_url" />
<spring:url value="/resources/js/jquery-1.7.2.min.js" var="jquery_url" />
<spring:url value="/resources/js/jquery.validate.min.js" var="jquery_validate_url" />

<link rel="stylesheet" type="text/css" media="screen" href="${bootstrap_url}" />
<link rel="stylesheet" type="text/css" media="screen" href="${css_url}" />

<script type="text/javascript" src="${bootstrapjs_url}"></script>
<script type="text/javascript" src="${jquery_url}"></script>
<script type="text/javascript" src="${jquery_validate_url}"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>

<script src='resources/js/bootstrap.min.js'></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src='resources/js/jquery-ui.custom.min.js'></script>

<title>SisGR</title>
</head>

<body>
	<div class="header">
		<div class="container">
			<tiles:insertAttribute name="header" ignore="true" />
		</div>
	</div>

	<div class="container">
		<div id="wrapper" class="row-fluid show-grid">
			<div class="span12">
 				<tiles:insertAttribute name="body" />
			</div>
		</div>
		<footer>
			<tiles:insertAttribute name="footer" ignore="true" />
		</footer>
	</div>
</body>
</html>