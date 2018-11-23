package CIRMS.Components;

public class ComponentTypeB
{
	private String tableName;
	private String reference;
	private String partNumber;
	private String paCkage;
	private String bin;
	private String total;
	private String ordar;
	private String datasheet;
	private String supplier;


	public ComponentTypeB(String tableName, String reference, String partNumber, String paCkage, String bin, String total, String ordar,
			String datasheet, String supplier) {

		this.tableName = new String(tableName);
		this.reference = new String(reference);
		this.partNumber = new String(partNumber);
		this.paCkage = new String(paCkage);
		this.bin = new String(bin);
		this.total = new String(total);
		this.ordar = new String(ordar);
		this.datasheet = new String(datasheet);
		this.supplier = new String(supplier);
	}


	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getPaCkage() {
		return paCkage;
	}

	public void setPaCkage(String paCkage) {
		this.paCkage = paCkage;
	}

	public String getBin() {
		return bin;
	}

	public void setBin(String bin) {
		this.bin = bin;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getOrdar() {
		return ordar;
	}

	public void setOrdar(String ordar) {
		this.ordar = ordar;
	}

	public String getDatasheet() {
		return datasheet;
	}

	public void setDatasheet(String datasheet) {
		this.datasheet = datasheet;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
}
