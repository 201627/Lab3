package it.polito.tdp.lab3.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab3.model.Corso;
import it.polito.tdp.lab3.model.Studente;

public class SegreteriaDAO {
	
	String jbdcURL = "jdbc:mysql://localhost/iscritticorsi?user=root&password=";
	String sql;
	
	public List<Corso> elencoCorsi() {
		
		LinkedList<Corso> result = new LinkedList<Corso>();
		
		try {
			Connection conn = DriverManager.getConnection(jbdcURL);
			
			Statement st = conn.createStatement();
			
			sql= "select codins, crediti, nome, pd from corso ";
			
			ResultSet res = st.executeQuery(sql);
			
			while(res.next()){
				
				Corso c = new Corso(res.getString("codins"), res.getInt("crediti"), 
						res.getString("nome"), res.getInt("pd"));
				result.add(c);
				
			}
			
			conn.close();
			st.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}

	public Studente getStudente(String matricola) {
		
			Studente t = null;
		
		try {
			Connection conn = DriverManager.getConnection(jbdcURL);
			
			Statement st = conn.createStatement();
			
			sql= String.format("select matricola, cognome, nome, cds from studente where matricola =%s ",
					matricola);
			
			ResultSet res = st.executeQuery(sql);
			
			while(res.next()){
				
				t = new Studente(""+res.getInt("matricola"), res.getString("cognome"), 
						res.getString("nome"), res.getString("cds"));

			}
			
			conn.close();
			st.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return t;
	}


	public List<Studente> getIscrittiCorso(Corso corso) {
		
		LinkedList<Studente> result = new LinkedList<Studente>();
				
				try {
					Connection conn = DriverManager.getConnection(jbdcURL);
					
					Statement st = conn.createStatement();
					
					sql= "select s.matricola, cognome, nome, cds from studente s, "
							+ "iscrizione i where s.matricola = i.matricola and i.codins "
							+ "='"+ corso.getCodice() + "'";
					
					ResultSet res = st.executeQuery(sql);
					
					while(res.next()){
						
						Studente t = new Studente(""+res.getInt("matricola"), res.getString("cognome"), 
								res.getString("nome"), res.getString("cds"));
						result.add(t);
						
					}
					
					conn.close();
					st.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		return result;
	}


	public List<Corso> getIscrizioniStudente(Studente studente) {
		
		LinkedList<Corso> result = new LinkedList<Corso>();
			
			try {
				Connection conn = DriverManager.getConnection(jbdcURL);
				
				Statement st = conn.createStatement();
				
				sql=String.format("select c.* "
						+ "from studente s, iscrizione i, corso c "
						+ "where c.codins = i.codins and s.matricola = i.matricola and "
						+ "s.matricola = %d ", Integer.parseInt(studente.getMatricola()));
				 
				
				ResultSet res = st.executeQuery(sql);
				
				while(res.next()){
					
					Corso c = new Corso(res.getString("codins"), res.getInt("crediti"), 
							res.getString("nome"), res.getInt("pd"));
					result.add(c);
					
				}
				
				conn.close();
				st.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return result;
			
		}


	public int iscrivi(Studente studente, Corso corso) {
			
		int i = 0;
		try {
			Connection conn = DriverManager.getConnection(jbdcURL);
			
			Statement st = conn.createStatement();
			
			sql=String.format("INSERT INTO `iscrizione` (`matricola`, `codins`) VALUES "
					+ "	(%d, '%s')",Integer.parseInt(studente.getMatricola()), corso.getCodice());
	
			i = st.executeUpdate(sql);
						
			conn.close();
			st.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
		
	}

}
