package cabanas.garcia.orienteering.dtos.club;

import java.io.Serializable;

/**
 * Clase que representa los datos del formulario de búsqueda de un club de orientación.
 * 
 * @author f009994r
 *
 */
public class ClubBusquedaForm implements Serializable {

	public static class Builder {

		private ClubBusquedaForm dto;
		
		
		public Builder() {
			dto = new ClubBusquedaForm();
		}


		public ClubBusquedaForm build() {
			return dto;
		}

	}

	private static final long serialVersionUID = 1L;

	
}
