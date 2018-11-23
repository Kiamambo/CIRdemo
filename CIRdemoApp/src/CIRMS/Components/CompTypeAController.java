package CIRMS.Components;

import java.net.URL;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

import CIRMS.Database.DatabaseHandler;
import CIRMS.imageView.ImageView;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CompTypeAController implements Initializable
{
	private ObservableList<CompProperty> listOfComp = FXCollections.observableArrayList();
	private DatabaseHandler handler;
	@FXML private AnchorPane rootlCompPane;
	@FXML private Label titleLabel;
	@FXML private TableView<CompProperty> tableView;
	@FXML private TableColumn<CompProperty, String> referenceCompCol;
	@FXML private TableColumn<CompProperty, Integer> totalCol;
	@FXML private TableColumn<CompProperty, Boolean> orderCol;
	@FXML private TableColumn<CompProperty, String> binCol;
	@FXML private TableColumn<CompProperty, String> labelColourCol;
	private Component compObj;


	/**
	 *  Event Listener on MenuItem.onAction
	 * @param event
	 */
	@FXML
	public void handlerEdit(ActionEvent event)
	{
		CompProperty selectedForEdit = tableView.getSelectionModel().getSelectedItem();
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
			controller.inflateUIforEdit(compObj.getTableName(), selectedForEdit);
			Stage stage = new Stage();
			stage.setTitle("Edit " + selectedForEdit.getReference());
			stage.setScene(new Scene(parent));
			stage.show();
			ImageView.setStageIcon(stage);
			Stage stage1 = (Stage)rootlCompPane.getScene().getWindow();
			stage1.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *Method handlerOnAddComponents will invoke the add component fxml
	 * @param event
	 */
    @FXML
    private void handlerOnAddComponents(ActionEvent event)
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
			Stage stage = (Stage)rootlCompPane.getScene().getWindow();
			stage.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
	/**
	 *  Method HandlerDelete Event Listener on MenuItem onAction for deletion.
	 * @param event
	 */
	@FXML
	public void HandlerDelete(ActionEvent event)
	{
		// get the selected item on the table
		CompProperty selectedForDeletion = tableView.getSelectionModel().getSelectedItem();
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
		alert.setContentText("Are you sure want to delete the item "+ selectedForDeletion.getReference());
		Optional<ButtonType> answer = alert.showAndWait();

		if (answer.get() == ButtonType.OK)
		{
			boolean result = DatabaseHandler.getInstance().deleteComponent(getCompObj().getTableName(),  getCompObj().getReference(), getCompObj().getBin(), selectedForDeletion);
			if (result)
			{
				Alert simpleAlert = new Alert(Alert.AlertType.INFORMATION);
				simpleAlert.setTitle(selectedForDeletion.getReference()+" deleted");
				simpleAlert.setContentText(selectedForDeletion.getReference()+" was deleted successfully");
				simpleAlert.showAndWait();

				listOfComp.remove(selectedForDeletion); // remove the deleted item from the list
			}else
			{
				Alert simp1leAlert = new Alert(Alert.AlertType.INFORMATION);
				simp1leAlert.setTitle("Failed");
				simp1leAlert.setContentText(selectedForDeletion.getReference()+" Could not be deleted");
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
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		handler = DatabaseHandler.getInstance();
		initCol();
	}
	/**
	 * method loadCompTypeAData will load the information on the table
	 * @param title
	 * @param compnentA
	 */
	public void loadCompTypeAData(String title, Component compnentA)
	{
		titleLabel.setText(title);
		String qu = "SELECT * FROM " + compnentA.getTableName();
		ResultSet result = handler.execQuery(qu);
		try
		{
			while (result.next())
			{
				listOfComp.add( new CompProperty(result.getString(compnentA.getReference()), result.getInt(compnentA.getTotal()),
						result.getBoolean(compnentA.getOrder()), result.getString(compnentA.getBin()), result.getString(compnentA.getLabelColour())));
			}
			DatabaseHandler.setComponent(compnentA);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tableView.setItems(listOfComp);
	}

	private void initCol()
	{
		referenceCompCol.setCellValueFactory( new PropertyValueFactory<>("reference"));
		totalCol.setCellValueFactory( new PropertyValueFactory<>("total"));
		orderCol.setCellValueFactory( new PropertyValueFactory<>("order"));
		binCol.setCellValueFactory( new PropertyValueFactory<>("bin"));
		labelColourCol.setCellValueFactory( new PropertyValueFactory<>("labelColour"));
	}

	public Component getCompObj() {
		return compObj;
	}
	public void setCompObj(Component compObj) {
		this.compObj = compObj;
	}

}
