/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_mobile.basics;

import java.util.List;

public class ModuleMobile {
	
	private int id;
	
	private List<NoticeMobile> noticies;
	
	private List<MaterialMobile> materials;
	
	private List<HomeworkMobile> homeworks;
	
	private String nome;

	public ModuleMobile() {}

	public ModuleMobile(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public ModuleMobile(int id, List<NoticeMobile> noticies, List<MaterialMobile> materials, List<HomeworkMobile> homeworks, String nome) {
		super();
		this.id = id;
		this.noticies = noticies;
		this.materials = materials;
		this.homeworks = homeworks;
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<HomeworkMobile> getHomeworks() {
		return homeworks;
	}

	public void setHomeworks(List<HomeworkMobile> homeworks) {
		this.homeworks = homeworks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<MaterialMobile> getMaterials() {
		return materials;
	}

	public void setMaterials(List<MaterialMobile> materials) {
		this.materials = materials;
	}

	public List<NoticeMobile> getNoticies() {
		return noticies;
	}

	public void setNoticies(List<NoticeMobile> noticies) {
		this.noticies = noticies;
	}
		
}