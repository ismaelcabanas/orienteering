package cabanas.garcia.orienteering.pages.club;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import cabanas.garcia.orienteering.dtos.club.ClubDto;
import cabanas.garcia.orienteering.pages.OrienteeringPage;

public class ListadoClubsPage extends OrienteeringPage{
	
	@FindBy(id="btnNuevo")
	private WebElement botonNuevoClub;
	
	public ListadoClubsPage(WebDriver driver) {
		super(driver);
	}

	public AltaClubPage nuevoClub() {
		
		botonNuevoClub.click();
		
		return new AltaClubPage(driver);
		
	}	

	public String getMensaje() {
		
		WebElement alert = driver.findElement(By.xpath("//div[starts-with(@class,'Metronic-alerts')]"));
		
		if(alert != null){
			return alert.getText();
		}
		
		return null;
		
	}

	public String getMenuSeleccionado() {
		// TODO Auto-generated method stub
		return null;
	}

	public void rellenaFormularioBusqueda(String nombre) {
		
		// busco el campo de texto nombre del formulario de búsqueda
		WebElement txtBusqNombre = findById("txtBusqNombre");
		txtBusqNombre.sendKeys(nombre);
				
	}

	public ListadoClubsPage buscar() {
		
		// busco el botón / link que realiza la búsqueda
		WebElement btnBuscar = findById("btnBuscar");
		btnBuscar.click();
		
		return new ListadoClubsPage(driver);
		
	}

	public List<ClubDto> getResultadoConsulta() {
		
		// busco el resultado de la consulta
		WebElement tablaClubs = findById("resultado_consulta"); // Elemento tbody
		
		List<WebElement> tdElements = tablaClubs.findElements(By.xpath("tr/td[1]"));
		for (WebElement tdElement : tdElements) {
			tdElement.getText();
		}
		
		return null;
	}

}
