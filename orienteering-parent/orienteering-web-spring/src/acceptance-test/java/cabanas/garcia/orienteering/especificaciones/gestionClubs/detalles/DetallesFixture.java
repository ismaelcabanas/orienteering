package cabanas.garcia.orienteering.especificaciones.gestionClubs.detalles;

import cabanas.garcia.orienteering.especificaciones.gestionClubs.GestionClubsFixture;
import cabanas.garcia.orienteering.pages.club.DetalleClubPage;
import cabanas.garcia.orienteering.pages.club.ListadoClubsPage;

public class DetallesFixture extends GestionClubsFixture {	
	
	private ListadoClubsPage listadoClubsPage;
	private DetalleClubPage detalleClubPage;

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
	
	public DetalleClubPage navegaDetalleClubs(String id){
		
		detalleClubPage = listadoClubsPage.ver(id);
		
		return detalleClubPage;
	}
	
	public ListadoClubsPage volver(){
		
		return listadoClubsPage;
		
	}
		
}
