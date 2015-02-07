package cabanas.garcia.orienteering.especificaciones.gestionClubs.bajas;

import org.junit.Before;

import cabanas.garcia.orienteering.especificaciones.gestionClubs.GestionClubsFixture;
import cabanas.garcia.orienteering.pages.club.ListadoClubsPage;

public class BajasFixture extends GestionClubsFixture {

	@Before
	public void setUp(){
		setNombreDataSet("datos.xml");
		super.setUp();
	}	
	
	public ListadoClubsPage informarBaja(String id){
		
		return listadoClubsPage.informarBaja(id);
		
	}
	
	public ListadoClubsPage baja(){
	
		return listadoClubsPage.baja();
		
	}
	
	public ListadoClubsPage cancelaBaja(){
		
		return listadoClubsPage.cancelarBaja();
	}
}
