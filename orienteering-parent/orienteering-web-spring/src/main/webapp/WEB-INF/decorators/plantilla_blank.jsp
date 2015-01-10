<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->

<!--[if !IE]><!-->

<html lang="en" >

<!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

<meta charset="utf-8"/>

<title><sitemesh:write property="title" /></title>

<meta http-equiv="X-UA-Compatible" content="IE=edge">

<meta content="width=device-width, initial-scale=1.0" name="viewport"/>

<meta content="" name="description"/>

<meta content="" name="author"/>

<!-- BEGIN GLOBAL MANDATORY STYLES -->


<!-- END GLOBAL MANDATORY STYLES -->

<!-- BEGIN PAGE LEVEL STYLES -->

<!-- END PAGE LEVEL STYLES -->


<!-- BEGIN THEME STYLES -->


<!-- END THEME STYLES -->


</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<!-- DOC: Apply "page-header-fixed-mobile" and "page-footer-fixed-mobile" class to body element to force fixed header or footer in mobile devices -->

<!-- DOC: Apply "page-sidebar-closed" class to the body and "page-sidebar-menu-closed" class to the sidebar menu element to hide the sidebar by default -->

<!-- DOC: Apply "page-sidebar-hide" class to the body to make the sidebar completely hidden on toggle -->

<!-- DOC: Apply "page-sidebar-closed-hide-logo" class to the body element to make the logo hidden on sidebar toggle -->

<!-- DOC: Apply "page-sidebar-hide" class to body element to completely hide the sidebar on sidebar toggle -->

<!-- DOC: Apply "page-sidebar-fixed" class to have fixed sidebar -->

<!-- DOC: Apply "page-footer-fixed" class to the body element to have fixed footer -->

<!-- DOC: Apply "page-sidebar-reversed" class to put the sidebar on the right side -->

<!-- DOC: Apply "page-full-width" class to the body element to have full width page without the sidebar menu -->

<body class="page-header-fixed page-quick-sidebar-over-content page-header-fixed-mobile page-footer-fixed1">

<!-- BEGIN HEADER -->

<div class="page-header navbar navbar-fixed-top">

	<!-- BEGIN HEADER INNER -->

	<div class="page-header-inner">

		<!-- BEGIN LOGO -->

		<div class="page-logo">

			<a href="index.html">

			<img src="${pageContext.servletContext.contextPath}/static/metronicv31/assets/admin/layout/img/logo.png" alt="logo" class="logo-default"/>

			</a>

			<div class="menu-toggler sidebar-toggler hide">

				<!-- DOC: Remove the above "hide" to enable the sidebar toggler button on header -->

			</div>

		</div>

		<!-- END LOGO -->

		<!-- BEGIN RESPONSIVE MENU TOGGLER -->

		<a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse">

		</a>

		<!-- END RESPONSIVE MENU TOGGLER -->

		<!-- BEGIN TOP NAVIGATION MENU -->

		<div class="top-menu">

			<ul class="nav navbar-nav pull-right">

				<!-- BEGIN USER LOGIN DROPDOWN -->

				<li class="dropdown dropdown-user">

					<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">

					<img alt="" class="img-circle" src="${pageContext.servletContext.contextPath}/static/metronicv31/assets/admin/layout/img/avatar3_small.jpg"/>

					<span class="username">

					Ismael Cabañas García </span>

					<i class="fa fa-angle-down"></i>

					</a>

					<ul class="dropdown-menu">

						<li>

							<a href="extra_profile.html">

							<i class="icon-user"></i> Mi perfil </a>

						</li>						

						<li>

							<a href="inbox.html">

							<i class="icon-envelope-open"></i> Mis carreras <span class="badge badge-danger">

							3 </span>

							</a>

						</li>						

						<li class="divider">

						</li>
						
						<li>

							<a href="login.html">

							<i class="icon-logout"></i> Desconectar </a>

						</li>

					</ul>

				</li>

				<!-- END USER LOGIN DROPDOWN -->

				<!-- BEGIN QUICK SIDEBAR TOGGLER -->

				<li class="dropdown dropdown-quick-sidebar-toggler">

					<a href="javascript:;" class="dropdown-toggle">

					<i class="icon-logout"></i>

					</a>

				</li>

				<!-- END QUICK SIDEBAR TOGGLER -->

			</ul>

		</div>

		<!-- END TOP NAVIGATION MENU -->

	</div>

	<!-- END HEADER INNER -->

</div>

<!-- END HEADER -->

<div class="clearfix">

</div>

<!-- BEGIN CONTAINER -->

