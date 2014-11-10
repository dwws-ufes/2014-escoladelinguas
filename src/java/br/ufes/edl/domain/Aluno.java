package br.ufes.edl.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vicente
 */
@Entity
public class Aluno extends Model implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nome;

	private String cpf;

	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "join_aluno_turma", joinColumns = {
		@JoinColumn(name = "aluno_id", referencedColumnName = "id")}, inverseJoinColumns = {
		@JoinColumn(name = "turma_id", referencedColumnName = "id")
	})
	private Set<Turma> turmas;

	public Aluno()
	{
		endereco = new Endereco();
		turmas = new HashSet<>();
		dataNascimento = new Date(0);
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getCpf()
	{
		return cpf;
	}

	public void setCpf(String cpf)
	{
		this.cpf = cpf;
	}

	public Date getDataNascimento()
	{
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento)
	{
		this.dataNascimento = dataNascimento;
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

	public void setTurmas(Set<Turma> turmas)
	{
		this.turmas = turmas;
	}

	@Override
	public int hashCode()
	{
		int hash = 7;
		return hash;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Aluno other = (Aluno) obj;
		return Objects.equals(this.getId(), other.getId());
	}
}
