package CIRMS.Components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import CIRMS.Database.DatabaseHandler;
import CIRMS.imageView.ImageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

public class CompTypeBTableController implements Initializable
{
	private ObservableList<CompBProperty> listOfComp = FXCollections.observableArrayList();
	private DatabaseHandler handler;
	@FXML private AnchorPane rootPane;
	@FXML private TableView<CompBProperty> tableView;
	@FXML private TableColumn<CompBProperty, String> referenceCol;
	@FXML private TableColumn<CompBProperty, String> partNumberCol;
	@FXML private TableColumn<CompBProperty, String> packageCol;
	@FXML private TableColumn<CompBProperty, String> binCol;
	@FXML private TableColumn<CompBProperty, Integer> totalCol;
	@FXML private TableColumn<CompBProperty, Boolean> orderCol;
	@FXML private TableColumn<CompBProperty, String> datasheetCol;
	@FXML private TableColumn<CompBProperty, String> supplierCol;
	@FXML private Label compName;
	private ComponentTypeB compObjB;


	/**
	 *  Event Listener on MenuItem.onAction
	 * @param event
	 */
	@FXML
	public void handlerEdit(ActionEvent event)
	{	// this will get the selected item in table
		CompBProperty selectedForEdit = tableView.getSelectionModel().getSelectedItem();
		if (selectedForEdit == null)
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("No item selected");
			alert.setContentText("Please select an item for delete.");
			alert.showAndWait();
			return;
		}
		// this try and catch will invoke the add component fxml page
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Components/AddCompoments.fxml"));
			Parent parent = loader.load();
			AddComponentsController controller = (AddComponentsController)loader.getController();
			controller.inflateUIforEditTypeB(getCompObjB().getTableName(), selectedForEdit);
			Stage stage = new Stage();
			stage.setTitle("Edit " + selectedForEdit.getReference());
			stage.setScene(new Scene(parent));
			stage.show();
			ImageView.setStageIcon(stage);
			Stage stage1 = (Stage)rootPane.getScene().getWindow();
	    	stage1.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *  Event Listener on MenuItem.onAction
	 * @param event
	 */
	@FXML
	public void HandlerDelete(ActionEvent event)
	{
		CompBProperty selectedForDeletion = tableView.getSelectionModel().getSelectedItem();

		if (selectedForDeletion == null)
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("No item selected");
			alert.setContentText("Please select an item for delete.");
			alert.showAndWait();
			return;
		}

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Deleting " +getCompObjB().getReference());
		alert.setContentText("Are you sure want to delete the "+ selectedForDeletion.getReference());
		Optional<ButtonType> answerM = alert.showAndWait();

		if (answerM.get() == ButtonType.OK)
		{
			boolean resultM = DatabaseHandler.getInstance().deleteComponentTypeB(getCompObjB().getTableName(),
					getCompObjB().getReference(), getCompObjB().getBin(), selectedForDeletion);
			if (resultM)
			{
				Alert simpleAlert = new Alert(Alert.AlertType.INFORMATION);
				simpleAlert.setTitle("Student deleted");
				simpleAlert.setContentText(selectedForDeletion.getReference()+" was deleted successfully");
				simpleAlert.showAndWait();

				// remove the selected row
				listOfComp.remove(selectedForDeletion);
			}else
			{
				Alert simp1leAlert = new Alert(Alert.AlertType.INFORMATION);
				simp1leAlert.setTitle("Failed");
				simp1leAlert.setContentText(selectedForDeletion.getReference()+" Could not be deleted");
				simp1leAlert.showAndWait();
			}
		}
	}
	 @FXML
	private void handlerOnAddComponentTypeB(ActionEvent event)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Components/AddCompoments.fxml"));
			Parent parent = loader.load();
			Stage Stage = new Stage();
			Stage.setScene(new Scene(parent));
			Stage.setResizable(false);
			Stage.show();
			ImageView.setStageIcon(Stage);
			Stage stage1 = (Stage)rootPane.getScene().getWindow();
	    	stage1.close();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		handler = DatabaseHandler.getInstance();
		initCol();
	}
	public void loadTypeBData(String title, ComponentTypeB compTypeB)
	{
		compName.setText(title);
		String qu = "SELECT * FROM " + compTypeB.getTableName() ;
		ResultSet result = handler.execQuery(qu);

		try
		{
			while(result.next())
			{
				listOfComp.add(new CompBProperty(result.getString(compTypeB.getReference()), result.getString(compTypeB.getPaCkage()),
						result.getString(compTypeB.getPaCkage()), result.getString(compTypeB.getBin()), result.getInt(compTypeB.getTotal()),
						result.getBoolean(compTypeB.getBin()), result.getString(compTypeB.getDatasheet()), result.getString(compTypeB.getSupplier())));
			}
			DatabaseHandler.setCompTypeB(compObjB);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		tableView.setItems(listOfComp);
	}
	private void initCol()
	{
		referenceCol.setCellValueFactory( new PropertyValueFactory<>("reference"));
		partNumberCol.setCellValueFactory( new PropertyValueFactory<>("partNumber"));
		packageCol.setCellValueFactory( new PropertyValueFactory<>("packageComp"));
		binCol.setCellValueFactory( new PropertyValueFactory<>("bin"));
		totalCol.setCellValueFactory( new PropertyValueFactory<>("total"));
		orderCol.setCellValueFactory( new PropertyValueFactory<>("order"));
		datasheetCol.setCellValueFactory( new PropertyValueFactory<>("datasheet"));
		supplierCol.setCellValueFactory( new PropertyValueFactory<>("supplier"));
	}
	public ComponentTypeB getCompObjB() {
		return compObjB;
	}
	public void setCompObjB(ComponentTypeB compObjB) {
		this.compObjB = compObjB;
	}

}
