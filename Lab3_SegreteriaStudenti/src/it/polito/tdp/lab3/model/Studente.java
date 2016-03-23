package it.polito.tdp.lab3.model;

public class Studente {
	
	private String matricola;
	private String cognome;
	private String nome;
	private String cds;
	
	public Studente(String matricola, String cognome, String nome, String cds) {
		super();
		this.matricola = matricola;
		this.cognome = cognome;
		this.nome = nome;
		this.cds = cds;
	}
	
	public String getMatricola() {
		return matricola;
	}
	public String getCognome() {
		return cognome;
	}
	public String getNome() {
		return nome;
	}
	public String getCDS() {
		return cds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricola == null) ? 0 : matricola.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Studente other = (Studente) obj;
		if (matricola == null) {
			if (other.matricola != null)
				return false;
		} else if (!matricola.equals(other.matricola))
			return false;
		return true;
	}
	
	
	public String toString(){
		return matricola + "\t" + cognome + "\t" + nome + "\t" + cds;
	}
	
}
