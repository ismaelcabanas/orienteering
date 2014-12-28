package cabanas.garcia.orienteering.assertions;

import org.fest.assertions.Assertions;
import org.fest.assertions.GenericAssert;

import cabanas.garcia.orienteering.dtos.club.ClubDto;

public class ClubDtoAssert extends GenericAssert<ClubDtoAssert, ClubDto> {

	public ClubDtoAssert(ClubDto actual) {
		super(ClubDtoAssert.class, actual);
	}

	public static ClubDtoAssert assertThat(ClubDto actual){
		return new ClubDtoAssert(actual);
	}
	
	public ClubDtoAssert tieneNombre(String nombre){
		
		String mensajeError = String.format("El nombre esperado del club tenía que ser "
				+ "<%s> pero fue <%s>", nombre, actual.getNombre());
		
		Assertions.assertThat(actual.getNombre())
			.overridingErrorMessage(mensajeError)
			.isEqualTo(nombre);
		
		return this;
	}

	public ClubDtoAssert esIgualQue(ClubDto club) {
		
		String mensajeError = String.format("El club esperado tenía que ser "
				+ "<%s> pero fue <%s>", club, actual);
		
		Assertions.assertThat(actual)
			.overridingErrorMessage(mensajeError)
			.isEqualTo(club);
		
		return this;
		
	}

	public ClubDtoAssert tieneId(Long id) {
		
		String mensajeError = String.format("El id esperado del club tenía que ser "
				+ "<%s> pero fue <%s>", id, actual.getId());
		
		Assertions.assertThat(actual.getId())
			.overridingErrorMessage(mensajeError)
			.isEqualTo(id);
		
		return this;
		
	}

	public ClubDtoAssert tieneIdNoNulo() {
		
		String mensajeError = String.format("El id esperado del club no puede ser nulo");
		
		Assertions.assertThat(actual.getId())
			.overridingErrorMessage(mensajeError)
			.isNotNull();
		
		return this;
		
	}
}
