package br.com.peladas.portalEventos.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Table;


@Table (name = "evento_usuario")
public class EventoUsuario implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EventoPK id;

	public EventoPK getId() {
		return id;
	}

	public void setId(EventoPK id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventoUsuario other = (EventoUsuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
