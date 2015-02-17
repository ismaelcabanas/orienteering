package cabanas.garcia.orienteering.web.controllers.club;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hamcrest.Matchers;
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
import cabanas.garcia.orienteering.dtos.club.ClubBusquedaForm;
import cabanas.garcia.orienteering.dtos.club.ClubDto;
import cabanas.garcia.orienteering.web.ConstantesWebTest;
import cabanas.garcia.orienteering.web.controllers.RequestMappings;
import cabanas.garcia.orienteering.web.util.mensaje.MensajeUsuario;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

/**
 * Clase de tests de integración de la vista que gestiona las peticiones de búsqueda de clubs.
 * 
 * @author f009994r
 *
 */
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
public class ITClubBuscarController {

	private static final String NOMBRE_CLUB = "club";
	private static final String NOMBRE_CLUB_QUE_NO_EXISTE = "kkclub";

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
	public void cuando_se_realiza_una_busqueda_deberia_devolver_la_vista_de_listado_de_clubs_y_la_lista_de_registros_que_coinciden_con_el_criterio_de_busqueda() throws Exception{
		
		// GIVEN
		ClubDto clubEsperado1 = ClubDto.getBuilder().conId(999).conNombre("CLUB 999").build();
		ClubDto clubEsperado2 = ClubDto.getBuilder().conId(998).conNombre("Club Escondite Madrid").build();
		
		MockHttpServletRequestBuilder peticionBuscarClubsPorNombre = post(RequestMappings.REQUEST_MAPPING_CLUB_ADMIN + RequestMappings.REQUEST_MAPPING_SEARCH)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)				
				.param(ConstantesWebTest.CAMPO_FORM_BUSQUEDA_NOMBRE, NOMBRE_CLUB)
				//.requestAttr(ClubController.REQUEST_ATTRIBUTE_CLUB_BUSQUEDA_FORM, ClubBusquedaForm.getBuilder().build())
				;
		
		// WHEN
		// al hacer el envío, el framework de Spring se encarga de hacer el mapeo con lo parámetros del método al que se llama
		ResultActions respuestaBusqueda = mockMvc.perform(peticionBuscarClubsPorNombre);
		
		// THEN
		respuestaBusqueda.andExpect(status().isOk())
			.andExpect(view().name(ClubController.VISTA_ADMIN_CLUBS_LISTADO))
			.andExpect(forwardedUrl("/WEB-INF/jsp/" + ClubController.VISTA_ADMIN_CLUBS_LISTADO + ".jsp"))
			.andExpect(request().attribute(ClubController.REQUEST_ATTRIBUTE_CLUBS, hasItems(clubEsperado1, clubEsperado2)));
		
		
	}
	
	@Test
	@DatabaseSetup("clubs.xml")
	@ExpectedDatabase(value="clubs.xml", assertionMode=DatabaseAssertionMode.NON_STRICT)
	public void cuando_se_realiza_una_busqueda_que_no_devuelve_resultados_deberia_devolver_la_vista_de_listado_de_clubs_e_informar_al_usuario() throws Exception{
		
		// GIVEN
		MockHttpServletRequestBuilder peticionBuscarClubsPorNombre = post(RequestMappings.REQUEST_MAPPING_CLUB_ADMIN + RequestMappings.REQUEST_MAPPING_SEARCH)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)				
				.param(ConstantesWebTest.CAMPO_FORM_BUSQUEDA_NOMBRE, NOMBRE_CLUB_QUE_NO_EXISTE)
				//.requestAttr(ClubController.REQUEST_ATTRIBUTE_CLUB_BUSQUEDA_FORM, ClubBusquedaForm.getBuilder().build())
				;
		
		// WHEN
		// al hacer el envío, el framework de Spring se encarga de hacer el mapeo con lo parámetros del método al que se llama
		ResultActions respuestaBusqueda = mockMvc.perform(peticionBuscarClubsPorNombre);
		
		// THEN
		respuestaBusqueda.andExpect(status().isOk())
			.andExpect(view().name(ClubController.VISTA_ADMIN_CLUBS_LISTADO))
			.andExpect(forwardedUrl("/WEB-INF/jsp/" + ClubController.VISTA_ADMIN_CLUBS_LISTADO + ".jsp"))
			.andExpect(request().attribute(ClubController.REQUEST_ATTRIBUTE_MENSAJE, is(equalTo(new MensajeUsuario("texto.accion.buscar.sinresultado")))));
				
	}
}
