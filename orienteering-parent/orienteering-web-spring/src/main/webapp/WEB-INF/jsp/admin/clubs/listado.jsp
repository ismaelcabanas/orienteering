<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
  <head>
	
    <title><spring:message code="page.titulo.club.listado"/></title>
	
	<content tag="pageLevelStyles">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/static/metronicv31/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/static/metronicv31/assets/global/plugins/bootstrap-datepicker/css/datepicker.css"/>
	</content>
		
  </head>

  <body>
	
			<!-- BEGIN PAGE HEADER-->

			<div class="row">

				<div class="col-md-12">

					<!-- BEGIN PAGE TITLE & BREADCRUMB-->

					<h3 class="page-title">

					Clubs de orientación <small><spring:message code="page.titulo.club.listado"/></small>

					</h3>

					<ul class="page-breadcrumb breadcrumb">					

						<li>

							<i class="fa fa-home"></i>

							<a href="${pageContext.servletContext.contextPath}/index.jsp">Home</a>

							<i class="fa fa-angle-right"></i>

						</li>

						<li>

							<a href="#">Clubs</a>

							<i class="fa fa-angle-right"></i>

						</li>

						<li>

							<a href="#">Listado de clubes</a>

						</li>

					</ul>

					<!-- END PAGE TITLE & BREADCRUMB-->

				</div>

			</div>

			<!-- END PAGE HEADER-->

			<!-- BEGIN PAGE CONTENT-->

			<div class="row">

				<div class="col-md-12">					

					<!-- Begin: life time stats -->

					<div class="portlet">

						<div class="portlet-title">

							<div class="caption">

								<!-- <i class="fa fa-shopping-cart"></i> --><spring:message code="page.titulo.club.listado"/>

							</div>

							<div class="actions">
								<c:url value="/admin/clubs/nuevo" var="urlNuevoClub">
									<c:param name="page" value="club" />
									<c:param name="submenu" value="01" />
								</c:url>
								<a id="btnNuevo" href="${urlNuevoClub}" class="btn default yellow-stripe">

								<i class="fa fa-plus"></i>

								<span class="hidden-480"><spring:message code="texto.boton.nuevo"/> </span>

								</a>

							</div>

						</div>

						<div class="portlet-body">

							<div class="table-container">								


								<table class="table table-striped table-bordered table-hover" id="tabla_clubes">

									<thead>
	
									<tr role="row" class="heading">
	
										<th width="20%">
	
											 <spring:message code="entidad.club.nombre"/>
	
										</th>									
	
	
										<th width="16%">
	
											 <spring:message code="texto.acciones"/>
	
										</th>
	
									</tr>
	 
									<tr role="row" class="filter">
										
										<form:form method="POST" commandName="clubBusquedaForm" action="${pageContext.servletContext.contextPath}/admin/clubs/buscar">
										
										<td>
	
											<input id="txtBusqNombre" type="text" class="form-control form-filter input-sm" name="nombre" placeholder="Nombre del club">
	
										</td>
										
	
										<td>
	
											<div class="margin-bottom-5">
	
												<button id="btnBuscar" type="submit" class="btn btn-sm yellow filter-submit margin-bottom"><i class="fa fa-search"></i> <spring:message code="texto.boton.buscar"/></button>
												<button class="btn btn-sm red filter-cancel"><i class="fa fa-times"></i> <spring:message code="texto.boton.limpiar"/></button>
													
											</div>
	
										</td>
										</form:form>									
									</tr>																
	 
									</thead>
	
									<tbody id="resultado_consulta">
										<c:if test="${not empty clubs}">
											<c:forEach var="club" items="${clubs}">
												<tr>
													<td>${club.nombre}</td>
													<td>
														<a href="detalle.jsp?id=${club.id}&page=club&submenu=01" class="btn btn-sm"><i class="fa fa-eye"></i> <spring:message code="texto.accion.ver"/></a>
														<a href="edicion.jsp?id=${club.id}&page=club&submenu=01" class="btn btn-sm"><i class="fa fa-edit"></i> <spring:message code="texto.accion.editar"/></a>
														<a href="" class="btn btn-sm"><i class="fa fa-trash-o"></i> <spring:message code="texto.accion.eliminar"/></a>
													</td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>

								</table>

							</div>

						</div>

					</div>

					<!-- End: life time stats -->

				</div>

			</div>

			<!-- END PAGE CONTENT-->
				
  </body>
  
  <content tag="pageLevelPlugins">
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/static/metronicv31/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/static/metronicv31/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/static/metronicv31/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>  	
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/static/metronicv31/assets/global/plugins/bootbox/bootbox.min.js"></script>
  </content>
  
  <content tag="pageLevelScripts">
	<script src="${pageContext.servletContext.contextPath}/static/metronicv31/assets/global/scripts/datatable.js"></script>
	<%-- <script src="${pageContext.servletContext.contextPath}/static/js/orientacion/tabla-clubes.js"></script> --%>
	<%-- <script src="${pageContext.servletContext.contextPath}/static/metronicv31/assets/admin/pages/scripts/table-ajax.js"></script> --%>  	
  </content>
  
  <content tag="script">
  <script type="text/javascript">      
		 
		 /*  $('#tabla_clubes').dataTable( {
			  "lengthMenu": [
			                    [10, 25, 50, 100, 150, -1],
			                    [10, 25, 50, 100, 150, "Todos"] // change per page values here
			                ],
			 	"pageLength" : 10,
			 	"pagingType" : "full_numbers",
			 	"searching" : false,
		        "ajax": "/orientacion-web/datos/clubes.txt",
		        "columns": [
		            { data: "nombre" },
		            { data: "provincia" },
		            { data: "localidad" },
		            { data: null, render:function(data, type, row){ return data.id}}
		        ],
		        "columnDefs": [{  // set default column settings
	                'orderable': false,
	                'targets': [3]
	            }, {
	                "searchable": false,
	                "targets": [3]
	            }]
		 		, "order" : [0, 'asc']
		    } ); */ 
		
		function init(){
			//TablaClubes.init();
		}	  	  
	</script>
	
	<script type="text/javascript">

	var TablaClubes = function () {

	    var initPickers = function () {
	        //init date pickers
	        $('.date-picker').datepicker({
	            rtl: Metronic.isRTL(),
	            autoclose: true
	        });
	    }

	    var handleRecords = function () {

	        var grid = new Datatable();

	        grid.init({
	            src: $("#tabla_clubes"),
	            onSuccess: function (grid) {
	                // execute some code after table records loaded
	            },
	            onError: function (grid) {
	                // execute some code on network or other general error  
	            },
	            dataTable: { // here you can define a typical datatable settings from http://datatables.net/usage/options 
	                "lengthMenu": [
	                    [10, 25, 50, 100, 150, -1],
	                    [10, 25, 50, 100, 150, "All"] // change per page values here
	                ],
	                "pageLength": 10, // default record count per page
	                "ajax": {
	                    "url": "/orientacion-web/datatable", // ajax source
	                }     
	                ,
			        "columnDefs": [{  // set default column settings
		                'orderable': false,
		                'targets': [3]
		            } ]          
	                ,"order": [
	                    [0, "asc"]
	                ] // set first column as a default sort by asc
	                , "columns" : [
						{ data: "nombre" },
			            { data: "provincia" },
			            { data: "localidad" },
			            { data: null, render: actions}                
			            /*
	                	{data: null , render:function(data, type, row){ return data.record}},
	                	{data: "record"},
	                	{data: "customer"},
	                	{data: "shipto"},
	                	{data: null, render:function(data, type, row){ return data.record}}*/
	                ]
	                
	            }
	        });

	        // handle group actionsubmit button click
	        grid.getTableWrapper().on('click', '.table-group-action-submit', function (e) {
	            e.preventDefault();
	            var action = $(".table-group-action-input", grid.getTableWrapper());
	            if (action.val() != "" && grid.getSelectedRowsCount() > 0) {
	                grid.setAjaxParam("customActionType", "group_action");
	                grid.setAjaxParam("customActionName", action.val());
	                grid.setAjaxParam("id", grid.getSelectedRows());
	                grid.getDataTable().fnDraw();
	                grid.clearAjaxParams();
	            } else if (action.val() == "") {
	                Metronic.alert({
	                    type: 'danger',
	                    icon: 'warning',
	                    message: 'Please select an action',
	                    container: grid.getTableWrapper(),
	                    place: 'prepend'
	                });
	            } else if (grid.getSelectedRowsCount() === 0) {
	                Metronic.alert({
	                    type: 'danger',
	                    icon: 'warning',
	                    message: 'No record selected',
	                    container: grid.getTableWrapper(),
	                    place: 'prepend'
	                });
	            }
	        });
	    }

	    return {

	        //main function to initiate the module
	        init: function () {

	            initPickers();
	            handleRecords();
	        }

	    };

	}();
	
	function actions(data, type, row){
		return "<a href='detalle.jsp?id=" + data.id + "&page=club&submenu=01' class='btn btn-sm'><i class='fa fa-eye'></i> Ver</a>" +
				"<a href='edicion.jsp?id=" + data.id + "&page=club&submenu=01' class='btn btn-sm'><i class='fa fa-edit'></i> Editar</a>" + 
				"<a href='javascript:mostrarDialogoEliminar(" + data.id + ",\"" + data.nombre + "\")' class='btn btn-sm'><i class='fa fa-trash-o'></i> Eliminar</a>";
	}
	
	var mensaje = "<spring:message code='${mensaje.key}'/>";	
	var tipo = '${mensaje.tipo}';
	
	if(mensaje != "")
		mostrarMensaje(mensaje, tipo);

	/*
	if(accion == "crear"){
		mensaje = "<spring:message code='texto.accion.crear'/>";
		tipo = "success";
		mostrarMensaje(mensaje, tipo);
	}
	else if(accion == "actualizar"){
		mensaje = "El registro se ha actualizado correctamente.";
		tipo = "success";
		mostrarMensaje(mensaje, tipo);
	}
	else if(accion == "cancelar"){
		mensaje = "<spring:message code='texto.accion.cancelar'/>";
		tipo = "info";
		mostrarMensaje(mensaje, tipo);
	}
	*/
	function mostrarMensaje(msg, tipo){
		Metronic.alert({
	        container: "", // alerts parent container(by default placed after the page breadcrumbs)
	        place: "append", // append or prepent in container 
	        type: tipo,  // alert's type
	        message: msg,  // alert's message
	        close: "true", // make alert closable
	        reset: "true", // close all previouse alerts first
	        focus: "true", // auto scroll to the alert after shown
	        closeInSeconds: 5, // auto close after defined seconds
	        icon: "" // put icon before the message
	    });
	}
	
	function mostrarDialogoEliminar(id, nombreClub){
		bootbox.confirm("¿Está seguro que quiere eliminar el club " + nombreClub + "?", function(result) {
            //alert("Confirm result: "+result);
         }); 
	}
		
    //end #demo_3
	</script>
  </content>
</html>
