package cabanas.garcia.orienteering.web.controllers.club;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
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
 * Tests unitarios para el controlador de modificación de clubs.
 * 
 * @author f009994r
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ClubActualizarControllerTest {

	@Mock
	private ClubServicio mockClubServicio;
	
	private ClubController controller;

	@Mock
	private HttpServletRequest mockRequest;
	
	private static final String NOMBRE_CLUB = "Pepe club";

	private static final long CLUB_ID = 1L;

	private static final String NUEVO_NOMBRE_CLUB = "Nuevo Pepe Club";
	
	@Before
	public void setUp(){
	
		controller = new ClubController(mockClubServicio);
		
	}
	
	@Test
	public void actualizar_deberia_mostrar_vista_de_listado_de_clubs_cuando_actualiza_un_club_correctamente(){
		
		// GIVEN
		ClubForm clubForm = ClubForm.getBuilder().conId(CLUB_ID).conNombre(NUEVO_NOMBRE_CLUB).build();
		
		// WHEN
		String vistaActual = controller.actualizar(clubForm, mockRequest);		
		
		// THEN
		assertThat("La vista resultado de actualizar un club debería ser " + 
				ClubController.VISTA_ADMIN_CLUBS_LISTADO, vistaActual, is(equalTo(ClubController.VISTA_ADMIN_CLUBS_LISTADO)));
		
	}
	
	@Test
	public void actualizar_deberia_invocar_al_servicio_de_actualizacion_de_club(){

		// GIVEN
		ClubForm clubForm = ClubForm.getBuilder().conId(CLUB_ID).conNombre(NUEVO_NOMBRE_CLUB).build();
		
		// WHEN
		controller.actualizar(clubForm, mockRequest);		
		
		// THEN
		ArgumentCaptor<ClubForm> argClubForm = ArgumentCaptor.forClass(ClubForm.class);
		
		verify(mockClubServicio, times(1)).actualiza(argClubForm.capture());
		
		assertThat(argClubForm.getValue(), is(equalTo(clubForm)));
		
	}
	
	@Test
	public void actualizar_deberia_establecer_mensaje_usuario_en_request_si_actualizacion_correcta(){

		// GIVEN
		ClubForm clubForm = ClubForm.getBuilder().conId(CLUB_ID).conNombre(NUEVO_NOMBRE_CLUB).build();
		MensajeUsuario mensajeUsuarioEsperado = new MensajeUsuario("texto.accion.actualizar");
		
		// WHEN
		controller.actualizar(clubForm, mockRequest);
		
		// THEN
		ArgumentCaptor<String> argKeyRequestAttribute = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<MensajeUsuario> argValueRequestAttribute = ArgumentCaptor.forClass(MensajeUsuario.class);
		
		verify(mockRequest, times(1)).setAttribute(argKeyRequestAttribute.capture(), argValueRequestAttribute.capture());
		
		assertThat(argKeyRequestAttribute.getValue(), is(equalTo(ClubController.REQUEST_ATTRIBUTE_MENSAJE)));
		assertThat(argValueRequestAttribute.getValue(), is(equalTo(mensajeUsuarioEsperado)));
		
	}
}
