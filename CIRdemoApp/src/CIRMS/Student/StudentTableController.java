package CIRMS.Student;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import CIRMS.Database.DatabaseHandler;
import CIRMS.imageView.ImageView;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StudentTableController implements Initializable {

	private DatabaseHandler databaseHandler;
	// Btech student list
	private ObservableList<StudentProperty> list = FXCollections.observableArrayList();
	// Master student list
	private ObservableList<StudentProperty> listOfMasterStud = FXCollections.observableArrayList();

//private MainViewController mainView;
	@FXML private JFXButton backButton;

	// Btech student instance variable
	@FXML private AnchorPane rootPaneStdTable;
	@FXML private TableView<StudentProperty> studentBTable;
	@FXML private TableColumn<StudentProperty, String> studNoBColumn;
	@FXML private TableColumn<StudentProperty, String> nameBColumn;
	@FXML private TableColumn<StudentProperty, String> surnameBColumn;
	@FXML private TableColumn<StudentProperty, String> supervisorBColumn;
	@FXML private TableColumn<StudentProperty, String> emailBColumn;
	@FXML private TableColumn<StudentProperty, String> cellphoneNoBColumn;
	@FXML private TableColumn<StudentProperty, String> stationBColumn;
	@FXML private TableColumn<StudentProperty, String> courseBColumn;

	// Master student instance variable
	@FXML private TableView<StudentProperty> masterStudTable;
	@FXML private TableColumn<StudentProperty, String> nameMColumn;
	@FXML private TableColumn<StudentProperty, String> surnameMColumn;
    @FXML private TableColumn<StudentProperty, String> studentaNoMColumn;
    @FXML private TableColumn<StudentProperty, String> supervisorMColumn;
    @FXML private TableColumn<StudentProperty, String> emailMColumn;
    @FXML private TableColumn<StudentProperty, String> cellphoneMColumn;
    @FXML private TableColumn<StudentProperty, String> stationMColumn;
    @FXML private TableColumn<StudentProperty, String> courseMColumn;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		databaseHandler = DatabaseHandler.getInstance();
		initColumnBtech();
		initColumnMaster();
		loadBtechData();
		loadMasterData();
	}
	/**
	 *
	 * @param event
	 */
	@FXML
    private void handlerEditMasterStud(ActionEvent event)
	{
		StudentProperty selectMasterForEdit = masterStudTable.getSelectionModel().getSelectedItem();
		if (selectMasterForEdit == null)
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("No Student selected");
			alert.setContentText("Please select a student for edit.");
			alert.showAndWait();
			return;
		}
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Student/AddStudent.fxml"));
			Parent parent = loader.load();
			AddStudentController controller = (AddStudentController)loader.getController();
			controller.editInputGUI(selectMasterForEdit);
			Stage stage = new Stage();
			stage.setTitle("Edit "+ selectMasterForEdit.getStudendID());
			stage.setScene(new Scene(parent));
			stage.show();
			ImageView.setStageIcon(stage);
			// Refresh on the close
			stage.setOnCloseRequest((e)-> handlerOnRefreshMasterStud(new ActionEvent()));

		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	/**
	 *
	 * @param event
	 */
	@FXML
    private void handlerEditStudentB(ActionEvent event)
	{
		StudentProperty selectedForEdit = studentBTable.getSelectionModel().getSelectedItem();
		if (selectedForEdit ==  null)
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("No Student selected");
			alert.setContentText("Please select a student for edit.");
			alert.showAndWait();
			return;
		}
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Student/AddStudent.fxml"));
			Parent parent = loader.load();
			AddStudentController controller = (AddStudentController)loader.getController();
			controller.inflateUI(selectedForEdit);
			Stage stage = new Stage();
			stage.setTitle("Edit Student");
			stage.setScene(new Scene(parent));
			stage.show();
			ImageView.setStageIcon(stage);
			// Refresh on the close
			stage.setOnCloseRequest((e)-> handlerRefresh(new ActionEvent()));

		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	/**
	 * Method handlerOnDeleteMasterStud will be invoke when the
	 * user select a specify row to be deleted.
	 * @param event
	 */
	@FXML
    private void handlerOnDeleteMasterStud(ActionEvent event)
	{
		StudentProperty selectMasterForDeletion = masterStudTable.getSelectionModel().getSelectedItem();

		if (selectMasterForDeletion == null)
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("No Student selected");
			alert.setContentText("Please select a student for delete.");
			alert.showAndWait();
			return;
		}

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Deleting Student");
		alert.setContentText("Are you sure want to delete the student "+ selectMasterForDeletion.getStudendID());
		Optional<ButtonType> answerM = alert.showAndWait();

		if (answerM.get() == ButtonType.OK)
		{
			boolean resultM = DatabaseHandler.getInstance().deleteMasterStudent(selectMasterForDeletion);
			if (resultM)
			{
				Alert simpleAlert = new Alert(Alert.AlertType.INFORMATION);
				simpleAlert.setTitle("Student deleted");
				simpleAlert.setContentText(selectMasterForDeletion.getStudendID()+" was deleted successfully");
				simpleAlert.showAndWait();

				// remove the selected row
				listOfMasterStud.remove(selectMasterForDeletion);
			}else
			{
				Alert simp1leAlert = new Alert(Alert.AlertType.INFORMATION);
				simp1leAlert.setTitle("Failed");
				simp1leAlert.setContentText(selectMasterForDeletion.getStudendID()+" Could not be deleted");
				simp1leAlert.showAndWait();
			}
		}else
		{
			Alert Infalert = new Alert(Alert.AlertType.INFORMATION);
			Infalert.setTitle("Deletion cancelled");
			Infalert.setContentText("Deletion process cancelled.");
			Infalert.showAndWait();
		}
    }
	/**
	 *
	 * @param event
	 */
	@FXML
    private void handlerDeleteStudentB(ActionEvent event)
	{
		//Fetch the selected row
		StudentProperty selectedForDelection = studentBTable.getSelectionModel().getSelectedItem();

		if (selectedForDelection ==  null)
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("No Student selected");
			alert.setContentText("Please select a student for delete.");
			alert.showAndWait();
			return;
		}

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Deleting Student");
		alert.setContentText("Are you sure want to delete the student "+ selectedForDelection.getStudendID());
		Optional<ButtonType> answer = alert.showAndWait();

		if (answer.get() == ButtonType.OK)
		{
			Boolean result = DatabaseHandler.getInstance().deleteStudentB(selectedForDelection);
			if (result)
			{
				Alert simpleAlert = new Alert(Alert.AlertType.INFORMATION);
				simpleAlert.setTitle("Student deleted");
				simpleAlert.setContentText(selectedForDelection.getStudendID()+" was deleted successfully");
				simpleAlert.showAndWait();
				// Remove from the list the selected one
				list.remove(selectedForDelection);
			}else
			{
				Alert simp1leAlert = new Alert(Alert.AlertType.INFORMATION);
				simp1leAlert.setTitle("Failed");
				simp1leAlert.setContentText(selectedForDelection.getStudendID()+" Could not be deleted");
				simp1leAlert.showAndWait();
			}
		}else
		{
			Alert Infalert = new Alert(Alert.AlertType.INFORMATION);
			Infalert.setTitle("Deletion cancelled");
			Infalert.setContentText("Deletion process cancelled.");
			Infalert.showAndWait();
		}
    }

	/**
	 *Method loadBtechData will retrieve and load the btech student information to the
	 *table.
	 */
	private void loadBtechData()
	{
		list.clear();
		String qu = "SELECT * FROM BTECHSTUDENT";
		ResultSet result = databaseHandler.execQuery(qu);

		try {// retrieve student information form database
			while(result.next())
			{
				String studendID = result.getString("studentNoB");
				String nameB = result.getString("nameB");
				String studSurnameB = result.getString("surnameB");
				String supervisorB = result.getString("supervisorB");
				String emailB = result.getString("emailB");
				String cellphoneB = result.getString("cellphoneNoB");
				String courseB = result.getString("stationB");
				String stationB = result.getString("courseB");

				list.add(new StudentProperty(studendID, nameB, studSurnameB,
						supervisorB, emailB, cellphoneB, courseB, stationB));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Transfer the list data on the table
		studentBTable.setItems(list);
	}
	/**
	 *Method loadMasterData will retrieve and load the master student to the table.
	 */
	private void loadMasterData()
	{
		listOfMasterStud.clear();
		String qu = "SELECT * FROM MASTERSTUDENT";
		ResultSet resultM = databaseHandler.execQuery(qu);
		try {
			// retrieve student information form database
			while(resultM.next())
			{
				String studIDM = resultM.getString("studentNoM");
				String studNameM = resultM.getString("nameM");
				String studSurnameM = resultM.getString("surnameM");
				String studSupervisorM = resultM.getString("supervisorM");
				String studEmailM = resultM.getString("emailM");
				String studCellphoneM = resultM.getString("cellphoneNoM");
				String studStationM = resultM.getString("stationM");
				String studCourseM = resultM.getString("courseM");

				listOfMasterStud.add(new StudentProperty(studIDM, studNameM, studSurnameM,
						studSupervisorM, studEmailM, studCellphoneM, studStationM, studCourseM));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		masterStudTable.setItems(listOfMasterStud);
	}
	/**
	 * Method initColumnBtech will fill up with the record about
	 * the student.
	 */
	private void initColumnBtech()
	{
		studNoBColumn.setCellValueFactory(new PropertyValueFactory<>("studendID"));
		nameBColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		surnameBColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
		supervisorBColumn.setCellValueFactory(new PropertyValueFactory<>("supervisor"));
		emailBColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
		cellphoneNoBColumn.setCellValueFactory(new PropertyValueFactory<>("cellphone"));
		stationBColumn.setCellValueFactory(new PropertyValueFactory<>("station"));
		courseBColumn.setCellValueFactory(new PropertyValueFactory<>("course"));
	}
	/**
	 * Method initColumnMaster will fill up the record on the
	 * table for each student.
	 */
	private void initColumnMaster()
	{
		studentaNoMColumn.setCellValueFactory(new PropertyValueFactory<>("studendID"));
		nameMColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		surnameMColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
		supervisorMColumn.setCellValueFactory(new PropertyValueFactory<>("supervisor"));
		emailMColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
		cellphoneMColumn.setCellValueFactory(new PropertyValueFactory<>("cellphone"));
		stationMColumn.setCellValueFactory(new PropertyValueFactory<>("station"));
		courseMColumn.setCellValueFactory(new PropertyValueFactory<>("course"));
	}

	@FXML
    private void loadMainFxml(ActionEvent event)
	{
		try
		{// invoke main view fxml
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/MainGUI/MainView.fxml"));
			Parent parent = loader.load();
			Stage Stage = new Stage();
			Stage.setScene(new Scene(parent));
			Stage.setResizable(false);
			Stage.show();
			// close the fxml
			Stage stage = (Stage)rootPaneStdTable.getScene().getWindow();
			stage.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
	/**
	 *
	 * @param actionEvent
	 */
	 @FXML
	private void handlerRefresh(ActionEvent event)
	{
		loadBtechData();
    }
    /**
     *
     * @param event
     */
    @FXML
    private void handlerOnRefreshMasterStud(ActionEvent event)
    {
    	loadMasterData();
    }

	/**
	 * StudentProperty is a inner class to fill the table
	 * @author Visi Mansukinini
	 *
	 */
	public static class StudentProperty
	{
		private final SimpleStringProperty studendID;
		private final SimpleStringProperty name;
		private final SimpleStringProperty surname;
		private final SimpleStringProperty supervisor;
		private final SimpleStringProperty email;
		private final SimpleStringProperty cellphone;
		private final SimpleStringProperty course;
		private final SimpleStringProperty station;

		public StudentProperty(String studendID, String name, String surname, String supervisor, String email,
				String cellphone, String course, String station)
		{
			this.studendID = new SimpleStringProperty(studendID);
			this.name = new SimpleStringProperty(name);
			this.surname = new SimpleStringProperty(surname);
			this.supervisor = new SimpleStringProperty(supervisor);
			this.email = new SimpleStringProperty(email);
			this.cellphone = new SimpleStringProperty(cellphone);
			this.course = new SimpleStringProperty(course);
			this.station = new SimpleStringProperty(station);
		}

		public String getStudendID() {
			return studendID.get();
		}

		public String getName() {
			return name.get();
		}

		public String getSurname() {
			return surname.get();
		}

		public String getSupervisor() {
			return supervisor.get();
		}

		public String getEmail() {
			return email.get();
		}

		public String getCellphone() {
			return cellphone.get();
		}

		public String getCourse() {
			return course.get();
		}

		public String getStation() {
			return station.get();
		}
	}
}
