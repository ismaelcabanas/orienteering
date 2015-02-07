package cabanas.garcia.orienteering.web.controllers.club;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import cabanas.garcia.orienteering.dtos.club.ClubForm;
import cabanas.garcia.orienteering.servicios.club.api.ClubServicio;
import cabanas.garcia.orienteering.web.util.mensaje.MensajeUsuario;

/**
 * Tests unitarios para el controlador de gestión de clubs.
 * 
 * @author f009994r
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ClubAltaControllerTest {

	private static final String NOMBRE_CLUB = "pepe";

	@Mock
	private ClubServicio mockClubServicio;
	
	@Mock
	private HttpServletRequest mockRequest;
	
	private ClubController controller;
	
	@Before
	public void setUp(){
	
		controller = new ClubController(mockClubServicio);
		
	}	
	
	@Test
	public void alta_deberia_devolver_la_vista_de_listado_de_clubs(){

		// GIVEN
		ClubForm clubForm = ClubForm.getBuilder().build();
		
		// WHEN
		String vista = controller.alta(clubForm, mockRequest);
		
		// THEN
		assertThat("La vista devuelta debería ser " + ClubController.VISTA_ADMIN_CLUBS_LISTADO, vista, is(equalTo(ClubController.VISTA_ADMIN_CLUBS_LISTADO)));
		
	}
	
	@Test
	public void alta_deberia_invocar_al_servicio_de_alta_club(){
		
		// GIVEN
		ClubForm clubForm = ClubForm.getBuilder().conNombre(NOMBRE_CLUB).build();
		
		// WHEN
		controller.alta(clubForm, mockRequest);
		
		// THEN
		ArgumentCaptor<ClubForm> argClubForm = ArgumentCaptor.forClass(ClubForm.class);		
		
		verify(mockClubServicio, times(1)).alta(argClubForm.capture());
		
		assertThat(argClubForm.getValue().getNombre(), is(equalTo(NOMBRE_CLUB)));
	}
	
	@Test
	public void alta_deberia_anadir_mensaje_confirmacion_a_atributo_request(){

		// GIVEN
		ClubForm clubForm = ClubForm.getBuilder().conNombre(NOMBRE_CLUB).build();
		MensajeUsuario mensajeUsuarioEsperado = new MensajeUsuario("texto.accion.alta");
		
		// WHEN
		controller.alta(clubForm, mockRequest);
		
		// THEN
		ArgumentCaptor<String> argKeyRequestAttribute = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<MensajeUsuario> argValueRequestAttribute = ArgumentCaptor.forClass(MensajeUsuario.class);
		
		verify(mockRequest, times(1)).setAttribute(argKeyRequestAttribute.capture(), argValueRequestAttribute.capture());
		
		assertThat(argKeyRequestAttribute.getValue(), is(equalTo(ClubController.REQUEST_ATTRIBUTE_MENSAJE)));
		assertThat(argValueRequestAttribute.getValue(), is(equalTo(mensajeUsuarioEsperado)));				
		
	}
	
}
