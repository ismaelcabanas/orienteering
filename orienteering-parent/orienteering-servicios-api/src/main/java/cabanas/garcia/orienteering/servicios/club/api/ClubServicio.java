package cabanas.garcia.orienteering.servicios.club.api;

import java.util.Collection;

import cabanas.garcia.orienteering.dtos.club.ClubBusquedaForm;
import cabanas.garcia.orienteering.dtos.club.ClubDto;
import cabanas.garcia.orienteering.dtos.club.ClubForm;

/**
 * Declara los m�todos utilizados para obtener y modificar la informaci�n de un Club de orientaci�n.
 * 
 * @author f009994r
 *
 */
public interface ClubServicio {

	/**
	 * Servicio que da de alta un club en el repositorio de almacenamiento proporcionados por <code>clubForm</code>.
	 *  
	 * @param clubForm
	 * 		Los datos del club a persistir.
	 * 
	 * @return
	 * 		Los datos del club persistidos.
	 */
	ClubDto alta(ClubForm clubForm);

	/**
	 * Este método busca los clubs de orientación del repositorio de almacenamiento que cumplen con un criterio de 
	 * búsqueda.
	 * 
	 * @param busquedaForm
	 * @return
	 */
	Collection<ClubDto> buscar(ClubBusquedaForm busquedaForm);

	/**
	 * Método que busca un club de orientación del repositorio de almacenamiento por identificador.
	 * 
	 * @param id
	 * @return
	 */
	ClubDto buscarPorId(Long id);

	/**
	 * Servicio que actualiza un club de orientación con los nuevos datos del club proporcionada por <code>clubForm</code>.
	 * 
	 * @param clubForm
	 * 		Los datos del club a actualizar.
	 * 
	 * @return
	 * 		Los datos del club actualizados.
	 */
	ClubDto actualiza(ClubForm clubForm);

}
