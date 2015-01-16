package cabanas.garcia.orienteering.repositorio.club.api;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import cabanas.garcia.orienteering.modelo.club.Club;
import cabanas.garcia.orienteering.modelo.club.Club_;

public class ClubEspecificaciones {

	public static Specification<Club> buscarPorNombre(final String nombre) {
		return new Specification<Club>() {
			
			@Override
			public Predicate toPredicate(Root<Club> clubRoot, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				
				String nombreEnLike = getLikePattern(nombre);
				Path<String> path = clubRoot.<String>get(Club_.nombre);
				Expression<String> lowerExpression = cb.lower(path);
				Predicate predicateLike = cb.like(lowerExpression, nombreEnLike);
				return predicateLike;
			}
			
			private String getLikePattern(final String searchTerm) {
                StringBuilder pattern = new StringBuilder();
                pattern.append("%");
                pattern.append(searchTerm.toLowerCase());
                pattern.append("%");
                return pattern.toString();
            }
		};
	}

}
