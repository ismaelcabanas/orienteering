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

import cabanas.garcia.orienteering.web.util.mensaje.MensajeUsuario;

@RunWith(MockitoJUnitRunner.class)
public class ClubCancelarControllerTest {

	private ClubController controller;
	
	@Mock
	private HttpServletRequest mockRequest;
	
	@Before
	public void setUp(){
	
		controller = new ClubController();
		
	}
	
	@Test
	public void cancelar_deberia_establecer_mensaje_informativo_usuario_en_request(){
	
		// GIVEN
		MensajeUsuario mensajeUsuarioEsperado = new MensajeUsuario("texto.accion.cancelar");
		
		// WHEN
		controller.cancelar(mockRequest);
		
		// THEN
		ArgumentCaptor<MensajeUsuario> argMensajeUsuario = ArgumentCaptor.forClass(MensajeUsuario.class);
		ArgumentCaptor<String> argKeyRequestAttribute = ArgumentCaptor.forClass(String.class);

		verify(mockRequest,times(1)).setAttribute(argKeyRequestAttribute.capture(), argMensajeUsuario.capture());
		
		assertThat(argKeyRequestAttribute.getValue(), is(equalTo(ClubController.REQUEST_ATTRIBUTE_MENSAJE)));
		assertThat(argMensajeUsuario.getValue(), is(equalTo(mensajeUsuarioEsperado)));
		
	}
	
	/**
	 * Este test comprueba que el controlador devuelve la vista adecuada.
	 */
	@Test
	public void cancelar_deberia_devolver_vista_listado_de_clubs(){
		
		// GIVEN
		
		// WHEN
		String vistaActual = controller.cancelar(mockRequest);
		
		// THEN
		assertThat("La vista resultado debería ser " + ClubController.VISTA_ADMIN_CLUBS_LISTADO, vistaActual, is(equalTo(ClubController.VISTA_ADMIN_CLUBS_LISTADO)));
		
	}
	
}
