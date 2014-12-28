package cabanas.garcia.orienteering.adaptadores.club;

import cabanas.garcia.orienteering.dtos.club.ClubDto;
import cabanas.garcia.orienteering.dtos.club.ClubForm;
import cabanas.garcia.orienteering.modelo.club.Club;

public class ClubAdapter {

	public static Club toEntidad(final ClubForm clubForm) {
		
		return Club.getBuilder().conNombre(clubForm.getNombre()).build();
		
	}

	public static ClubDto toDto(Club bean) {
		
		return ClubDto.getBuilder()
				.conNombre(bean.getNombre())
				.conId(bean.getId())
				.build();
		
	}

}
