package br.ufes.edl.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author vicente
 */
@MappedSuperclass
public abstract class Model
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)

	private Long id;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}
}
