package cabanas.garcia.orienteering.especificaciones.gestionClubs.consultas;

import java.util.ArrayList;
import java.util.List;

import cabanas.garcia.orienteering.dtos.club.ClubDto;
import cabanas.garcia.orienteering.especificaciones.gestionClubs.GestionClubsFixture;
import cabanas.garcia.orienteering.pages.club.ListadoClubsPage;

public class ConsultasFixture extends GestionClubsFixture {
	
	private ListadoClubsPage listadoClubsPage;

	public ListadoClubsPage navegaListadoClubs(){
		
		listadoClubsPage = navega(URI_BASE + "/admin/clubs/listado", ListadoClubsPage.class);
		
		return listadoClubsPage;
		
	}
	
	public void rellenaCamposFormularioBusquedaClubs(String nombre){
		
		// relleno los campos del formulario de búsqueda
		listadoClubsPage.rellenaFormularioBusqueda(nombre);
		
	}
	
	public ListadoClubsPage buscar(){
		
		return listadoClubsPage.buscar();
		
	}
	
	public List<ClubDto> obtenerClubs(){
		
		return listadoClubsPage.getResultadoConsulta();
		
	}
}
