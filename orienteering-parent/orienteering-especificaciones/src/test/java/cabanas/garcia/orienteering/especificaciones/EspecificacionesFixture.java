package cabanas.garcia.orienteering.especificaciones;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.concordion.integration.junit3.ConcordionTestCase;
import org.concordion.integration.junit4.ConcordionRunner;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cabanas.garcia.orienteering.pages.OrienteeringPage;

@RunWith(ConcordionRunner.class) 
public class EspecificacionesFixture extends ConcordionTestCase {

	private static Logger LOGGER = LoggerFactory.getLogger(EspecificacionesFixture.class);
	
	private static Properties dbProperties = null;
	
	static{
		// leo el fichero de propiedades config.properties del perfil de test-aceptacion
		dbProperties = new Properties();
		InputStream is = EspecificacionesFixture.class.getClassLoader().getResourceAsStream("db.properties");
		if(is != null)
			try {
				dbProperties.load(is);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		else
			throw new RuntimeException("El fichero db.properties no se ha encontrado en el classpath.");
	}
	
	protected WebDriver driver;
	
	protected static final String URI_BASE = "http://localhost:8888/orienteering-web";

	private IDatabaseTester databaseTester;
		
	/**
	 * Indica el nombre del dataset que ejecutará un ejemplo
	 */
	private String nombreDataSet;


	@Before
	public void setUp() {
		
		LOGGER.debug("Ejecutando la especificación " + this.getClass().getCanonicalName());
		
		// inicializo el dataset con el que se ejecutará el test
		try {
			inicializaDbUnit();
		} catch (IllegalArgumentException e){
			LOGGER.warn("El test " + this.getClass().getCanonicalName() + " no tiene dataset.");
		} catch (Exception e) {
			throw new RuntimeException("Error al inicializar DbUnit para el test " + this.getClass().getName(), e);
		}
		
		// inicializo WebDriver
		inicializaDriver();
		
	}		

	@After
	public void tearDown() throws Exception{				
		
		LOGGER.debug("Finalizando la especificación " + this.getClass().getCanonicalName());
		
		// finalizo dbunit
		finalizaDbUnit();
		
		// finalizo webdriver
		finalizaDriver();
		
	}
	
	private void finalizaDbUnit() throws Exception {
		databaseTester.onTearDown();
	}

	private void inicializaDbUnit() throws Exception {
		
		// inicializo databaseTester
		databaseTester = new JdbcDatabaseTester(dbProperties.getProperty("db.driver"), 
				dbProperties.getProperty("db.url"), dbProperties.getProperty("db.username"), 
				dbProperties.getProperty("db.password"));
		
		// le indico al databaseTesteer la operación que debe realizar cuando se inicialice, en este caso limpia la base de datos y la rellena con el dataset
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		
		//databaseTester.setSetUpOperation(AutoCommitTransactionOperation.AUTO_COMMIT_TRANSACTION(DatabaseOperation.CLEAN_INSERT));
		
		// le indico al databaseTester la operación que debe realizar cuando se finalice
		databaseTester.setTearDownOperation(DatabaseOperation.NONE);
		
		// le indico al databaseTester qué dataset ejecutar		
		databaseTester.setDataSet(getDataSetParaInicializarTest(getNombreDataSet()));
						
		// hago que se inicialice el databaseTester y ejecute las operaciones
		databaseTester.onSetup();
		
//		Class.forName(dbProperties.getProperty("db.driver"));
//		Connection connection = DriverManager.getConnection(dbProperties.getProperty("db.url"), dbProperties.getProperty("db.username"), 
//				dbProperties.getProperty("db.password"));
//		PreparedStatement pst = connection.prepareStatement("SELECT * FROM CLUBS");
//		ResultSet rs = pst.executeQuery();
//		if(rs != null){
//			while(rs.next()){
//				System.out.println(rs.getLong(1) + " - " + rs.getString(2));
//			}
//		}
	}
	
	private IDataSet getDataSetParaInicializarTest(String nombreDelDataSet) throws DataSetException {
		
		if(nombreDelDataSet == null){
			throw new IllegalArgumentException("El test no necesita dataset.");
		}
		
		FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
		builder.setColumnSensing(true);		
		IDataSet dataSet = builder.build(getClass().getResourceAsStream(nombreDelDataSet));
		
		return dataSet;
	}

	private String getNombreDataSet() {
		return nombreDataSet;
	}
	
	public void setNombreDataSet(String nombreDataSet){
		this.nombreDataSet = nombreDataSet;
	}

	protected void inicializaDriver() {
//		FirefoxProfile profile = new FirefoxProfile();		
//		FirefoxBinary binary = new FirefoxBinary(new File("C:\\AEAT\\FirefoxPortable\\App\\Firefox\\firefox.exe"));
//		driver = new FirefoxDriver(binary, profile);
		driver = new HtmlUnitDriver(true);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	protected void finalizaDriver() {
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
