	package CIRMS.Components;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CompBProperty
{
	private SimpleStringProperty reference;
	private SimpleStringProperty partNumber;
	private SimpleStringProperty packageComp;
	private SimpleStringProperty bin;
	private SimpleIntegerProperty total;
	private SimpleBooleanProperty order;
	private SimpleStringProperty datasheet;
	private SimpleStringProperty supplier;


	public CompBProperty(String reference, String partNumber, String packageComp,
			String bin, Integer total, Boolean order, String datasheet, String supplier)
	{

		this.reference = new SimpleStringProperty(reference);
		this.partNumber = new SimpleStringProperty(partNumber);
		this.packageComp = new SimpleStringProperty(packageComp);
		this.bin = new SimpleStringProperty(bin);
		this.total = new SimpleIntegerProperty(total);
		this.order = new SimpleBooleanProperty(order);
		this.datasheet = new SimpleStringProperty(datasheet);
		this.supplier = new SimpleStringProperty(supplier);
	}


	public String getReference() {
		return reference.get();
	}

	public String getPartNumber() {
		return partNumber.get();
	}

	public String getPackageComp() {
		return packageComp.get();
	}

	public String getBin() {
		return bin.get();
	}

	public Integer getTotal() {
		return total.get();
	}

	public Boolean getOrder() {
		return order.get();
	}

	public String getDatasheet() {
		return datasheet.get();
	}

	public String getSupplier() {
		return supplier.get();
	}

}
