package it.polito.tdp.lab3.model;

public class Corso {

	private String codice;
	private int cfu;
	private String nome;
	private int pd;
	
	public Corso(String codice, int cfu, String nome, int pd) {
		super();
		this.codice = codice;
		this.cfu = cfu;
		this.nome = nome;
		this.pd = pd;
	}

	public String getCodice() {
		return codice;
	}

	public int getCfu() {
		return cfu;
	}

	public String getNome() {
		return nome;
	}

	public int getPd() {
		return pd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codice == null) ? 0 : codice.hashCode());
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
		Corso other = (Corso) obj;
		if (codice == null) {
			if (other.codice != null)
				return false;
		} else if (!codice.equals(other.codice))
			return false;
		return true;
	}
	
	public String toString(){
		if (codice.equals("campovuoto"))
			return "-- seleziona un corso --";
		else
			return codice + " " + cfu + " " + nome + " " + pd;
	}
	
}
