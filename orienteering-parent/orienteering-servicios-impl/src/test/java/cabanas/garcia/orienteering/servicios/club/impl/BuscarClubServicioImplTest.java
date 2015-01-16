package cabanas.garcia.orienteering.servicios.club.impl;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.core.IsCollectionContaining.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.jpa.domain.Specification;

import cabanas.garcia.orienteering.assertions.ClubDtoAssert;
import cabanas.garcia.orienteering.assertions.CollectionClubDtoAssert;
import cabanas.garcia.orienteering.dtos.club.ClubBusquedaForm;
import cabanas.garcia.orienteering.dtos.club.ClubDto;
import cabanas.garcia.orienteering.dtos.club.ClubForm;
import cabanas.garcia.orienteering.modelo.club.Club;
import cabanas.garcia.orienteering.repositorio.club.api.ClubEspecificaciones;
import cabanas.garcia.orienteering.repositorio.club.api.ClubRepositorio;
import cabanas.garcia.orienteering.servicios.club.api.ClubServicio;
import cabanas.garcia.orienteering.servicios.club.exceptions.ClubNoExisteException;

@RunWith(MockitoJUnitRunner.class)
public class BuscarClubServicioImplTest {

	private static final Long ID_CLUB = Long.valueOf(1);

	private static final String CAMPO_FORM_NOMBRE = "club";

	@Mock
	private ClubRepositorio mockServicioPersistencia;
	
	private ClubServicio servicio;
	
	@Before
	public void setUp(){
		servicio = new ClubServicioImpl(mockServicioPersistencia);
	}
	
	@Test
	public void cuando_se_realiza_busqueda_por_nombre_y_existen_clubs_que_coinciden_con_los_criterios_de_busqueda_entonces_se_devuelve_listado_de_clubs_que_coinciden_con_busqueda(){
		
		// GIVEN
		ClubBusquedaForm datosFormBusquedaClub = ClubBusquedaForm.getBuilder().conNombre(CAMPO_FORM_NOMBRE).build();
		
		Collection<ClubDto> clubsEsperados = new ArrayList<ClubDto>();		
		clubsEsperados.add(ClubDto.getBuilder().conId(1L).conNombre("club1").build());
		clubsEsperados.add(ClubDto.getBuilder().conId(2L).conNombre("club2").build());
		
		List<Club> clubsResultadoBusqueda = new ArrayList<Club>();
		clubsResultadoBusqueda.add(Club.getBuilder().conId(1L).conNombre("club1").build());
		clubsResultadoBusqueda.add(Club.getBuilder().conId(2L).conNombre("club2").build());
		
		when(mockServicioPersistencia.findAll(any(Specification.class))).thenReturn(clubsResultadoBusqueda);
				
		// WHEN
		Collection<ClubDto> clubs = servicio.buscar(datosFormBusquedaClub);
		
		// THEN
		assertThat("El listado de clubs resultado de la búsqueda no debe ser nulo", clubs, is(notNullValue()));
		assertThat("El número de clubs debe ser mayor que 0", clubs.size(), is(greaterThan(0)));
		CollectionClubDtoAssert.assertThat(clubs).tieneLosClubs(clubsEsperados);
//		assertThat("El resultado de clubs debe contener los clubs " + clubsEsperados, clubs, hasItems(clubsEsperados.toArray(new ClubDto[clubsEsperados.size()])));		
		
	}

	@Test
	public void cuando_se_realiza_busqueda_por_nombre_y_no_existen_clubs_que_coinciden_con_los_criterios_de_busqueda_entonces_se_devuelve_listado_vacio(){

		// GIVEN
		ClubBusquedaForm datosFormBusquedaClub = ClubBusquedaForm.getBuilder().conNombre(CAMPO_FORM_NOMBRE).build();
		
		Collection<ClubDto> clubsEsperados = new ArrayList<ClubDto>();		

		List<Club> clubsResultadoBusqueda = new ArrayList<Club>();

		when(mockServicioPersistencia.findAll(any(Specification.class))).thenReturn(clubsResultadoBusqueda);
				
		// WHEN
		Collection<ClubDto> clubs = servicio.buscar(datosFormBusquedaClub);
		
		// THEN
		assertThat("El listado de clubs resultado de la búsqueda no debe ser nulo", clubs, is(notNullValue()));
		assertThat("El número de clubs debe ser 0", clubs.size(), is(equalTo(0)));
		CollectionClubDtoAssert.assertThat(clubs).tieneLosClubs(clubsEsperados);
//		assertThat("El resultado de clubs debe contener los clubs " + clubsEsperados, clubs, hasItems(clubsEsperados.toArray(new ClubDto[clubsEsperados.size()])));
				
	}
	
	@Test
	public void dado_un_club_del_repositorio_cuando_se_busca_el_club_por_id_entonces_el_servicio_devuelve_la_informacion_del_club() throws Exception{
		
		// GIVEN
		Long identificador = ID_CLUB;
		String nombreEsperado = CAMPO_FORM_NOMBRE;
		
		Club club = Club.getBuilder().conId(identificador).conNombre(CAMPO_FORM_NOMBRE).build();
		
		when(mockServicioPersistencia.findOne(anyLong())).thenReturn(club);
		
		// WHEN
		ClubDto actual = servicio.buscarPorId(identificador);
		
		// THEN
		ClubDtoAssert.assertThat(actual)
			.tieneIdNoNulo()
			.tieneNombre(nombreEsperado);
		
		ArgumentCaptor<Long> argId = ArgumentCaptor.forClass(Long.class);
		
		verify(mockServicioPersistencia, times(1)).findOne(argId.capture());
		
		assertThat(argId.getValue(), is(equalTo(identificador)));
		
	}
	
	@Test(expected=ClubNoExisteException.class)
	public void cuando_se_busca_un_club_por_id_que_no_existe_en_el_repositorio_entonces_el_servicio_devuelve_la_excepcion_ClubNoExisteException() throws Exception{
		
		// GIVEN
		Long identificador = ID_CLUB;
		
		when(mockServicioPersistencia.findOne(anyLong())).thenReturn(null);
		
		// WHEN
		servicio.buscarPorId(identificador);
		
		// THEN				
	}
	
}
