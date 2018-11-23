package CIRMS.MainGUI;


import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;

import CIRMS.Components.CompTypeAController;
import CIRMS.Components.CompTypeBTableController;
import CIRMS.Components.CompTypeCTableController;
import CIRMS.Components.Component;
import CIRMS.Components.ComponentTypeB;
import CIRMS.Components.ComponentTypeC;
import CIRMS.Database.DatabaseHandler;
import CIRMS.Equpments.Equipment;
import CIRMS.Equpments.EquipmentTableController;
import CIRMS.Student.Student;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainViewController implements Initializable
{	// creating list of view issues
	ObservableList<String> lisOfView = FXCollections.observableArrayList();
	// create observable list of equipments to fill the combo box.
	private ObservableList<String> listOfEquip = FXCollections.observableArrayList("Function Generator");
//	private ObservableList<String> listOfEquip = FXCollections.observableArrayList("BreadBoards",
//    		"Computers", "Function Generator", "Multimeters", "Oscilloscopes", "Power Supplies", "Tools");
	// Create observable list of student qualification to fill the combo box.
	private ObservableList<String> listOfStd = FXCollections.observableArrayList("Btech");
	// create instance variables
	private Stage primaryStage;
	private Equipment equip;
	private Component componentTypeA;
	private ComponentTypeB compTypeB;
	private ComponentTypeC compTypeC;
	private Student student;
	private String EquipAssetNum;
	private Boolean isReadytoSubmission = false;
	@FXML private JFXDrawer drawer;
	@FXML private JFXHamburger hamburger;
	@FXML private StackPane rootStackPane;
	@FXML private HBox EquipmentInfo;
	@FXML private HBox studentInfo;
	@FXML private TextField equipModelInput;
	@FXML private TextField assetNumberInput;
	@FXML private Text equipName;
	@FXML private Text equipmentCondition;
	@FXML private Text equipStatus;
	@FXML private JFXComboBox<String> comboBoxTables;
	@FXML private JFXComboBox<String> studComboBox;
    @FXML private TextField studNumTxtField;
    @FXML private Text studSurname;
    @FXML private Text studContact;
    @FXML private Text emailAddress;
    @FXML private JFXTextField EquipModel;
    @FXML private ListView<String> listViewData;
    private DatabaseHandler handler;

    /**
     * Method equipmentNameComboBox will provide a menu of equipment name to be select
     * and set the
     * @param event
     */
	@FXML
    private void equipmentNameComboBox(ActionEvent event)
	{
		String selectedEquip = comboBoxTables.getValue().toString();
		if (selectedEquip.equalsIgnoreCase("Function Generator")) {
			equip = new Equipment("FUNCTIONGENERATOR", "FgName", "FgModel", "FgSerialNumber", "FgAssetNumber", "FgNotes", "FgTotal", "FgAvailable");
    		}
//		if (selectedEquip.equalsIgnoreCase("BreadBoards")) {
//			equip = new Equipment("BREADBOARD", "BdName", "BdModel", "BdSerialNumber", "BdAssetNumber", "BdNotes", "BdTotal", "BdAvailable");
//		}else
//		if (selectedEquip.equalsIgnoreCase("Computers")) {
//			equip = new Equipment("COMPUTERS", "CompName", "CompModel", "CompSerialNumber", "CompAssetNumber", "CompNotes", "CompTotal", "CompAvailable");
//		}else
//		if (selectedEquip.equalsIgnoreCase("Multimeters")) {
//			equip = new Equipment("MULTIMETER", "MName", "MModel", "MSerialNumber", "MAssetNumber", "MNotes", "MdTotal", "MAvailable");
//    	}else
//		if (selectedEquip.equalsIgnoreCase("Oscilloscopes")) {
//			equip = new Equipment("OSCILLOSCOPE", "OSCIName", "OSCIModel", "OSCISerialNumber", "OSCIAssetNumber", "OSCINotes", "OSCITotal", "OSCIAvailable");
//    	}else
//		if (selectedEquip.equalsIgnoreCase("Power Supplies")) {
//			equip = new Equipment("POWERSUPPLIER", "PSName", "PSModel", "PSSerialNumber", "PSAssetNumber", "PSNotes", "PSTotal", "PSAvailable");
//    	}else
//		if (selectedEquip.equalsIgnoreCase("Tools")) {
//			equip = new Equipment("TOOLS", "TName", "TModel", "TSerialNumber", "TAssetNumber", "TNotes", "TTotal", "TAvailable");
//    	}
    }
	/**
	 *
	 * @param event
	 */
    @FXML
    private void loadEquipmentInfo(ActionEvent event)
    {
    	clearEquipmentField();
    	if (comboBoxTables.getPromptText().isEmpty())
    	{
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Please Select Equipment");
    		alert.showAndWait();
    		return;
		}

    	if (equipModelInput.getText().isEmpty())
    	{
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Please Enter Equipment Model");
    		alert.showAndWait();
    		return;
		}

    	if (assetNumberInput.getText().isEmpty())
    	{
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Please Enter Equipment Asset Number");
    		alert.showAndWait();
    		return;
		}
    	String modelNum = equipModelInput.getText();
    	String assetNum = assetNumberInput.getText();
    	String qu = "SELECT * FROM "+ equip.getTableName()+ " WHERE "+ equip.getModel()+ " = '"+ modelNum +"' AND "
    					+ equip.getAssetNumber()+ " = '" +assetNum+ "'";
    	ResultSet result = handler.execQuery(qu);
    	Boolean flag = false;

    	try {
			while (result.next())
			{
				String EquipName = result.getString(equip.getEquipName());
				String equipCondition = result.getString(equip.getNotes());
				Boolean EquipStatus = result.getBoolean(equip.getAvail());
				equipName.setText(EquipName);
				equipmentCondition.setText(equipCondition);
				String status = (EquipStatus)?"Available": "Not Available";
				equipStatus.setText(status);
				flag = true;
			}
			if (!flag)
			{equipName.setText("No Such Equipment Available");}
		} catch (SQLException e) {
			dialogBox("Error occured on Equipment information", "Error: "+e.getCause());
		}
    }
    /**
     * Method clearEquipmentField will clean the equipment fields in main view.
     */
    private void clearEquipmentField()
    {
    	equipName.setText("");
    	equipmentCondition.setText("");
    	equipStatus.setText("");
    }
    /**
     * Method clearStudentField will clean the student fields in main view.
     */
    private void clearStudentField()
    {
    	studSurname.setText("");
    	studContact.setText("");
    	emailAddress.setText("");
    }
    /**
     * Method loadStudComboBox will load the student details to the main view.
     * @param event
     */
    @FXML
    private void loadStudComboBox(ActionEvent event)
    {
    	String selectedOption = studComboBox.getValue().toString();
    	if (selectedOption.equalsIgnoreCase("Btech")){
    		student = new Student("BTECHSTUDENT", "studentNoB", "nameB",
    				"surnameB", "supervisorB", "emailB", "cellphoneNoB", "stationB", "courseB");
		}
//    	else
//		if (selectedOption.equalsIgnoreCase("Master")){
//			student = new Student("MASTERSTUDENT", "studentNoM", "nameM",
//    				"surnameM", "supervisorM", "emailM", "cellphoneNoM", "stationM", "courseM");
//	}
    }

    @FXML
    private void loadStudent(ActionEvent event)
    {
    	clearStudentField();
    	if (studComboBox.getPromptText().isEmpty())
    	{
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Please Select Student qualification");
    		alert.showAndWait();
    		return;
		}

    	if (studNumTxtField.getText().isEmpty())
    	{
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Please Enter Student Number");
    		alert.showAndWait();
    		return;
		}

    	String studNum = studNumTxtField.getText();
    	String qu = "SELECT * FROM "+ student.getTableName()+ " WHERE "+ student.getStudNumber() + " = '"+ studNum +"'";
    	ResultSet result = handler.execQuery(qu);
    	Boolean flag = false;
    	try {
			while (result.next())
			{
				String stdSurname = result.getString(student.getStudSurname());
				String stdContsctNo = result.getString(student.getStudCellphoneNo());
				String stdEmailAddress = result.getString(student.getStudEmail());
				studSurname.setText(stdSurname);
				studContact.setText(stdContsctNo);
				emailAddress.setText(stdEmailAddress);
				flag = true;
			}
			if (!flag)
			{studSurname.setText("No such Student Available");}
		} catch (SQLException e) {
			dialogBox("Error occured on load student", "Error: "+e.getCause());
		}
    }
    @FXML
    private void issueEquipmentBtn(ActionEvent event)
    {
    	String studentNumber = studNumTxtField.getText();
    	String equipAssetNumber = assetNumberInput.getText();
    	String equipModel = equipModelInput.getText();

    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirm issue operation");
    	alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to issue equipment "+ equipName.getText()+ " \n to "+ studSurname.getText());
		Optional<ButtonType> answer = alert.showAndWait();

		if (answer.get() == ButtonType.OK)
		{
			String qu = "INSERT INTO ISSUE(modelNumber, assetNumber, studentNo) VALUES("
						+ "'" + equipModel +"',"
						+ "'" + equipAssetNumber +"',"
						+ "'" + studentNumber + "')";
			String qu2 = "UPDATE "+ equip.getTableName() +" SET "+equip.getAvail()+ " = false WHERE "+equip.getModel()+" = '"+
					equipModel+ "' AND "+equip.getAssetNumber()+" = '"+equipAssetNumber+"'";

			if (handler.execAction(qu2)&&handler.execAction(qu))
			{
				Alert alertInfo = new Alert(Alert.AlertType.INFORMATION );
				alertInfo.setTitle("Success");
				alertInfo.setHeaderText(null);
				alertInfo.setContentText("Equipment Issue complete ");
				alertInfo.showAndWait();
			}else
			{
				Alert alertErr = new Alert(Alert.AlertType.ERROR);
				alertErr.setTitle("Failed");
				alertErr.setHeaderText(null);
				alertErr.setContentText("Issue 0peration failed");
				alertErr.showAndWait();
			}
		}else
		{
			Alert alertErr = new Alert(Alert.AlertType.INFORMATION);
			alertErr.setTitle("Cancelled");
			alertErr.setHeaderText(null);
			alertErr.setContentText("Issue 0peration Cancelled");
			alertErr.showAndWait();
		}
    }
    @SuppressWarnings("deprecation")
	@FXML
    private void loadEquimentIssue(ActionEvent event)
    {
    	isReadytoSubmission = false;
    	lisOfView.clear();
    	String modelNum = EquipModel.getText();
    	String qu = "SELECT * FROM ISSUE WHERE modelNumber = '"+modelNum+"'";
    	ResultSet result = handler.execQuery(qu);

    	try {
			while (result.next())
			{
				String EquipModelNum = modelNum;
				EquipAssetNum = result.getString("assetNumber");
				String student = result.getString("studentNo");
				Timestamp issueTime = result.getTimestamp("issueTime");
				int equipRenewCount = result.getInt("renewCount");
				lisOfView.add("Issue Date and Time: " + issueTime.toGMTString());
				lisOfView.add("Renew Count: " +equipRenewCount);

				lisOfView.add("Student Information:-");
				qu = "SELECT * FROM BTECHSTUDENT WHERE studentNoB = '"+student+"'";
				ResultSet result1 = handler.execQuery(qu);

				while(result1.next())
				{
					lisOfView.add("\tStudent number: " + result1.getString("studentNoB"));
					lisOfView.add("\tStudent Name: " + result1.getString("nameB"));
					lisOfView.add("\tStudent Surname: " + result1.getString("surnameB"));
					lisOfView.add("\tStudent Supervisor: " + result1.getString("supervisorB"));
					lisOfView.add("\tStudent Email: " + result1.getString("emailB"));
					lisOfView.add("\tStudent Cellphone: " + result1.getString("cellphoneNoB"));
					lisOfView.add("\tStudent Station: " + result1.getString("stationB"));
					lisOfView.add("\tStudent Course: " + result1.getString("courseB"));
				}

				lisOfView.add("Equipment Information:-");
				qu = "SELECT * FROM FUNCTIONGENERATOR WHERE FgModel = '"+ EquipModelNum+"' AND FgAssetNumber = '"+EquipAssetNum +"'";
				ResultSet result2 = handler.execQuery(qu);
				while(result2.next())
				{
					lisOfView.add("\tEquipment Name: " + result2.getString("FgName"));
					lisOfView.add("\tEquipment Model: " + result2.getString("FgModel"));
					lisOfView.add("\tEquipment Serial Number: " + result2.getString("FgSerialNumber"));
					lisOfView.add("\tEquipment Asset Number: " + result2.getString("FgAssetNumber"));
					lisOfView.add("\tEquipment Note: " + result2.getString("FgNotes"));
					isReadytoSubmission = true;
				}
			}
		} catch (SQLException e) {
			dialogBox("Error occured on Equipment Issue", "Error: "+e.getCause());
		}
    	listViewData.setItems(lisOfView);
    	if (lisOfView.isEmpty())
    	{
    		Alert alertErr = new Alert(Alert.AlertType.INFORMATION);
			alertErr.setTitle("Equipment");
			alertErr.setHeaderText(null);
			alertErr.setContentText("No such equipment found.");
			alertErr.showAndWait();
		}
    }

    @FXML
    private void handleOnSubmission(ActionEvent event)
    {
    	if (!isReadytoSubmission) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setTitle("Fail");
    		alert.setHeaderText(null);
    		alert.setContentText("Please select Equipment for Submit.");
    		alert.showAndWait();
			return;
		}

    	Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
    	alert2.setTitle("Confirm Submission Operation");
    	alert2.setHeaderText(null);
    	alert2.setContentText("Are you sure you want to return Equipment?");
		Optional<ButtonType> answer = alert2.showAndWait();

		if (answer.get() == ButtonType.OK)
		{
	    	String query = "DELETE FROM ISSUE WHERE modelNumber = '"+EquipModel.getText()+"'";
	    	String query1 = "UPDATE FUNCTIONGENERATOR SET FgAvailable = TRUE WHERE FgModel = '"+EquipModel.getText()+"' AND FgAssetNumber = '"+EquipAssetNum+"'";

	    	if (handler.execAction(query)&&handler.execAction(query1))
	    	{
	    		Alert alertErr = new Alert(Alert.AlertType.INFORMATION);
				alertErr.setTitle("Success");
				alertErr.setHeaderText(null);
				alertErr.setContentText("Equipment has been Submited.");
				alertErr.showAndWait();
			}else
			{
				Alert alertErr = new Alert(Alert.AlertType.ERROR);
				alertErr.setTitle("Fail");
				alertErr.setHeaderText(null);
				alertErr.setContentText("Submission fail.");
				alertErr.showAndWait();
			}
		}else
		{
			Alert alertErr = new Alert(Alert.AlertType.INFORMATION);
			alertErr.setTitle("Cancelled");
			alertErr.setHeaderText(null);
			alertErr.setContentText("Submission 0peration Cancelled");
			alertErr.showAndWait();
		}
    }

    @FXML
    private void loadRenewOperation(ActionEvent event)
    {
    	if (!isReadytoSubmission) {
    		Alert alert1 = new Alert(Alert.AlertType.ERROR);
    		alert1.setTitle("Fail");
    		alert1.setHeaderText(null);
    		alert1.setContentText("Please select Equipment for Submit.");
    		alert1.showAndWait();
			return;
		}

    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    	alert.setTitle("Confirm Renew Operation");
    	alert.setHeaderText(null);
    	alert.setContentText("Are you sure you want to renew Equipment?");
		Optional<ButtonType> answer = alert.showAndWait();

		if (answer.get() == ButtonType.OK)
		{
			String query = "UPDATE ISSUE SET issueTime = CURRENT_TIMESTAMP, renewCount = renewCount+1 WHERE modelNumber = '"+EquipModel.getText()+"'";
			if (handler.execAction(query))
			{
				Alert alertErr = new Alert(Alert.AlertType.INFORMATION);
				alertErr.setTitle("Success");
				alertErr.setHeaderText(null);
				alertErr.setContentText("Equipment has been Renew.");
				alertErr.showAndWait();
			}else
			{
				Alert alertErr = new Alert(Alert.AlertType.ERROR);
				alertErr.setTitle("Fail");
				alertErr.setHeaderText(null);
				alertErr.setContentText("Renew process has been failed.");
				alertErr.showAndWait();
			}
		}else
		{
			Alert alertErr = new Alert(Alert.AlertType.INFORMATION);
			alertErr.setTitle("Cancelled");
			alertErr.setHeaderText(null);
			alertErr.setContentText("Renew 0peration Cancelled");
			alertErr.showAndWait();
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		handler = DatabaseHandler.getInstance();
		JFXDepthManager.setDepth(EquipmentInfo, 1);
		JFXDepthManager.setDepth(studentInfo, 1);
		comboBoxTables.setItems(listOfEquip);
		comboBoxTables.setVisibleRowCount(4);
		studComboBox.setItems(listOfStd);
	}

    /**
     * Method handleOnClose will exit on the system and terminate the operation
     * @param event
     */
    @FXML
    private void handleOnClose(ActionEvent event)
    {
    	System.exit(0);
    }
    @FXML
    private void handleOnRestart(ActionEvent event)
    {
    	Stage stage = (Stage)rootStackPane.getScene().getWindow();
		stage.close();
        loadFxmlWindows("/CIRMS/MainGUI/Login.fxml", "Login");
    }
    /**
     * Method viewStudentsBtn will close the main and load the student table
     * to display student information.
     * @param event
     */
    @FXML
    private void viewStudentsBtn(ActionEvent event)
    {
    	Stage stage = (Stage)rootStackPane.getScene().getWindow();
		stage.close();
        loadFxmlWindows("/CIRMS/Student/StudentTable.fxml", "View Students");
    }
    /**
     * Method loadAddStudents will load the add student page to enter the student information.
     * @param event
     */
    @FXML
    private void loadAddStudents(ActionEvent event)
    {
    	loadFxmlWindows("/CIRMS/Student/AddStudent.fxml", "Add Student");
    }
    /**
     * Method displayBananaPlugsTable will invoke the fxml and display the information on table
     * @param event
     */
    @FXML
    private void displayBananaPlugsTable(ActionEvent event)
    {
    	try
		{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Components/CompTypeATable.fxml"));
    		Parent parent = loader.load();
    		CompTypeAController controller = (CompTypeAController)loader.getController();
    		componentTypeA = new Component("BANANAPLUGS", "BANPReferece", "BANPTotal", "BANPBin", "BANPLabelColour", "BANPOrder");
    		controller.loadCompTypeAData("Banana Plugs", componentTypeA);
    		controller.setCompObj(componentTypeA);
    		primaryStage = new Stage();
    		primaryStage.setScene(new Scene(parent));
    		primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
			ImageView.setStageIcon(primaryStage);
		} catch(Exception e) {
			dialogBox("Error occured on Banana Plugs", "Error: "+e.getCause());
		}
    }
    /**
	 * Method displayBNCTable will invoke the fxml component type a on the table
	 *
	 * @param event
	 */
    @FXML
    private void displayBNCTable(ActionEvent event)
    {
    	try
		{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Components/CompTypeATable.fxml"));
    		Parent parent = loader.load();
    		CompTypeAController controller = (CompTypeAController)loader.getController();
    		componentTypeA = new Component("BNC", "BNCReferece", "BNCTotal", "BNCBin", "BNCLabelColour", "BNCOrder");
    		controller.loadCompTypeAData("BNC connectors", componentTypeA);
    		controller.setCompObj(componentTypeA);
    		primaryStage = new Stage();
    		primaryStage.setScene(new Scene(parent));
    		primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
			ImageView.setStageIcon(primaryStage);
		} catch(Exception e) {
			dialogBox("Error occured on BNC Connectors", "Error: "+e.getCause());
		}
    }
	@FXML
    private void displayCrocClipsTable(ActionEvent event)
    {
    	try
		{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Components/CompTypeATable.fxml"));
    		Parent parent = loader.load();
    		CompTypeAController controller = (CompTypeAController)loader.getController();
    		componentTypeA = new Component("CROC_CLIPS", "CCReferece", "CCTotal", "CCBin", "CCLabelColour", "CCOrder");
    		controller.loadCompTypeAData("Croc Clips", componentTypeA);
    		controller.setCompObj(componentTypeA);
    		primaryStage = new Stage();
    		primaryStage.setScene(new Scene(parent));
    		primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
			ImageView.setStageIcon(primaryStage);
		} catch(Exception e) {
			dialogBox("Error occured on Croc Clips", "Error: "+e.getCause());
		}
    }
	/**
     * Method loadDataSocketTable will load the Data Socket component on the table component.
     * @param event
     */
    @FXML
    private void loadDataSocketTable(ActionEvent event)
    {
    	try
		{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Components/CompTypeATable.fxml"));
    		Parent parent = loader.load();
    		CompTypeAController controller = (CompTypeAController)loader.getController();
    		componentTypeA = new Component("DATASOCKETS", "DAReferece", "DATotal", "DABin", "DALabelColour", "DAOrder");
    		controller.loadCompTypeAData("Data Socket", componentTypeA);
    		controller.setCompObj(componentTypeA);
    		primaryStage = new Stage();
    		primaryStage.setScene(new Scene(parent));
    		primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
			ImageView.setStageIcon(primaryStage);
		} catch(Exception e) {
			dialogBox("Error Ocurrer on Data Socket Component", "Error: "+e.getMessage());
		}
    }
	@FXML
    private void displayDInConnectsTable(ActionEvent event)
    {
    	try
		{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Components/CompTypeATable.fxml"));
    		Parent parent = loader.load();
    		CompTypeAController controller = (CompTypeAController)loader.getController();
    		componentTypeA = new Component("DINCONNECTORS", "DINReferece", "DINTotal", "DINBin", "DINLabelColour", "DINOrder");
    		controller.loadCompTypeAData("DIN Connectors", componentTypeA);
    		controller.setCompObj(componentTypeA);
    		primaryStage = new Stage();
    		primaryStage.setScene(new Scene(parent));
    		primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
			ImageView.setStageIcon(primaryStage);
		} catch(Exception e) {
			dialogBox("Error occured on Din Connectors", "Error: "+e.getCause());
		}
    }
	/**
     * Method displayDisplayTable will load the component display on the table component.
     * @param event
     */
    @FXML
    private void displayDisplayTable(ActionEvent event)
    {
		try
		{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Components/CompTypeATable.fxml"));
    		Parent parent = loader.load();
    		CompTypeAController controller = (CompTypeAController)loader.getController();
    		componentTypeA = new Component("DISPLAY", "disReferece", "disTotal", "disBin", "disLabelColour", "disOrder");
    		controller.loadCompTypeAData("Display", componentTypeA);
    		controller.setCompObj(componentTypeA);
    		primaryStage = new Stage();
    		primaryStage.setScene(new Scene(parent));
    		primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
			ImageView.setStageIcon(primaryStage);
		} catch(Exception e) {
			dialogBox("Error Ocurrer on display Component", "Error: "+e.getMessage());
		}
	}

	@FXML
    private void displayDCMotorsTable(ActionEvent event)
    {
    	try
		{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Components/CompTypeATable.fxml"));
    		Parent parent = loader.load();
    		CompTypeAController controller = (CompTypeAController)loader.getController();
    		componentTypeA = new Component("DC_MOTORS", "DCMReferece", "DCMTotal", "DCMBin", "DCMLabelColour", "DCMOrder");
    		controller.loadCompTypeAData("DC Motors", componentTypeA);
    		controller.setCompObj(componentTypeA);
    		primaryStage = new Stage();
    		primaryStage.setScene(new Scene(parent));
    		primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
			ImageView.setStageIcon(primaryStage);
		} catch(Exception e) {
			dialogBox("Error occured on DC Motors", "Error: "+e.getCause());
		}
    }
	@FXML
    private void displayETDClipsTable(ActionEvent event)
    {
    	try
		{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Components/CompTypeATable.fxml"));
    		Parent parent = loader.load();
    		CompTypeAController controller = (CompTypeAController)loader.getController();
    		componentTypeA = new Component("ETD_CLIPS", "ECReferece", "ECTotal", "ECBin", "ECLabelColour", "ECOrder");
    		controller.loadCompTypeAData("ETD Clips", componentTypeA);
    		controller.setCompObj(componentTypeA);
    		primaryStage = new Stage();
    		primaryStage.setScene(new Scene(parent));
    		primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
			ImageView.setStageIcon(primaryStage);
		} catch(Exception e) {
			dialogBox("Error occured on ETD Clips", "Error: "+e.getMessage());
		}
    }

	@FXML
    private void displayFusesHoldersTable(ActionEvent event)
    {
    	try
		{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Components/CompTypeATable.fxml"));
    		Parent parent = loader.load();
    		CompTypeAController controller = (CompTypeAController)loader.getController();
    		componentTypeA = new Component("FUSES_HOLDERS", "FHReferece", "FHTotal", "FHBin", "FHLabelColour", "FHOrder");
    		controller.loadCompTypeAData("Fuses & Fuses Holders", componentTypeA);
    		controller.setCompObj(componentTypeA);
    		primaryStage = new Stage();
    		primaryStage.setScene(new Scene(parent));
    		primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
			ImageView.setStageIcon(primaryStage);
		} catch(Exception e) {
			dialogBox("Error occured on Fuses Holders ", "Error: "+e.getMessage());
		}
    }
	@FXML
    private void displayHighSpeedDiodesTable(ActionEvent event)
    {
    	try
 		{
     		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Components/CompTypeCTable.fxml"));
     		Parent parent = loader.load();
     		CompTypeCTableController controller = (CompTypeCTableController)loader.getController();
     		compTypeC = new ComponentTypeC("HIGHSPEEDDIODES", "HSDReferece", "HSDTotal", "HSDBin", "HSDLabelColour", "HSDOrder", "HSDPartNumber",
     				"HSDPackage", "HSDataSheet", "HSDRsComponents", "HSDMantech", "HSDCommunica", "HSDJpElectronics", "HSDElectrocComp");
     		controller.loadCData("High Speed / Faster recovery Diodes", compTypeC);
     		controller.setCompObjC(compTypeC);
     		primaryStage = new Stage();
     		primaryStage.setScene(new Scene(parent));
     		primaryStage.initModality(Modality.APPLICATION_MODAL);
 			primaryStage.show();
 			ImageView.setStageIcon(primaryStage);
 		} catch(Exception e) {
 			dialogBox("Error occured on High speed diodes", "Error: "+e.getMessage());
 		}
    }

	@FXML
    private void displayIGBTTable(ActionEvent event)
    {
    	try
 		{
     		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Components/CompTypeCTable.fxml"));
     		Parent parent = loader.load();
     		CompTypeCTableController controller = (CompTypeCTableController)loader.getController();
     		compTypeC = new ComponentTypeC("IGBTtable", "IGBReferece", "IGBTotal", "IGBBin", "IGBLabelColour", "IGBOrder", "IGBPartNumber",
     				"IGBPackage", "IGBDataSheet", "IGBRsComponents", "IGBMantech", "IGBCommunica", "IGBJpElectronics", "IGBElectrocComp");
     		controller.loadCData("IGBT", compTypeC);
     		controller.setCompObjC(compTypeC);
     		primaryStage = new Stage();
     		primaryStage.setScene(new Scene(parent));
     		primaryStage.initModality(Modality.APPLICATION_MODAL);
 			primaryStage.show();
 			ImageView.setStageIcon(primaryStage);
 		} catch(Exception e) {
 			dialogBox("Error occured on IGBT", "Error: "+e.getMessage());
 		}
    }
	@FXML
    private void DisplayInsulatorsTable(ActionEvent event)
	{
		try
		{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Components/CompTypeATable.fxml"));
    		Parent parent = loader.load();
    		CompTypeAController controller = (CompTypeAController)loader.getController();
    		componentTypeA = new Component("INSULATORS", "INSReferece", "INSTotal", "INSBin", "INSLabelColour", "INSOrder");
    		controller.loadCompTypeAData("Insulators", componentTypeA);
    		controller.setCompObj(componentTypeA);
    		primaryStage = new Stage();
    		primaryStage.setScene(new Scene(parent));
    		primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
			ImageView.setStageIcon(primaryStage);
		} catch(Exception e) {
			dialogBox("Error occured on Insulators", "Error: "+e.getMessage());
		}
    }
	@FXML
    private void displayIntegratedCircuitTable(ActionEvent event)
    {
    	try
		{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Components/CompTypeBTable.fxml"));
    		Parent parent = loader.load();
    		CompTypeBTableController controller = (CompTypeBTableController)loader.getController();
    		compTypeB = new ComponentTypeB("INTEGRATEDCIRCUIT", "ICTReferece", "ICTPartNumber", "ICTPackage", "ICTBin",
    				"ICTTotal", "ICTOrder", "ICTDataSheet", "ICTSupplier");
    		controller.setCompObjB(compTypeB);
    		controller.loadTypeBData("Integrated Circuit", compTypeB);
    		primaryStage = new Stage();
    		primaryStage.setScene(new Scene(parent));
    		primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
			ImageView.setStageIcon(primaryStage);
		} catch(Exception e) {
			dialogBox("Error occured on Integrated Circuit", "Error: "+e.getMessage());
		}
    }
	/**
     * Method DisplayLedsTable will load the led component on the table component.
     * @param event
     */
    @FXML
    private void DisplayLedsTable(ActionEvent event)
    {
    	try
		{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Components/CompTypeATable.fxml"));
    		Parent parent = loader.load();
    		CompTypeAController controller = (CompTypeAController)loader.getController();
    		componentTypeA = new Component("LEDS", "ledReferece", "ledTotal", "ledBin", "ledLabelColour", "ledOrder");
    		controller.loadCompTypeAData("Leds", componentTypeA);
    		controller.setCompObj(componentTypeA);
    		primaryStage = new Stage();
    		primaryStage.setScene(new Scene(parent));
    		primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
			ImageView.setStageIcon(primaryStage);
		} catch(Exception e) {
			dialogBox("Error Ocurrer on Leds Component", "Error: "+e.getMessage());
		}
    }
	 @FXML
    private void displayLogicalGateTable(ActionEvent event)
    {
    	try
		{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Components/CompTypeBTable.fxml"));
    		Parent parent = loader.load();
    		CompTypeBTableController controller = (CompTypeBTableController)loader.getController();
    		compTypeB = new ComponentTypeB("LOGICGATES", "LGReferece", "LGPartNumber", "LGPackage", "LGBin",
    				"LGTotal", "LGOrder", "LGDataSheet", "LGSupplier");
    		controller.setCompObjB(compTypeB);
    		controller.loadTypeBData("Logic Gates", compTypeB);
    		primaryStage = new Stage();
    		primaryStage.setScene(new Scene(parent));
    		primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
			ImageView.setStageIcon(primaryStage);
		} catch(Exception e) {
			dialogBox("Error occured Logic Gate", "Error: "+e.getMessage());
		}
    }
	 /**
     * Method displayMicrophoneTable will invoke the fxml to display a microphone informations.
     * @param event
     */
    @FXML
    private void displayMicrophoneTable(ActionEvent event)
    {
    	try
		{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Components/CompTypeATable.fxml"));
    		Parent parent = loader.load();
    		CompTypeAController controller = (CompTypeAController)loader.getController();
    		componentTypeA = new Component("MICROPHONE", "MICROReferece", "MICROTotal", "MICROBin", "MICROLabelColour", "MICROOrder");
    		controller.loadCompTypeAData("Microphone", componentTypeA);
    		controller.setCompObj(componentTypeA);
    		primaryStage = new Stage();
    		primaryStage.setScene(new Scene(parent));
    		primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
			ImageView.setStageIcon(primaryStage);
		} catch(Exception e) {
			dialogBox("Error occured on Microphone", "Error: "+e.getMessage());
		}
    }
	/**
     * Method displayOpAmpsTable will invoke the fxml for a table to be display
     * @param event
     */
    @FXML
    private void displayOpAmpsTable(ActionEvent event)
    {
    	try
		{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Components/CompTypeBTable.fxml"));
    		Parent parent = loader.load();
    		CompTypeBTableController controller = (CompTypeBTableController)loader.getController();
    		compTypeB = new ComponentTypeB("OPAMPS", "OPAReferece", "OPAPartNumber", "OPAPackage", "OPABin",
    				"OPATotal", "OPAOrder", "OPADataSheet", "OPASupplier");
    		controller.setCompObjB(compTypeB);
    		controller.loadTypeBData("Op-Amps", compTypeB);
    		primaryStage = new Stage();
    		primaryStage.setScene(new Scene(parent));
    		primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
			ImageView.setStageIcon(primaryStage);
		} catch(Exception e) {
			dialogBox("Error occured Op-Amps", "Error: "+e.getMessage());
		}
    }
	 /**
     * Method loadPowerHoleTable will load power hole
     * @param event
     */
    @FXML
    private void loadPowerHoleTable(ActionEvent event)
    {
    	try
		{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Components/CompTypeATable.fxml"));
    		Parent parent = loader.load();
    		CompTypeAController controller = (CompTypeAController)loader.getController();
    		componentTypeA = new Component("POWERHOLECONNECTOR", "PHReferece", "PHTotal", "PHBin", "PHLabelColour", "PHOrder");
    		controller.loadCompTypeAData("Power Hole Connectors", componentTypeA);
    		controller.setCompObj(componentTypeA);
    		primaryStage = new Stage();
    		primaryStage.setScene(new Scene(parent));
    		primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
			ImageView.setStageIcon(primaryStage);
		} catch(Exception e) {
			dialogBox("Error Ocurrer on Power Hole Component", "Error: "+e.getMessage());
		}
    }
	/**
     * Method loadTemperatureDeviceTable will load the temperature device information on the table
     * @param event
     */
     @FXML
    private void loadTemperatureDeviceTable(ActionEvent event)
    {
    	try
 		{
     		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Components/CompTypeBTable.fxml"));
     		Parent parent = loader.load();
     		CompTypeBTableController controller = (CompTypeBTableController)loader.getController();
     		compTypeB = new ComponentTypeB("TEMPERATUREDEVICE", "TEMPEReferece", "TEMPEPartNumber", "TEMPEPackage", "TEMPEBin",
    				"TEMPETotal", "TEMPEOrder", "TEMPEDataSheet", "TEMPESupplier");
    		controller.setCompObjB(compTypeB);
     		controller.loadTypeBData("Op-Amps", compTypeB);
     		primaryStage = new Stage();
     		primaryStage.setScene(new Scene(parent));
     		primaryStage.initModality(Modality.APPLICATION_MODAL);
 			primaryStage.show();
 			ImageView.setStageIcon(primaryStage);
 		} catch(Exception e) {
 			dialogBox("Error Ocurrer on Temperature Device Component", "Error: "+e.getMessage());
 		}
    }
	/**
     * Method loadTransistorsTable will load the tansistor information on the table.
     * @param event
     */
   @FXML
   private void loadTransistorsTable(ActionEvent event)
   {
   	try
		{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Components/CompTypeCTable.fxml"));
    		Parent parent = loader.load();
    		CompTypeCTableController controller = (CompTypeCTableController)loader.getController();
    		compTypeC = new ComponentTypeC("TRANSISTORS", "TRANSIReferece", "TRANSITotal", "TRANSIBin", "TRANSILabelColour", "TRANSIOrder", "TRANSIPartNumber",
    				"TRANSIPackage", "TRANSIDataSheet", "TRANSIRsComponents", "TRANSIMantech", "TRANSICommunica", "TRANSIJpElectronics", "TRANSIElectrocComp");
    		controller.loadCData("Transistors", compTypeC);
    		controller.setCompObjC(compTypeC);
    		primaryStage = new Stage();
    		primaryStage.setScene(new Scene(parent));
    		primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
			ImageView.setStageIcon(primaryStage);
		} catch(Exception e) {
			dialogBox("Error Ocurrer on Tansistor Component", "Error: "+e.getMessage());
		}
   }
	/**
     * Method loadZenerDiodesTable will load the Zener diodes information on the table.
     * @param event
     */
    @FXML
    private void loadZenerDiodesTable(ActionEvent event)
    {
    	try
 		{
     		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Components/CompTypeCTable.fxml"));
     		Parent parent = loader.load();
     		CompTypeCTableController controller = (CompTypeCTableController)loader.getController();
     		compTypeC = new ComponentTypeC("ZENERDIODES", "ZENEReferece", "ZENETotal", "ZENEBin", "ZENELabelColour", "ZENEOrder", "ZENEPartNumber",
     				"ZENEPackage", "ZENEDataSheet", "ZENERsComponents", "ZENEMantech", "ZENECommunica", "ZENEJpElectronics", "ZENEElectrocComp");
     		controller.loadCData("Zener Diodes", compTypeC);
     		controller.setCompObjC(compTypeC);
     		primaryStage = new Stage();
     		primaryStage.setScene(new Scene(parent));
     		primaryStage.initModality(Modality.APPLICATION_MODAL);
 			primaryStage.show();
 			ImageView.setStageIcon(primaryStage);
 		} catch(Exception e) {
 			dialogBox("Error Ocurrer on Zener Diodes Component", "Error: "+e.getMessage());
 		}
    }
	 /**
     * Method loadBreadBoardTable will load the bread board information on the table.
     * @param event
     */
    @FXML
    private void loadBreadBoardTable(ActionEvent event)
    {
    	try
		{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Equpments/EquipmentTable.fxml"));
    		Parent parent = loader.load();
    		EquipmentTableController controller = (EquipmentTableController)loader.getController();
    		equip = new Equipment("BREADBOARD", "BdName", "BdModel", "BdSerialNumber", "BdAssetNumber", "BdNotes", "BdTotal", "BdAvailable");
    		controller.setEquipObj(equip);
    		controller.loadEquipData("BreadBoards", equip);
    		primaryStage = new Stage();
    		primaryStage.setScene(new Scene(parent));
    		primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
			ImageView.setStageIcon(primaryStage);
		} catch(Exception e) {
			dialogBox("Error Ocurrer on Breadboard Equipment", "Error: "+e.getMessage());
		}
    }
	 /**
     * Method loadComputersTable will load the computer information on the table.
     * @param event
     */
    @FXML
    private void loadComputersTable(ActionEvent event)
    {
    	try
		{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Equpments/EquipmentTable.fxml"));
    		Parent parent = loader.load();
    		EquipmentTableController controller = (EquipmentTableController)loader.getController();
    		equip = new Equipment("COMPUTER", "CompName", "CompModel", "CompSerialNumber", "CompAssetNumber", "CompNotes", "CompTotal", "CompAvailable");
    		controller.setEquipObj(equip);
    		controller.loadEquipData("Computer", equip);
    		primaryStage = new Stage();
    		primaryStage.setScene(new Scene(parent));
    		primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
			ImageView.setStageIcon(primaryStage);
		} catch(Exception e) {
			dialogBox("Error Ocurrer on computer Equipment", "Error: "+e.getMessage());
		}
    }
	 /**
     * Method loadFunctionGeneratorTable will load the function generator information on the table.
     * @param event
     */
    @FXML
    private void loadFunctionGeneratorTable(ActionEvent event)
    {
    	try
		{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Equpments/EquipmentTable.fxml"));
    		Parent parent = loader.load();
    		EquipmentTableController controller = (EquipmentTableController)loader.getController();
    		equip = new Equipment("FUNCTIONGENERATOR", "FgName", "FgModel", "FgSerialNumber", "FgAssetNumber", "FgNotes", "FgTotal", "FgAvailable");
    		controller.setEquipObj(equip);
    		controller.loadEquipData("Function Generator", equip);
    		primaryStage = new Stage();
    		primaryStage.setScene(new Scene(parent));
    		primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
			ImageView.setStageIcon(primaryStage);
		} catch(Exception e) {
			dialogBox("Error Ocurrer on Function Generator Equipment", "Error: "+e.getMessage());
		}
    }

	/**
     * Method loadMultimetersTable will load the multimeter information on the table
     * @param event
     */
    @FXML
    private void loadMultimetersTable(ActionEvent event)
    {
    	try
		{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Equpments/EquipmentTable.fxml"));
    		Parent parent = loader.load();
    		EquipmentTableController controller = (EquipmentTableController)loader.getController();
    		equip = new Equipment("MULTIMETER", "MName", "MModel", "MSerialNumber", "MAssetNumber", "MNotes", "MdTotal", "MdAvailable");
    		controller.setEquipObj(equip);
    		controller.loadEquipData("Multimeter", equip);
    		primaryStage = new Stage();
    		primaryStage.setScene(new Scene(parent));
    		primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
			ImageView.setStageIcon(primaryStage);
		} catch(Exception e) {
			dialogBox("Error Ocurrer on Multimeter Equipment", "Error: "+e.getMessage());
		}
    }

	/**
     * Method loadOscilloscopeTable will load Oscilloscope equipment information on the
     * table.
     * @param event
     */
    @FXML
    private void loadOscilloscopeTable(ActionEvent event)
    {
    	try
		{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Equpments/EquipmentTable.fxml"));
    		Parent parent = loader.load();
    		EquipmentTableController controller = (EquipmentTableController)loader.getController();
    		equip = new Equipment("OSCILLOSCOPE", "OSCIName", "OSCIModel", "OSCISerialNumber", "OSCIAssetNumber", "OSCINotes", "OSCITotal", "OSCIAvailable");
    		controller.setEquipObj(equip);
    		controller.loadEquipData("Oscilloscope", equip);
    		primaryStage = new Stage();
    		primaryStage.setScene(new Scene(parent));
    		primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
			ImageView.setStageIcon(primaryStage);
		} catch(Exception e) {
			dialogBox("Error Ocurrer on Oscilloscope Equipment", "Error: "+e.getMessage());
		}
    }
	/**
     * Method loadPowerSuppliesTable will load power supply equipment on the table.
     * @param event
     */
    @FXML
    private void loadPowerSuppliesTable(ActionEvent event)
    {
    	try
		{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Equpments/EquipmentTable.fxml"));
    		Parent parent = loader.load();
    		EquipmentTableController controller = (EquipmentTableController)loader.getController();
    		equip = new Equipment("POWERSUPPLY", "PSName", "PSModel", "PSSerialNumber", "PSAssetNumber", "PSNotes", "PSTotal", "PSAvailable");
    		controller.setEquipObj(equip);
    		controller.loadEquipData("Power Supply", equip);
    		primaryStage = new Stage();
    		primaryStage.setScene(new Scene(parent));
    		primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
			ImageView.setStageIcon(primaryStage);
		} catch(Exception e) {
			dialogBox("Error Ocurrer on Power Supply Equipment", "Error: "+e.getMessage());
		}
    }
	/**
     * Method loadToolsTable will load tools equipment on the table equipment.
     * @param event
     */
    @FXML
    private void loadToolsTable(ActionEvent event)
    {
    	try
		{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Equpments/EquipmentTable.fxml"));
    		Parent parent = loader.load();
    		EquipmentTableController controller = (EquipmentTableController)loader.getController();
    		equip = new Equipment("TOOLS", "TName", "TModel", "TSerialNumber", "TAssetNumber", "TNotes", "TTotal", "TAvailable");
    		controller.setEquipObj(equip);
    		controller.loadEquipData("Tools", equip);
    		primaryStage = new Stage();
    		primaryStage.setScene(new Scene(parent));
    		primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
			ImageView.setStageIcon(primaryStage);
		} catch(Exception e) {
			dialogBox("Error Ocurrer on tools Equipment", "Error: "+e.getMessage());
		}
    }
	/**
     * Method addComponents will invoke the fxml add component to add on the system.
     * @param event
     */
    @FXML
    private void addComponents(ActionEvent event)
    {
    	try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Components/AddCompoments.fxml"));
			Parent parent = loader.load();
			Stage Stage = new Stage();
			Stage.setScene(new Scene(parent));
			Stage.setResizable(false);
			Stage.initModality(Modality.APPLICATION_MODAL);
			Stage.show();
			ImageView.setStageIcon(Stage);
		} catch(Exception e) {
			dialogBox("Error occured on Add Component", "Error: "+e.getMessage());
		}
    }
	/**
     * Method addEquipment will invoke fxml for add equipment on the system.
     * @param event
     */
    @FXML
    private void addEquipment(ActionEvent event)
    {
    	try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Equpments/AddEquipments.fxml"));
			Parent parent = loader.load();
			Stage Stage = new Stage();
			Stage.setScene(new Scene(parent));
			Stage.setResizable(false);
			Stage.initModality(Modality.APPLICATION_MODAL);
			Stage.show();
			ImageView.setStageIcon(Stage);
		} catch(Exception e) {
			dialogBox("Error occured on Add Equipment", "Error: "+e.getMessage());
		}
    }
	/**
     * Method handlerOnSetting will invoke the fxml for the settings.
     * @param event
     */
    @FXML
    private void handlerOnSetting(ActionEvent event)
    {
    	try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Settings/Settings.fxml"));
			Parent parent = loader.load();
			Stage Stage = new Stage();
			Stage.setScene(new Scene(parent));
			Stage.setResizable(false);
			Stage.initModality(Modality.APPLICATION_MODAL);
			Stage.show();
			ImageView.setStageIcon(Stage);
		} catch(Exception e) {
			dialogBox("Error occured on Settings", "Error: "+e.getMessage());
		}
    }
	/**
     * Method loadFxmlWindows will receive two parameter the fxml path and
     * title.
     * @param fxmlPath
     * @param title
     */
    private void loadFxmlWindows(String fxmlPath, String title)
    {
    	try
    	{
			Parent parent = FXMLLoader.load(getClass().getResource(fxmlPath));
			Stage stage = new Stage(StageStyle.DECORATED);
			stage.setTitle(title);
			stage.setScene(new Scene(parent));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
			ImageView.setStageIcon(stage);
		} catch (IOException e) {
			dialogBox("Error occured", "Error: "+e.getMessage());
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
}

