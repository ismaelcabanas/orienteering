package cabanas.garcia.orienteering.web.util.mensaje;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class MensajeUsuario implements Serializable{

	/**
	 * La key del mensaje a mostrar.
	 */
	private String key;
	private String tipo;

	public MensajeUsuario(String key) {
		this.key = key;
		this.tipo = "success";
	}

	private static final long serialVersionUID = 1L;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(key)
			.append(tipo)
			.toHashCode();		
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof MensajeUsuario){
			final MensajeUsuario otro = (MensajeUsuario) obj;
			return new EqualsBuilder()
				.append(key, otro.key)
				.append(tipo, otro.tipo)
				.isEquals();
		}
		
		return false;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	
	
}
