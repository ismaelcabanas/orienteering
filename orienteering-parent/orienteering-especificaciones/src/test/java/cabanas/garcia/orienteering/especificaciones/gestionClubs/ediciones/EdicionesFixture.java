package cabanas.garcia.orienteering.especificaciones.gestionClubs.ediciones;

import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import cabanas.garcia.orienteering.especificaciones.gestionClubs.GestionClubsFixture;
import cabanas.garcia.orienteering.pages.club.EdicionClubPage;
import cabanas.garcia.orienteering.pages.club.ListadoClubsPage;

public class EdicionesFixture extends GestionClubsFixture {	
	
	private EdicionClubPage edicionClubPage;

	@Before
	public void setUp(){
		setNombreDataSet("datos.xml");
		super.setUp();
	}		
	
	public EdicionClubPage navegaEdicionClubs(String id){
		
		edicionClubPage = listadoClubsPage.editar(id);
		
		return edicionClubPage;
	}
	
	public void rellenarFormulario(String nombre){

		// relleno el formulario
		edicionClubPage.rellenaFormulario(nombre);
		
	}
	
	public ListadoClubsPage actualizar(){
		
		// acción de actualizar
		return edicionClubPage.actualizar();
		
	}	
	
	public ListadoClubsPage cancelar(){
		
		// se simula el click sobre el botón de 'Cancelar'
		ListadoClubsPage returnPage = edicionClubPage.cancelar();
		
		// se espera unos 5 segundos para que se cargue la página
		(new WebDriverWait(driver, 5)).until(new ExpectedCondition<Boolean>() {
          public Boolean apply(WebDriver d) {
              return d.getTitle().toLowerCase().startsWith("listado");
          }
		});
		
		return returnPage;
		
	}
}
