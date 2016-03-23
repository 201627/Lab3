package it.polito.tdp.lab3.model;

import java.util.List;

import it.polito.tdp.lab3.dao.SegreteriaDAO;

public class Segreteria {
	
	public List<Corso> elencoCorsi(){
		SegreteriaDAO dao = new SegreteriaDAO();
		return dao.elencoCorsi();
	}
	
	
	public Studente getStudente(String matricola){
		
		SegreteriaDAO dao = new SegreteriaDAO();
		return dao.getStudente( matricola);
	}

	public List<Studente> getIscrittiCorso(Corso corso){
		SegreteriaDAO dao = new SegreteriaDAO();
		return dao.getIscrittiCorso(corso);
	}
	public List<Corso> getIscrizioniStudente(Studente studente){
		SegreteriaDAO dao = new SegreteriaDAO();
		return dao.getIscrizioniStudente( studente);
	}


	public int iscrivi(Studente studente, Corso corso) {
		SegreteriaDAO dao = new SegreteriaDAO();
		return dao.iscrivi( studente,  corso);		
	}
}
