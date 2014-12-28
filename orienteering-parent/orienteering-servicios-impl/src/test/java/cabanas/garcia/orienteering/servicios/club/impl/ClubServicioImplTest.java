package cabanas.garcia.orienteering.servicios.club.impl;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import cabanas.garcia.orienteering.assertions.ClubDtoAssert;
import cabanas.garcia.orienteering.dtos.club.ClubDto;
import cabanas.garcia.orienteering.dtos.club.ClubForm;
import cabanas.garcia.orienteering.modelo.club.Club;
import cabanas.garcia.orienteering.repositorio.club.api.ClubRepositorio;
import cabanas.garcia.orienteering.servicios.club.api.ClubServicio;

@RunWith(MockitoJUnitRunner.class)
public class ClubServicioImplTest {

	private static final long ID_CLUB = 1L;
	private static final long UN_ID = 1L;
	private static final String NOMBRE_CLUB = "un club";
	private static final String OTRO_NOMBRE_CLUB = "OTRO CLUB";
	
	@Mock
	private ClubRepositorio mockServicioPersistencia;
	
	private ClubServicio servicio;
	
	@Before
	public void setUp(){
		servicio = new ClubServicioImpl(mockServicioPersistencia);
	}

	@Test
	public void dado_los_datos_de_un_club_cuando_se_da_de_alta_entonces_se_persiste_el_club_en_el_sistema_con_la_informacion_del_club(){
				
		// GIVEN
		ClubForm form = ClubForm.getBuilder()
				.conNombre(NOMBRE_CLUB)
				.build();
		
		Club clubPersistido = Club.getBuilder()
				.conNombre(NOMBRE_CLUB)
				.conId(UN_ID)
				.build();
		
		when(mockServicioPersistencia.save(any(Club.class))).thenReturn(clubPersistido);
		
		// WHEN
		servicio.alta(form);
		
		// THEN
		ArgumentCaptor<Club> clubArg = ArgumentCaptor.forClass(Club.class);
		verify(mockServicioPersistencia, times(1)).save(clubArg.capture());
		verifyNoMoreInteractions(mockServicioPersistencia);
		
		assertThat("El nombre del club a persistir debe ser " + NOMBRE_CLUB, clubArg.getValue().getNombre(), is(equalTo(NOMBRE_CLUB)));
		
	}
	
	@Test
	public void dado_los_datos_de_un_club_cuando_se_da_de_alta_entonces_se_devuelven_los_datos_del_club_persistido(){
		
		// GIVEN
		ClubForm form = ClubForm.getBuilder()
				.conNombre(NOMBRE_CLUB)
				.build();
		
		Club clubPersistido = Club.getBuilder()
				.conNombre(NOMBRE_CLUB)
				.conId(UN_ID)
				.build();
		
		when(mockServicioPersistencia.save(any(Club.class))).thenReturn(clubPersistido);
		
		// WHEN
		ClubDto dtoCreado = servicio.alta(form);
		
		// THEN
		ClubDtoAssert.assertThat(dtoCreado)
			.tieneNombre(NOMBRE_CLUB)
			.tieneId(UN_ID);
		
	}
	
	@Test
	public void dado_un_club_persistido_en_el_sistema_si_se_modifica_el_nombre_del_club_cuando_se_actualiza_el_club_entonces_se_actualizan_en_el_sistema_los_nuevos_datos(){
		
		//GIVEN
		ClubForm clubFormPersistido = ClubForm.getBuilder().conId(ID_CLUB).conNombre(NOMBRE_CLUB).build();
		clubFormPersistido.setNombre(OTRO_NOMBRE_CLUB);
		
		Club clubPersistido = Club.getBuilder()
				.conNombre(NOMBRE_CLUB)
				.conId(UN_ID)
				.build();
		
		when(mockServicioPersistencia.findOne(anyLong())).thenReturn(clubPersistido);
		
		// WHEN
		servicio.actualiza(clubFormPersistido);
		
		// THEN
		ArgumentCaptor<Club> clubArg = ArgumentCaptor.forClass(Club.class);
		verify(mockServicioPersistencia, times(1)).findOne(ID_CLUB);
		verify(mockServicioPersistencia, times(1)).save(clubArg.capture());
		verifyNoMoreInteractions(mockServicioPersistencia);
		
		assertThat("El identificador del club debe ser " + ID_CLUB, clubArg.getValue().getId(), is(equalTo(ID_CLUB)));
		assertThat("El nombre del club a actualizar debe ser " + OTRO_NOMBRE_CLUB, clubArg.getValue().getNombre(), is(equalTo(OTRO_NOMBRE_CLUB)));
		
	}
	
	@Test
	public void dado_un_club_persistido_en_el_sistema_si_se_modifica_el_nombre_del_club_cuando_se_actualiza_el_club_entonces_se_devuelven_los_datos_del_club_actualizado(){
		
		//GIVEN
		ClubForm clubFormPersistido = ClubForm.getBuilder().conId(ID_CLUB).conNombre(NOMBRE_CLUB).build();
		clubFormPersistido.setNombre(OTRO_NOMBRE_CLUB);
		
		Club clubPersistido = Club.getBuilder()
				.conNombre(NOMBRE_CLUB)
				.conId(UN_ID)
				.build();
		
		when(mockServicioPersistencia.findOne(anyLong())).thenReturn(clubPersistido);
		
		// WHEN
		ClubDto dtoActualizado = servicio.actualiza(clubFormPersistido);
		
		// THEN
		assertThat("El nombre del club a actualizar debe ser " + OTRO_NOMBRE_CLUB, dtoActualizado.getNombre(), is(equalTo(OTRO_NOMBRE_CLUB)));
		
	}
}
