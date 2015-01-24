package cabanas.garcia.orienteering.web.controllers.club;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ClubVolverControllerTest {

	private ClubController controller;

	@Mock
	private HttpServletRequest mockRequest;
	
	@Before
	public void setUp(){
	
		controller = new ClubController();
		
	}
	
	@Test
	public void vovler_deberia_devolver_la_vista_de_listado_de_clubs(){
		
		// GIVEN
		
		// WHEN
		String vistaActual = controller.volver();
		
		// THEN
		assertThat("La vista debería ser " + ClubController.VISTA_ADMIN_CLUBS_LISTADO, vistaActual, is(equalTo(ClubController.VISTA_ADMIN_CLUBS_LISTADO)));
		
	}
}
