<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
  <head>
	
    <title><spring:message code="page.titulo.club.alta"/></title>
	
	<content tag="pageLevelStyles">
		<link href="${pageContext.servletContext.contextPath}/static/metronicv31/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css" rel="stylesheet"/>
	</content>
		
  </head>

  <body>
	
			<!-- BEGIN PAGE HEADER-->

			<div class="row">

				<div class="col-md-12">

					<!-- BEGIN PAGE TITLE & BREADCRUMB-->

					<h3 class="page-title">

					Clubes de orientación <small><spring:message code="page.titulo.club.alta"/></small>

					</h3>

					<ul class="page-breadcrumb breadcrumb">					

						<li>

							<i class="fa fa-home"></i>

							<a href="${pageContext.servletContext.contextPath}/index.jsp">Home</a>

							<i class="fa fa-angle-right"></i>

						</li>

						<li>

							<a href="#">Clubes</a>

							<i class="fa fa-angle-right"></i>

						</li>

						<li>

							<a href="#">Nuevo club</a>

						</li>

					</ul>

					<!-- END PAGE TITLE & BREADCRUMB-->

				</div>

			</div>

			<!-- END PAGE HEADER-->

			<!-- BEGIN PAGE CONTENT-->
			
			<div class="row">
				<div class="col-md-12">
					<div class="portlet">
						<div class="portlet-title">

							<div class="caption">

								<spring:message code="page.titulo.club.alta"/>

							</div>
							
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">

				<div class="col-md-12">					

					<div class="portlet box green-meadow">

									<div class="portlet-title">

										<div class="caption">

											Datos del club

										</div>
										
									</div>

									<div class="portlet-body form">

										<!-- BEGIN FORM-->
																				
										<form:form method="POST" commandName="clubForm" action="${pageContext.servletContext.contextPath}/admin/clubs/alta">

											<div class="form-body">
												
												<!-- 
												<div class="row">

													<div class="col-md-6">

														<div class="form-group">

															<label class="control-label col-md-3">Logo</label>
	
															<div class="col-md-9">

																<div class="fileinput fileinput-new" data-provides="fileinput">
					
																	<div class="input-group input-large">
					
																		<div class="form-control uneditable-input span3" data-trigger="fileinput">
					
																			<i class="fa fa-file fileinput-exists"></i>&nbsp; <span class="fileinput-filename">
					
																			</span>
					
																		</div>
					
																		<span class="input-group-addon btn default btn-file">
					
																		<span class="fileinput-new">
					
																		Seleccionar </span>
					
																		<span class="fileinput-exists">
					
																		Cambiar </span>
					
																		<input type="file" name="...">
					
																		</span>
					
																		<a href="#" class="input-group-addon btn red fileinput-exists" data-dismiss="fileinput">
					
																		Eliminar </a>
					
																	</div>
					
																</div>
					
															</div>																																

														</div>

													</div>
													
													

												</div>
												 -->
												 
												<div class="form-group">

													<label class="control-label">Nombre</label>

													<form:input id="txtNombre" path="nombre" class="form-control" placeholder="Nombre del club" />

												</div>
<!-- 
												<div class="form-group">

													<label class="control-label">Domicilio</label>

													<input id="txtDomicilio" type="text" class="form-control" placeholder="Domicilio del club">

												</div>
							
												<div class="form-group">

													<label class="control-label">Provincia</label>

													<select id="selProvincia" class="form-control">
															
															<option value="-1">-- Seleccione una provincia --</option>
															
															<option value="">Madrid</option>

															<option value="">C�diz</option>

													</select>
														
												</div>

												<div class="form-group">

													<label class="control-label">Localidad</label>
													
													<select id="selLocalidad" class="form-control">
															
															<option value="-1">-- Seleccione una localidad --</option>
															
															<option value="">Valdemoro</option>

															<option value="">Puerto Real</option>

													</select>

												</div>

												<div class="form-group">

													<label class="control-label">C�digo postal</label>

													<input id="txtCp" type="text" class="form-control" placeholder="C�digo postal">

												</div>
 -->
											</div>	

											<div class="form-actions fluid">

												<div class="row">

													<div class="col-md-6">

														<div class="col-md-offset-3 col-md-9">

															<button id="btnCrear" type="submit" class="btn green-meadow">Crear</button>
															
															<a id="btnCancelar" href="${pageContext.servletContext.contextPath}/admin/clubs/cancelar" class="btn default">Cancelar</a>
															
														</div>

													</div>

													<div class="col-md-6">

													</div>

												</div>

											</div>

										</form:form>

										<!-- END FORM-->

									</div>
					</div>

				</div>

			</div>

			<!-- END PAGE CONTENT-->
				
  </body>
  
  <content tag="pageLevelPlugins">
  	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/static/metronicv31/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>
  </content>
  
  <content tag="pageLevelScripts">
  </content>
  

</html>
