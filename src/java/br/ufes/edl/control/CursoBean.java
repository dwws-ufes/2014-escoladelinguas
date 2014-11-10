package br.ufes.edl.control;

import br.ufes.edl.domain.Curso;
import br.ufes.edl.enums.Idioma;
import br.ufes.edl.service.CursoService;
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
public class CursoBean implements GenericBean, Serializable
{
	private static final long serialVersionUID = 1L;

	@EJB
	private CursoService cursoService;

	private Long cursoId;
	private Curso curso = new Curso();

	public Curso getCurso()
	{
		return curso;
	}

	public void setCurso(Curso curso)
	{
		this.curso = curso;
	}

	public Long getCursoId()
	{
		return cursoId;
	}

	public void setCursoId(Long cursoId)
	{
		this.cursoId = cursoId;
	}

	public Idioma[] getIdioma()
	{
		return Idioma.values();
	}

	public List<Curso> getCursos()
	{
		List<Curso> cursos = cursoService.findAll();
		cursos.sort((Curso o1, Curso o2) -> o1.getTitulo().compareTo(o2.getTitulo()));
		return cursos;
	}

	@Override
	public String actionSalvar()
	{
		FacesMessage message;
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			cursoService.save(curso);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Curso cadastrado com sucesso.", null);
			context.addMessage(null, message);
			return pageListar();
		} catch (Exception ex) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao cadastrar curso.", null);
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
			cursoService.update(curso);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Curso atualizado com sucesso.", null);
			context.addMessage(null, message);
			return pageListar();
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao atualizar curso.", null);
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
			cursoService.delete(cursoId);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Curso excluído com sucesso.", null);
			context.addMessage(null, message);
		} catch (Exception ex) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao excluir curso.", null);
			context.addMessage(null, message);
		}
		return null;
	}

	@Override
	public String pageCriar()
	{
		curso = new Curso();
		return "/views/curso/criar.xhtml";
	}

	@Override
	public String pageEditar()
	{
		curso = cursoService.findOne(cursoId);
		return "/views/curso/editar.xhtml";
	}

	@Override
	public String pageDetalhes()
	{
		curso = cursoService.findOne(cursoId);
		return "/views/curso/detalhes.xhtml";
	}

	@Override
	public String pageListar()
	{
		return "/views/curso/listar.xhtml";
	}
}
