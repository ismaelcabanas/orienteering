package cabanas.garcia.orienteering.pages.club;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;

import cabanas.garcia.orienteering.dtos.club.ClubDto;
import cabanas.garcia.orienteering.pages.OrienteeringPage;

public class ListadoClubsPage extends OrienteeringPage{
	
	private static final long DOS_SEGUNDOS = 2000;
	
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
		
		List<ClubDto> clubsDeConsulta = new ArrayList<ClubDto>();
		
		// busco el resultado de la consulta
		WebElement tablaClubs = findById("resultado_consulta"); // Elemento tbody
		
		List<WebElement> tdElements = tablaClubs.findElements(By.xpath("tr/td[1]"));
		for (WebElement tdElement : tdElements) {
			clubsDeConsulta.add(ClubDto.getBuilder().conNombre(tdElement.getText()).build());
		}
		
		return clubsDeConsulta;
	}

	public EdicionClubPage editar(String id) {
		
		String idLinkEdicion = "edtLinkClub" + id;
		
		WebElement linkEdicionClubSeleccionado = findById(idLinkEdicion);
		
		linkEdicionClubSeleccionado.click();
		
		return new EdicionClubPage(driver);
		
	}

	public DetalleClubPage ver(String id) {
		
		String idLinkDetalle = "detLinkClub" + id;
		
		WebElement linkEdicionClubSeleccionado = findById(idLinkDetalle);
		
		linkEdicionClubSeleccionado.click();
		
		return new DetalleClubPage(driver);
		
	}
	
	/**
	 * Método que obtiene el título del diálogo de baja.
	 * 
	 * El elemento que contiene el título del cuadro de dialogo debe tener la clase <code>modal-title</code>.
	 * 
	 * @return
	 */
	public String getTituloDialogoBaja(){
		
		WebElement tituloDialogoBaja = driver.findElement(By.className("modal-title"));
		
		if(tituloDialogoBaja != null)
			return tituloDialogoBaja.getText();
		
		return null;
		
	}

	public ListadoClubsPage informarBaja(String id) {
		
		String idLinkBaja = "bajLinkClub" + id;
				
		WebElement linkBajaClubSeleccionado = findById(idLinkBaja);
		
		linkBajaClubSeleccionado.click();
		
//		// espero hasta que se cargue correctamente el diálogo de baja
//		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
//				.withTimeout(10, TimeUnit.SECONDS)
//				.pollingEvery(1, TimeUnit.SECONDS);
//		
//		wait.until(new Function<WebDriver, Boolean>() {
//
//					@Override
//					public Boolean apply(WebDriver driver) {
//						List<WebElement> dialogos = driver.findElements(By.className("modal-title"));
//						int i = 0;
//						boolean resultado = false;
//						for (WebElement webElement : dialogos) {
//							System.out.println("KK: " + (i++) + " " + (webElement != null ? webElement.getText() : "sin datos"));
//							if(webElement != null && !webElement.getText().equals(""))
//								resultado = true;
//						}
//						return resultado;
//					}
//				});
		
		
		return this;
		
	}

	public ListadoClubsPage baja() {
		
		WebElement btnAceptarBaja = driver.findElement(By.xpath("//button[@data-bb-handler='aceptar']"));
		
		btnAceptarBaja.click();
		
		return this;
		
	}

	public ListadoClubsPage cancelarBaja() {
		
		WebElement btnCancelarBaja = driver.findElement(By.xpath("//button[@data-bb-handler='cancelar']"));
		
		btnCancelarBaja.click();
		
		return this;
		
	}

}
