package CIRMS.Student;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import CIRMS.Database.DatabaseHandler;
import CIRMS.Validation.validateInput;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddStudentController implements Initializable
{
	@FXML private JFXRadioButton radioButtonBtech;
    @FXML private ToggleGroup toggleGroup;
    @FXML private JFXRadioButton radioButtonMaster;

	// Instance variable
    @FXML private JFXTextField nameTxt;
    @FXML private JFXTextField surnameTxt;
    @FXML private JFXTextField studentNoTxt;
    @FXML private JFXTextField supervisorTxt;
    @FXML private JFXTextField emailTxt;
    @FXML private JFXTextField cellNoTxt;
    @FXML private JFXTextField stationTxt;
    @FXML private JFXTextField courseTxt;
    @FXML private AnchorPane rootPane;

    private DatabaseHandler handler;
    private Boolean isInEditMode = Boolean.FALSE;
    private String tableName;


    /**
     * Method addStudent will get the input data from text field for
     * the Master student and insert to the database.
     * @param event
     */
    @FXML
    private void addStudent(ActionEvent event)
    {
    	String studentID = studentNoTxt.getText();
    	String studentName = nameTxt.getText();
    	String studentSurname = surnameTxt.getText();
    	String studentSupervior = supervisorTxt.getText();
    	String studentEmail = emailTxt.getText();
    	String studentCellphone = cellNoTxt.getText();
    	String studentStation = stationTxt.getText();
    	String studentCourse = courseTxt.getText();

    	if (studentID.isEmpty()||studentName.isEmpty()||studentSurname.isEmpty()||
    			studentSupervior.isEmpty()||studentEmail.isEmpty()||studentCellphone.isEmpty()
    			||studentStation.isEmpty()||studentCourse.isEmpty())
    	{
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Please Enter in all fields");
    		alert.showAndWait();
    		return;
		}
    	// validatetin student information
    	if (!validateInput.validateName(studentName))
    	{
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Please Enter name starting with Capital letter");
    		alert.showAndWait();
    		return;
		}else
		if (!validateInput.validateSurname(studentSurname))
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Please Enter Surname starting with Capital letter");
    		alert.showAndWait();
    		return;
		}else
		if (!validateInput.validateStudentNo(studentID))
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Please Enter student number that start with 1 or 2 and 9 digits long");
    		alert.showAndWait();
    		return;

		}else
		if (!validateInput.validateEmailAddress(studentEmail))
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Missing character, Please Enter @ or . in your email address");
    		alert.showAndWait();
    		return;
		}else
		if (!validateInput.validateCellphoneNo(studentCellphone))
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Please Enter cellphone number with 10 digits");
    		alert.showAndWait();
    		return;
		}
		else
    	if (!validateInput.validateSupervisor(studentSupervior))
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Please Enter supervisor name that starting with Capital letter");
    		alert.showAndWait();
    		return;

		}

    	// Check radio button selection
    	if (radioButtonBtech.isSelected()||radioButtonMaster.isSelected())
    	{
    		if (radioButtonBtech.isSelected())
    		{
    			tableName = "BTECHSTUDENT";
    		}else
			if(radioButtonMaster.isSelected())
			{
				tableName = "MASTERSTUDENT";
			}
		}else
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Please Select student Qualification");
    		alert.showAndWait();
    		return;
		}
    	// check if it is on edit mode
    	if (isInEditMode)
    	{
    		handlerOnEditMaster();
    		handlerEditOperation();
    		return;
		}
    	// Insert values in the MASTERSTUDENT table.
    	String quM = "INSERT INTO " + tableName +" VALUES("+
    			"'" + studentID + "'," +
    			"'" + studentName + "'," +
    			"'" + studentSurname + "'," +
    			"'" + studentSupervior + "'," +
    			"'" + studentEmail + "'," +
    			"'" + studentCellphone + "'," +
    			"'" + studentStation + "'," +
    			"'" + studentCourse + "'" +
    			")";

    	if (handler.execAction(quM))
    	{
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
    		alert.setHeaderText(null);
    		alert.setContentText("Saved");
    		Optional<ButtonType> answer = alert.showAndWait();
    		if (answer.get() == ButtonType.OK)
    		{
    			Stage stage = (Stage)rootPane.getScene().getWindow();
    	    	stage.close();
			}
		} else
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Error Occured");
    		alert.showAndWait();
		}
    }
    /**
     * Method cancelMasterStudent will hide the window when it is
     * invoked.
     * @param event
     */
    @FXML
    private void cancelStudent(ActionEvent event)
    {
    	Stage stage =(Stage)rootPane.getScene().getWindow();
    	stage.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		handler = DatabaseHandler.getInstance();
		radioButtonBtech.setToggleGroup(toggleGroup);
		radioButtonMaster.setToggleGroup(toggleGroup);
	}
	/**
     *Method editInputGUI will receive data from StudentProperty inner class in StudentTableController
     *and set it to text field to be edit.
     * @param masterStudent
     */
    public void editInputGUI(StudentTableController.StudentProperty masterStudent)
    {
    	nameTxt.setText(masterStudent.getName());
    	surnameTxt.setText(masterStudent.getSurname());
    	studentNoTxt.setText(masterStudent.getStudendID());
    	supervisorTxt.setText(masterStudent.getSupervisor());
    	emailTxt.setText(masterStudent.getEmail());
    	cellNoTxt.setText(masterStudent.getCellphone());
    	stationTxt.setText(masterStudent.getStation());
    	courseTxt.setText(masterStudent.getCourse());
    	studentNoTxt.setEditable(false);
    	isInEditMode = Boolean.TRUE;
    }
    /**
	 *Method inflateUI will receive the data from StudentTableController.StudentProperty inner class
	 *and set to text field to be edit.
	 */
	public void inflateUI(StudentTableController.StudentProperty Bstudent)
	{
		nameTxt.setText(Bstudent.getName());
		surnameTxt.setText(Bstudent.getSurname());
		studentNoTxt.setText(Bstudent.getStudendID());
		supervisorTxt.setText(Bstudent.getSupervisor());
		emailTxt.setText(Bstudent.getEmail());
		cellNoTxt.setText(Bstudent.getCellphone());
		stationTxt.setText(Bstudent.getStation());
		courseTxt.setText(Bstudent.getCourse());
		studentNoTxt.setEditable(false);
		isInEditMode = Boolean.TRUE;
	}
	/**
	 * Method handlerOnEditMasterOperation will set the StudentProperty class with new values
	 * and then update the Master student table in the database.
	 */
	private void handlerOnEditMaster()
	{
		StudentTableController.StudentProperty Mstudent = new StudentTableController.StudentProperty(studentNoTxt.getText(), nameTxt.getText(),
				surnameTxt.getText(), supervisorTxt.getText(), emailTxt.getText(), cellNoTxt.getText(), stationTxt.getText(), courseTxt.getText());

		if(handler.updateMasterstudent(Mstudent))
		{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
    		alert.setHeaderText("success");
    		alert.setContentText("Student Updated");
    		Optional<ButtonType> answer = alert.showAndWait();
    		if (answer.get() == ButtonType.OK)
    		{
    			Stage stage = (Stage)rootPane.getScene().getWindow();
    	    	stage.close();
			}
		}else
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText("Failed");
    		alert.setContentText("Can't update student");
    		alert.showAndWait();
		}
	}
	/**
	 *Method handlerEditOperation will set in StudentProperty class a new values
	 *and then update the Btech student table in the database.
	 */
	private void handlerEditOperation()
    {
		StudentTableController.StudentProperty Bstudent = new StudentTableController.StudentProperty(studentNoTxt.getText(), nameTxt.getText(),
				surnameTxt.getText(), supervisorTxt.getText(), emailTxt.getText(), cellNoTxt.getText(), stationTxt.getText(), courseTxt.getText());
		if(handler.updateBstudent(Bstudent))
		{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
    		alert.setHeaderText("success");
    		alert.setContentText("Student Updated");
    		Optional<ButtonType> answer = alert.showAndWait();
    		if (answer.get() == ButtonType.OK)
    		{
    			Stage stage = (Stage)rootPane.getScene().getWindow();
    	    	stage.close();
			}
		}else
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText("Failed");
    		alert.setContentText("Can't update student");
    		alert.showAndWait();
		}
	}
}
