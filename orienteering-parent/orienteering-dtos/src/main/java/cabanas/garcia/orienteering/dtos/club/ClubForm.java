package cabanas.garcia.orienteering.dtos.club;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import cabanas.garcia.orienteering.dtos.club.ClubForm.Builder;

/**
 * Clase que representa los datos del formulario de alta o edición de un club de orientación.
 * 
 * @author f009994r
 *
 */
public class ClubForm implements Serializable{

	private static final long serialVersionUID = 1L;

	public static class Builder {

		private ClubForm instancia;
		
		
		public Builder() {
			instancia = new ClubForm();
		}


		public ClubForm build() {

			return instancia;
			
		}


		public Builder conNombre(String nombre) {
			instancia.setNombre(nombre);
			return this;
		}


		public Builder domicilio(String domicilio) {
			instancia.setDomicilio(domicilio);
			return this;
		}


		public Builder fechaAlta(String fechaAlta) {
			instancia.setFechaAlta(fechaAlta);
			return this;
		}


		public Builder localidad(String idLocalidad) {
			instancia.setLocalidad(idLocalidad);
			return this;
		}


		public Builder provincia(String idProvincia) {
			instancia.setProvincia(idProvincia);
			return this;
		}


		public Builder codigoPostal(String codigoPostal) {
			instancia.setCodigoPostal(codigoPostal);
			return this;
		}


		public Builder conId(Long id) {
			instancia.setId(id);
			return this;
		}

	}

	public static Builder getBuilder(){
		return new Builder();
	}		

	private String nombre;
	private String domicilio;
	private String fechaAlta;
	private String localidad;
	private String provincia;
	private String codigoPostal;
	private Long id;
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public void setProvincia(String idProvincia) {
		this.provincia = idProvincia;
	}

	public void setLocalidad(String idLocalidad) {
		this.localidad = idLocalidad;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
		
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public String getLocalidad() {
		return this.localidad;
	}

	public String getCodigoPostal() {
		return this.codigoPostal;
	}

	public String getDomicilio() {
		return this.domicilio;
	}
	
	

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(nombre)
			.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClubForm other = (ClubForm) obj;
		
		return new EqualsBuilder()
				.append(id, other.getId())
				.append(nombre, other.getNombre())
				.isEquals();
	}


}
