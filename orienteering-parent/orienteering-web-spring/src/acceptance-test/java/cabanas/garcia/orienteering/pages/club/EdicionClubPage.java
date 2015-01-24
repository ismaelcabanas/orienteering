package cabanas.garcia.orienteering.pages.club;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cabanas.garcia.orienteering.pages.OrienteeringPage;

public class EdicionClubPage extends OrienteeringPage {

	private WebElement btnCancelar;
	private WebElement txtNombre;
	private WebElement btnActualizar;
	
	public EdicionClubPage(WebDriver driver) {
		super(driver);
	
		this.txtNombre = findById("txtNombre");
		this.btnCancelar = findById("btnCancelar");
		this.btnActualizar = findById("btnActualizar");
		
	}
	
	public void rellenaFormulario(String nombre) {
		
		// simulo el rellenado del formulario
		this.txtNombre.clear();
		this.txtNombre.sendKeys(nombre);
		
	}

	public ListadoClubsPage cancelar() {
		
		// simulo el click sobre el botón de crear
		this.btnCancelar.click();
		
		return new ListadoClubsPage(driver);
				
	}

	public ListadoClubsPage actualizar() {
		
		this.btnActualizar.click();
		
		return new ListadoClubsPage(driver);
	}
}
