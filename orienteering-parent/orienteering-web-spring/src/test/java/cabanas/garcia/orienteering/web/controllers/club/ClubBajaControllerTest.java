package cabanas.garcia.orienteering.web.controllers.club;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
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

@RunWith(MockitoJUnitRunner.class)
public class ClubBajaControllerTest {

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
	public void baja_deberia_devolver_la_vista_de_listado_de_clubs(){
		
		// GIVEN
		Long id = 1L; // el id del club a dar de baja
		String esperado = ClubController.VISTA_ADMIN_CLUBS_LISTADO;
		
		// WHEN
		String actual = controller.baja(id, mockRequest);
		
		// THEN
		assertThat("El resultado de la vista debería ser " + esperado, actual , is(equalTo(esperado)));
		
	}
	
	@Test
	public void baja_deberia_invocar_al_servicio_de_baja_de_club(){
		
		// GIVEN
		Long id = 1L; // el id del club a dar de baja
		
		// WHEN
		controller.baja(id, mockRequest);
		
		// THEN
		ArgumentCaptor<Long> argId = ArgumentCaptor.forClass(Long.class);
		
		verify(mockClubServicio, times(1)).baja(argId.capture());
		verifyNoMoreInteractions(mockClubServicio);
		
		assertThat(argId.getValue(), is(equalTo(id)));
		
	}
	
	@Test
	public void baja_deberia_establecer_mensaje_informativo_en_request_si_se_da_de_baja_el_club_correctamente(){
		
		// GIVEN
		Long id = 1L; // el id del club a dar de baja
		MensajeUsuario mensajeUsuarioEsperado = new MensajeUsuario("texto.accion.baja");
		
		// WHEN
		controller.baja(id, mockRequest);
		
		// THEN
		ArgumentCaptor<String> argKeyRequestAttribute = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<MensajeUsuario> argValueRequestAttribute = ArgumentCaptor.forClass(MensajeUsuario.class);
		
		verify(mockRequest, times(1)).setAttribute(argKeyRequestAttribute.capture(), argValueRequestAttribute.capture());
		
		assertThat(argKeyRequestAttribute.getValue(), is(equalTo(ClubController.REQUEST_ATTRIBUTE_MENSAJE)));
		assertThat(argValueRequestAttribute.getValue(), is(equalTo(mensajeUsuarioEsperado)));		
		
	}
}
