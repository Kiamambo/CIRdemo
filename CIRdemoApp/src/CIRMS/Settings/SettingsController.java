package CIRMS.Settings;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;

import CIRMS.Database.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SettingsController implements Initializable
{
	// instance variables
	private DatabaseHandler handler;
	private String newPass;
	private String confirmPass;
	@FXML private ToggleGroup toggleGroup;
    @FXML private HBox createUserInfo;
    @FXML private JFXRadioButton createUserRadioBtn;
    @FXML private JFXTextField usernameCU;
    @FXML private JFXPasswordField passwordCU;
    @FXML private JFXPasswordField confirmPasswordCU;
    @FXML private HBox renewPassword;
    @FXML private JFXRadioButton renewpassordRadioBtn;
    @FXML private JFXTextField usernameRP;
    @FXML private JFXPasswordField newPasswordRP;
    @FXML private JFXPasswordField confirmPassowrdRP;



	/**
	 * Method handlerOnCancelSettings will exit the user from the page.
	 * @param event
	 */
	@FXML
	private void handlerOnCancelSettings(ActionEvent event)
	{
		Stage stage = (Stage)renewPassword.getScene().getWindow();
    	stage.close();
	}
	/**
	 * Method handlerOnSaveSettings will invoke the selected option either
	 * create user or renew password.
	 * @param event
	 */
	@FXML
	private void handlerOnSaveSettings(ActionEvent event)
	{
		if (createUserRadioBtn.isSelected())
		{
			handlerOnCreateUser();
		}else
		if(renewpassordRadioBtn.isSelected())
		{
			handlerOnRenewPassword();
		}
	}
	/**
	 * Method handlerOnRenewPassword will update the user password.
	 */
	private void handlerOnRenewPassword()
	{	// Alert the user for input  information
		if (usernameRP.getText().isEmpty()||newPasswordRP.getText().isEmpty()||confirmPassowrdRP.getText().isEmpty())
		{
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Please Enter in all fields");
    		alert.showAndWait();
    		return;
		}

		String query = "UPDATE SETTING SET Password = '"+newPasswordRP.getText().toString()+
								"' WHERE Username = '"+ usernameRP.getText().toString() +"'";
		// comparing the passwords if it is the same
		if (newPass.equals(confirmPass))
		{
			if (handler.execAction(query))
	    	{
	    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    		alert.setHeaderText(null);
	    		alert.setContentText("Password Updated");
	    		Optional<ButtonType> answer = alert.showAndWait();
	    		if (answer.get() == ButtonType.OK)
	    		{
	    			Stage stage = (Stage)renewPassword.getScene().getWindow();
	    	    	stage.close();
				}
			} else
			{
				Alert alert = new Alert(Alert.AlertType.ERROR);
	    		alert.setHeaderText(null);
	    		alert.setContentText("Error Occured");
	    		alert.showAndWait();
			}
		}else
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Mismeste on password");
    		alert.showAndWait();
		}
	}
	/**
	 * Method handlerOnCreateUser will create a user and setting him/her with
	 * user name and password.
	 */
	private void handlerOnCreateUser()
	{
		String username = usernameCU.getText();
		String password = passwordCU.getText();
		String confirmPassword = confirmPasswordCU.getText();
		// alert if there is a fields empty
		if (username.isEmpty()||password.isEmpty()||confirmPassword.isEmpty() )
		{
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Please Enter in all fields");
    		alert.showAndWait();
    		return;
		}
		//query to insert
		String qu = "INSERT INTO SETTING VALUES("+
				"'"+ username +"',"+
				"'"+ password +"'"+
				")";
		// Compare the password to proceed with insert in database
		if (password.equals(confirmPassword))
		{
			if (handler.execAction(qu))
	    	{
	    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    		alert.setHeaderText(null);
	    		alert.setContentText("Saved");
	    		Optional<ButtonType> answer = alert.showAndWait();
	    		if (answer.get() == ButtonType.OK)
	    		{
	    			Stage stage = (Stage)renewPassword.getScene().getWindow();
	    	    	stage.close();
				}
			} else
			{
				Alert alert = new Alert(Alert.AlertType.ERROR);
	    		alert.setHeaderText(null);
	    		alert.setContentText("Error Occured");
	    		alert.showAndWait();
			}
		}else
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Mismeste on password");
    		alert.showAndWait();
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		confirmPass = confirmPassowrdRP.getText();
		newPass = newPasswordRP.getText();
		handler = DatabaseHandler.getInstance();
		JFXDepthManager.setDepth(createUserInfo, 1);	
		JFXDepthManager.setDepth(renewPassword, 1);
		createUserRadioBtn.setToggleGroup(toggleGroup);
		renewpassordRadioBtn.setToggleGroup(toggleGroup);

	}
	// disable some text fields on invoked method
	public void radioButtonSelected(boolean flag)
	{
		if (flag)
		{
			createUserRadioBtn.setDisable(true);
			usernameCU.setDisable(true);
			passwordCU.setDisable(true);
			confirmPasswordCU.setDisable(true);
		}else
		{
			usernameCU.setDisable(false);
			passwordCU.setDisable(false);
			confirmPasswordCU.setDisable(false);
		}
	}
}
