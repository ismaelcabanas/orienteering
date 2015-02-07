package cabanas.garcia.orienteering.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrienteeringPage {

	protected WebDriver driver;
	
	public OrienteeringPage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Método que recupera un componente Web de una página por identificador.
	 * 
	 * El componente web debe tener establecida la propiedad id. 
	 * 
	 * El siguiente ejemplo muestra un componente web Text con la propiedad id establecida
	 * 
	 * 		<input id="txtNombre" type="text" value="Componente Web" />
	 * 
	 * @param id
	 * 		el identificador
	 * 
	 * @return
	 * 		Componente web. Devuelve null si no existe el componente web para el identificador indicado.
	 */
	protected WebElement findById(String id) {
		
		return this.driver.findElement(By.id(id));
	
	}
	
	/**
	 * Método que recupera el título de la página que tiene cargada el driver.
	 * 
	 * @return
	 */
	public String getTitulo() {
		
		return driver.getTitle();
		
	}
	
	protected String getValueAttribute(WebElement inputElement){
		
		if(inputElement != null){
			return inputElement.getAttribute("value");
		}
		
		return null;
	}
	
}
