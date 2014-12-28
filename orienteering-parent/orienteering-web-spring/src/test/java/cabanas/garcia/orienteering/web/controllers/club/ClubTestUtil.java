package cabanas.garcia.orienteering.web.controllers.club;

import cabanas.garcia.orienteering.dtos.club.ClubDto;
import cabanas.garcia.orienteering.dtos.club.ClubForm;

public final class ClubTestUtil {

	public static final String NOMBRE_CLUB = "Club de Prueba";

	public static ClubForm crearClubForm(String nombre) {
		return new ClubForm.Builder().conNombre(NOMBRE_CLUB).build();
	}

	public static ClubDto crearClubDto(long id, String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

}
