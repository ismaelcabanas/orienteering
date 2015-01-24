package cabanas.garcia.orienteering.web.controllers.club;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
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

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import cabanas.garcia.orienteering.ColumnSensingFlatXMLDataSetLoader;
import cabanas.garcia.orienteering.dtos.club.ClubDto;
import cabanas.garcia.orienteering.web.ConstantesWebTest;
import cabanas.garcia.orienteering.web.util.mensaje.MensajeUsuario;

/**
 * Esta clase realiza test de integración para el controlador {@link ClubController}.
 * 
 * Se crea una interface mock, MockMvc, para enviar peticiones al controlador y validar la respuesta.
 * 
 * @author f009994r
 *
 */
//Para indicarle a Spring que configure un contenedor web configurado para testing
@WebAppConfiguration
// Para indicarle a JUnit que ejecute los test con el runner de Spring
@RunWith(SpringJUnit4ClassRunner.class) // Con esta anotaci�n arrancamos el contexto de Spring
// Para indicar a Spring dónde se encuentran los ficheros de configuración
@ContextConfiguration(locations = {"classpath:META-INF/spring/applicationContext.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    //TransactionDbUnitTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class })
@DbUnitConfiguration(dataSetLoader = ColumnSensingFlatXMLDataSetLoader.class)
public class ITClubAltaController {

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
	@ExpectedDatabase(value="clubs-alta-esperado.xml", assertionMode=DatabaseAssertionMode.NON_STRICT)
	public void la_peticion_de_alta_de_club_deberia_devolver_la_vista_del_listado_de_clubs() throws Exception{
		
		// GIVEN
		MockHttpServletRequestBuilder peticionAltaClub = post(ClubControllerPaths.ALTA)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param(ConstantesWebTest.CAMPO_FORM_ALTA_NOMBRE, "testClub");
		
		MensajeUsuario mensajeUsuarioEsperado = new MensajeUsuario("texto.accion.alta");
		
		// WHEN
		ResultActions respuestaAlta = mockMvc.perform(peticionAltaClub);
				
		// THEN		
		respuestaAlta
				.andExpect(status().isOk())
				.andExpect(view().name(ClubController.VISTA_ADMIN_CLUBS_LISTADO))
				.andExpect(forwardedUrl("/WEB-INF/jsp/" + ClubController.VISTA_ADMIN_CLUBS_LISTADO + ".jsp"))
				.andExpect(request().attribute(ClubController.REQUEST_ATTRIBUTE_MENSAJE, mensajeUsuarioEsperado));
				//.andExpect(model().attribute("listado", Matchers.anyCollectionOf(ClubDto.class)));
		
	}
	
}
