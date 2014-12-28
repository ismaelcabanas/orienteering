package cabanas.garcia.orienteering.dtos.club;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ClubDto implements Serializable {

	public static class Builder {

		private ClubDto dto;
		
		public Builder(){
			dto = new ClubDto();
		}
		
		public ClubDto build() {
			return dto;
		}

		public Builder conNombre(String nombreClub) {
			dto.setNombre(nombreClub);
			return this;
		}

		public Builder direccion(String domicilioClub, String localidadClub,
				String provinciaClub, String cpClub) {
//			dto.setDomicilio(domicilioClub);
//			dto.setLocalidad(localidadClub);
//			dto.setProvincia(provinciaClub);
//			dto.setCodigoPostal(cpClub);
			return this;
		}

		public Builder conId(long id) {
			dto.setId(id);
			return this;
		}

	}

	private static final long serialVersionUID = 1L;

	public static Builder getBuilder() {
		return new Builder();
	}

	private String nombre;
//	private String domicilio;
//	private String localidad;
//	private String provincia;
//	private String codigoPostal;
	private Long id;

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
//	public String getDomicilio() {
//		return domicilio;
//	}
//	public void setDomicilio(String domicilio) {
//		this.domicilio = domicilio;
//	}
//	public String getLocalidad() {
//		return localidad;
//	}
//	public void setLocalidad(String localidad) {
//		this.localidad = localidad;
//	}
//	public String getProvincia() {
//		return provincia;
//	}
//	public void setProvincia(String provincia) {
//		this.provincia = provincia;
//	}
//	public String getCodigoPostal() {
//		return codigoPostal;
//	}
//	public void setCodigoPostal(String codigoPostal) {
//		this.codigoPostal = codigoPostal;
//	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(nombre)
			.toHashCode();
	}



	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ClubDto){
			final ClubDto otro = (ClubDto) obj;
			return new EqualsBuilder()
				.append(nombre, otro.nombre)
				.isEquals();
		}
		
		return false;
	}



	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}	
	
	
}
