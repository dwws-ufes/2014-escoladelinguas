package br.ufes.edl.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 *
 * @author vicente
 */
@Entity
public class Endereco extends Model implements Serializable
{
	private static final long serialVersionUID = 1L;

	@NotNull
	private String rua;

	@NotNull
	private String cep;

	@NotNull
	private String bairro;

	@NotNull
	private String telefone;

	@NotNull
	private String cidade;

	public String getRua()
	{
		return rua;
	}

	public void setRua(String rua)
	{
		this.rua = rua;
	}

	public String getCep()
	{
		return cep;
	}

	public void setCep(String cep)
	{
		this.cep = cep;
	}

	public String getBairro()
	{
		return bairro;
	}

	public void setBairro(String bairro)
	{
		this.bairro = bairro;
	}

	public String getTelefone()
	{
		return telefone;
	}

	public void setTelefone(String telefone)
	{
		this.telefone = telefone;
	}

	public String getCidade()
	{
		return cidade;
	}

	public void setCidade(String cidade)
	{
		this.cidade = cidade;
	}

}
