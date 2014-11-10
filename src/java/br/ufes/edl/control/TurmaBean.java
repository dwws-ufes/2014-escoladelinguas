/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.edl.control;

import br.ufes.edl.domain.Professor;
import br.ufes.edl.domain.Serie;
import br.ufes.edl.domain.Turma;
import br.ufes.edl.enums.DiaDaSemana;
import br.ufes.edl.service.CursoService;
import br.ufes.edl.service.ProfessorService;
import br.ufes.edl.service.SerieService;
import br.ufes.edl.service.TurmaService;
import java.io.Serializable;
import java.util.List;
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
public class TurmaBean implements GenericBean, Serializable
{
	private static final long serialVersionUID = 1L;

	@EJB
	private TurmaService turmaService;

	@EJB
	private CursoService cursoService;

	@EJB
	private ProfessorService professorService;

	@EJB
	private SerieService serieService;

	private Turma turma = new Turma();

	private Long turmaId;
	private Long cursoSelecionadoId;
	private Long serieSelecionadaId;
	private Long professorSelecionadoId;

	private List<Serie> seriesDisponiveis;
	private List<Professor> professoresDisponiveis;

	public Turma getTurma()
	{
		return turma;
	}

	public void setTurma(Turma turma)
	{
		this.turma = turma;
	}

	public Long getTurmaId()
	{
		return turmaId;
	}

	public void setTurmaId(Long turmaId)
	{
		this.turmaId = turmaId;
	}

	public Long getCursoSelecionadoId()
	{
		return cursoSelecionadoId;
	}

	public void setCursoSelecionadoId(Long cursoSelecionadoId)
	{
		this.cursoSelecionadoId = cursoSelecionadoId;
	}

	public Long getSerieSelecionadaId()
	{
		return serieSelecionadaId;
	}

	public void setSerieSelecionadaId(Long serieSelecionadaId)
	{
		this.serieSelecionadaId = serieSelecionadaId;
	}

	public Long getProfessorSelecionadoId()
	{
		return professorSelecionadoId;
	}

	public void setProfessorSelecionadoId(Long professorSelecionadoId)
	{
		this.professorSelecionadoId = professorSelecionadoId;
	}

	public void carregaSeriesProfessoresDisponiveis()
	{
		seriesDisponiveis = cursoService.getSeries(cursoSelecionadoId);
		professoresDisponiveis = professorService.getProfessores(cursoSelecionadoId);
	}

	public List<Serie> getSeriesDisponiveis()
	{
		return seriesDisponiveis;
	}

	public void setSeriesDisponiveis(List<Serie> seriesDisponiveis)
	{
		this.seriesDisponiveis = seriesDisponiveis;
	}

	public List<Professor> getProfessoresDisponiveis()
	{
		return professoresDisponiveis;
	}

	public void setProfessoresDisponiveis(List<Professor> professoresDisponiveis)
	{
		this.professoresDisponiveis = professoresDisponiveis;
	}

	public DiaDaSemana[] getDiasDaSemana()
	{
		return DiaDaSemana.values();
	}

	public List<Turma> getTurmas()
	{
		return turmaService.findAll();
	}

	@Override
	public String actionSalvar()
	{
		FacesMessage message;
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			Serie s = serieService.findOne(serieSelecionadaId);
			Professor p = professorService.findOne(professorSelecionadoId);
			turma.setSerie(s);
			turma.setProfessor(p);
			turmaService.save(turma);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Turma cadastrada com sucesso.", null);
			context.addMessage(null, message);
			return pageListar();
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar turma.", null);
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
			Serie s = serieService.findOne(serieSelecionadaId);
			Professor p = professorService.findOne(professorSelecionadoId);
			turma.setSerie(s);
			turma.setProfessor(p);
			turmaService.update(turma);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Turma atualizada com sucesso.", null);
			context.addMessage(null, message);
			return pageListar();
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao atualizar turma.", null);
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
			turmaService.delete(turmaId);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Turma excluída com sucesso.", null);
			context.addMessage(null, message);
		} catch (Exception ex) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao excluir turma.", null);
			context.addMessage(null, message);
		}
		return null;
	}

	@Override
	public String pageCriar()
	{
		turma = new Turma();
		return "/views/turma/criar.xhtml";
	}

	@Override
	public String pageEditar()
	{
		turma = turmaService.findOne(turmaId);
		cursoSelecionadoId = turma.getSerie().getCurso().getId();
		serieSelecionadaId = turma.getSerie().getId();
		professorSelecionadoId = turma.getProfessor().getId();
		return "/views/turma/editar.xhtml";
	}

	@Override
	public String pageDetalhes()
	{
		turma = turmaService.findOne(turmaId);
		return "/views/turma/detalhes.xhtml";
	}

	@Override
	public String pageListar()
	{
		return "/views/turma/listar.xhtml";
	}

}
