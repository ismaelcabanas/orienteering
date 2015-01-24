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

@RunWith(MockitoJUnitRunner.class)
public class ClubNuevoControllerTest {

	private ClubController controller;
	
	@Mock
	private HttpServletRequest mockRequest;
	
	@Before
	public void setUp(){
	
		controller = new ClubController();
		
	}
	
	@Test
	public void nuevoClub_deberia_establecer_formulario_club_como_attributo_en_request(){
	
		// GIVEN
		ClubForm clubFormEsperado = ClubForm.getBuilder().build();
		
		// WHEN
		controller.nuevo(mockRequest);
		
		// THEN
		ArgumentCaptor<ClubForm> argClubForm = ArgumentCaptor.forClass(ClubForm.class);
		ArgumentCaptor<String> argKeyRequestAttribute = ArgumentCaptor.forClass(String.class);

		verify(mockRequest,times(1)).setAttribute(argKeyRequestAttribute.capture(), argClubForm.capture());
		
		assertThat(argKeyRequestAttribute.getValue(), is(equalTo(ClubController.REQUEST_ATTRIBUTE_CLUB_FORM)));
		assertThat(argClubForm.getValue(), is(clubFormEsperado));
		
	}
	
	/**
	 * Este test comprueba que el controlador devuelve la vista adecuada.
	 */
	@Test
	public void nuevoClub_deberia_devolver_vista_formulario_nuevo_club(){
		
		// GIVEN
		
		// WHEN
		String vistaActual = controller.nuevo(mockRequest);
		
		// THEN
		assertThat("La vista resultado debería ser " + ClubController.VISTA_ADMIN_CLUBS_NUEVO, vistaActual, is(equalTo(ClubController.VISTA_ADMIN_CLUBS_NUEVO)));
		
	}
}
