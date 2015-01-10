package cabanas.garcia.orienteering.especificaciones;

import java.util.concurrent.TimeUnit;

import org.concordion.integration.junit3.ConcordionTestCase;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.PageFactory;

import cabanas.garcia.orienteering.pages.OrienteeringPage;

@RunWith(ConcordionRunner.class) 
public class EspecificacionesFixture extends ConcordionTestCase {

	protected WebDriver driver;
	
	protected static final String URI_BASE = "http://localhost:8888/orienteering-web";
	
	@Before
	public void setUp(){
		
		inicializaDriver();
		
	}

	
	@After
	public void tearDown(){
		
		cierraDriver();
		
	}
	
	protected void inicializaDriver() {
//		FirefoxProfile profile = new FirefoxProfile();		
//		FirefoxBinary binary = new FirefoxBinary(new File("C:\\AEAT\\FirefoxPortable\\App\\Firefox\\firefox.exe"));
//		driver = new FirefoxDriver(binary, profile);
		driver = new HtmlUnitDriver(true);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	protected void cierraDriver() {
		if(driver != null){
			driver.close();
			driver.quit();
		}
	}
	
	/**
	 * Método que navega a un URI determinada.
	 * 
	 * @param uri
	 * @param pageClassToProxy
	 * @return
	 */
	protected <T extends OrienteeringPage> T navega(String uri, Class<T> pageClassToProxy){
		
		// navega a uri
		driver.navigate().to(uri);
		
		// devuelve la clase que representa la página a la que se navega
		return PageFactory.initElements(driver, pageClassToProxy);
		
	}	
}
