package cabanas.garcia.orienteering.web.controllers.club;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;

import cabanas.garcia.orienteering.dtos.club.ClubBusquedaForm;
import cabanas.garcia.orienteering.dtos.club.ClubDto;
import cabanas.garcia.orienteering.servicios.club.api.ClubServicio;
import cabanas.garcia.orienteering.web.util.mensaje.MensajeUsuario;

@RunWith(MockitoJUnitRunner.class)
public class ClubBuscarControllerTest {

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
	public void buscar_deberia_devolver_la_vista_de_listado_de_clubs(){
		
		// GIVEN
		ClubBusquedaForm clubFormBusqueda = ClubBusquedaForm.getBuilder().conNombre("pepe").build();
		
		// WHEN 
		String vista = controller.buscar(clubFormBusqueda, mockRequest);
		
		// THEN
		assertThat("La vista resultado debería ser " + ClubController.VISTA_ADMIN_CLUBS_LISTADO, vista, is(equalTo(ClubController.VISTA_ADMIN_CLUBS_LISTADO)));
		
	}
	
	@Test
	public void buscar_deberia_invocar_al_servicio_de_busqueda_de_clubs(){
		
		// GIVEN
		ClubBusquedaForm clubFormBusqueda = ClubBusquedaForm.getBuilder().conNombre("pepe").build();
		
		// WHEN 
		controller.buscar(clubFormBusqueda, mockRequest);
		
		// THEN
		ArgumentCaptor<ClubBusquedaForm> argClubBusquedaForm = ArgumentCaptor.forClass(ClubBusquedaForm.class);
		verify(mockClubServicio, times(1)).buscar(argClubBusquedaForm.capture());
		
		assertThat(argClubBusquedaForm.getValue().getNombre(), is(equalTo("pepe")));
		
	}
	
	@Test
	public void buscar_deberia_establecer_en_model_la_lista_de_clubs_que_coinciden_con_criterio_busqueda(){
		
		// GIVEN
		ClubBusquedaForm clubFormBusqueda = ClubBusquedaForm.getBuilder().conNombre("pepe").build();
		ClubDto clubDtoEsperado1 = ClubDto.getBuilder().conNombre("pepe1").build();
		ClubDto clubDtoEsperado2 = ClubDto.getBuilder().conNombre("pepe2").build();
		
		Collection<ClubDto> clubs = new ArrayList<ClubDto>();
		clubs.add(clubDtoEsperado1);
		clubs.add(clubDtoEsperado2);
		
		when(mockClubServicio.buscar(Mockito.any(ClubBusquedaForm.class))).thenReturn(clubs);
		
		// WHEN 
		controller.buscar(clubFormBusqueda, mockRequest);
		
		// THEN
		ArgumentCaptor<String> argKey = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<Collection> argListadoClubs = ArgumentCaptor.forClass(Collection.class);
		
		verify(mockRequest, times(1)).setAttribute(argKey.capture(), argListadoClubs.capture());
		verifyNoMoreInteractions(mockRequest);
		
		assertThat((Collection<ClubDto>)argListadoClubs.getValue(), containsInAnyOrder(clubDtoEsperado1, clubDtoEsperado2));
		assertThat(argKey.getValue(), is(equalTo(ClubController.REQUEST_ATTRIBUTE_CLUBS)));		
		
	}
	
	@Test
	public void buscar_deberia_mostrar_mensaje_informativo_si_la_busqueda_no_produce_resultados(){
		
		// GIVEN
		ClubBusquedaForm clubFormBusqueda = ClubBusquedaForm.getBuilder().conNombre("pepe").build();
		MensajeUsuario mensajeUsuarioEsperado = new MensajeUsuario("texto.accion.buscar.sinresultado");
		
		// WHEN
		controller.buscar(clubFormBusqueda, mockRequest);
		
		// THEN
		ArgumentCaptor<String> argMensajeKey = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<MensajeUsuario> argMensajeValue = ArgumentCaptor.forClass(MensajeUsuario.class);
		
		verify(mockRequest, times(1)).setAttribute(argMensajeKey.capture(), argMensajeValue.capture());
		verifyNoMoreInteractions(mockRequest);
		
		assertThat(argMensajeKey.getValue(), is(equalTo(ClubController.REQUEST_ATTRIBUTE_MENSAJE)));
		assertThat(argMensajeValue.getValue(), is(equalTo(mensajeUsuarioEsperado)));		
		
	}
}
