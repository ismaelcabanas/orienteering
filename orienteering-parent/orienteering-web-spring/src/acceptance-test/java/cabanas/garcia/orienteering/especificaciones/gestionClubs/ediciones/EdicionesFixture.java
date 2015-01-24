package cabanas.garcia.orienteering.especificaciones.gestionClubs.ediciones;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import cabanas.garcia.orienteering.especificaciones.gestionClubs.GestionClubsFixture;
import cabanas.garcia.orienteering.pages.club.EdicionClubPage;
import cabanas.garcia.orienteering.pages.club.ListadoClubsPage;

public class EdicionesFixture extends GestionClubsFixture {	
	
	private ListadoClubsPage listadoClubsPage;
	private EdicionClubPage edicionClubPage;

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
