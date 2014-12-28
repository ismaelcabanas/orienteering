package cabanas.garcia.orienteering.modelo.club;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@Entity
@Table(name = "clubs")
public class Club implements Serializable {

	public static final class ClubBuilder {

		private Club bean;

		public ClubBuilder() {
			bean = new Club();
		}

		public ClubBuilder conNombre(String nombre) {
			bean.setNombre(nombre);
			return this;
		}

		public Club build() {
			return bean;
		}

		public ClubBuilder conId(Long id) {
			bean.setId(id);
			return this;
		}

	}

	private static final long serialVersionUID = 1L;

	public static ClubBuilder getBuilder() {
		return new ClubBuilder();
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	

	@Column(name = "nombre", nullable = false, length = 100, unique=true)	
	private String nombre;

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public void actualizar(String nuevoNombre) {
		this.nombre = nuevoNombre;		
	}

}
