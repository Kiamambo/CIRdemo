	package application;

import CIRMS.Database.DatabaseHandler;
import CIRMS.imageView.ImageView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXMLLoader;

/**
 * @author Visi Mansukinini
 *
 * Default username: admin
 * Default password: admin
 */

public class Main extends Application
{
	/**
	 * This is the driver of the Center of Instrumentation Research
	 * Management System application.
	 */
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			Parent root = FXMLLoader.load(getClass().getResource("/CIRMS/MainGUI/Login.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/CIRMS/MainGUI/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Center of Instrumentation Management System");
			primaryStage.setResizable(false);
			primaryStage.show();
			ImageView.setStageIcon(primaryStage);
			new Thread(()->DatabaseHandler.getInstance()).start();
		} catch(Exception e) {
			dialogBox("Error occured on Login", "Error on "+e.getCause());
		}
	}
	private void dialogBox(String title, String context) {
		Platform.runLater(()->{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle(title);
			alert.setContentText(context);
			alert.show();
		});
	}
	public static void main(String[] args) {
		launch(args);
	}
}
