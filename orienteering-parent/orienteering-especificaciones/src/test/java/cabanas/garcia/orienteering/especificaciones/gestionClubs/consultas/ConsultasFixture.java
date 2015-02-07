package cabanas.garcia.orienteering.especificaciones.gestionClubs.consultas;

import java.util.List;

import org.junit.Before;

import cabanas.garcia.orienteering.dtos.club.ClubDto;
import cabanas.garcia.orienteering.especificaciones.gestionClubs.GestionClubsFixture;
import cabanas.garcia.orienteering.pages.club.ListadoClubsPage;

public class ConsultasFixture extends GestionClubsFixture {
		
	@Before
	public void setUp(){
		setNombreDataSet("datos.xml");
		super.setUp();
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
