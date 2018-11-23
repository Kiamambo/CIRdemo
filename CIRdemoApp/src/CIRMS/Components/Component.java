package CIRMS.Components;

public class Component
{
	private String tableName;
	private String reference;
	private String total;
	private String bin;
	private String labelColour;
	private String order;

	public Component(String tableName, String reference, String total, String bin, String labelColour, String order)
	{
		this.tableName = new String(tableName);
		this.reference = new String(reference);
		this.total = new String(total);
		this.bin = new String(bin);
		this.labelColour = new String(labelColour);
		this.order = new String(order);
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getBin() {
		return bin;
	}

	public void setBin(String bin) {
		this.bin = bin;
	}

	public String getLabelColour() {
		return labelColour;
	}

	public void setLabelColour(String labelColour) {
		this.labelColour = labelColour;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
