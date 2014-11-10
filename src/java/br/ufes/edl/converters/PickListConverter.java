/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.edl.converters;

import br.ufes.edl.domain.Aluno;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "PickListConverter")
public class PickListConverter implements Converter
{
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2)
	{
		Aluno a = new Aluno();
		a.setId(Long.parseLong(arg2));
		return a;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2)
	{
		Aluno a = (Aluno) arg2;
		return a.getId().toString();
	}
}
