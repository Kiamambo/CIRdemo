package CIRMS.Equpments;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import CIRMS.Database.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddEquipmentsController implements Initializable
{	// List of equipments names for the combobox
	private ObservableList<String> comboList = FXCollections.observableArrayList("BreadBoards",
    		"Computers", "Function Generator", "Multimeters", "Oscilloscopes", "Power Supplies", "Tools");
	// Array of table names
    private String[] arrayTable = new String[]{ "BREADBOARD", "COMPUTER", "FUNCTIONGENERATOR", "MULTIMETER",
    							"OSCILLOSCOPE", "POWERSUPPLY", "TOOLS"};
    private List<String> listOfTables = Arrays.asList(arrayTable);
	@FXML private AnchorPane rootPane;
    @FXML private JFXTextField NameText;
    @FXML private JFXTextField modelTextField;
    @FXML private JFXTextField serialNumberTxt;
    @FXML private JFXTextField assetNumberText;
    @FXML private JFXTextField noteTxtField;
    @FXML private JFXTextField totalTextField;
    @FXML private Label txtLabel;
    @FXML private JFXComboBox<String> comboBox;
    private String tableName;
    private DatabaseHandler handler;
    private Boolean isInEditMode = Boolean.FALSE;

    /**
     * Method inflateEquipUI will set the selected equipment information to the gui
     * @param selectedForEdit
     */
    public void inflateEquipUI(EquipmentProperty selectedForEdit)
	{
    	comboBox.setPromptText(tableName);
    	NameText.setText(selectedForEdit.getName());
    	modelTextField.setText(selectedForEdit.getModel());
    	serialNumberTxt.setText(selectedForEdit.getSerialNumber());
    	assetNumberText.setText(selectedForEdit.getAssetNumber());
    	noteTxtField.setText(selectedForEdit.getNotes());
    	totalTextField.setText(String.valueOf(selectedForEdit.getTotal()));
    	comboBox.setDisable(true);
    	modelTextField.setEditable(false);
    	assetNumberText.setEditable(false);
    	isInEditMode = Boolean.TRUE;
	}
    /**
     * Method handlerEditOperation will get the editable equipment information to the database
     */
    public void handlerEditOperation()
    {
    	try
    	{
    		EquipmentProperty equipProperty = new EquipmentProperty(NameText.getText(), modelTextField.getText(),
        			serialNumberTxt.getText(), assetNumberText.getText(), noteTxtField.getText(), Integer.valueOf(totalTextField.getText()), true);
        	if (handler.updateEquipments(equipProperty))
        	{
        		Alert alert = new Alert(Alert.AlertType.INFORMATION);
        		alert.setHeaderText("Success");
        		alert.setContentText(equipProperty.getName()+" Updated");
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
        		alert.setContentText("Can't update " + equipProperty.getName());
        		alert.showAndWait();
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    /**
     * Method comboBoxOptions will set the selected equipment table on the table name.
     * @param event
     */
    @FXML
    private void comboBoxOptions(ActionEvent event)
	{
    	String selectedCombo = comboBox.getValue().toString();
    	if (selectedCombo.equalsIgnoreCase("BreadBoards")){
    		tableName = "BREADBOARD";
		}else
		if (selectedCombo.equalsIgnoreCase("Computers")) {
			tableName = "COMPUTER";
		}else
		if (selectedCombo.equalsIgnoreCase("Function Generator")) {
			tableName = "FUNCTIONGENERATOR";
		}else
		if (selectedCombo.equalsIgnoreCase("Multimeters")) {
			tableName = "MULTIMETER";
		}else
		if (selectedCombo.equalsIgnoreCase("Oscilloscopes")) {
			tableName = "OSCILLOSCOPE";
		}else
		if (selectedCombo.equalsIgnoreCase("Power Supplies")) {
			tableName = "POWERSUPPLY";
		}else
		if (selectedCombo.equalsIgnoreCase("Tools")) {
			tableName = "TOOLS";
		}
    }

    /**
     *Method AddEquipment will invoke the add equipment or editable information into the system.
     * @param event
     */
    @FXML
    private void AddEquipment(ActionEvent event)
    {
    	if (listOfTables.contains(tableName))
    	{
			insertEquipments();
		}else
		if (isInEditMode)
    	{
    		handlerEditOperation();
		}
    }
    /**
     * Method insertEquipments will perform the insert data into the database.
     */
    private void insertEquipments()
    {
    	String equipName = NameText.getText();
    	String equipModel = modelTextField.getText();
    	String equipSerialNum = serialNumberTxt.getText();
    	String equipAssetNum = assetNumberText.getText();
    	String equipNotes = noteTxtField.getText();
    	int equipTotalNum = Integer.parseInt(totalTextField.getText());
    	
    	// check if it is null.
    	if (equipName.isEmpty()||equipModel.isEmpty()||equipSerialNum.isEmpty()||equipAssetNum.isEmpty()
    			||equipNotes.isEmpty()||totalTextField.getText().isEmpty())
    	{
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Please Enter in all fields");
    		alert.showAndWait();
    		return;
		}
    	// check if it is on editable mode
    	if (isInEditMode)
    	{
    		handlerEditOperation();
    		return;
		}

		String query = "INSERT INTO " +tableName+ " VALUES("+
	    			"'" + equipAssetNum + "'," +
	    			"'" + equipName + "'," +
	    			"'" + equipModel + "'," +
	    			"'" + equipSerialNum + "'," +
	    			"'" + equipNotes + "'," +
	    			""  + equipTotalNum + "," +
	    			" " + "true" + "" +
	    			")";

    	if (handler.execAction(query))
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
    	}else
    	{
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Error Occured");
    		alert.showAndWait();
    	}
	}
	/**
     * Methd cancelEquipment will terminate the operation.
     * @param event
     */
    @FXML
    private void cancelEquipment(ActionEvent event)
    {
    	Stage stage = (Stage)rootPane.getScene().getWindow();
    	stage.close();
    }
    /**
     *
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		handler = DatabaseHandler.getInstance();
		comboBox.setItems(comboList);
		comboBox.setVisibleRowCount(4);
	}
}
