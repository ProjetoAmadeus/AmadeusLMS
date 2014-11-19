package br.ufpe.cin.amadeus.amadeus_web.domain.content_management;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;

@SuppressWarnings("serial")
@Entity
public class Log implements Serializable{

	@Id @GeneratedValue (strategy=GenerationType.AUTO) 
	private int id;
	
	private Date date;
	
	@OneToOne
	@JoinColumn(name="PERSON_ID")
	private Person idUser;
	private Integer codigo;
	private Integer idObjeto;
	private Integer fases;
	private Integer tempo;
	private Integer pontuacao;
	private Integer tamanhoMensagem;
	@Column(name="metaalternativa")
	private Integer metaAlternativa;

	public final static int LOG_CODIGO_LOGIN = 1; //Ok
	public final static int LOG_CODIGO_LOGOUT = 2; //Ok
	public final static int LOG_CODIGO_FORUM_POST = 3; //Ok - postagens em foruns
	public final static int LOG_CODIGO_FORUM_TOPICO = 4; //Ok - visualizacoes de topicos
	public final static int LOG_CODIGO_VISUALIZACAO_MATERIAL = 5; //Ok - abertura de materias dos modulos
	public final static int LOG_CODIGO_RESPOSTA_ENQUETE = 6; //Ok
	public final static int LOG_CODIGO_ABRIR_JOGO = 7; //Ok
	public final static int LOG_CODIGO_JOGAR = 8; //Ok
	public final static int LOG_CODIGO_ENTREGAR_MATERIAL = 9; //Ok
	public final static int LOG_CODIGO_VISUALIZACAO_VIDEO = 10;

	public Log (){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Person getIdUser() {
		return idUser;
	}

	public void setIdUser(Person idUser) {
		this.idUser = idUser;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public Integer getIdObjeto() {
		return idObjeto;
	}

	public void setIdObjeto(Integer idObjeto) {
		this.idObjeto = idObjeto;
	}

	public Integer getFases() {
		return fases;
	}

	public void setFases(Integer fases) {
		this.fases = fases;
	}

	public Integer getTempo() {
		return tempo;
	}

	public void setTempo(Integer tempo) {
		this.tempo = tempo;
	}

	public Integer getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Metodo que cria um log ja com a data de criacao setada.
	 * @return
	 */
	public static Log getLog(){
		Log log = new Log();
		log.setDate(new Date());
		return log;
	}
	
	
	public Integer getTamanhoMensagem() {
		return tamanhoMensagem;
	}

	public void setTamanhoMensagem(Integer tamanhoMensagem) {
		this.tamanhoMensagem = tamanhoMensagem;
	}
	
	public Integer getMetaAlternativa() {
		return metaAlternativa;
	}

	public void setMetaAlternativa(Integer metaAlternativa) {
		this.metaAlternativa = metaAlternativa;
	}
}
