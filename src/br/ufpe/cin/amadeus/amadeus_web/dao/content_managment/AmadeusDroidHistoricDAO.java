package br.ufpe.cin.amadeus.amadeus_web.dao.content_managment;

import java.util.List;

import br.ufpe.cin.amadeus.amadeus_web.dao.GenericDAO;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.AmadeusDroidHistoric;

public interface AmadeusDroidHistoricDAO extends GenericDAO<AmadeusDroidHistoric, Integer>{
	
	List<AmadeusDroidHistoric> getHistoric();
	

}
