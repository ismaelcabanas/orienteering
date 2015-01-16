package cabanas.garcia.orienteering.dtos.club;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import cabanas.garcia.orienteering.dtos.club.ClubBusquedaForm.Builder;

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


		public Builder conNombre(String unNombreDeClub) {
			dto.setNombre(unNombreDeClub);
			return this;
		}

	}

	private static final long serialVersionUID = 1L;

	public static Builder getBuilder() {
		return new Builder();
		
	}

	private String nombre;

	public void setNombre(String unNombreDeClub) {
		this.nombre = unNombreDeClub;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	
}
