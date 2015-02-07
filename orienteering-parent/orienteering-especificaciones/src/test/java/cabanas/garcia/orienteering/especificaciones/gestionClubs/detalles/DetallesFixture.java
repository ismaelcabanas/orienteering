package cabanas.garcia.orienteering.especificaciones.gestionClubs.detalles;

import org.junit.Before;

import cabanas.garcia.orienteering.especificaciones.gestionClubs.GestionClubsFixture;
import cabanas.garcia.orienteering.pages.club.DetalleClubPage;
import cabanas.garcia.orienteering.pages.club.ListadoClubsPage;

public class DetallesFixture extends GestionClubsFixture {	
	
	private DetalleClubPage detalleClubPage;

	@Before
	public void setUp(){
		setNombreDataSet("datos.xml");
		super.setUp();
	}			
	
	public DetalleClubPage navegaDetalleClubs(String id){
		
		detalleClubPage = listadoClubsPage.ver(id);
		
		return detalleClubPage;
	}
	
	public ListadoClubsPage volver(){
		
		return detalleClubPage.volver();
		
	}	
		
}
