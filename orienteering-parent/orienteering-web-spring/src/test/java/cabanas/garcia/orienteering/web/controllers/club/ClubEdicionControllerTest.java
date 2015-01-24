package cabanas.garcia.orienteering.web.controllers.club;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import javax.servlet.http.HttpServletRequest;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import cabanas.garcia.orienteering.dtos.club.ClubDto;
import cabanas.garcia.orienteering.dtos.club.ClubForm;
import cabanas.garcia.orienteering.servicios.club.api.ClubServicio;
import cabanas.garcia.orienteering.servicios.club.exceptions.ClubNoExisteException;

/**
 * Tests unitarios para el controlador de edición de clubs.
 * 
 * @author f009994r
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ClubEdicionControllerTest {

	private static final String NOMBRE_CLUB = "Pepe club";

	private static final long CLUB_ID = 1L;

	@Mock
	private ClubServicio mockClubServicio;
	
	private ClubController controller;

	@Mock
	private HttpServletRequest mockRequest;
	
	@Before
	public void setUp(){
	
		controller = new ClubController(mockClubServicio);
		
	}
	
	@Test
	public void edicion_deberia_devolver_la_vista_de_edicion_del_club_seleccionado() throws ClubNoExisteException{
		
		// GIVEN
		Long idClubSeleccionado = CLUB_ID;
		ClubDto clubDtoEsperado = ClubDto.getBuilder().conId(idClubSeleccionado).conNombre(NOMBRE_CLUB).build();
		
		when(mockClubServicio.buscarPorId(anyLong())).thenReturn(clubDtoEsperado);
		
		// WHEN
		String actual = controller.edicion(idClubSeleccionado, mockRequest);
		
		// THEN
		assertThat("La vista resultado debería ser " + ClubController.VISTA_ADMIN_CLUBS_EDICION, actual, is(equalTo(ClubController.VISTA_ADMIN_CLUBS_EDICION)));
		
	}
	
	@Test
	public void edicion_deberia_recuperar_el_club_seleccionado_del_repositorio_de_persistencia() throws ClubNoExisteException{
		
		// GIVEN
		Long idClubSeleccionado = 1L;
		ClubDto clubDtoEsperado = ClubDto.getBuilder().conId(idClubSeleccionado).conNombre(NOMBRE_CLUB).build();
		
		when(mockClubServicio.buscarPorId(anyLong())).thenReturn(clubDtoEsperado);
		
		// WHEN
		controller.edicion(idClubSeleccionado, mockRequest);
		
		// THEN
		ArgumentCaptor<Long> argIdClub = ArgumentCaptor.forClass(Long.class);
		
		verify(mockClubServicio, times(1)).buscarPorId(argIdClub.capture());
		verifyNoMoreInteractions(mockClubServicio);
		
		assertThat(argIdClub.getValue(), is(equalTo(idClubSeleccionado)));
		
	}
	
	@Test
	public void edicion_deberia_establecer_instancia_de_formulario_de_edicion_en_request_con_los_datos_del_club_seleccionado() throws ClubNoExisteException{
		
		// GIVEN
		Long idClubSeleccionado = 1L;
		ClubForm clubFormEsperado = ClubForm.getBuilder().conId(idClubSeleccionado).conNombre(NOMBRE_CLUB).build();		
		ClubDto clubDtoEsperado = ClubDto.getBuilder().conId(idClubSeleccionado).conNombre(NOMBRE_CLUB).build();
		
		when(mockClubServicio.buscarPorId(anyLong())).thenReturn(clubDtoEsperado);
		
		// WHEN
		controller.edicion(idClubSeleccionado, mockRequest);
		
		// THEN
		ArgumentCaptor<ClubForm> argClubForm = ArgumentCaptor.forClass(ClubForm.class);
		ArgumentCaptor<String> argKeyRequestAttribute = ArgumentCaptor.forClass(String.class);
		
		verify(mockRequest,times(1)).setAttribute(argKeyRequestAttribute.capture(), argClubForm.capture());
		
		assertThat(argKeyRequestAttribute.getValue(), is(equalTo(ClubController.REQUEST_ATTRIBUTE_CLUB_FORM)));
		assertThat(argClubForm.getValue(), is(clubFormEsperado));
	}
}
