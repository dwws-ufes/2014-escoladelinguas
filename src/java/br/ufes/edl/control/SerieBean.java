/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.edl.control;

import br.ufes.edl.domain.Curso;
import br.ufes.edl.domain.Serie;
import br.ufes.edl.enums.Nivel;
import br.ufes.edl.service.CursoService;
import br.ufes.edl.service.SerieService;
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
public class SerieBean implements GenericBean, Serializable
{
	private static final long serialVersionUID = 1L;
	@EJB
	private SerieService serieService;

	@EJB
	private CursoService cursoService;

	private Long serieId;
	private Serie serie = new Serie();

	private Long cursoId;
	private Curso curso = new Curso();

	public Long getSerieId()
	{
		return serieId;
	}

	public void setSerieId(Long serieId)
	{
		this.serieId = serieId;
	}

	public Serie getSerie()
	{
		return serie;
	}

	public void setSerie(Serie serie)
	{
		this.serie = serie;
	}

	public Long getCursoId()
	{
		return cursoId;
	}

	public void setCursoId(Long cursoId)
	{
		this.cursoId = cursoId;
	}

	public Curso getCurso()
	{
		return curso;
	}

	public void setCurso(Curso curso)
	{
		this.curso = curso;
	}

	public Nivel[] getNiveis()
	{
		return Nivel.values();
	}

	public List<Serie> getSeries()
	{
		return serieService.findAll();
	}

	@Override
	public String actionSalvar()
	{
		FacesMessage message;
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			curso = cursoService.findOne(cursoId);
			serie.setCurso(curso);
			serieService.save(serie);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Série cadastrada com sucesso.", null);
			context.addMessage(null, message);
			return pageListar();
		} catch (Exception ex) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao cadastrar série.", null);
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
			serieService.update(serie);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Série atualizada com sucesso.", null);
			context.addMessage(null, message);
			return pageListar();
		} catch (Exception ex) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao atualizar série.", null);
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
			serieService.delete(serieId);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Série excluída com sucesso.", null);
			context.addMessage(null, message);
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Falha ao excluir série.", null);
			context.addMessage(null, message);
		}
		return null;
	}

	@Override
	public String pageCriar()
	{
		serie = new Serie();
		curso = cursoService.findOne(cursoId);
		return "/views/serie/criar.xhtml";
	}

	@Override
	public String pageEditar()
	{
		serie = serieService.findOne(serieId);
		return "/views/serie/editar.xhtml";
	}

	@Override
	public String pageDetalhes()
	{
		serie = serieService.findOne(serieId);
		return "/views/serie/detalhes.xhtml";
	}

	@Override
	public String pageListar()
	{
		curso = cursoService.findOne(cursoId);
		return "/views/serie/listar.xhtml";
	}
}
