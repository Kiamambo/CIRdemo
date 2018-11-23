package CIRMS.Equpments;

public class Equipment
{
	private String tableName;
	private String equipName;
	private String model;
	private String serialNumber;
	private String assetNumber;
	private String notes;
	private String total;
	private String avail;


	public Equipment(String tableName, String equipName, String model, String serialNumber, String assetNumber, String notes,
			String total, String avail)
	{

		this.tableName = new String(tableName);
		this.equipName = new String(equipName);
		this.model = new String(model);
		this.serialNumber = new String(serialNumber);
		this.assetNumber = new String(assetNumber);
		this.notes = new String(notes);
		this.total = new String(total);
		this.avail = new String(avail);
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = (tableName);
	}

	public String getEquipName() {
		return this.equipName;
	}

	public void setEquipName(String equipName) {
		this.equipName = (equipName);
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = (model);
	}

	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = (serialNumber);
	}

	public String getAssetNumber() {
		return this.assetNumber;
	}

	public void setAssetNumber(String assetNumber) {
		this.assetNumber = (assetNumber);
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = (notes);
	}

	public String getTotal() {
		return this.total;
	}

	public void setTotal(String total) {
		this.total = (total);
	}

	public String getAvail() {
		return avail;
	}

	public void setAvail(String avail) {
		this.avail = avail;
	}

}
