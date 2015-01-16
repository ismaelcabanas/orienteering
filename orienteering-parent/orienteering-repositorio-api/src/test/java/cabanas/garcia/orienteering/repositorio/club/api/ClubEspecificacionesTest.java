package cabanas.garcia.orienteering.repositorio.club.api;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.jpa.domain.Specification;

import cabanas.garcia.orienteering.modelo.club.Club;
import cabanas.garcia.orienteering.modelo.club.Club_;

/**
 * Este test no tiene ningún valor, es dificil de seguir y mantener.
 * 
 * @author f009994r
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ClubEspecificacionesTest {

	private static final String CAMPO_BUSQUEDA_NOMBRE = "UN NOMBRE";

	private static final String CAMPO_BUSQUEDA_NOMBRE_LIKE_PATTERN = "%un nombre%";
	
	@Mock
	private CriteriaQuery mockCriteriaQuery;

	@Mock
	private Root<Club> mockClubRoot;

	@Mock
	private CriteriaBuilder mockCriteriaBuilder;

	@Test
	public void buscarPorNombre_deberia_crear_especificacion(){
		
		// GIVEN
		Path<String> mockNombrePath = mock(Path.class);
		when(mockClubRoot.get(Club_.nombre)).thenReturn(mockNombrePath);
		
		Expression<String> mockNombreToLowerExpression = mock(Expression.class);
		when(mockCriteriaBuilder.lower(mockNombrePath)).thenReturn(mockNombreToLowerExpression);
		
		Predicate mockNombreLikePredicate = mock(Predicate.class);
		when(mockCriteriaBuilder.like(mockNombreToLowerExpression, CAMPO_BUSQUEDA_NOMBRE_LIKE_PATTERN)).thenReturn(mockNombreLikePredicate);
		
		// WHEN
		Specification<Club> actual = ClubEspecificaciones.buscarPorNombre(CAMPO_BUSQUEDA_NOMBRE);
		Predicate predicadoActual = actual.toPredicate(mockClubRoot, mockCriteriaQuery, mockCriteriaBuilder);
		
		// THEN
		verify(mockClubRoot, times(1)).get(Club_.nombre);
		verifyNoMoreInteractions(mockClubRoot);
		
		verify(mockCriteriaBuilder, times(1)).lower(mockNombrePath);		
		verify(mockCriteriaBuilder, times(1)).like(mockNombreToLowerExpression, CAMPO_BUSQUEDA_NOMBRE_LIKE_PATTERN);
		verifyNoMoreInteractions(mockCriteriaBuilder);
		
		verifyZeroInteractions(mockCriteriaQuery, mockNombrePath, mockNombreToLowerExpression);
		
		assertThat(predicadoActual, is(equalTo(mockNombreLikePredicate)));
		
	}
}
