package cabanas.garcia.orienteering.modelo.club;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Una clase meta model utilizada para crear consultas sobre la información de un club.
 * @author f009994r
 *
 */
@StaticMetamodel(Club.class)
public class Club_ {

	public static volatile SingularAttribute<Club, String> nombre;
}
