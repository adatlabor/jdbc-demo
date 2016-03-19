/**
 * This JavaFX skeleton is provided for the Software Laboratory 5 course. Its structure
 * should provide a general guideline for the students.
 * As suggested by the JavaFX model, we'll have a GUI (view), 
 * a controller class (this one) and a model.
 */

package application;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.MapValueFactory;
import javafx.collections.ObservableList;
import java.util.HashMap;
import java.util.Map;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

// Controller class
public class Controller {

	// Root (vertical box) layout
	@FXML
	private VBox rootLayout;

	// Connection panel
	@FXML
	private HBox connectionLayout;

	// Username textfield
	@FXML
	private TextField usernameField;

	// Password textfield
	@FXML
	private TextField passwordField;

	// Connect button
	@FXML
	private Button connectButton;

	// Label displaying connection's state
	@FXML
	private Label connectionStateLabel;

	// Log tab
	@FXML
	private Tab logTab;

	// Text area for displaying logs
	@FXML
	private TextArea logTextArea;

	// Search tab
	@FXML
	private Tab searchTab;

	// Search text field
	@FXML
	private TextField searchTextField;

	// Search button
	@FXML
	private Button searchButton;

	// Table containing search results
	@FXML
	private TableView searchTable;

	// Titles and map keys of table columns
	String columnTitles[] = new String[] { "Title 1", "Title 2", "Title 3", "Title 4" };
	String columnKeys[] = new String[] { "col1", "col2", "col3", "col4" };

	// Our model layer
	private Model model = new Model();

	/**
	 * Controller constructor
	 */
	public Controller() {
	}

	/**
	 * View initialization, it will be called after view was prepared
	 */
	@FXML
	public void initialize() {

		// Clear username and password textfields and display status
		// 'disconnected'
		usernameField.setText("");
		passwordField.setText("");
		connectionStateLabel.setText("Connection: disconnected");
		connectionStateLabel.setTextFill(Color.web("#ee0000"));

		// Create table (search table) columns
		for (int i = 0; i < columnTitles.length; i++) {
			// Create table column
			TableColumn<Map, String> column = new TableColumn<>(columnTitles[i]);
			// Set map factory
			column.setCellValueFactory(new MapValueFactory(columnKeys[i]));
			// Set width of table column
			column.prefWidthProperty().bind(searchTable.widthProperty().divide(4));
			// Add column to the table
			searchTable.getColumns().add(column);
		}

	}

	/**
	 * Initialize controller with data from AppMain (now only sets stage)
	 * 
	 * @param stage
	 *            The top level JavaFX container
	 */
	public void initData(Stage stage) {

		// Set 'onClose' event handler (of the container)
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent winEvent) {
				// ...
			}
		});
	}

	/**
	 * This is called whenever the connect button is pressed
	 * 
	 * @param event
	 *            Contains details about the JavaFX event
	 */
	@FXML
	private void connectEventHandler(ActionEvent event) {

		// Model's connect method will do everything for us, just call
		// it
		if (model.connect(usernameField.getText(), passwordField.getText())) {

			connectionStateLabel.setText("Connection created");
			connectionStateLabel.setTextFill(Color.web("#009900"));

			// Test the connection
			String results = model.testConnection();
			if (results != null) {

				logMsg("Connection seems to be working.");
				logMsg("Connected to: '" + model.getDatabaseUrl() + "'");
				logMsg(String.format("DBMS: %s, version: %s", model.getDatabaseProductName(),
						model.getDatabaseProductVersion()));
				logMsg(results);

			} else {

				logMsg("It's a TRAP!");
				logMsg(model.getLastError());

			}

		}
		else {
			
			logMsg(model.getLastError());
			
		}

	}

	/**
	 * This is called whenever the search button is pressed
	 * 
	 * @param event
	 *            Contains details about the JavaFX event
	 */
	@FXML
	private void searchEventHandler(ActionEvent event) {

		// Get a reference to the row list of search table
		ObservableList<Map> allRows = searchTable.getItems();

		// Delete all the rows
		allRows.clear();

		// Add a new (sample) row to the table
		String sampleRow[] = new String[] { "Sample 1", "Sample 2", "Sample 3", "Sample 4" };

		// Create a map object from string array
		Map<String, String> dataRow = new HashMap<>();
		for (int i = 0; i < searchTable.getColumns().size(); i++) {

			dataRow.put(columnKeys[i], sampleRow[i]);

		}

		// Add the row to the table
		allRows.add(dataRow);

	}

	/**
	 * Appends the message (with a line break added) to the log
	 * 
	 * @param message
	 *            The message to be logged
	 */
	protected void logMsg(String message) {

		logTextArea.appendText(message + "\n");

	}

}
