package cabanas.garcia.orienteering.assertions;

import java.util.Collection;

import org.fest.assertions.Assertions;
import org.fest.assertions.GenericAssert;

import cabanas.garcia.orienteering.dtos.club.ClubDto;

public class CollectionClubDtoAssert extends GenericAssert<CollectionClubDtoAssert, Collection<ClubDto>> {

	public CollectionClubDtoAssert(Collection<ClubDto> actual){
		super(CollectionClubDtoAssert.class, actual);
	}
	
	public static CollectionClubDtoAssert assertThat(Collection<ClubDto> actual) {
		return new CollectionClubDtoAssert(actual);
	}

	public CollectionClubDtoAssert tieneLosClubs(Collection<ClubDto> clubsEsperados) {
		
		for (ClubDto clubDtoActual : actual) {			
			String mensajeError = String.format("El club <%s> no se "
						+ "encuentra entre los clubs <%s>", clubDtoActual, clubsEsperados);
			Assertions.assertThat(clubDtoActual)
				.overridingErrorMessage(mensajeError)
				.isIn(clubsEsperados);
		}
		
		return this;
		
	}

	

}
