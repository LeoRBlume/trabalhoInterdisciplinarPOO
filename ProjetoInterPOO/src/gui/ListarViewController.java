package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.InfoPessoa;
import services.InfoPessoaService;

public class ListarViewController implements Initializable{
	
	private InfoPessoaService service;
	
	private ObservableList<InfoPessoa> obsList;

	@FXML
	private TableView<InfoPessoa> tableViewCalculo;
	
	@FXML
	private TableColumn<InfoPessoa, String> tableColumnName;
	
	@FXML
	private TableColumn<InfoPessoa, String> tableColumnSexo;
	
	@FXML
	private TableColumn<InfoPessoa, Double> tableColumnAcMasc;
	
	@FXML
	private TableColumn<InfoPessoa, Double> tableColumnAcFem;
	
	public void setInfoPessoaService(InfoPessoaService service) {
		this.service = service;
	}
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
			initializeNodes();
	}
	
	private void initializeNodes() {
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
		tableColumnAcMasc.setCellValueFactory(new PropertyValueFactory<>("acuraciaMasc"));
		tableColumnAcFem.setCellValueFactory(new PropertyValueFactory<>("acuraciaFem"));
		
		Stage stage = (Stage) Main.getMaainScene().getWindow();
		tableViewCalculo.prefHeightProperty().bind(stage.heightProperty());
	}
	
	public void UpdateTableView() {
		
		List<InfoPessoa> list = service.mostrarDados();
		
		obsList = FXCollections.observableArrayList(list);
		
		tableViewCalculo.setItems(obsList);

	}
	
}
