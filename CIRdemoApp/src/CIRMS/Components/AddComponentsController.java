
package CIRMS.Components;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import CIRMS.Database.DatabaseHandler;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
/**
 * @author Visi Mansukinini
 *
 */

public class AddComponentsController implements Initializable
{
	// Creating observable list for all components name
	private ObservableList<String> listOfComp = FXCollections.observableArrayList("Banana Plugs", "BNC Connectors", "Croc Clips", "Data Socket", "Din Connectors",
			"Display", "DC Motors", "ETD Clips", "Fuses & Fuse Holders", "High Speed/ Faster recovery Diodes", "IGBT", "Insulator", "Integrated Circuit", "Leds",
			"Logical Gate", "Microphone", "Op-Amps", "Power Hole Connectors", "Rectifiers", "Temperature Device", "Tansistors", "Zener Diodes");
	// Instantiate an array of string to acquire table names with the same columns named by type A.
	private String[] arrayTableTypeA = new String[]{ "BANANAPLUGS", "BNC", "CROC_CLIPS", "DATASOCKETS", "DINCONNECTORS", "DISPLAY", "DC_MOTORS", "ETD_CLIPS",
										"FUSES_HOLDERS", "INSULATORS", "LEDS", "MICROPHONE", "POWERHOLECONNECTOR"};
	// Instantiate an array of string to acquire table names with the same columns named by type B.
	private String[] arrayTableTypeB = new String[]{"INTEGRATEDCIRCUIT", "LOGICGATES", "OPAMPS", "TEMPERATUREDEVICE"};
	// Instantiate an array of string to acquire table names with the same columns named by type C.
	private String[] arrayTableTypeC = new String[]{ "ZENERDIODES", "TRANSISTORS", "IGBTtable", "HIGHSPEEDDIODES"};
	private List<String> listOfTablesTypeA = Arrays.asList(arrayTableTypeA);				// instantiate list to acquire data from array named by type A.
	private List<String> listOfTablesTypeB = Arrays.asList(arrayTableTypeB);				// instantiate list to acquire data from array named by type A.
	private List<String> listOfTablesTypeC = Arrays.asList(arrayTableTypeC);				// instantiate list to acquire data from array named by type A.
	private ObservableList<String> listOfOrder = FXCollections.observableArrayList("true", "false");		// instantiate observable list of string.
	private ObservableList<String> listOfDatasheet = FXCollections.observableArrayList("Yes", "No");		// instantiate observable list of string .
	private String tableName;	// Instantiate variable table name
	private Boolean isInEditMode = Boolean.FALSE;	// Instantiate variable is in edit mode
	private DatabaseHandler handler;				// Instantiate database handler class
	@FXML private BorderPane rootAddCompPane;
	@FXML private JFXComboBox<String> comboBoxComponents;
    @FXML private JFXTextField referenceCompTxtField;
    @FXML private JFXTextField totaTextField;
    @FXML private JFXTextField binTextField;
    @FXML private JFXTextField partNumberTextField;
    @FXML private JFXTextField packageTextField;
    @FXML private JFXComboBox<String> comboBoxOrder;
    @FXML private JFXColorPicker colourPicker;
    @FXML private JFXComboBox<String> comboBoxDatasheet;
    @FXML private JFXTextField supplierTextField;
    @FXML private JFXTextField rsCompTextField;
    @FXML private JFXTextField mantechTxtField;
    @FXML private JFXTextField comunicTextField;
    @FXML private JFXTextField jpElectronicTextField;
    @FXML private JFXTextField electronicCompTextField;


