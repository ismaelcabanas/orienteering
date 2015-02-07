package cabanas.garcia.orienteering.especificaciones.gestionClubs;

import cabanas.garcia.orienteering.especificaciones.EspecificacionesFixture;
import cabanas.garcia.orienteering.pages.club.ListadoClubsPage;

public class GestionClubsFixture extends EspecificacionesFixture {

	protected ListadoClubsPage listadoClubsPage;
	
	public ListadoClubsPage navegaListadoClubs(){
		
		listadoClubsPage = navega(URI_BASE + "/admin/clubs/listado", ListadoClubsPage.class);
		
		return listadoClubsPage;
		
	}		

	public ListadoClubsPage buscar(String nombre){
		
		// relleno los campos del formulario de búsqueda
		listadoClubsPage.rellenaFormularioBusqueda(nombre);
		
		// realizo la búsqueda
		listadoClubsPage.buscar();
		
		return listadoClubsPage;
	}
}
