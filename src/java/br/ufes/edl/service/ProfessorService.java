/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.edl.service;

import br.ufes.edl.domain.Curso;
import br.ufes.edl.domain.Professor;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author vicente
 */
@Stateless
@LocalBean
public class ProfessorService extends GenericService<Professor>
{
	@Override
	public Professor findOne(Long id)
	{
		return this.getEm().find(Professor.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Professor> findAll()
	{
		return this.getEm().createQuery("SELECT p FROM Professor p").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Professor> getProfessores(Long cursoId)
	{
		Curso c = this.getEm().find(Curso.class, cursoId);
		Query q = this.getEm().createQuery("SELECT p FROM Professor p WHERE :c MEMBER OF p.cursos");
		q.setParameter("c", c);
		return q.getResultList();
	}
}
