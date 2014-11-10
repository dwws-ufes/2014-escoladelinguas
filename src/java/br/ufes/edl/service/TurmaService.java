/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.edl.service;

import br.ufes.edl.domain.Turma;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author vicente
 */
@Stateless
@LocalBean
public class TurmaService extends GenericService<Turma>
{
	@Override
	public Turma findOne(Long id)
	{
		return this.getEm().find(Turma.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Turma> findAll()
	{
		return this.getEm().createQuery("SELECT t FROM Turma t").getResultList();
	}
}
