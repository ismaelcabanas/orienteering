package cabanas.garcia.orienteering.web.controllers.club;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;

import cabanas.garcia.orienteering.dtos.club.ClubBusquedaForm;
import cabanas.garcia.orienteering.dtos.club.ClubDto;
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
public class ClubControllerTest {

	@Mock
	private ClubServicio mockClubServicio;
	
	@Mock
	private Model mockModel;
	
	private ClubController controller;
	
	@Before
	public void setUp(){
	
		controller = new ClubController(mockClubServicio);
		
	}
	
	@Test
	public void nuevoClub_deberia_anadir_nuevo_clubForm_a_atributo_request(){
	
		// GIVEN
		
		// WHEN
		String vista = controller.nuevoClub(mockModel);
		
		// THEN
		verify(mockModel, times(1)).addAttribute("clubForm", ClubForm.getBuilder().build());
		
	}
	
	/**
	 * Este test comprueba que el controlador devuelve la vista adecuada.
	 */
	@Test
	public void nuevoClub_deberia_devolver_vista_formulario_nuevo_club(){
		
		// PREPARACIÓN
		
		// EJECUCIÓN
		String vista = controller.nuevoClub(mockModel);
		
		// VERIFICACIÓN
		assertThat(vista, is(equalTo(ClubController.VISTA_ADMIN_CLUBS_NUEVO)));
		
	}
	
	@Test
	public void crearClub_deberia_dar_de_alta_club(){
		
		// GIVEN
		ArgumentCaptor<ClubForm> argClubForm = ArgumentCaptor.forClass(ClubForm.class);
		ClubForm clubForm = ClubForm.getBuilder().build();
		
		// WHEN
		controller.crearClub(clubForm, mockModel);
		
		// THEN
		verify(mockClubServicio, times(1)).alta(argClubForm.capture());
		
	}
	
	@Test
	public void crearClub_deberia_devolver_la_vista_de_listado_de_clubs(){

		// GIVEN
		ClubForm clubForm = ClubForm.getBuilder().build();
		
		// WHEN
		String vista = controller.crearClub(clubForm, mockModel);
		
		// THEN
		assertThat("La vista devuelta debería ser " + ClubController.VISTA_ADMIN_CLUBS_LISTADO, vista, is(equalTo(ClubController.VISTA_ADMIN_CLUBS_LISTADO)));
		
	}
	
	@Test
	public void crearClub_deberia_establecer_mensaje_confirmacion(){		
		
		// GIVEN
		ClubForm clubForm = ClubForm.getBuilder().build();
		
		// WHEN
		String vista = controller.crearClub(clubForm, mockModel);
		
		// THEN
		assertThat("La vista devuelta debería ser " + ClubController.VISTA_ADMIN_CLUBS_LISTADO, vista, is(equalTo(ClubController.VISTA_ADMIN_CLUBS_LISTADO)));
		
	}
	
	@Test
	public void crearClub_deberia_anadir_mensaje_confirmacion_a_atributo_request(){

		// GIVEN
		ClubForm clubForm = ClubForm.getBuilder().build();
		
		// WHEN
		String vista = controller.crearClub(clubForm, mockModel);
		
		// THEN
		verify(mockModel, times(1)).addAttribute(anyString(), anyObject());
		
	}
	
	@Test
	public void cancelar_debería_la_vista_listado_de_clubs(){

		// GIVEN
		
		// WHEN
		String vista = controller.cancelar(mockModel);
		
		// THEN
		assertThat("La vista devuelta debería ser " + ClubController.VISTA_ADMIN_CLUBS_LISTADO, vista, is(equalTo(ClubController.VISTA_ADMIN_CLUBS_LISTADO)));		
		
	}
	
	@Test
	public void cancelar_deberia_anadir_mensaje_confirmacion_a_atributo_request(){
		
		// GIVEN
		
		// WHEN
		String vista = controller.cancelar(mockModel);
		
		// THEN
		verify(mockModel, times(1)).addAttribute(anyString(), anyObject());
		
	}
	
//	/**
//	 * Este test comprueba que 
//	 * <ul>
//	 * <li>la vista que devuelve el controlador es la adecuada</li>
//	 * <li>se llama al servicio de alta de un club</li>
//	 * <li>se llama al servicio de búsqueda de clubs</li>
//	 * <li>se establece el atributo <code>accion</code> del objeto Model a valor <code>crear</code></li>
//	 * <li>se establece el atributo <code>listado</code> del objeto Model a la lista de clubs</li>
//	 * </ul>
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void crearClub_deberia_dar_alta_club_y_devolver_vista_listado_clubs() throws Exception {
//
//		// PREPARACIÓN
//		ClubController clubController = new ClubController(mockClubServicio);
//		ClubForm clubForm = ClubTestUtil.crearClubForm(ClubTestUtil.NOMBRE_CLUB);		
//		
//		// EJECUCIÓN
//		String vista = clubController.crearClub(clubForm, mockModel);
//		
//		// VERIFICACIÓN
//		Mockito.verify(mockClubServicio, VerificationModeFactory.times(1)).alta(clubForm);
//		Mockito.verifyNoMoreInteractions(mockClubServicio);
//		Mockito.verify(mockModel, VerificationModeFactory.times(1)).addAttribute("accion", "crear");
//		Assert.assertThat(vista, Matchers.is(Matchers.equalTo(ClubController.VISTA_ADMIN_CLUBS_LISTADO)));
//		
//	}	
	
//	/**
//	 * Este test comprueba que 
//	 * <ul>
//	 * <li>la vista que devuelve el controlador es la adecuada</li>
//	 * <li>se llama al servicio de alta de un club</li>
//	 * <li>se llama al servicio de búsqueda de clubs</li>
//	 * <li>se establece el atributo <code>accion</code> del objeto Model a valor <code>crear</code></li>
//	 * <li>se establece el atributo <code>listado</code> del objeto Model a la lista de clubs</li>
//	 * </ul>
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void crearClub_deberia_dar_alta_club_y_devolver_listado_clubs() throws Exception {
//
//		// PREPARACIÓN
//		ClubBusquedaForm mockClubBusquedaForm = Mockito.mock(ClubBusquedaForm.class);
//		ClubController clubController = new ClubController(mockClubServicio, mockClubBusquedaForm);
//		ClubForm clubForm = new ClubForm.Builder().nombre("unNombre").build();		
//		Collection<ClubDto> listadoClubs = Mockito.anyCollectionOf(ClubDto.class);
//		Mockito.when(mockClubServicio.buscar(mockClubBusquedaForm)).thenReturn(listadoClubs);
//		
//		// EJECUCIÓN
//		String vista = clubController.crearClub(clubForm, mockModel);
//		
//		// VERIFICACIÓN
//		Mockito.verify(mockClubServicio, VerificationModeFactory.times(1)).alta(clubForm);
//		Mockito.verify(mockClubServicio, VerificationModeFactory.times(1)).buscar(mockClubBusquedaForm);
//		Mockito.verifyNoMoreInteractions(mockClubServicio);
//		Mockito.verify(mockModel, VerificationModeFactory.times(1)).addAttribute("accion", "crear");
//		Mockito.verify(mockModel, VerificationModeFactory.times(1)).addAttribute("listado", listadoClubs);
//		Assert.assertThat(vista, Matchers.is(Matchers.equalTo(ClubController.VISTA_ADMIN_CLUBS_LISTADO)));
//		
//	}
}
