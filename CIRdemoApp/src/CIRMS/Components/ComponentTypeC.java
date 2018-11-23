package CIRMS.Components;

public class ComponentTypeC extends Component
{
	private String partNumber;
	private String paCkage;
	private String datasheet;
	private String rsComponents;
	private String mantech;
	private String communic;
	private String jpElectronics;
	private String electronicComp;


	public ComponentTypeC(String tableName, String reference, String total, String bin, String labelColour,
			String order, String partNumber, String paCkage, String datasheet, String rsComponents, String mantech,
			String communic, String jpElectronics, String electronicComp) {
		super(tableName, reference, total, bin, labelColour, order);
		this.partNumber = new String(partNumber);
		this.paCkage = new String(paCkage);
		this.datasheet = new String(datasheet);
		this.rsComponents = new String(rsComponents);
		this.mantech = new String(mantech);
		this.communic = new String(communic);
		this.jpElectronics = new String(jpElectronics);
		this.electronicComp = new String(electronicComp);
	}

	public String getPartNumber() {
		return partNumber;
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

	public String getDatasheet() {
		return datasheet;
	}

	public void setDatasheet(String datasheet) {
		this.datasheet = datasheet;
	}

	public String getRsComponents() {
		return rsComponents;
	}

	public void setRsComponents(String rsComponents) {
		this.rsComponents = rsComponents;
	}

	public String getMantech() {
		return mantech;
	}

	public void setMantech(String mantech) {
		this.mantech = mantech;
	}

	public String getCommunic() {
		return communic;
	}

	public void setCommunic(String communic) {
		this.communic = communic;
	}

	public String getJpElectronics() {
		return jpElectronics;
	}

	public void setJpElectronics(String jpElectronics) {
		this.jpElectronics = jpElectronics;
	}

	public String getElectronicComp() {
		return electronicComp;
	}

	public void setElectronicComp(String electronicComp) {
		this.electronicComp = electronicComp;
	}
}
