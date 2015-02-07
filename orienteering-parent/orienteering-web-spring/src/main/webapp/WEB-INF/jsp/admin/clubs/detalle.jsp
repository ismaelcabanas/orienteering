<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
  <head>
	
    <title><spring:message code="page.titulo.club.detalle"/></title>
	
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

					Clubs de orientación <small><spring:message code="page.titulo.club.detalle"/></small>

					</h3>

					<ul class="page-breadcrumb breadcrumb">					

						<li>

							<i class="fa fa-home"></i>

							<a href="${pageContext.servletContext.contextPath}/index.jsp"><spring:message code="miga.home"/></a>

							<i class="fa fa-angle-right"></i>

						</li>

						<li>

							<a href="#"><spring:message code="miga.club.listado"/></a>

							<i class="fa fa-angle-right"></i>

						</li>

						<li>

							<a href="#"><spring:message code="miga.club.detalle"/></a>

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

								<spring:message code="page.titulo.club.detalle"/>

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
										
											<div class="form-body">																							
												 
												<div class="form-group">
	
													<label class="control-label"><spring:message code="club.nombre.label"/></label>
													
													<input id="lblNombre" value="${clubDto.nombre}" class="form-control" disabled />	
													
												</div>
	
											</div>	
											
										
										<div class="form-actions fluid">

											<div class="row">

												<div class="col-md-6">

													<div class="col-md-12">

														<a id="btnVolver" href="${pageContext.servletContext.contextPath}/admin/clubs/volver" class="btn default"><spring:message code="texto.boton.volver"/></a>
														
													</div>

												</div>

												<div class="col-md-6">

												</div>

											</div>

										</div>

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
