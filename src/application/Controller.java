package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dal.DataAccessLayer;
import dal.exceptions.CouldNotConnectException;
import dal.exceptions.NotConnectedException;
import dal.impl.VideoDal;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import model.Member;
import model.Person;
import model.Video;

/**
 * Class for implementing the logic.
 */
public class Controller implements Initializable {
	private DataAccessLayer<Video, Member> dal;

	@FXML
	TextField usernameField;
	@FXML
	TextField passwordField;
	@FXML
	Button connectButton;
	@FXML
	Label connectionStateLabel;
	@FXML
	ComboBox<ComboBoxItem<String>> sampleCombo;

	@FXML
	TableColumn<Person, String> nameColumn;

	@FXML
	TableColumn<Person, String> identityNumberColumn;

	@FXML
	TableView<Person> searchTable;

	public Controller() {
		dal = new VideoDal();
	}

	@FXML
	public void connectEventHandler(ActionEvent event) {
		//Getting the input from the UI.
		String username = usernameField.getText();
		String password = passwordField.getText();

		try {
			//Connect to the database, and update the UI
			dal.connect(username, password);
			connectionStateLabel.setText("Connection created!");
			connectionStateLabel.setTextFill(Paint.valueOf("green"));
		} catch (ClassNotFoundException e) {
			//Driver is not found
			connectionStateLabel.setText("JDBC driver not found!");
			connectionStateLabel.setTextFill(Paint.valueOf("red"));
		} catch (CouldNotConnectException e) {
			//Could not connect, e.g. invalid username or password
			connectionStateLabel.setText("Could not connect to the server!");
			connectionStateLabel.setTextFill(Paint.valueOf("red"));
		}
	}

	@FXML
	public void searchEventHandler() {
		//TODO: replace this query with your solution.
		try {
			List<Person> people = dal.sampleQuery();
			searchTable.setItems(FXCollections.observableArrayList(people));
		} catch (NotConnectedException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void commitEventHandler() {
		//TODO: handle the click of the commit button.
	}

	@FXML
	public void editEventHandler() {
		//TODO: handle the click of the edit button
	}

	@FXML
	public void statisticsEventHandler() {
		//TODO: handle the click of the statistics button
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO: initalize property-value factories
		//EXAMPLE: sampleCombo stores two strings.
		sampleCombo.getItems().add(new ComboBoxItem<String>("Value A", "a"));
		sampleCombo.getItems().add(new ComboBoxItem<String>("Value B", "b"));
		
		//EXAMPLE: this is how we bind the private variables to a data cell
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		identityNumberColumn.setCellValueFactory(new PropertyValueFactory<>("identityNumber"));
	}

	public void disconnect() {
		dal.disconnect();
	}

}
