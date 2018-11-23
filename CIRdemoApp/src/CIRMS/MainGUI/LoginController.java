package CIRMS.MainGUI;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import CIRMS.Database.DatabaseHandler;
import CIRMS.Settings.SettingsController;
import CIRMS.imageView.ImageView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController implements Initializable
{
	// instance variables declare
	private String username = "admin";
	private String password = "admin";
	private DatabaseHandler handler;
	@FXML private AnchorPane rootPane;
    @FXML private JFXPasswordField passwordTxtField;
    @FXML private JFXTextField usernameTxtField;
    private String userId = null;
	private String pass = null;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		handler = DatabaseHandler.getInstance();	// create an instance of database handler

		String qu = "SELECT * FROM SETTING";
    	ResultSet result = handler.execQuery(qu);

    	try {// retrieve information form setting database
			while(result.next())
			{
				 userId = result.getString("Username");
				 pass = result.getString("Password");
			}
		} catch (SQLException e1) {
			dialogBox("Error occured on retrieve data", "Error: "+e1.getMessage());
		}
	}
	/**
	 * Method handleOnForgotPassword will invoke the setting page Fxml for user to update
	 * the user password.
	 * @param event
	 */
    @FXML
    private void handleOnForgotPassword(ActionEvent event)
    {
    	try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Settings/Settings.fxml"));
			Parent parent = loader.load();
			SettingsController controller = (SettingsController)loader.getController();
			controller.radioButtonSelected(true);
			Stage Stage = new Stage();
			Stage.setScene(new Scene(parent));
			Stage.setTitle("Settings");
			Stage.setResizable(false);
			Stage.initModality(Modality.APPLICATION_MODAL);
			Stage.show();
		} catch(Exception e) {
			dialogBox("Error occured on Settings", "Error: "+e.getMessage());
		}
    }
    /**
     * Method handlerCancelBtn will exit on the system an terminate the
     * program.
     * @param event
     */
    @FXML
    private void handlerCancelBtn(ActionEvent event)
    {
    	System.exit(0);
    }
    /**
     * Method handlerLoginBtn will load the main view page and close
     * the currently page if it is successfully.
     * @param event
     */
    @FXML
    private void handlerLoginBtn(ActionEvent event)
    {
    	if (usernameTxtField.getText().equals(userId)&&passwordTxtField.getText().equals(pass))
    	{
    		try
			{
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/MainGUI/MainView.fxml"));
				Parent parent = loader.load();
				Stage Stage = new Stage();
				Stage.setTitle("Main View");
				Stage.setScene(new Scene(parent));
				Stage.show();
				ImageView.setStageIcon(Stage);
				Stage stage = (Stage)rootPane.getScene().getWindow();
				stage.close();
			} catch(Exception e) {
				e.printStackTrace();
				dialogBox("Error occured on loading Main", "Error: "+e.getMessage());
			}
		}else
    	if (usernameTxtField.getText().equals(username)&&passwordTxtField.getText().equals(password))
    	{
			try
			{
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/MainGUI/MainView.fxml"));
				Parent parent = loader.load();
				Stage Stage = new Stage();
				Stage.setTitle("Main View");
				Stage.setScene(new Scene(parent));
				Stage.show();
				ImageView.setStageIcon(Stage);
				Stage stage = (Stage)rootPane.getScene().getWindow();
				stage.close();
			} catch(Exception e) {
				e.printStackTrace();
				dialogBox("Error occured on loading Main", "Error: "+e.getMessage());
			}
    	}else
    	{
    		usernameTxtField.getStyleClass().add("wrong-credentials");
    		passwordTxtField.getStyleClass().add("wrong-credentials");

    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Invalid credentials");
    		alert.showAndWait();
    		return;
    	}
    }
    /**
     * Method dialogBox will receive two parameter, the title and context
     * to throw an alert.
     * @param title
     * @param context
     */
    private void dialogBox(String title, String context) {
		Platform.runLater(()->{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle(title);
			alert.setContentText(context);
			alert.show();
		});
	}
}
