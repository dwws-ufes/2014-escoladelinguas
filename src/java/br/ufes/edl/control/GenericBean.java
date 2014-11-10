/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.edl.control;

/**
 *
 * @author vicente
 */
public interface GenericBean
{
	public String actionSalvar();

	public String actionEditar();

	public String actionDeletar();

	public String pageEditar();

	public String pageCriar();

	public String pageDetalhes();

	public String pageListar();
}
