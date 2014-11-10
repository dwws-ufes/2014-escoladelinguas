/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.edl.enums;

/**
 *
 * @author vicente
 */
public enum DiaDaSemana
{
	SEGUNDA("Segunda-feira"),
	TERCA("Terça-feira"),
	QUARTA("Quarta-feira"),
	QUINTA("Quinta-feira"),
	SEXTA("Sexta-feira");

	private final String label;

	private DiaDaSemana(String label)
	{
		this.label = label;
	}

	public String getLabel()
	{
		return label;
	}
}
