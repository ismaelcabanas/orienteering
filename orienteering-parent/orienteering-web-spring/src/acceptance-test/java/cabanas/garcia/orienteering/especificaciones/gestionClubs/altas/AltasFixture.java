package cabanas.garcia.orienteering.especificaciones.gestionClubs.altas;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import cabanas.garcia.orienteering.especificaciones.gestionClubs.GestionClubsFixture;
import cabanas.garcia.orienteering.pages.club.AltaClubPage;
import cabanas.garcia.orienteering.pages.club.ListadoClubsPage;

/**
 * Este test funcional debería dar de alta un club de orientación:
 * 
 * 1.- El usuario accede a la página de listado de clubs de orientación y hace click sobre el botón 'Nuevo Club' para navegar a la página de alta de clubs
 * 2.- El usuario rellena el formulario de alta de club de orientación:
 * 2.1.- rellena el campo nombre
 * 2.2.- rellena el campo domicilio
 * 2.3.- rellena la provincia
 * 2.4.- rellena la localidad
 * 3.- El usuario pulsa el botón de 'Crear'
 * 4.- El sistema devuelve al usuario a la página de listado de clubs de orientación
 * 
 * y comprueba que después de realizar el alta:
 * 
 * 1.- se vuelve a la página de listado de clubs de orientación, comprobando el título de la página
 * 2.- se comprueba que sale el mensaje de que el alta se realizó correctamente
 * 3.- el menú seleccionado es el de gestión de clubs
 * 
 * @throws Exception
 */
public class AltasFixture extends GestionClubsFixture {

	private ListadoClubsPage listadoClubsPage;
	private AltaClubPage altaClubPage;
	
	public ListadoClubsPage navegaListadoClubs(){
		
		listadoClubsPage = navega(URI_BASE + "/admin/clubs/listado", ListadoClubsPage.class);
		
		return listadoClubsPage;
		
	}
	
	public AltaClubPage nuevoClub(){
		
		// se simula el click sobre el bot�n de 'Nuevo club'
		altaClubPage = listadoClubsPage.nuevoClub();
		
		return altaClubPage;
		
	}
	
	public void rellenarFormulario(String nombre){

		// relleno el formulario
		altaClubPage.rellenaFormulario(nombre);
		
	}
	
	public ListadoClubsPage buscar(String nombre){
		
		// relleno los campos del formulario de búsqueda
		listadoClubsPage.rellenaFormularioBusqueda(nombre);
		
		// realizo la búsqueda
		listadoClubsPage.buscar();
		
		return listadoClubsPage;
	}	
	
	public ListadoClubsPage crearClub(String nombre){
		
		// relleno el formulario de alta
		altaClubPage.rellenaFormulario(nombre);
		
		// se simula el click sobre el botón de 'Crear'
		ListadoClubsPage returnPage = altaClubPage.crear();
		
		// se espera unos 5 segundos para que se cargue la página
		(new WebDriverWait(driver, 5)).until(new ExpectedCondition<Boolean>() {
          public Boolean apply(WebDriver d) {
              return d.getTitle().toLowerCase().startsWith("listado");
          }
		});
		
		return returnPage;
		
	}
	
	public ListadoClubsPage cancelar(){
		
		// se simula el click sobre el botón de 'Cancelar'
		ListadoClubsPage returnPage = altaClubPage.cancelar();
		
		// se espera unos 5 segundos para que se cargue la página
		(new WebDriverWait(driver, 5)).until(new ExpectedCondition<Boolean>() {
          public Boolean apply(WebDriver d) {
              return d.getTitle().toLowerCase().startsWith("listado");
          }
		});
		
		return returnPage;
		
	}
}