    /**
     * Method inflateUIforEdit will inflate the user interface with data to be edit.
     * @param tableName
     * @param compProperty
     */
	public void inflateUIforEdit(String tableName, CompProperty compProperty)
	{
		comboBoxComponents.setPromptText(tableName);
		referenceCompTxtField.setText(compProperty.getReference());
		totaTextField.setText(String.valueOf(compProperty.getTotal()));
		comboBoxOrder.setValue(String.valueOf(compProperty.getOrder()));
		binTextField.setText(compProperty.getBin());
		colourPicker.setPromptText(compProperty.getLabelColour());
		referenceCompTxtField.setEditable(false);
		binTextField.setEditable(false);
		isInEditMode = Boolean.TRUE;
	}
	/**
	 * Method inflateUIforEditTypeB will inflate the user interface with data from  type B to be edit .
	 * @param tableName
	 * @param compBProperty
	 */
	public void inflateUIforEditTypeB(String tableName, CompBProperty compBProperty)
	{
		comboBoxComponents.setPromptText(tableName);
		referenceCompTxtField.setText(compBProperty.getReference());
		partNumberTextField.setText(compBProperty.getPartNumber());
		packageTextField.setText(compBProperty.getPackageComp());
		binTextField.setText(compBProperty.getBin());
		totaTextField.setText(String.valueOf(compBProperty.getTotal()));
		comboBoxOrder.setValue(String.valueOf(compBProperty.getOrder()));
		comboBoxDatasheet.setValue(compBProperty.getDatasheet());
		referenceCompTxtField.setEditable(false);
		supplierTextField.setText(compBProperty.getSupplier());
		binTextField.setEditable(false);
		isInEditMode = Boolean.TRUE;
	}
	/**
	 * Method inflateUIforEditTypeC will inflate the user interface with data from type C to be edit.
	 * @param tableName
	 * @param compCProperty
	 */
	public void inflateUIforEditTypeC(String tableName, CompCProperty compCProperty)
	{
		comboBoxComponents.setPromptText(tableName);
		referenceCompTxtField.setText(compCProperty.getReference());
		totaTextField.setText(String.valueOf(compCProperty.getTotal()));
		comboBoxOrder.setValue(String.valueOf(compCProperty.getOrder()));
		binTextField.setText(compCProperty.getBin());
		partNumberTextField.setText(compCProperty.getPartNumber());
		packageTextField.setText(compCProperty.getPacKage());
		comboBoxDatasheet.setValue(compCProperty.getDatasheet());
		rsCompTextField.setText(compCProperty.getRsComponent());
		mantechTxtField.setText(compCProperty.getMantech());
		comunicTextField.setText(compCProperty.getCommunica());
		jpElectronicTextField.setText(compCProperty.getJpElectonics());
		electronicCompTextField.setText(compCProperty.getElectrocComp());
		colourPicker.setPromptText(compCProperty.getLabelColour());
		referenceCompTxtField.setEditable(false);
		supplierTextField.setDisable(true);
		binTextField.setEditable(false);
		isInEditMode = Boolean.TRUE;
	}
	/**
	 * Method handlerOnEditCompTypeA will get the edited data type A back to the database.
	 */
	public void handlerOnEditCompTypeA()
	{
		CompProperty editCompProperty = new CompProperty(referenceCompTxtField.getText(), Integer.parseInt(totaTextField.getText()),
				Boolean.getBoolean(comboBoxOrder.getValue().toString()), binTextField.getText(), colourPicker.getValue().toString());
		try
		{	// passing the updated information to be updated on database
			if (handler.updateComponentTypeA(editCompProperty))
			{
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    		alert.setHeaderText("Success");
	    		alert.setContentText(editCompProperty.getReference()+" Updated");
	    		Optional<ButtonType> answer = alert.showAndWait();

	    		if (answer.get() == ButtonType.OK)
	    		{
	    			Stage stage = (Stage)rootAddCompPane.getScene().getWindow();
	    	    	stage.close();
				}
			}else
			{
				Alert alert = new Alert(Alert.AlertType.ERROR);
	    		alert.setHeaderText("Failed");
	    		alert.setContentText("Can't update " + editCompProperty.getReference());
	    		alert.showAndWait();
			}
		} catch (Exception e) {
			dialogBox("Error On Edit type A", "Error :" + e.getMessage());
		}
	}
	/**
	 * Method handlerOnEditOperationTypeB will get the edited data type B back to the database.
	 */
	private void handlerOnEditOperationTypeB()
	{
		CompBProperty editTypeCProperty = new CompBProperty(referenceCompTxtField.getText(), partNumberTextField.getText(), packageTextField.getText(),
				binTextField.getText(), Integer.parseInt(totaTextField.getText()), Boolean.getBoolean(comboBoxOrder.getValue().toString()),
				comboBoxDatasheet.getValue().toString(), supplierTextField.getText());

		if (handler.updateComponenTypeB(editTypeCProperty))
		{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
    		alert.setHeaderText("Success");
    		alert.setContentText(editTypeCProperty.getReference()+" Updated");
    		Optional<ButtonType> answer = alert.showAndWait();

    		if (answer.get() == ButtonType.OK)
    		{
    			Stage stage = (Stage)rootAddCompPane.getScene().getWindow();
    	    	stage.close();
			}
		}
	}
	/**
	 * Method handlerOnEditOperationTypeC will get the edited data type C back to the database.
	 */
	private void handlerOnEditOperationTypeC()
	{
		CompCProperty editTypeCPropeerty = new CompCProperty(referenceCompTxtField.getText(), partNumberTextField.getText(), packageTextField.getText(),
				binTextField.getText(), Integer.parseInt(totaTextField.getText()), Boolean.getBoolean(comboBoxOrder.getValue().toString()),
				comboBoxDatasheet.getValue().toString(), colourPicker.getValue().toString(), rsCompTextField.getText(), mantechTxtField.getText(),
				comunicTextField.getText(), jpElectronicTextField.getText(), electronicCompTextField.getText());

		if (handler.updateComponentTypeC(editTypeCPropeerty))
		{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
    		alert.setHeaderText("Success");
    		alert.setContentText(editTypeCPropeerty.getReference()+" Updated");
    		Optional<ButtonType> answer = alert.showAndWait();
    		if (answer.get() == ButtonType.OK)
    		{
    			Stage stage = (Stage)rootAddCompPane.getScene().getWindow();
    	    	stage.close();
			}
		}
	}
	/**
	 * Method comboBoxCompSelected will get the selected option in comboBox to be checked.
	 * @param event
	 */
	@FXML
    private void comboBoxCompSelected(ActionEvent event)
    {	// get the selected element
    	String selectedComp = comboBoxComponents.getValue().toString();

    	if (selectedComp.equalsIgnoreCase("Banana Plugs")){
    		tableName = "BANANAPLUGS";disableComponentTypeA();
    	}else
    	if (selectedComp.equalsIgnoreCase("BNC Connectors")){
    		tableName = "BNC";disableComponentTypeA();
    	}else
		if (selectedComp.equalsIgnoreCase("Croc Clips")){
    		tableName = "CROC_CLIPS";disableComponentTypeA();
    	}else
		if (selectedComp.equalsIgnoreCase("Data Socket")){
    		tableName = "DATASOCKETS";disableComponentTypeA();
    	}else
        if (selectedComp.equalsIgnoreCase("Din Connectors")){
        	tableName = "DINCONNECTORS";disableComponentTypeA();
        }else
        if (selectedComp.equalsIgnoreCase("Display")){
        	tableName = "DISPLAY";disableComponentTypeA();
        }else
        if (selectedComp.equalsIgnoreCase("DC Motors")){
           tableName = "DC_MOTORS";disableComponentTypeA();
        }else
        if (selectedComp.equalsIgnoreCase("ETD Clips")){
           tableName = "ETD_CLIPS";disableComponentTypeA();
        }else
        if (selectedComp.equalsIgnoreCase("Fuses & Fuse Holders")){
            tableName = "FUSES_HOLDERS";disableComponentTypeA();
        }else
        if (selectedComp.equalsIgnoreCase("High Speed/ Faster recovery Diodes")){
            tableName = "HIGHSPEEDDIODES";disableComponentC();
        }else
    	if (selectedComp.equalsIgnoreCase("IGBT")){
            tableName = "IGBTtable";disableComponentC();
        }else
        if (selectedComp.equalsIgnoreCase("Insulator")){
            tableName = "INSULATORS";disableComponentTypeA();
        }else
    	if (selectedComp.equalsIgnoreCase("Integrated Circuit")){
            tableName = "INTEGRATEDCIRCUIT";disableComponentTypeB();
        }else
        if (selectedComp.equalsIgnoreCase("Leds")){
            tableName = "LEDS";disableComponentTypeA();
        }else
        if (selectedComp.equalsIgnoreCase("Logical Gate")){
            tableName = "LOGICGATES";disableComponentTypeB();
        }else
        if (selectedComp.equalsIgnoreCase("Microphone")){
            tableName = "MICROPHONE";disableComponentTypeA();
        }else
        if (selectedComp.equalsIgnoreCase("Op-Amps")){
            tableName = "OPAMPS";disableComponentTypeB();
        }else
    	if (selectedComp.equalsIgnoreCase("Power Hole Connectors")){
            tableName = "POWERHOLECONNECTOR";disableComponentTypeA();
        }else
        if (selectedComp.equalsIgnoreCase("Temperature Device")){
            tableName = "TEMPERATUREDEVICE";disableComponentTypeB();
        }else
        if (selectedComp.equalsIgnoreCase("Tansistors")){
            tableName = "TRANSISTORS";disableComponentC();
        }else
        if (selectedComp.equalsIgnoreCase("Zener Diodes")){
            tableName = "ZENERDIODES";disableComponentC();}
    }

