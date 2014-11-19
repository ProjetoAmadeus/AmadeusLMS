/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.struts.validation;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.Resources;

public class Validator {

	public static boolean validateIdentico(Object bean, ValidatorAction va,
			Field field, ActionMessages errors, HttpServletRequest request) {

		String value = ValidatorUtils.getValueAsString(bean, field
				.getProperty());
		String sProperty2 = field.getVarValue("secondProperty");
		String value2 = ValidatorUtils.getValueAsString(bean, sProperty2);

		if (!GenericValidator.isBlankOrNull(value)) {
			try {
				if (!value.equals(value2)) {
					errors.add(field.getKey(), Resources.getActionMessage(
							request, va, field));
					return false;
				}
			} catch (Exception e) {
				errors.add(field.getKey(), Resources.getActionMessage(request,
						va, field));
				return false;
			}
		}
		return true;
	}

	/**
	 * Validate a cpf field
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @param request
	 * @return
	 */
	public static boolean validateCpf(Object bean, ValidatorAction va,
			Field field, ActionMessages errors, HttpServletRequest request) {
		
		boolean ret = true;
		
		String cpfField = field.getVarValue("firstProperty");
		String cpf = ValidatorUtils.getValueAsString(bean, cpfField).trim();
		String cpfDigitField = field.getVarValue("secondProperty");
		String cpfDigit = ValidatorUtils.getValueAsString(bean, cpfDigitField).trim();
		
		//veririfica se os dois campos t�m algum conte�do
		if (!GenericValidator.isBlankOrNull(cpf) && !GenericValidator.isBlankOrNull(cpfDigit)) {
			try {
				if (cpf.length() != 9 || cpfDigit.length() != 2) { //verifica tamanho das strings
					errors.add(field.getKey(), Resources.getActionMessage(
							request, va, field));
					ret = false;
				}else{
					//calcula d�gito verificador
					Integer primDig, segDig;                                        
					int soma = 0, peso = 10;                                        
					for (int i = 0; i < cpf.length(); i++)                          
					    soma += Integer.parseInt(cpf.substring(i, i + 1)) * peso--; 
					                                                                
					if (soma % 11 == 0 | soma % 11 == 1)                            
					    primDig = new Integer(0);                                   
					else                                                            
					    primDig = new Integer(11 - (soma % 11));                    
					                                                                
					soma = 0;                                                       
					peso = 11;                                                      
					for (int i = 0; i < cpf.length(); i++)                          
					    soma += Integer.parseInt(cpf.substring(i, i + 1)) * peso--; 
					                                                                
					soma += primDig.intValue() * 2;                                 
					if (soma % 11 == 0 | soma % 11 == 1)                            
					    segDig = new Integer(0);                                    
					else                                                            
					    segDig = new Integer(11 - (soma % 11));                     
					                                                                
					String digVerifier = primDig.toString() + segDig.toString();
					
					if(!digVerifier.equals(cpfDigit)){ //verifica se o d�gito calculado � o mesmo do d�gito dado como entrada
						errors.add(field.getKey(), Resources.getActionMessage(
								request, va, field));
						ret = false;
					}
				}
				
				
			} catch (Exception e) {
				errors.add(field.getKey(), Resources.getActionMessage(request,
						va, field));
				return false;
			}
		}
		//verifica se os dois campos est�o vazios
		if (GenericValidator.isBlankOrNull(cpf) && GenericValidator.isBlankOrNull(cpfDigit)) {
			ret = true;
		}
		
		return ret;
	}

	/**
	 * Validates a date
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @param request
	 * @return
	 */
	public static boolean validateDate(Object bean, ValidatorAction va,
			Field field, ActionMessages errors, HttpServletRequest request) {

		String day = ValidatorUtils.getValueAsString(bean, field.getProperty());
		String monthField = field.getVarValue("secondProperty");
		String yearField = field.getVarValue("thirdProperty");
		String month = ValidatorUtils.getValueAsString(bean, monthField);
		String year = ValidatorUtils.getValueAsString(bean, yearField);
		boolean isValid = true;
		
		if(!GenericValidator.isBlankOrNull(day) || !GenericValidator.isBlankOrNull(month) || !GenericValidator.isBlankOrNull(year)){
			
			try {
								
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				formatter.setLenient( false ); 
				Date date = (Date)formatter.parse(day+"/"+month+"/"+year);		
				
				
			} catch (Exception e) {
				errors.add(field.getKey(), Resources.getActionMessage(request, va,field));
				return false;
				
			}		
		
		}
	
		return isValid;
	}
	/**
	 * Validates a phone Number
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @param request
	 * @return
	 */
	public static boolean validatePhone(Object bean, ValidatorAction va,
			Field field, ActionMessages errors, HttpServletRequest request) {
		boolean isValid = false;
		
		String dddField = field.getVarValue("firstProperty");
		String ddd = ValidatorUtils.getValueAsString(bean, dddField).trim();
		String phoneField = field.getVarValue("secondProperty");
		String phone = ValidatorUtils.getValueAsString(bean, phoneField).trim();
		
		try {
			if (field.getKey().equals("phone")) {
				if (ddd.length() != 2 || phone.length() != 8) {
					errors.add(field.getKey(), Resources.getActionMessage(request, va, field));
					isValid = false;
				} else {
					isValid = true;
				}
			}
		} catch (Exception e) {
			errors.add(field.getKey(), Resources.getActionMessage(request, va, field));
			isValid = false;
		}
		return isValid;
	}

}