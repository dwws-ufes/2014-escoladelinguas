/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.edl.control;

import br.ufes.edl.domain.Aluno;
import br.ufes.edl.domain.Turma;
import br.ufes.edl.service.AlunoService;
import br.ufes.edl.service.TurmaService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.DualListModel;

/**
 *
 * @author vicente
 */
@Named
@SessionScoped
public class MatriculaBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	@EJB
	private TurmaService turmaService;

	@EJB
	private AlunoService alunoService;

	private Turma turma;

	private Long turmaId;

	private DualListModel<Aluno> alunos;

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

	public DualListModel<Aluno> getAlunos()
	{
		return alunos;
	}

	public void setAlunos(DualListModel<Aluno> alunos)
	{
		this.alunos = alunos;
	}

	public String actionMatricular()
	{
		FacesMessage message;
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			List<Aluno> alunosParaMatricular = alunos.getTarget();
			for (Aluno am : alunosParaMatricular) {
				Aluno a = alunoService.findOne(am.getId());
				a.getTurmas().add(turma);
				alunoService.update(a);
			}
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alunos matriculados com sucesso.", null);
			context.addMessage(null, message);
			return "/views/turma/listar.xhtml";
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao matricular alunos.", null);
			context.addMessage(null, message);
		}
		return null;
	}

	public String pageMatricular()
	{
		turma = turmaService.findOne(turmaId);
		List<Aluno> alunosMatriculados = new ArrayList<>(turma.getAlunos());
		List<Aluno> alunosNaoMatriculados = alunoService.findAll();
		alunosNaoMatriculados.removeAll(alunosMatriculados);
		alunos = new DualListModel<>(alunosNaoMatriculados, alunosMatriculados);
		return "/views/matricula/matricular.xhtml";
	}
}
