package cabanas.garcia.orienteering.servicios.club.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cabanas.garcia.orienteering.adaptadores.club.ClubAdapter;
import cabanas.garcia.orienteering.dtos.club.ClubBusquedaForm;
import cabanas.garcia.orienteering.dtos.club.ClubDto;
import cabanas.garcia.orienteering.dtos.club.ClubForm;
import cabanas.garcia.orienteering.modelo.club.Club;
import cabanas.garcia.orienteering.repositorio.club.api.ClubEspecificaciones;
import cabanas.garcia.orienteering.repositorio.club.api.ClubRepositorio;
import cabanas.garcia.orienteering.servicios.club.api.ClubServicio;
import cabanas.garcia.orienteering.servicios.club.exceptions.ClubNoExisteException;

/**
 * Esta implementaci�n de la interfaz {@link ClubServicio} se comunica con la base de datos utilizando Spring Data JPA.
 * 
 * @author f009994r
 *
 */
@Service
public class ClubServicioImpl implements ClubServicio {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClubServicioImpl.class);
	
	@Resource
	private ClubRepositorio repositorio;
	
	
	public ClubServicioImpl() {
	}

	public ClubServicioImpl(ClubRepositorio repositorio) {
		this.repositorio = repositorio;
	}

	/* 
	 * Dado los datos de un club encapsulados en la instancia de ClubForm, adapta esta instancia a una instancia de la 
	 * entidad Club para que la clase que gestiona el repositorio de almacenamiento ClubRepositorio se encargue de 
	 * almacenar la instancia. Por último, devuelve la instancia de ClubDto que representa la entidad persistida. 
	 * 
	 * @see cabanas.garcia.orienteering.servicios.club.api.ClubServicio#alta(cabanas.garcia.orienteering.dtos.club.ClubForm)
	 */
	@Transactional
	public ClubDto alta(ClubForm clubForm) {
		
		// se crea la instancia de Club y 
		Club club = Club.getBuilder()
				.conNombre(clubForm.getNombre())
				.build();		
		
		LOGGER.debug("Creando un nuevo club con la siguiente información: " + club);
		
		// se persiste la entidad
		Club clubPersistido = repositorio.save(club);
		
		// se transforma la entidad en Dto
		return ClubAdapter.toDto(clubPersistido);
		
	}

	
	@Override
	@Transactional
	public ClubDto actualiza(ClubForm clubForm) {

		// TODO Usar el método del servicio!!
		
		// se busca la entidad
		Club club = repositorio.findOne(clubForm.getId());
		
		// se actualiza el club
		club.actualizar(clubForm.getNombre());
		
		// se persiste los datos actualizados del club
		repositorio.save(club);
		
		// se transforma la entidad en Dto
		return ClubAdapter.toDto(club);
		
	}

	@Override
	@Transactional(readOnly=true)
	public ClubDto buscarPorId(Long id) throws ClubNoExisteException {
		
		LOGGER.debug("Buscando club por identificador " + id);
		
		// recupero la entidad club por el identificador pasado por parámetro		
		Club club = repositorio.findOne(id);
		
		// si no existe el club para el identificador lanzamos excepción
		if(club == null){
			throw new ClubNoExisteException("El club con identificador " + id + " no existe.");
		}
		
		// creo el Dto a partir de la entidad
		ClubDto clubDto = ClubDto.getBuilder().conId(club.getId()).conNombre(club.getNombre()).build();
		
		return clubDto;
		
	}

	@Override
	public Collection<ClubDto> buscar(ClubBusquedaForm clubBusquedaForm) {
		
		Collection<ClubDto> clubs = null;
		
		LOGGER.debug("Buscando clubs con el criterio de búsqueda: " + clubBusquedaForm);
		
		// realizo la búsqueda contra el repositorio de almacenamiento
		List<Club> clubsResultadoBusqueda = repositorio.findAll(ClubEspecificaciones.buscarPorNombre(clubBusquedaForm.getNombre()));
		
		// convierto las entidades a dtos
		if(clubsResultadoBusqueda != null){
			clubs = new ArrayList<ClubDto>();
			for (Club club : clubsResultadoBusqueda) {
				ClubDto clubDto = ClubDto.getBuilder().conId(club.getId()).conNombre(club.getNombre()).build();
				clubs.add(clubDto);
			}		
		}
		
		return clubs ;
		
	}


	/**
	 * M�todo necesario para tests.
	 * 
	 * @param repositorio
	 */
	protected void setClubRepositorio(ClubRepositorio repositorio) {
		this.repositorio = repositorio;
	}

}
