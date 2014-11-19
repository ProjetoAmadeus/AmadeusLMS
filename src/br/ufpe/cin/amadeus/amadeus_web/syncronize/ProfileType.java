package br.ufpe.cin.amadeus.amadeus_web.syncronize;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum ProfileType implements Serializable{
	ADMIN, STUDENT, PROFESSOR, INACTIVE
}
