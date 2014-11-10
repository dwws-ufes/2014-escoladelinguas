/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.edl.service;

import br.ufes.edl.domain.Aluno;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author vicente
 */
@Stateless
@LocalBean
public class AlunoService extends GenericService<Aluno>
{
	@Override
	public Aluno findOne(Long id)
	{
		return this.getEm().find(Aluno.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Aluno> findAll()
	{
		return this.getEm().createQuery("SELECT a FROM Aluno a").getResultList();
	}
}