<div class="page-container">

	<!-- BEGIN SIDEBAR -->

	<div class="page-sidebar-wrapper">

		<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->

		<!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->

		<div class="page-sidebar navbar-collapse collapse">

			<!-- BEGIN SIDEBAR MENU -->

			<ul class="page-sidebar-menu" data-auto-scroll="true" data-slide-speed="200">

				<!-- DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element -->

				<li class="sidebar-toggler-wrapper">

					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->

					<div class="sidebar-toggler">

					</div>

					<!-- END SIDEBAR TOGGLER BUTTON -->

				</li>

				<!-- DOC: To remove the search box from the sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element -->



				<li class="start ${(empty param.page ? 'active' : '')} ">

					<a href="${pageContext.servletContext.contextPath}/index.jsp">

					<i class="icon-home"></i>

					<span class="title">Panel de control</span>

					</a>

				</li>
				
				<li ${(param.page == 'club' ? 'class="active open"' : '')}>
					<a href="javascript:;">
					<span class="title">Gestión de clubs</span>
					<span class="arrow "></span>
					${(param.page == 'club' ? '<span class="selected"></span>' : '')}
					</a>
					<ul class="sub-menu">
						<li ${((param.page == 'club' and param.submenu == '01') ? 'class="active"' : '')}>
							<c:url value="/admin/clubs/listado" var="urlListadoClubs">
								<c:param name="page" value="club" />
								<c:param name="submenu" value="01" />
							</c:url>
							<a href="${urlListadoClubs}">
							Clubs de orientación</a>
						</li>
					</ul>
				</li>
				
				<li ${(param.page == 'corredores' ? 'class="active open"' : '')}>
					<a href="javascript:;">
					<span class="title">Gestión de corredores</span>
					<span class="arrow "></span>
					${(param.page == 'corredores' ? '<span class="selected"></span>' : '')}
					</a>
					<ul class="sub-menu">
						<li ${((param.page == 'corredores' and param.submenu == '01') ? 'class="active"' : '')}>
							<a href="${pageContext.servletContext.contextPath}/paginas/corredores/listado.jsp?page=corredores&submenu=01">
							Corredores de orientación</a>
						</li>
					</ul>
				</li>
				
				<li ${(param.page == 'carreras' ? 'class="active open"' : 'class="last"')}>
					<a href="javascript:;">
					<span class="title">Gestión de carreras</span>
					<span class="arrow "></span>
					${(param.page == 'carreras' ? '<span class="selected"></span>' : '')}
					</a>
					<ul class="sub-menu">
						<li ${((param.page == 'carreras' and param.submenu == '01') ? 'class="active"' : '')}>
							<a href="${pageContext.servletContext.contextPath}/paginas/carreras/listado.jsp?page=carreras&submenu=01">
							Carrereas de orientación</a>
						</li>
					</ul>
				</li>
				
			</ul>

			<!-- END SIDEBAR MENU -->

		</div>

	</div>

	<!-- END SIDEBAR -->

	<!-- BEGIN CONTENT -->

	<div class="page-content-wrapper">

		<div class="page-content">

			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->

			<div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

				<div class="modal-dialog">

					<div class="modal-content">

						<div class="modal-header">

							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

							<h4 class="modal-title">Modal title</h4>

						</div>

						<div class="modal-body">

							 Widget settings form goes here

						</div>

						<div class="modal-footer">

							<button type="button" class="btn blue">Save changes</button>

							<button type="button" class="btn default" data-dismiss="modal">Close</button>

						</div>

					</div>

					<!-- /.modal-content -->

				</div>

				<!-- /.modal-dialog -->

			</div>

			<!-- /.modal -->

			<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->


			<!-- BEGIN PAGE CONTENT -->

			<sitemesh:write property="body" />
			
			<!-- END PAGE CONTENT-->

		</div>

	</div>

	<!-- END CONTENT -->


</div>

<!-- END CONTAINER -->

<!-- BEGIN FOOTER -->

<div class="page-footer">

	<div class="page-footer-inner">

		 2014 &copy; Orientación by pajarokillo.

	</div>

	<div class="page-footer-tools">

		<span class="go-top">

		<i class="fa fa-angle-up"></i>

		</span>

	</div>

</div>

<!-- END FOOTER -->

<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

<!-- BEGIN CORE PLUGINS -->

<script src="${pageContext.servletContext.contextPath}/static/metronicv31/assets/global/plugins/jquery-1.11.0.min.js" type="text/javascript"></script>

<!-- END CORE PLUGINS -->

<!-- BEGIN PAGE LEVEL PLUGINS -->

<!-- END PAGE LEVEL PLUGINS -->

<!-- BEGIN PAGE LEVEL SCRIPTS -->

<!-- END PAGE LEVEL SCRIPTS -->
	     
<script src="${pageContext.servletContext.contextPath}/static/metronicv31/assets/global/scripts/metronic.js" type="text/javascript"></script>
      
	<script type="text/javascript">
      jQuery(document).ready(function() {    

         Metronic.init(); // init metronic core components

		 Layout.init(); // init current layout
		 
		 init(); // función que inicializa componentes JS
		 
      });
    
      function init(){}
      
  	</script>
  	
  	<sitemesh:write property="page.script" />
  	  	
<!-- END JAVASCRIPTS -->

</body>

<!-- END BODY -->

</html>