/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.edl.validators;

import java.util.InputMismatchException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author vicente
 */
@FacesValidator(value = "CPFValidator")
public class CPFValidator implements Validator
{
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException
	{
		if (!CPFValido(String.valueOf(value))) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "CPF não é válido", null));
		}
	}

	public static boolean CPFValido(String cpf)
	{
		// Considerar como erro CPFs com apenas números iguais.
		if (cpf.equals("00000000000") || cpf.equals("11111111111")
			|| cpf.equals("22222222222") || cpf.equals("33333333333")
			|| cpf.equals("44444444444") || cpf.equals("55555555555")
			|| cpf.equals("66666666666") || cpf.equals("77777777777")
			|| cpf.equals("88888888888") || cpf.equals("99999999999")
			|| (cpf.length() != 11)) {
			return (false);
		}
		// Declaração das variaveis.
		char Digito1, Digito2;
		int Soma, i, r, Numero, Peso;

		// Try para caso dê algum erro.
		try {
			// Calcular primeiro dígito.
			Soma = 0;
			Peso = 10;
			for (i = 0; i < 9; i++) {
				// Converter caractere na posição "i"  para número inteiro.
				// 48 é a posição de '0' na tabela ASCII.
				Numero = (cpf.charAt(i) - 48);
				Soma = Soma + (Numero * Peso);
				Peso = Peso - 1;
			}
			// Depois da soma feita, calcular o dígito.
			r = 11 - (Soma % 11);
			if ((r == 10) || (r == 11)) {   // Se o resultado do cálculo for 10 ou
				Digito1 = '0';              // 11, o valor do dígito será zero.
			} else {
				Digito1 = (char) (r + 48);  // Converte o inteiro em char
			}                               // de acordo com a tabela ASCII

			// Calcular segundo dígito.
			Soma = 0;
			Peso = 11;
			for (i = 0; i < 10; i++) {
				// Converter caractere na posição "i"  para número inteiro.
				// 48 é a posição de '0' na tabela ASCII.
				Numero = (cpf.charAt(i) - 48);
				Soma = Soma + (Numero * Peso);
				Peso = Peso - 1;
			}
			// Depois da soma feita, calcular o dígito.
			r = 11 - (Soma % 11);
			if ((r == 10) || (r == 11)) {   // Se o resultado do cálculo for 10 ou
				Digito2 = '0';              // 11, o valor do dígito será zero.
			} else {
				Digito2 = (char) (r + 48);  // Converte o inteiro em char
			}                               // de acordo com a tabela ASCII

			// Verificar se os dígitos estão corretos.
			if ((Digito1 == cpf.charAt(9)) && (Digito2 == cpf.charAt(10))) {
				return (true);  // Se os dígitos calculados forem iguais aos
			} else {            // da string informada, torna "verdadeiro",
				return (false); // caso contrário, retorna "falso".
			}
		} catch (InputMismatchException erro) { // Catch para caso dê algum erro
			return (false);                     // de conversão.
		}
	}

}
