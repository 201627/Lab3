package it.polito.tdp.lab3.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab3.model.Corso;
import it.polito.tdp.lab3.model.Segreteria;
import it.polito.tdp.lab3.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SegreteriaStudentiController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> cmbCorsi;

    @FXML
    private TextField txtMatricola;

    @FXML
    private ImageView btnIcona;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCerca;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private Button btnReset;

	private Segreteria model;

    @FXML
    void doCerca(ActionEvent event) {
    	
    	List<Studente> outStudente = null;
    	List<Corso>	outCorso = null;
    	
    	if (cmbCorsi.getValue()!= null && !cmbCorsi.getValue().getCodice().equals("campovuoto") ){
	    		outStudente = model.getIscrittiCorso(cmbCorsi.getValue());
	    	
	    	if (outStudente != null){
	    		for (Studente t : outStudente){
	    			txtRisultato.appendText(t.toString() + "\n");
	    		}
	    	}
    	}else if (cmbCorsi.getValue().getCodice().equals("campovuoto") && 
    			txtMatricola.getText().equals("") == false ){
    		if (model.getStudente(txtMatricola.getText())!= null){
    			//studente presente
    			
    			outCorso = model.getIscrizioniStudente(model.getStudente(txtMatricola.getText())); 
    			
    			if (outCorso !=null){
    				for (Corso c : outCorso){
    					txtRisultato.appendText(c.toString() + "\n");
    				}
    				
    			}
    			     			 
    		}else {
    			//studente non trovato
    			txtRisultato.setText("Errore, studente non presente");
    		}
    	}
    		
    }

    @FXML
    void doComplete(MouseEvent event) {
    	
    	String matricola =  txtMatricola.getText() ;
    	Studente stud = model.getStudente(matricola) ;
    	if (stud != null){
    		txtNome.setText(stud.getNome());
        	txtCognome.setText(stud.getCognome());
    	}
    	
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	
    	if (model.getStudente(txtMatricola.getText())== null ){
    		txtRisultato.setText("Errore, studente non presente");
    	}else if (cmbCorsi.getValue().getCodice().equals("campovuoto")){
    		txtRisultato.setText("Errore, corso non selezionato");
    	}else if (model.getIscrizioniStudente(model.getStudente(txtMatricola.getText())).contains(cmbCorsi.getValue())==true){
    		txtRisultato.setText("Errore, studente già iscritto al corso");
    	}else {
    		model.iscrivi(model.getStudente(txtMatricola.getText()),cmbCorsi.getValue());
    		txtRisultato.setText("Studente iscritto al corso");
    	}
    }

    @FXML
    void doReset(ActionEvent event) {

    txtMatricola.clear();
    txtNome.clear();
    txtCognome.clear();
    btnIcona.setDisable(false);
    txtRisultato.clear();
    }

    @FXML
    void initialize() {
        assert cmbCorsi != null : "fx:id=\"cmbCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIcona != null : "fx:id=\"btnIcona\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        
        cmbCorsi.getItems().add(new Corso ("campovuoto", 0 , null , 0));
        
    }

	public void setModel(Segreteria model) {
		this.model= model;
		cmbCorsi.getItems().addAll(model.elencoCorsi());
	}
}
