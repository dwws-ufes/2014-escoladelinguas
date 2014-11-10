/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.edl.control;

import br.ufes.edl.domain.Aluno;
import br.ufes.edl.service.AlunoService;
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
@SessionScoped
@Named
public class AlunoBean implements GenericBean, Serializable {

    @EJB
    private AlunoService alunoService;

    private Long alunoId;

    private Aluno aluno = new Aluno();

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public List<Aluno> getAlunos() {
        return alunoService.findAll();
    }

    @Override
    public String actionSalvar() {
        FacesMessage message;
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            alunoService.save(aluno);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aluno cadastrado com sucesso.", null);
            context.addMessage(null, message);
            return pageListar();
        } catch (Exception ex) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao cadastrar aluno.", null);
            context.addMessage(null, message);
        }
        return null;
    }

    @Override
    public String actionEditar() {
        FacesMessage message;
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            aluno.setId(alunoId);
            alunoService.update(aluno);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aluno atualizado com sucesso.", null);
            context.addMessage(null, message);
            return pageListar();
        } catch (Exception ex) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao atualizar aluno.", null);
            context.addMessage(null, message);
        }
        return null;
    }

    @Override
    public String actionDeletar() {
        FacesMessage message;
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            alunoService.delete(alunoId);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aluno excluído com sucesso.", null);
            context.addMessage(null, message);
        } catch (Exception ex) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao excluir aluno.", null);
            context.addMessage(null, message);
        }
        return pageListar();
    }

    @Override
    public String pageCriar() {
        aluno = new Aluno();
        return "/views/aluno/criar.xhtml";
    }

    @Override
    public String pageEditar() {
        aluno = alunoService.findOne(alunoId);
        return "/views/aluno/editar.xhtml";
    }

    @Override
    public String pageDetalhes() {
        aluno = alunoService.findOne(alunoId);
        return "/views/aluno/detalhes.xhtml";
    }

    @Override
    public String pageListar() {
        return "/views/aluno/listar.xhtml";
    }

    public void pageDetalhesVoid(Aluno a) {
        this.setAluno(alunoService.findOne(a.getId()));
        System.out.println(this.getAluno().getNome());
    }

    public String getMaskCpf() {
        return aluno.getCpf().substring(0, 3) + "." + aluno.getCpf().substring(3, 6) + "." + aluno.getCpf().substring(6, 9) + "-" + aluno.getCpf().substring(9);
    }
}
