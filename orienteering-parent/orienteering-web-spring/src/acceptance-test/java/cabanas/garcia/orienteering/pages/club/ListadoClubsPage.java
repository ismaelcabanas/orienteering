package cabanas.garcia.orienteering.pages.club;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

}
