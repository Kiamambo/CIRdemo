package CIRMS.Equpments;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class EquipmentProperty
{
	// instance variables property
	private SimpleStringProperty name;
	private SimpleStringProperty model;
	private SimpleStringProperty serialNumber;
	private SimpleStringProperty assetNumber;
	private SimpleStringProperty notes;
	private SimpleIntegerProperty total;
	private SimpleBooleanProperty available;
	/**
	 * Creating constructor
	 * @param name
	 * @param model
	 * @param serialNumber
	 * @param assetNumber
	 * @param notes
	 * @param total
	 * @param available
	 */
	public EquipmentProperty(String name, String model, String serialNumber, String assetNumber, String notes,
			Integer total, Boolean available) {

		this.name = new SimpleStringProperty(name);
		this.model = new SimpleStringProperty(model);
		this.serialNumber = new SimpleStringProperty(serialNumber);
		this.assetNumber = new SimpleStringProperty(assetNumber);
		this.notes = new SimpleStringProperty(notes);
		this.total = new SimpleIntegerProperty(total);
		this.available = new SimpleBooleanProperty(available);
	}

	public String getName() {
		return name.get();
	}

	public String getModel() {
		return model.get();
	}

	public String getSerialNumber() {
		return serialNumber.get();
	}

	public String getAssetNumber() {
		return assetNumber.get();
	}

	public String getNotes() {
		return notes.get();
	}

	public Integer getTotal() {
		return total.get();
	}

	public Boolean getAvailable() {
		return available.get();
	}
}
