package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import services.InfoPessoaService;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemCalcular;

	@FXML
	private MenuItem menuItemListar;

	@FXML
	public void onMenuItemCalcularAction() {

		loadView("/gui/CalcularView.fxml");

	}

	@FXML
	public void onMenuItemListarAction() {

		loadView2("/gui/ListView.fxml");

	}

	@Override
	public void initialize(URL uri, ResourceBundle rb) {

	}

	private void loadView(String absoluteName) {
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
		VBox newVBox = loader.load();
		
		Scene mainScene = Main.getMaainScene();
		VBox mainVBox = (VBox) ((ScrollPane)mainScene.getRoot()).getContent();
		
		Node mainMenu = mainVBox.getChildren().get(0);
		
		mainVBox.getChildren().clear();
		
		mainVBox.getChildren().add(mainMenu);
		
		mainVBox.getChildren().addAll(newVBox.getChildren());
		
		}
		catch(IOException e) {
			System.out.println("IO Exception");
		}
	}
	
	private void loadView2(String absoluteName) {
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
		VBox newVBox = loader.load();
		
		Scene mainScene = Main.getMaainScene();
		VBox mainVBox = (VBox) ((ScrollPane)mainScene.getRoot()).getContent();
		
		Node mainMenu = mainVBox.getChildren().get(0);
		
		mainVBox.getChildren().clear();
		
		mainVBox.getChildren().add(mainMenu);
		
		mainVBox.getChildren().addAll(newVBox.getChildren());
		
		ListarViewController controller = loader.getController();
		
		controller.setInfoPessoaService(new InfoPessoaService());
		
		controller.UpdateTableView();
		
		}
		catch(IOException e) {
			System.out.println("IO Exception");
		}
	}

}
