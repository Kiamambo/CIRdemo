/**
 *
 */
package CIRMS.Components;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Visi Mansukinini
 *
 */
public class CompProperty
{
	private SimpleStringProperty reference;
	private SimpleIntegerProperty total;
	private SimpleBooleanProperty order;
	private SimpleStringProperty bin;
	private SimpleStringProperty labelColour;

	public CompProperty(String reference, Integer total, boolean order, String bin, String labelColour)
	{
		this.reference = new SimpleStringProperty(reference);
		this.total = new SimpleIntegerProperty(total);
		this.order = new SimpleBooleanProperty(order);
		this.bin = new SimpleStringProperty(bin);
		this.labelColour = new SimpleStringProperty(labelColour);
	}

	public String getReference() {
		return reference.get();
	}

	public Integer getTotal() {
		return total.get();
	}

	public boolean getOrder() {
		return order.get();
	}

	public String getBin() {
		return bin.get();
	}

	public String getLabelColour() {
		return labelColour.get();
	}

}
