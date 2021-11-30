package gui;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.InfoPessoa;
import model.Pessoa;
import services.ExternalProgram;
import services.InfoPessoaService;

public class CalcularViewController {

	InfoPessoaService service = new InfoPessoaService();

	public List<InfoPessoa> getListInfo() {
		return this.getListInfo();
	}

	@FXML
	private Label myLabel;
	@FXML
	private Label ver;
	@FXML
	private Label ver2;
	@FXML
	private Label ver3;
	@FXML
	private TextField myTextField;
	@FXML
	private TextField myTextField2;
	@FXML
	private TextField myTextField3;

	@FXML
	private Button myButton;

	@FXML
	private Button myButton1;

	@FXML
	private Label acMasc1;
	@FXML
	private Label acMasc2;
	@FXML
	private Label acMasc3;

	@FXML
	private Label acFem1;
	@FXML
	private Label acFem2;
	@FXML
	private Label acFem3;
	@FXML
	private Label error;

	public void submit(ActionEvent event) throws InvocationTargetException {

		

		try {

			ArrayList<Pessoa> cList = new ArrayList<Pessoa>();
			
			cList = completarTexto();
			
			ExternalProgram.abrirPrograma(cList);

			Thread.sleep(2000);
			
			ArrayList<InfoPessoa> dados = ExternalProgram.leituraDoArquivo(cList);

			service.adcionarLista(dados);

			inputInformartion(dados);
		}

		catch (IndexOutOfBoundsException e) {

			error.setText("Error: Foi passado menos que 3 argumentos");
			clearText();

		}
		catch(Exception e) {
			error.setText("Error: Um erro inesperado aconteceu");
			clearText();
		}

		
	}

	public void inputInformartion(ArrayList<InfoPessoa> dados) {

		error.setText("");

		if ((dados.get(0).getSexo()) != null) {
			ver.setText(dados.get(0).getSexo());
			acMasc1.setText(String.valueOf(dados.get(0).getAcuraciaMasc()));
			acFem1.setText(String.valueOf(dados.get(0).getAcuraciaFem()));

		}
		if ((dados.get(1).getSexo()) != null) {
			ver2.setText(dados.get(1).getSexo());
			acMasc2.setText(String.valueOf(dados.get(1).getAcuraciaMasc()));
			acFem2.setText(String.valueOf(dados.get(1).getAcuraciaFem()));
		}
		if ((dados.get(2).getSexo()) != null) {
			ver3.setText(dados.get(2).getSexo());
			acMasc3.setText(String.valueOf(dados.get(2).getAcuraciaMasc()));
			acFem3.setText(String.valueOf(dados.get(2).getAcuraciaFem()));
		}

	}

	public void clear(ActionEvent event) {

		myTextField.setText("");

		myTextField2.setText("");

		myTextField3.setText("");

		ver.setText("");
		acMasc1.setText("");
		acFem1.setText("");

		ver2.setText("");
		acMasc2.setText("");
		acFem2.setText("");

		ver3.setText("");
		acMasc3.setText("");
		acFem3.setText("");

	}
	
	public void clearText() {

		myTextField.setText("");

		myTextField2.setText("");

		myTextField3.setText("");

		ver.setText("");
		acMasc1.setText("");
		acFem1.setText("");

		ver2.setText("");
		acMasc2.setText("");
		acFem2.setText("");

		ver3.setText("");
		acMasc3.setText("");
		acFem3.setText("");

	}

	public ArrayList<Pessoa> completarTexto(){
		
		ArrayList<Pessoa> cList = new ArrayList<Pessoa>();
		
		if(myTextField.getText() == "" || myTextField2.getText() == "" || myTextField3.getText() == "") {
			
			cList = null;
			throw new IndexOutOfBoundsException();

		}
		else {
			cList.add(new Pessoa(myTextField.getText()));

			cList.add(new Pessoa(myTextField2.getText()));

			cList.add(new Pessoa(myTextField3.getText()));
		}
	
		return cList;

	
	}

}
