package br.ufes.edl.domain;

import br.ufes.edl.enums.Formacao;
import br.ufes.edl.enums.Idioma;
import br.ufes.edl.enums.Nacionalidade;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author vicente
 */
@Entity
public class Professor extends Model implements Serializable
{
	private static final long serialVersionUID = 1L;

	@NotNull
	private String nome;

	@Enumerated(EnumType.STRING)
	private Nacionalidade nacionalidade;

	@Enumerated(EnumType.STRING)
	private Formacao formacao;

	private String instituicao;

	private String pais;

	@Enumerated(EnumType.STRING)
	private Idioma idioma;

	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "join_professor_curso", joinColumns = {
		@JoinColumn(name = "professor_id", referencedColumnName = "id")}, inverseJoinColumns = {
		@JoinColumn(name = "curso_id", referencedColumnName = "id")
	})
	private final Set<Curso> cursos;

	@OneToMany(mappedBy = "professor", fetch = FetchType.EAGER)
	private final Set<Turma> turmas;

	public Professor()
	{
		endereco = new Endereco();
		cursos = new HashSet<>();
		turmas = new HashSet<>();
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public Endereco getEndereco()
	{
		return endereco;
	}

	public void setEndereco(Endereco endereco)
	{
		this.endereco = endereco;
	}

	public Set<Turma> getTurmas()
	{
		return turmas;
	}

	public void addTurma(Turma turma)
	{
		turmas.add(turma);
	}

	public Set<Curso> getCursos()
	{
		return cursos;
	}

	public void addCurso(Curso curso)
	{
		cursos.add(curso);
	}

	public Nacionalidade getNacionalidade()
	{
		return nacionalidade;
	}

	public void setNacionalidade(Nacionalidade nacionalidade)
	{
		this.nacionalidade = nacionalidade;
	}

	public String getInstituicao()
	{
		return instituicao;
	}

	public void setInstituicao(String instituicao)
	{
		this.instituicao = instituicao;
	}

	public String getPais()
	{
		return pais;
	}

	public void setPais(String pais)
	{
		this.pais = pais;
	}

	public Idioma getIdioma()
	{
		return idioma;
	}

	public void setIdioma(Idioma idioma)
	{
		this.idioma = idioma;
	}

	public Formacao getFormacao()
	{
		return formacao;
	}

	public void setFormacao(Formacao formacao)
	{
		this.formacao = formacao;
	}
}
