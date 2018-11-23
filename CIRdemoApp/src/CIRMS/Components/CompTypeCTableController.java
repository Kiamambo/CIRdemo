package CIRMS.Components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

import CIRMS.Database.DatabaseHandler;
import CIRMS.imageView.ImageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

public class CompTypeCTableController implements Initializable
{
	private ObservableList<CompCProperty> listOfTypeC = FXCollections.observableArrayList();
	private DatabaseHandler handler;
	@FXML private AnchorPane rootPane;
	@FXML private TableView<CompCProperty> tableView;
	@FXML private TableColumn<CompCProperty, String> referenceCol;
	@FXML private TableColumn<CompCProperty, String> partNumberCol;
	@FXML private TableColumn<CompCProperty, String> packageCol;
	@FXML private TableColumn<CompCProperty, String> binCol;
	@FXML private TableColumn<CompCProperty, Integer> totalCol;
	@FXML private TableColumn<CompCProperty, Boolean> orderCol;
	@FXML private TableColumn<CompCProperty, String> datasheetCol;
	@FXML private TableColumn<CompCProperty, String> labelColourCol;
	@FXML private TableColumn<CompCProperty, String> rsComponentCol;
	@FXML private TableColumn<CompCProperty, String> mantechCol;
	@FXML private TableColumn<CompCProperty, String> communicaCol;
	@FXML private TableColumn<CompCProperty, String> jpElectronicCol;
	@FXML private TableColumn<CompCProperty, String> electrocCompCol;
	@FXML private Label compName;
	private ComponentTypeC compObjC;
	/**
	 *  Event Listener on MenuItem.onAction
	 * @param event
	 */
	@FXML
	public void handlerEdit(ActionEvent event)
	{
		CompCProperty selectedForEdit = tableView.getSelectionModel().getSelectedItem();
		if (selectedForEdit == null)
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("No item selected");
			alert.setContentText("Please select an item for edit.");
			alert.showAndWait();
			return;
		}
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/CIRMS/Components/AddCompoments.fxml"));
			Parent parent = loader.load();
			AddComponentsController controller = (AddComponentsController)loader.getController();
			controller.inflateUIforEditTypeC(compObjC.getTableName(), selectedForEdit);
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
		CompCProperty selectedForDeletion = tableView.getSelectionModel().getSelectedItem();
		if (selectedForDeletion == null)
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("No item selected");
			alert.setContentText("Please select an item for delete.");
			alert.showAndWait();
			return;
		}

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Deleting " + selectedForDeletion.getReference());
		alert.setContentText("Are you sure want to delete the "+ selectedForDeletion.getReference());
		Optional<ButtonType> answer = alert.showAndWait();

		if (answer.get() == ButtonType.OK)
		{
			boolean result = DatabaseHandler.getInstance().deleteComponentTypeC(getCompObjC().getTableName(),
					getCompObjC().getReference(), getCompObjC().getBin(), selectedForDeletion);
			if (result)
			{
				Alert simpleAlert = new Alert(Alert.AlertType.INFORMATION);
				simpleAlert.setTitle(selectedForDeletion.getReference()+" deleted");
				simpleAlert.setContentText(selectedForDeletion.getReference()+" was deleted successfully");
				simpleAlert.showAndWait();

				listOfTypeC.remove(selectedForDeletion);
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
    private void handlerAddComponentsTypeC(ActionEvent event)
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
	public void loadCData(String title, ComponentTypeC compTypeC)
	{
		compName.setText(title);
		String qu = "SELECT * FROM " + compTypeC.getTableName();
		ResultSet result = handler.execQuery(qu);

		try
		{
			while (result.next())
			{
				listOfTypeC.add( new CompCProperty(result.getString(compTypeC.getReference()), result.getString(compTypeC.getPartNumber()),
						result.getString(compTypeC.getPaCkage()),result.getString(compTypeC.getBin()) ,result.getInt(compTypeC.getTotal()),result.getBoolean(compTypeC.getOrder()),
						result.getString(compTypeC.getDatasheet()), result.getString(compTypeC.getLabelColour()), result.getString(compTypeC.getRsComponents()), result.getString(compTypeC.getMantech()),
						result.getString(compTypeC.getCommunic()), result.getString(compTypeC.getJpElectronics()), result.getString(compTypeC.getElectronicComp())));
			}
			DatabaseHandler.setCompTypeC(compTypeC);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tableView.setItems(listOfTypeC);
	}
	private void initCol()
	{
		referenceCol.setCellValueFactory( new PropertyValueFactory<>("reference"));
		partNumberCol.setCellValueFactory( new PropertyValueFactory<>("partNumber"));
		packageCol.setCellValueFactory( new PropertyValueFactory<>("pacKage"));
		binCol.setCellValueFactory( new PropertyValueFactory<>("bin"));
		totalCol.setCellValueFactory( new PropertyValueFactory<>("total"));
		orderCol.setCellValueFactory( new PropertyValueFactory<>("order"));
		datasheetCol.setCellValueFactory( new PropertyValueFactory<>("datasheet"));
		labelColourCol.setCellValueFactory( new PropertyValueFactory<>("labelColour"));
		rsComponentCol.setCellValueFactory( new PropertyValueFactory<>("rsComponent"));
		mantechCol.setCellValueFactory( new PropertyValueFactory<>("mantech"));
		communicaCol.setCellValueFactory( new PropertyValueFactory<>("communica"));
		jpElectronicCol.setCellValueFactory( new PropertyValueFactory<>("jpElectonics"));
		electrocCompCol.setCellValueFactory( new PropertyValueFactory<>("electrocComp"));
	}
	public ComponentTypeC getCompObjC() {
		return compObjC;
	}
	public void setCompObjC(ComponentTypeC compObjC) {
		this.compObjC = compObjC;
	}
}
