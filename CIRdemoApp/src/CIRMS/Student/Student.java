package CIRMS.Student;

public class Student
{
	private String tableName;
	private String studNumber;
	private String studName;
	private String studSurname;
	private String studSupervisor;
	private String studEmail;
	private String studCellphoneNo;
	private String studStation;
	private String studCourse;

	/**
	 * Creating constructor for student object
	 * @param tableName
	 * @param studNumber
	 * @param studName
	 * @param studSurname
	 * @param studSupervisor
	 * @param studEmail
	 * @param studCellphoneNo
	 * @param studStation
	 * @param studCourse
	 */
	public Student(String tableName, String studNumber, String studName, String studSurname, String studSupervisor,
			String studEmail, String studCellphoneNo, String studStation, String studCourse) {
		super();
		this.tableName = new String(tableName);
		this.studNumber = new String(studNumber);
		this.studName = new String(studName);
		this.studSurname = new String(studSurname);
		this.studSupervisor = new String(studSupervisor);
		this.studEmail = new String(studEmail);
		this.studCellphoneNo = new String(studCellphoneNo);
		this.studStation = new String(studStation);
		this.studCourse = new String(studCourse);
	}


	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getStudNumber() {
		return studNumber;
	}

	public void setStudNumber(String studNumber) {
		this.studNumber = studNumber;
	}

	public String getStudName() {
		return studName;
	}

	public void setStudName(String studName) {
		this.studName = studName;
	}

	public String getStudSurname() {
		return studSurname;
	}

	public void setStudSurname(String studSurname) {
		this.studSurname = studSurname;
	}

	public String getStudSupervisor() {
		return studSupervisor;
	}

	public void setStudSupervisor(String studSupervisor) {
		this.studSupervisor = studSupervisor;
	}

	public String getStudEmail() {
		return studEmail;
	}

	public void setStudEmail(String studEmail) {
		this.studEmail = studEmail;
	}


	public String getStudCellphoneNo() {
		return studCellphoneNo;
	}

	public void setStudCellphoneNo(String studCellphoneNo) {
		this.studCellphoneNo = studCellphoneNo;
	}

	public String getStudStation() {
		return studStation;
	}

	public void setStudStation(String studStation) {
		this.studStation = studStation;
	}

	public String getStudCourse() {
		return studCourse;
	}

	public void setStudCourse(String studCourse) {
		this.studCourse = studCourse;
	}
}
