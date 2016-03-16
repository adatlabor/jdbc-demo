/**
 * This JavaFX skeleton is provided for the Software Laboratory 5 course. Its structure
 * should provide a general guideline for the students. Though we were trying to
 * create a good example application here, the code is probably not suitable for
 * a real life application.
 */

package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


// Application class
public class AppMain extends Application {
	
	// This class includes the entry point of the application.
	
	// Display GUI window
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/application/View.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Entry point for the application
	public static void main(String[] args) {
		launch(args);
	}
}
