package br.ufes.edl.domain;

import br.ufes.edl.enums.DiaDaSemana;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author vicente
 */
@Entity
public class Turma extends Model implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String codigo;

	private String semestre;

	private String horario;

	private Integer vagas;

	@Enumerated(EnumType.STRING)
	private DiaDaSemana diaDaSemana;

	@ManyToOne
	private Serie serie;

	@ManyToOne
	private Professor professor;

	@ManyToMany(mappedBy = "turmas", fetch = FetchType.EAGER)
	private Set<Aluno> alunos;

	public Turma()
	{
		professor = new Professor();
		serie = new Serie();
		alunos = new HashSet<>();
	}

	public Serie getSerie()
	{
		return serie;
	}

	public void setSerie(Serie serie)
	{
		this.serie = serie;
	}

	public String getCodigo()
	{
		return codigo;
	}

	public void setCodigo(String codigo)
	{
		this.codigo = codigo;
	}

	public String getSemestre()
	{
		return semestre;
	}

	public void setSemestre(String semestre)
	{
		this.semestre = semestre;
	}

	public String getHorario()
	{
		return horario;
	}

	public void setHorario(String horario)
	{
		this.horario = horario;
	}

	public DiaDaSemana getDiaDaSemana()
	{
		return diaDaSemana;
	}

	public void setDiaDaSemana(DiaDaSemana diaDaSemana)
	{
		this.diaDaSemana = diaDaSemana;
	}

	public Integer getVagas()
	{
		return vagas;
	}

	public void setVagas(Integer vagas)
	{
		this.vagas = vagas;
	}

	public Professor getProfessor()
	{
		return professor;
	}

	public void setProfessor(Professor professor)
	{
		this.professor = professor;
	}

	public Set<Aluno> getAlunos()
	{
		return alunos;
	}

	public void setAlunos(Set<Aluno> alunos)
	{
		this.alunos = alunos;
	}

}
