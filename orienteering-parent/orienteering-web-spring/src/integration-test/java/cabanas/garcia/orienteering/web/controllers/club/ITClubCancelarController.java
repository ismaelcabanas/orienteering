package cabanas.garcia.orienteering.web.controllers.club;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import cabanas.garcia.orienteering.ColumnSensingFlatXMLDataSetLoader;
import cabanas.garcia.orienteering.dtos.club.ClubForm;
import cabanas.garcia.orienteering.web.util.mensaje.MensajeUsuario;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

//Para indicarle a Spring que configure un contenedor web configurado para testing
@WebAppConfiguration
//Para indicarle a JUnit que ejecute los test con el runner de Spring
@RunWith(SpringJUnit4ClassRunner.class) // Con esta anotaci�n arrancamos el contexto de Spring
//Para indicar a Spring dónde se encuentran los ficheros de configuración
@ContextConfiguration(locations = {"classpath:META-INF/spring/applicationContext.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
DirtiesContextTestExecutionListener.class,
//TransactionDbUnitTestExecutionListener.class,
TransactionalTestExecutionListener.class,
DbUnitTestExecutionListener.class })
@DbUnitConfiguration(dataSetLoader = ColumnSensingFlatXMLDataSetLoader.class)
public class ITClubCancelarController {

	// La interface mock que se utiliza para enviar peticiones simuladas
	private MockMvc mockMvc;
	
	@Autowired
	private ClubController clubController;

	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp(){
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build(); //.standaloneSetup(clubController).build();
	}
	
	@Test
	@DatabaseSetup("clubs.xml")
	@ExpectedDatabase(value="clubs.xml", assertionMode=DatabaseAssertionMode.NON_STRICT)
	public void la_peticion_de_cancelacion_de_alta_y_o_edicion_de_club_deberia_devolver_la_vista_de_listado_de_clubs() throws Exception {
		
		// GIVEN
		MockHttpServletRequestBuilder peticionCancelacionAltaEdicionClub = get(ClubControllerPaths.CANCELAR)
				.contentType(MediaType.TEXT_HTML);				
		
		// WHEN
		ResultActions respuesta = mockMvc.perform(peticionCancelacionAltaEdicionClub);
		
		// THEN
		respuesta.andExpect(status().isOk())
			.andExpect(view().name(ClubController.VISTA_ADMIN_CLUBS_LISTADO))
			.andExpect(forwardedUrl("/WEB-INF/jsp/" + ClubController.VISTA_ADMIN_CLUBS_LISTADO + ".jsp"))
			.andExpect(request().attribute(ClubController.REQUEST_ATTRIBUTE_MENSAJE, new MensajeUsuario("texto.accion.cancelar")));
		
	}
	
}
