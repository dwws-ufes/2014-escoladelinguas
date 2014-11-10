/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.edl.control;

import br.ufes.edl.domain.Curso;
import br.ufes.edl.domain.Professor;
import br.ufes.edl.enums.Formacao;
import br.ufes.edl.enums.Idioma;
import br.ufes.edl.enums.Nacionalidade;
import br.ufes.edl.service.CursoService;
import br.ufes.edl.service.ProfessorService;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author vicente
 */
@Named
@SessionScoped
public class ProfessorBean implements GenericBean, Serializable
{
	private static final long serialVersionUID = 1L;

	@EJB
	private ProfessorService professorService;

	@EJB
	private CursoService cursoService;

	private Long professorId;
	private Professor professor = new Professor();
	private Set<String> cursosSelecionados = new HashSet<>();

	public Long getProfessorId()
	{
		return professorId;
	}

	public void setProfessorId(Long professorId)
	{
		this.professorId = professorId;
	}

	public Professor getProfessor()
	{
		return professor;
	}

	public void setProfessor(Professor professor)
	{
		this.professor = professor;
	}

	public ProfessorService getProfessorService()
	{
		return professorService;
	}

	public void setProfessorService(ProfessorService professorService)
	{
		this.professorService = professorService;
	}

	public Set<String> getCursosSelecionados()
	{
		return cursosSelecionados;
	}

	public void setCursosSelecionados(Set<String> cursosSelecionados)
	{
		this.cursosSelecionados = cursosSelecionados;
	}

	public Nacionalidade[] getNacionalidades()
	{
		return Nacionalidade.values();
	}

	public Formacao[] getFormacao()
	{
		return Formacao.values();
	}

	public Idioma[] getIdiomas()
	{
		return Idioma.values();
	}

	public List<Professor> getProfessores()
	{
		return professorService.findAll();
	}

	@Override
	public String actionSalvar()
	{
		FacesMessage message;
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			for (String cursoId : cursosSelecionados) {
				Curso c = cursoService.findOne(Long.parseLong(cursoId));
				professor.addCurso(c);
			}
			professorService.save(professor);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Professor cadastrado com sucesso.", null);
			context.addMessage(null, message);
			return pageListar();
		} catch (Exception ex) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao cadastrar professor.", null);
			context.addMessage(null, message);
		}
		return null;
	}

	@Override
	public String actionEditar()
	{
		FacesMessage message;
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			for (String cursoId : cursosSelecionados) {
				Curso c = cursoService.findOne(Long.parseLong(cursoId));
				professor.addCurso(c);
			}
			professorService.update(professor);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Professor atualizado com sucesso.", null);
			context.addMessage(null, message);
			return pageListar();
		} catch (Exception ex) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao atualizar professor.", null);
			context.addMessage(null, message);
		}
		return null;
	}

	@Override
	public String actionDeletar()
	{
		FacesMessage message;
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			professorService.delete(professorId);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Professor excluído com sucesso.", null);
			context.addMessage(null, message);
		} catch (Exception ex) {
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Falha ao excluir professor.", null);
			context.addMessage(null, message);
		}
		return null;
	}

	@Override
	public String pageCriar()
	{
		professor = new Professor();
		return "/views/professor/criar.xhtml";
	}

	@Override
	public String pageEditar()
	{
		professor = professorService.findOne(professorId);
		for (Curso c : professor.getCursos()) {
			cursosSelecionados.add(c.getId().toString());
		}
		return "/views/professor/editar.xhtml";
	}

	@Override
	public String pageDetalhes()
	{
		professor = professorService.findOne(professorId);
		return "/views/professor/detalhes.xhtml";
	}

	@Override
	public String pageListar()
	{
		return "/views/professor/listar.xhtml";
	}

}
