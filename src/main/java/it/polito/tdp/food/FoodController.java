/**
 * Sample Skeleton for 'Food.fxml' Controller Class
 */

package it.polito.tdp.food;

import java.net.URL;
import java.util.ResourceBundle;
import it.polito.tdp.food.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FoodController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtCalorie"
    private TextField txtCalorie; // Value injected by FXMLLoader

    @FXML // fx:id="txtPassi"
    private TextField txtPassi; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCorrelate"
    private Button btnCorrelate; // Value injected by FXMLLoader

    @FXML // fx:id="btnCammino"
    private Button btnCammino; // Value injected by FXMLLoader

    @FXML // fx:id="boxPorzioni"
    private ComboBox<String> boxPorzioni; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCammino(ActionEvent event) {
    	int n;
    	try {
    		n = Integer.parseInt(txtPassi.getText());
    	}catch(NumberFormatException ex) {
    		txtResult.appendText("Errore, inserisci il numero di passi massimo\n");
    		return;
    		}
    	if(boxPorzioni==null) {
    		txtResult.appendText("Seleziona un tipo di porzione");
    		return;
    	}
    	String msg = model.doCammino(n, boxPorzioni.getValue());
    	txtResult.appendText(msg);
    }

    @FXML
    void doCorrelate(ActionEvent event) {
    	if(boxPorzioni==null) {
    		txtResult.appendText("Seleziona un tipo di porzione");
    		return;
    	}
    	String msg = model.doCorrelate(boxPorzioni.getValue());
    	txtResult.appendText(msg);
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	double n;
    	try {
    		n = Double.parseDouble(txtCalorie.getText());
    	}catch(NumberFormatException ex) {
    		txtResult.appendText("Errore, inserisci il numero di calorie massimo\n");
    		return;
    		}
    	String msg = model.doCreaGrafo(n);
    	txtResult.appendText(msg);
    	boxPorzioni.getItems().clear();
    	boxPorzioni.getItems().addAll(model.getVertici());
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtCalorie != null : "fx:id=\"txtCalorie\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtPassi != null : "fx:id=\"txtPassi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCorrelate != null : "fx:id=\"btnCorrelate\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCammino != null : "fx:id=\"btnCammino\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxPorzioni != null : "fx:id=\"boxPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
