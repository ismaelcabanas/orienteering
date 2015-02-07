package cabanas.garcia.orienteering.pages.club;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cabanas.garcia.orienteering.pages.OrienteeringPage;

public class DetalleClubPage extends OrienteeringPage {

	public DetalleClubPage(WebDriver driver) {
		super(driver);
	}

	public String nombreDelClub() {
		
		WebElement lblNombre = findById("lblNombre");
		
		String nombre = getValueAttribute(lblNombre);
		
		return nombre;
		
	}

	public ListadoClubsPage volver() {
		
		WebElement btnVolver = findById("btnVolver");
		
		btnVolver.click();
		
		return new ListadoClubsPage(driver);
		
	}

	
}
