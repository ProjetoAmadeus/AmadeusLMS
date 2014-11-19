/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
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