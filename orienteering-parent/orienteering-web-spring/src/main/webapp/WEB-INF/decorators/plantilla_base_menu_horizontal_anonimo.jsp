<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- <link rel="shortcut icon" href="${pageContext.servletContext.contextPath}/assets/ico/favicon.png"> -->
	
    <title><sitemesh:write property="title" /></title>
		
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/static/bootstrap/css/bootstrap.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/static/bootstrap/css/bootstrap-theme.css" media="screen" />

    <!-- Custom styles for this template -->
	<link href="${pageContext.servletContext.contextPath}/static/css/estilos.css" rel="stylesheet">

	<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/static/bootstrap/css/font-awesome.css" />
	
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${pageContext.servletContext.contextPath}/static/bootstrap/assets/js/html5shiv.js"></script>
      <script src="${pageContext.servletContext.contextPath}/static/bootstrap/assets/js/respond.min.js"></script>
    <![endif]-->
    
    <sitemesh:write property="head"/>
  </head>

  <body>

<div class="container-fluid">
	
    <!-- Static navbar -->
    <div class="navbar navbar-default navbar-static-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">El deporte de Orientación</a>
        </div>
        <div class="collapse navbar-collapse">           	
          <ul class="nav navbar-nav navbar-right">
          	<li>
          		<a id="drop1" href="#" role="button">Regístrate</a>
          	</li>
            <li><a href="">Inicia sesión</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
	
		
	<sitemesh:write property="body" />
	  	
</div>		

<!-- Modal -->
<div class="modal fade" id="delete-confirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">Confirmación</h4>
      </div>
      <div class="modal-body">
        Se va a eliminar el registro seleccionado, ¿está seguro?.
      </div>
      <div class="modal-footer">
        <a href="#" class="btn btn-default" data-dismiss="modal">Cancelar</a>
        <a href="/pony" class="btn btn-danger">Eliminar</a>
      </div>
    </div>
  </div>
</div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/static/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/static/js/jquery.validate-1.9.0.js"></script>
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/static/bootstrap/js/bootstrap.js"></script>    
    <!-- Placed at the end of the document so the pages load faster -->
    
    <script type="text/javascript">
    
	 // Delete confirmation modals
	    $('#delete-confirm').on('show', function() {
	      var $submit = $(this).find('.btn-danger'),
	          href = $submit.attr('href');
	      alert($(this).data('id'));
	      $submit.attr('href', href.replace('pony', $(this).data('id')));
	    });
	
	    $('.delete-confirm').click(function(e) {
	      alert("KK");	
	      e.preventDefault();
	      $('#delete-confirm').data('id', $(this).data('id')).modal('show');
	    });
	    
    </script>
    
  </body>
</html>
