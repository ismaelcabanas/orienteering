package cabanas.garcia.orienteering.web.controllers.club;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import java.util.ArrayList;
import java.util.Collection;

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

@RunWith(MockitoJUnitRunner.class)
public class ClubBuscarControllerTest {

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
	public void buscar_deberia_devolver_la_vista_de_listado_de_clubs(){
		
		// GIVEN
		ClubBusquedaForm clubFormBusqueda = ClubBusquedaForm.getBuilder().conNombre("pepe").build();
		
		// WHEN 
		String vista = controller.buscar(clubFormBusqueda, mockModel);
		
		// THEN
		assertThat("La vista resultado debería ser " + ClubController.VISTA_ADMIN_CLUBS_LISTADO, vista, is(equalTo(ClubController.VISTA_ADMIN_CLUBS_LISTADO)));
		
	}
	
	@Test
	public void buscar_deberia_invocar_al_servicio_de_busqueda_de_clubs(){
		
		// GIVEN
		ClubBusquedaForm clubFormBusqueda = ClubBusquedaForm.getBuilder().conNombre("pepe").build();
		
		// WHEN 
		controller.buscar(clubFormBusqueda, mockModel);
		
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
		controller.buscar(clubFormBusqueda, mockModel);
		
		// THEN
		ArgumentCaptor<Collection> argListadoClubs = ArgumentCaptor.forClass(Collection.class);
		
		verify(mockModel, times(1)).addAttribute(anyString(), argListadoClubs.capture());
		
		assertThat(argListadoClubs.getValue(), hasItems(clubDtoEsperado1, clubDtoEsperado2));
		
	}
}
