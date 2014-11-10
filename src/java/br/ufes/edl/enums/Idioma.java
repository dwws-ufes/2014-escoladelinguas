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
public enum Idioma
{

        ALEMAO("Alemão"),
        ESPANHOL("Espanhol"),
        FRANCES("Francês"),
	INGLES("Inglês"),
        ITALIANO("Italiano"),
	PORTUGUES("Português");


	private final String label;

	private Idioma(String label)
	{
		this.label = label;
	}

	public String getLabel()
	{
		return label;
	}
}
