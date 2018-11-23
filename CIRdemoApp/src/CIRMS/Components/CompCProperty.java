package CIRMS.Components;

import javafx.beans.property.SimpleStringProperty;

public class CompCProperty extends CompProperty
{
	private SimpleStringProperty partNumber;
	private SimpleStringProperty pacKage;
	private SimpleStringProperty datasheet;
	private SimpleStringProperty rsComponent;
	private SimpleStringProperty mantech;
	private SimpleStringProperty communica;
	private SimpleStringProperty jpElectonics;
	private SimpleStringProperty electrocComp;


	public CompCProperty(String reference,  String partNumber, String pacKage, String bin, Integer total, boolean order,
			String datasheet, String labelColour, String rsComponent, String mantech, String communica,  String jpElectonics,  String electrocComp)
	{
		super(reference, total, order, bin, labelColour);
		this.partNumber = new SimpleStringProperty(partNumber);
		this.pacKage = new SimpleStringProperty(pacKage);
		this.datasheet = new SimpleStringProperty(datasheet);
		this.rsComponent = new SimpleStringProperty(rsComponent);
		this.mantech = new SimpleStringProperty(mantech);
		this.communica = new SimpleStringProperty(communica);
		this.jpElectonics = new SimpleStringProperty(jpElectonics);
		this.electrocComp = new SimpleStringProperty(electrocComp);
	}

	public String getPartNumber() {
		return partNumber.get();
	}

	public String getPacKage() {
		return pacKage.get();
	}

	public String getDatasheet() {
		return datasheet.get();
	}

	public String getRsComponent() {
		return rsComponent.get();
	}

	public String getMantech() {
		return mantech.get();
	}

	public String getCommunica() {
		return communica.get();
	}

	public String getJpElectonics() {
		return jpElectonics.get();
	}

	public String getElectrocComp() {
		return electrocComp.get();
	}
}
