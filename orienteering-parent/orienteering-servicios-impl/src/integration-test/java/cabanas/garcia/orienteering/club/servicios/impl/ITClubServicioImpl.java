package cabanas.garcia.orienteering.club.servicios.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import cabanas.garcia.orienteering.assertions.ClubDtoAssert;
import cabanas.garcia.orienteering.assertions.CollectionClubDtoAssert;
import cabanas.garcia.orienteering.dtos.club.ClubBusquedaForm;
import cabanas.garcia.orienteering.dtos.club.ClubDto;
import cabanas.garcia.orienteering.dtos.club.ClubForm;
import cabanas.garcia.orienteering.modelo.club.Club;
import cabanas.garcia.orienteering.servicios.club.api.ClubServicio;
import cabanas.garcia.orienteering.servicios.club.exceptions.ClubNoExisteException;
import cabanas.garcia.orienteering.servicios.club.impl.ClubServicioImpl;

/**
 * Test de integraci�n del servicio {@link ClubServicioImpl}.
 * 
 * @author f009994r
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={ 
		"classpath:/META-INF/spring/applicationContext-servicios.xml", 
		"classpath:/META-INF/spring/applicationContext-repositorios.xml",
		"classpath:/META-INF/spring/applicationContext-persistence.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class})
public class ITClubServicioImpl {

	private static final String NOMBRE_CLUB = "un club";
	private static final String OTRO_NOMBRE_CLUB = "OTRO NOMBRE CLUB";
	private static final String CAMPO_BUSQUEDA_NOMBRE_LIKE = "club";
	private static final String CAMPO_BUSQUEDA_NOMBRE_LIKE_SIN_RESULTADOS = "XXXX";
	
	@Autowired
	private ClubServicio clubServicio;
	
	/**
	 * Este test de integraci�n comprueba que se persiste en el repositorio una instancia de la entidad {@link Club}.
	 * 
	 * @throws Exception
	 */
	@Test
	@DatabaseSetup("clubs-test-creacion.xml")
	public void alta_de_un_club() throws Exception {

		// GIVEN
		ClubForm clubForm = new ClubForm.Builder()
			.conNombre(NOMBRE_CLUB)			
			.build();

		// WHEN
		ClubDto clubPersistido = clubServicio.alta(clubForm);

		// THEN
		ClubDto _clubPersisido = clubServicio.buscarPorId(clubPersistido.getId());
		ClubDtoAssert.assertThat(_clubPersisido)
			.tieneNombre(NOMBRE_CLUB)
			.tieneIdNoNulo();
				
	}
	
	@Test
	public void dado_un_club_persistido_que_se_le_modifica_el_nombre_cuando_se_actualiza_entonces_se_actualiza_el_club_en_el_sistema_con_los_nuevos_datos_del_club() throws Exception {

		// GIVEN
		ClubForm clubForm = new ClubForm.Builder()
			.conNombre(NOMBRE_CLUB)			
			.build();
		
		ClubDto dto = clubServicio.alta(clubForm);
		
		clubForm.setId(dto.getId());
		clubForm.setNombre(OTRO_NOMBRE_CLUB);
		
		// WHEN
		ClubDto clubActualizado = clubServicio.actualiza(clubForm);

		// THEN
		ClubDtoAssert.assertThat(clubActualizado)
			.tieneNombre(OTRO_NOMBRE_CLUB)
			.tieneId(dto.getId());
				
	}	
	
	@Test
	@DatabaseSetup("clubs-test-listado.xml")
	public void buscar_por_nombre_cuando_existen_clubs_que_coinciden_con_criterios_de_busqueda(){
		
		// GIVEN 		
		ClubBusquedaForm busquedaForm = ClubBusquedaForm.getBuilder().conNombre(CAMPO_BUSQUEDA_NOMBRE_LIKE).build();
		Collection<ClubDto> clubsEsperados = new ArrayList<ClubDto>();
		clubsEsperados.add(ClubDto.getBuilder().conId(999).conNombre("CLUB 999").build());
		clubsEsperados.add(ClubDto.getBuilder().conId(998).conNombre("Club Escondite Madrid").build());
		
		// WHEN 
		Collection<ClubDto> actual = clubServicio.buscar(busquedaForm);
		
		// THEN
		CollectionClubDtoAssert.assertThat(actual).tieneLosClubs(clubsEsperados);
		
	}
	
	@Test
	@DatabaseSetup("clubs-test-listado.xml")
	public void buscar_por_nombre_cuando_no_existen_clubs_que_coinciden_con_criterios_de_busqueda(){
		
		// GIVEN 		
		ClubBusquedaForm busquedaForm = ClubBusquedaForm.getBuilder().conNombre(CAMPO_BUSQUEDA_NOMBRE_LIKE_SIN_RESULTADOS).build();
		Collection<ClubDto> clubsEsperados = new ArrayList<ClubDto>();
		
		// WHEN 
		Collection<ClubDto> actual = clubServicio.buscar(busquedaForm);
		
		// THEN
		CollectionClubDtoAssert.assertThat(actual).tieneLosClubs(clubsEsperados);
		
	}
	
	@Test
	@DatabaseSetup("clubs-test-listado.xml")
	public void buscarPorId_cuando_existe_club_en_repositorio() throws Exception{
		
		// GIVEN
		Long id = 999L;
		
		// WHEN
		ClubDto actual = clubServicio.buscarPorId(id);
		
		// THEN
		ClubDtoAssert.assertThat(actual)
			.tieneId(id)
			.tieneNombre("CLUB 999");
		
	}
	
	@Test(expected=ClubNoExisteException.class)
	@DatabaseSetup("clubs-test-listado.xml")
	public void buscarPorId_cuando_no_existe_club_en_repositorio() throws Exception{
		
		// GIVEN
		Long id = 99999L;
		
		// WHEN
		ClubDto actual = clubServicio.buscarPorId(id);
		
		// THEN
		
	}
	
//	/**
//	 * Este test comprueba que no se persiste una instancia de la entidad {@link Club} si no se establecen los campos 
//	 * obligatorios de la entidad, en este caso, el nombre del club.
//	 * 
//	 * El test intenta persitir una instancia del club sin establecer el nombre del club y comprueba que,
//	 *  se produce una excepci�n de validaci�n de la entidad.
//	 * 
//	 * @throws Exception
//	 */
//	@Test(expected=ConstraintViolationException.class)
//	public void alta_deberia_lanzar_excepcion_si_se_persiste_un_club_sin_proporcionar_los_campos_obligatorios() throws Exception {
//
//		// PREPARACI�N
//		ClubForm clubForm = new ClubForm.Builder()
//			.domicilio(DOMICILIO_CLUB)
//			.localidad(LOCALIDAD_CLUB)
//			.provincia(PROVINCIA_CLUB)
//			.codigoPostal(CP_CLUB)
//			.build();
//
//
//		// EJECUCI�N
//		clubServicio.alta(clubForm);
//		
//		// VERIFICACI�N		
//		
//	}
}
