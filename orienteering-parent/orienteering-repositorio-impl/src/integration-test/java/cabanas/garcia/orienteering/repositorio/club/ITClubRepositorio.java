package cabanas.garcia.orienteering.repositorio.club;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import cabanas.garcia.orienteering.modelo.club.Club;
import cabanas.garcia.orienteering.repositorio.club.api.ClubRepositorio;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/META-INF/spring/applicationContext-repositorios.xml",
	"classpath:/META-INF/spring/applicationContext-persistence.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ITClubRepositorio {

	private static final String UN_NOMBRE = "Un nombre";

	private static final String OTRO_NOMBRE = "Otro nombre";
	
	@Resource
	ClubRepositorio repo;
	
	@Test
	public void deberia_persistir_un_club(){
		
		// GIVEN		
		Club club = Club.getBuilder().conNombre(UN_NOMBRE).build();
		
		// WHEN 
		Club clubPersistido = repo.save(club);
		
		// THEN
		assertThat("El identificador del club no puede ser nulo", clubPersistido.getId(), is(notNullValue()));
		assertThat("El nombre del club debería ser " + UN_NOMBRE, clubPersistido.getNombre(), is(equalTo(UN_NOMBRE)));
		
	}
	
	@Test
	public void deberia_buscar_un_club_por_id(){
		
		// GIVEN
		Club clubPersistido = Club.getBuilder().conNombre(UN_NOMBRE).build();
		repo.save(clubPersistido);
		
		// WHEN
		Club club = repo.findOne(clubPersistido.getId());
		
		// THEN
		assertThat("El identificador del club debe ser " + clubPersistido.getId(), club.getId(), is(equalTo(clubPersistido.getId())));
		assertThat("El nombre del club debería ser " + UN_NOMBRE, club.getNombre(), is(equalTo(UN_NOMBRE)));
		
	}
	
	@Test
	public void deberia_actualizar_un_club(){

		// GIVEN
		Club clubPersistido = Club.getBuilder().conNombre(UN_NOMBRE).build();
		repo.save(clubPersistido);
		clubPersistido.setNombre(OTRO_NOMBRE);
		
		// WHEN
		Club club = repo.save(clubPersistido);
		
		// THEN
		assertThat("El identificador del club debe ser " + clubPersistido.getId(), club.getId(), is(equalTo(clubPersistido.getId())));
		assertThat("El nombre del club debería ser " + OTRO_NOMBRE, club.getNombre(), is(equalTo(OTRO_NOMBRE)));
		
	}
	
}
