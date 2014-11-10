package br.ufes.edl.service;

import br.ufes.edl.domain.Model;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vicente
 * @param <T>
 */
abstract public class GenericService<T extends Model>
{
	@PersistenceContext
	private EntityManager em;

	abstract public T findOne(Long id);

	abstract public List<T> findAll();

	public EntityManager getEm()
	{
		return em;
	}

	public void setEm(EntityManager em)
	{
		this.em = em;
	}

	public void save(T object) throws Exception
	{
		em.persist(object);
		em.flush();
	}

	public void update(T object) throws Exception
	{
		em.merge(object);
		em.flush();
	}

	public void delete(Long id) throws Exception
	{
		T object = this.findOne(id);
		em.remove(object);
		em.flush();
	}
}
