/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.edl.service;

import br.ufes.edl.domain.Curso;
import br.ufes.edl.domain.Serie;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author vicente
 */
@Stateless
@LocalBean
public class CursoService extends GenericService<Curso>
{
	@Override
	public Curso findOne(Long id)
	{
		return this.getEm().find(Curso.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Curso> findAll()
	{
		return this.getEm().createQuery("SELECT c FROM Curso c").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Serie> getSeries(Long id)
	{
		return this.getEm().createQuery("SELECT s FROM Serie s WHERE s.curso.id = :id").setParameter("id", id).getResultList();
	}
}
