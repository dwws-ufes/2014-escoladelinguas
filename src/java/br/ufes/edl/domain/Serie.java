package br.ufes.edl.domain;

import br.ufes.edl.enums.Nivel;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author vicente
 */
@Entity
public class Serie extends Model implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	private Nivel nivel;

	private String codigo;

	@ManyToOne(fetch = FetchType.EAGER)
	private Curso curso;

	@OneToMany(mappedBy = "serie", cascade = CascadeType.ALL)
	private Set<Turma> turmas;

	@OneToOne
	private Serie prerequisito;

	public Serie()
	{
		curso = new Curso();
		turmas = new HashSet<>();
	}

	public Nivel getNivel()
	{
		return nivel;
	}

	public void setNivel(Nivel nivel)
	{
		this.nivel = nivel;
	}

	public String getCodigo()
	{
		return codigo;
	}

	public void setCodigo(String codigo)
	{
		this.codigo = codigo;
	}

	public Serie getPrerequisito()
	{
		return prerequisito;
	}

	public void setPrerequisito(Serie prerequisito)
	{
		this.prerequisito = prerequisito;
	}

	public Curso getCurso()
	{
		return curso;
	}

	public void setCurso(Curso curso)
	{
		this.curso = curso;
	}

	public Set<Turma> getTurmas()
	{
		return turmas;
	}

	public void setTurmas(Set<Turma> turmas)
	{
		this.turmas = turmas;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Serie)) {
			return false;
		}
		Serie other = (Serie) obj;
		if (this.getId() == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!this.getId().equals(other.getId())) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode()
	{
		int hash = 7;
		return hash;
	}
}
