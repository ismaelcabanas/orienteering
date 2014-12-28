package cabanas.garcia.orienteering.repositorio.club.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cabanas.garcia.orienteering.modelo.club.Club;


/**
 * Interface que proporciona los servicios necesarios para gestionar la 
 * persistencia en el repositorio de un club.
 * 
 * @author f009994r
 *
 */
@Repository
public interface ClubRepositorio extends JpaRepository<Club, Long>{

}
