/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.edl.service;

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
public class SerieService extends GenericService<Serie>
{
	@Override
	public Serie findOne(Long id)
	{
		return this.getEm().find(Serie.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Serie> findAll()
	{
		return this.getEm().createQuery("SELECT s FROM Serie s").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Serie> findSeriesFromCurso(Long cursoId)
	{
		return this.getEm().createQuery("SELECT s FROM Serie s WHERE s.curso.id = :id").setParameter(":id", cursoId).getResultList();
	}
}
