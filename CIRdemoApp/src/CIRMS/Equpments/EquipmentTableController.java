package CIRMS.Equpments;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import CIRMS.Database.DatabaseHandler;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EquipmentTableController implements Initializable
{
	private ObservableList<EquipmentProperty> listOfEquip = FXCollections.observableArrayList();

	@FXML private AnchorPane rootPane;
	@FXML private TableView<EquipmentProperty> tableView;
	@FXML private TableColumn<EquipmentProperty, String> nameColumn;
	@FXML private TableColumn<EquipmentProperty, String> modelColumn;
	@FXML private TableColumn<EquipmentProperty, String> serialNumCol;
	@FXML private TableColumn<EquipmentProperty, String> assetNumColumn;
	@FXML private TableColumn<EquipmentProperty, String> notesColumn;
	@FXML private TableColumn<EquipmentProperty, Integer> totalColumn;
	@FXML private TableColumn<EquipmentProperty, Boolean> availColumn;
	@FXML private Label labelName;
	private DatabaseHandler handler;
	private Equipment equipObj;


	/**
	 *Method initialize will invoke the database class and the columns value to
	 *populate the table.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		handler = DatabaseHandler.getInstance();
		initCol();
	}
	/**
	 * Method addEquipments will invoke the add equipment frame to ask for
	 * the user to enter equipment information.
	 * @param event
	 */
	@FXML
	private void addEquipments(ActionEvent event)
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
			Stage stage1 = (Stage)rootPane.getScene().getWindow();
	    	stage1.close();
		} catch(Exception e) {
			dialogBox("Error occured on Add Equipment", "Error: "+e.getMessage());
		}

	}
	/**
	 * Method handlerOnDelete will get the selected row on the to later perform
	 * the deletion process.
	 * @param event
	 */
	@FXML
	private void handlerOnDelete(ActionEvent event)
	{
		// get the selected item in the table
		EquipmentProperty selectedForDeletion = tableView.getSelectionModel().getSelectedItem();

		if (selectedForDeletion == null)
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("No item selected");
			alert.setContentText("Please select an item for delete.");
			alert.showAndWait();
			return;
		}

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Deleting item");
		alert.setContentText("Are you sure want to delete "+ selectedForDeletion.getName());
		Optional<ButtonType> answer = alert.showAndWait();

		if (answer.get() == ButtonType.OK)
		{
			boolean result = DatabaseHandler.getInstance().deleteEquipment(getEquipObj().getTableName(), getEquipObj().getModel(), getEquipObj().getAssetNumber(), selectedForDeletion);
			if (result)
			{
				Alert simpleAlert = new Alert(Alert.AlertType.INFORMATION);
				simpleAlert.setTitle(selectedForDeletion.getName()+" deleted");
				simpleAlert.setContentText(selectedForDeletion.getName()+" was deleted successfully");
				simpleAlert.showAndWait();
				listOfEquip.remove(selectedForDeletion);
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
	 * Method handlerOnEdit will get the selected item on the table and then will proceed
	 * with the edition process
	 * @param event
	 */
	@FXML
	private void handlerOnEdit(ActionEvent event)
	{
		EquipmentProperty selectedForEdit = tableView.getSelectionModel().getSelectedItem();

		if (selectedForEdit == null)
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("No item selected");
			alert.setContentText("Please select an item for delete.");
			alert.showAndWait();
			return;
		}
		try
		{
			Stage stage1 = (Stage)rootPane.getScene().getWindow();
	    	stage1.close();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Equpments/AddEquipments.fxml"));
			Parent parent = loader.load();
			AddEquipmentsController controller = (AddEquipmentsController)loader.getController();
			controller.inflateEquipUI(selectedForEdit);
			Stage stage = new Stage();
			stage.setTitle("Edit Equipments");
			stage.setScene(new Scene(parent));
			stage.show();
			ImageView.setStageIcon(stage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadEquipData(String title, Equipment equip)
	{
		listOfEquip.clear();
		labelName.setText(title);
		listOfEquip.clear();
		String query = "SELECT * FROM " + equip.getTableName();
		ResultSet result = handler.execQuery(query);

		try {
			while(result.next())
			{
				listOfEquip.add(new EquipmentProperty(result.getString(equip.getEquipName()), result.getString(equip.getModel()),
						result.getString(equip.getSerialNumber()), result.getString(equip.getAssetNumber()),
						result.getString(equip.getNotes()), result.getInt(equip.getTotal()), result.getBoolean(equip.getAvail())));
			}
			DatabaseHandler.setEquip(equip);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		tableView.setItems(listOfEquip);
	}

	private void initCol()
	{
		nameColumn.setCellValueFactory( new PropertyValueFactory<>("name"));
		modelColumn.setCellValueFactory( new PropertyValueFactory<>("model"));
		serialNumCol.setCellValueFactory( new PropertyValueFactory<>("serialNumber"));
		assetNumColumn.setCellValueFactory( new PropertyValueFactory<>("assetNumber"));
		notesColumn.setCellValueFactory( new PropertyValueFactory<>("notes"));
		totalColumn.setCellValueFactory( new PropertyValueFactory<>("total"));
		availColumn.setCellValueFactory( new PropertyValueFactory<>("available"));
	}

	public Equipment getEquipObj() {
		return equipObj;
	}
	public void setEquipObj(Equipment equipObj) {
		this.equipObj = equipObj;
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
