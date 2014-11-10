package br.ufes.edl.domain;

import br.ufes.edl.enums.Idioma;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author vicente
 */
@Entity
public class Curso extends Model implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String titulo;

	@Enumerated(EnumType.STRING)
	private Idioma idioma;

	@OneToMany(mappedBy = "curso", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Serie> series;

	@ManyToMany(mappedBy = "cursos", fetch = FetchType.EAGER)
	private Set<Professor> professores;

	public Curso()
	{
		series = new HashSet<>();
		professores = new HashSet<>();
	}

	public String getTitulo()
	{
		return titulo;
	}

	public void setTitulo(String titulo)
	{
		this.titulo = titulo;
	}

	public Idioma getIdioma()
	{
		return idioma;
	}

	public void setIdioma(Idioma idioma)
	{
		this.idioma = idioma;
	}

	public Set<Serie> getSeries()
	{
		return series;
	}

	public void setSeries(Set<Serie> series)
	{
		this.series = series;
	}

	public Set<Professor> getProfessores()
	{
		return professores;
	}

	public void setProfessores(Set<Professor> professores)
	{
		this.professores = professores;
	}
}