    /**
     *Method AddComponents will verify the selected option form comboBox and select the specify method
     *to insert in database.
     * @param event
     */
    @FXML
    private void AddComponents(ActionEvent event)
    {
    	// Select and Insert the specific type of component
    	if (listOfTablesTypeA.contains(tableName))
    	{
    		insertComponentTypeA();
    	}else
    	if (listOfTablesTypeB.contains(tableName))
    	{
    		insertCompTypeB();
    	}else
    	if (listOfTablesTypeC.contains(tableName))
    	{
    		insertCompTypeC();
    	}else
    	if (isInEditMode){
			if (listOfTablesTypeA.contains(comboBoxComponents.getPromptText())) {
				handlerOnEditCompTypeA();
			}else
			if (listOfTablesTypeB.contains(comboBoxComponents.getPromptText())){
				handlerOnEditOperationTypeB();
			}else
			if (listOfTablesTypeC.contains(comboBoxComponents.getPromptText())) {
				handlerOnEditOperationTypeC();
			}
    	}
    }
    /**
     *Method cancelEquipment will cancel the operation.
     * @param event
     */
    @FXML
    private void cancelEquipment(ActionEvent event)
    {
    	Stage stage = (Stage)rootAddCompPane.getScene().getWindow();
    	stage.close();
    }
    /**
     * Method initialize will initialize some variable component with data.
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		handler = DatabaseHandler.getInstance();
		comboBoxOrder.setItems(listOfOrder);
		comboBoxDatasheet.setItems(listOfDatasheet);
		comboBoxComponents.setItems(listOfComp);
		comboBoxComponents.setVisibleRowCount(6);
	}
	/**
	 * Method insertComponentTypeA will insert component type A to the database.
	 */
	private void insertComponentTypeA()
	{

		// check if the variables are empty
		if (referenceCompTxtField.getText().isEmpty()||totaTextField.getText().isEmpty()||binTextField.getText().isEmpty())
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Please Enter in all fields");
    		alert.showAndWait();
    		return;
		}
		// check if the Order is selected
		if (comboBoxOrder.getSelectionModel().isEmpty())
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Please select the Order");
    		alert.showAndWait();
    		return;
		}

		String referenceComp = referenceCompTxtField.getText();
		Integer totalComp = Integer.parseInt(totaTextField.getText());
		Boolean orderComp = Boolean.getBoolean(comboBoxOrder.getValue().toString());
		String binComp = binTextField.getText();
		String labelColour = colourPicker.getValue().toString();

		// check if it is in edit mode, if it is not return process.
		if (isInEditMode)
		{
			handlerOnEditCompTypeA();
			return;
		}

			String qu = "INSERT INTO " + tableName + " VALUES("+
    				"'" + referenceComp + "'," +
    				" " + totalComp + "," +
    				"'" + binComp + "'," +
    				"'" + labelColour + "'," +
    				"" +  orderComp + "" +
    				")";
		// Execute the command to database
		if (handler.execAction(qu))
		{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
    		alert.setHeaderText(null);
    		alert.setContentText("Saved");
    		Optional<ButtonType> answer = alert.showAndWait();
    		if (answer.get() == ButtonType.OK)
    		{
    			Stage stage = (Stage)rootAddCompPane.getScene().getWindow();
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
	 * Method insertCompTypeB will insert component type B to the database.
	 */
	private void insertCompTypeB()
	{
		String referenceComp = referenceCompTxtField.getText();
		String partNumberComp = partNumberTextField.getText();
		String packageComp = packageTextField.getText();
		String binComp = binTextField.getText();
		Integer totalComp = Integer.parseInt(totaTextField.getText());
		Boolean orderComp = Boolean.getBoolean(comboBoxOrder.getValue().toString());
		String dataSheet = comboBoxDatasheet.getValue().toString();
		String supplierComp = supplierTextField.getText();

		// check if the variables are empty
		if (referenceComp.isEmpty()||partNumberComp.isEmpty()||packageComp.isEmpty()||totaTextField.getText().isEmpty()||
			binComp.isEmpty()||comboBoxOrder.getValue().toString().isEmpty()||dataSheet.isEmpty()||supplierComp.isEmpty())
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Please Enter in all fields");
    		alert.showAndWait();
    		return;
		}
		// check if it is in edit mode, if it is not return process.
		if (isInEditMode)
		{
			handlerOnEditOperationTypeB();
			return;
		}
			String qu = "INSERT INTO " + tableName + " VALUES("+
    				"'" + referenceComp + "'," +
    				"'" + partNumberComp + "'," +
    				"'" + packageComp + "'," +
    				"'" + binComp + "'," +
    				""  + totalComp + "," +
    				""  + orderComp + "," +
    				"'" + dataSheet + "'," +
    				"'" + supplierComp + "'" +
    				")";
		// Execute the command to database
		if (handler.execAction(qu))
		{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
    		alert.setHeaderText(null);
    		alert.setContentText("Saved");
    		Optional<ButtonType> answer = alert.showAndWait();
    		if (answer.get() == ButtonType.OK)
    		{
    			Stage stage = (Stage)rootAddCompPane.getScene().getWindow();
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
	 * Method insertCompTypeC will insert component type C to the database.
	 */
	private void insertCompTypeC()
	{
		String referenceComp = referenceCompTxtField.getText();
		String partNumberComp = partNumberTextField.getText();
		String packageComp = packageTextField.getText();
		String binComp = binTextField.getText();
		Integer totalComp = Integer.parseInt(totaTextField.getText());
		Boolean orderComp = Boolean.getBoolean(comboBoxOrder.getValue().toString());
		String dataSheetComp = comboBoxDatasheet.getValue().toString();
		String labelColour = colourPicker.getValue().toString();
		String rsComponents = rsCompTextField.getText();
		String mantechComp = mantechTxtField.getText();
		String comminucaComp = comunicTextField.getText();
		String jpElectronicsComp = jpElectronicTextField.getText();
		String electrocComp = electronicCompTextField.getText();

		// check if the variables are empty
		if (referenceComp.isEmpty()||partNumberComp.isEmpty()||packageComp.isEmpty()||totaTextField.getText().isEmpty()||
			binComp.isEmpty()||comboBoxOrder.getValue().toString().isEmpty()||dataSheetComp.isEmpty()||labelColour.isEmpty()||
			rsComponents.isEmpty()||mantechComp.isEmpty()||comminucaComp.isEmpty()||jpElectronicsComp.isEmpty()||electrocComp.isEmpty())
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setContentText("Please Enter in all fields");
    		alert.showAndWait();
    		return;
		}
		// check if it is in edit mode, if it is not return process.
		if (isInEditMode)
		{
			handlerOnEditOperationTypeC();
			return;
		}

			String qu = "INSERT INTO " + tableName + " VALUES("+
    				"'" + referenceComp + "'," +
    				"'" + partNumberComp + "'," +
    				"'" + packageComp + "'," +
    				"'" + binComp + "'," +
    				""  + totalComp + "," +
    				""  + orderComp + "," +
    				"'" + dataSheetComp + "'," +
    				"'" + labelColour + "'," +
    				"'" + rsComponents + "'," +
    				"'" + mantechComp + "'," +
    				"'" + comminucaComp + "'," +
    				"'" + jpElectronicsComp + "'," +
    				"'" + electrocComp + "'" +
    				")";
		// Execute the command to database
		if (handler.execAction(qu))
		{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
    		alert.setHeaderText(null);
    		alert.setContentText("Saved");
    		Optional<ButtonType> answer = alert.showAndWait();
    		if (answer.get() == ButtonType.OK)
    		{
    			Stage stage = (Stage)rootAddCompPane.getScene().getWindow();
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
	 * Method disableComponentTypeA will disable few element on the user interface.
	 */
	private void disableComponentTypeA()
	{
		comboBoxDatasheet.setDisable(true);
		electronicCompTextField.setDisable(true);;
		jpElectronicTextField.setDisable(true);
		comunicTextField.setDisable(true);
		mantechTxtField.setDisable(true);
		rsCompTextField.setDisable(true);
		supplierTextField.setDisable(true);
		packageTextField.setDisable(true);
		partNumberTextField.setDisable(true);
		colourPicker.setDisable(false);
	}
	/**
	 * Method componentTypeB will disable few element on the user interface.
	 */
	private void disableComponentTypeB()
	{
		colourPicker.setDisable(true);
		electronicCompTextField.setDisable(true);;
		jpElectronicTextField.setDisable(true);
		comunicTextField.setDisable(true);
		mantechTxtField.setDisable(true);
		rsCompTextField.setDisable(true);
		comboBoxDatasheet.setDisable(false);
		supplierTextField.setDisable(false);
		packageTextField.setDisable(false);
		partNumberTextField.setDisable(false);
	}
	private void disableComponentC()
	{
		comboBoxDatasheet.setDisable(false);
		electronicCompTextField.setDisable(false);;
		jpElectronicTextField.setDisable(false);
		comunicTextField.setDisable(false);
		mantechTxtField.setDisable(false);
		rsCompTextField.setDisable(false);
		supplierTextField.setDisable(false);
		packageTextField.setDisable(false);
		partNumberTextField.setDisable(false);
		colourPicker.setDisable(false);
		supplierTextField.setDisable(true);
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
