package cabanas.garcia.orienteering.pages.club;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import cabanas.garcia.orienteering.pages.OrienteeringPage;

/**
 * Clase que representa la página de alta de un club.
 * 
 * @author f009994r
 *
 */
public class AltaClubPage extends OrienteeringPage{	
	
	@FindBy(id="txtNombre")
	private WebElement nombre;
	
//	@FindBy(id="txtDomicilio")
//	private WebElement domicilio;
//		
//	@FindBy(id="selProvincia")
//	private WebElement provincia;
//	
//	@FindBy(id="selLocalidad")
//	private WebElement localidad;
//	
//	@FindBy(id="txtCp")
//	private WebElement cp;
//	
	@FindBy(id="btnCrear")
	private WebElement btnCrear;

	private WebElement btnCancelar;
	
	public AltaClubPage(WebDriver driver) {
		
		super(driver);
		
		this.nombre = findById("txtNombre");
//		this.domicilio = findById("txtDomicilio");
//		this.cp = findById("txtCp");
//		this.provincia = findById("selProvincia");
//		this.localidad = findById("selLocalidad");
		this.btnCrear = findById("btnCrear");		
		this.btnCancelar = findById("btnCancelar");
	}	

	public void rellenaFormulario(String nombre) {
		
		// simulo el rellenado del formulario
		this.nombre.sendKeys(nombre);
		
	}

	public ListadoClubsPage crear() {
		
		// simulo el click sobre el botón de crear
		this.btnCrear.click();
		
		return new ListadoClubsPage(driver);
		
	}

	public ListadoClubsPage cancelar() {
		
		// simulo el click sobre el botón de crear
		this.btnCancelar.click();
		
		return new ListadoClubsPage(driver);
				
	}

}
