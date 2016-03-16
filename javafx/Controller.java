/**
 * This JavaFX skeleton is provided for the Software Laboratory 5 course. Its structure
 * should provide a general guideline for the students. Though we were trying to
 * create a good example application here, the code is probably not suitable for
 * a real life application.
 */

package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


// Controller class
public class Controller {
	
	// As suggested by the JavaFX model, we'll have a GUI (view), a controllers class (this one)
	// and a model.
	
	@FXML
	private Text output;
	
	@FXML
	private TextField usernamefield;
	
	@FXML
	private TextField passwordfield;
	
	@FXML
	private Label connectionstatelabel;
	
	@FXML
	private Button connectbutton;
	
	@FXML
	private TextArea log;
	
	// Model
	private Model model = new Model();
	
	// Connect button action listener
	@FXML
	private void connect(ActionEvent event) {
		
		connectButtonActionPerformed(event);
		output.setText("Connected to: '" + model.getDatabaseUrl() + "'");
		output.setText(String.format("DBMS: %s, version: %s", model.getDatabaseProductName(), model.getDatabaseProductVersion()));
		
	}
	
	// Constructor
	public Controller() {
	}

	// Initialize view
	@FXML
    public void initialize(){
        //Will be called by FXMLLoader
		usernamefield.setText("username");
		passwordfield.setText("password");
		connectbutton.setText("Connect");
		connectionstatelabel.setText("Connection: disconnected");
    }
	
	/**
	 * This is called whenever the connect button is pressed.
	 * @param event Contains details about the event.
	 */
	protected void connectButtonActionPerformed(ActionEvent event) {

		try {

			// The model's connect method will do everything for us, just call it
			model.connect(usernamefield.getText(), passwordfield.getText());
			connectionstatelabel.setText("Connection created");

			// Test the connection
			if (model.testConnection()) {
				log.setText("Connection seems to be working.");
			} else {
				log.setText("It's a TRAP!");
			}

		} catch (Exception e) {
			
		}
		/*catch (SQLException e) {

			// !TODO: More user friendly error handling
			log(e.toString());

		} catch (ClassNotFoundException e) {

			// !TODO: More user friendly error handling
			log(e.toString());

		}*/

	}
	
}
