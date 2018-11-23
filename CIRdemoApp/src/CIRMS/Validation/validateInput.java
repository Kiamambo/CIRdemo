package CIRMS.Validation;

import java.util.regex.Pattern;

public class validateInput
{
	public static boolean validateName(String name)
	{
		return name.matches("[A-Z]+[a-zA-Z]+|[A-Z]+\\s[a-zA-Z]*");
	}
	public static boolean validateSurname(String surname)
	{
		return surname.matches("([A-Z][a-zA-Z]*)+|[A-Z]+\\s[a-zA-Z]*");
	}
	public static boolean validateStudentNo(String studentNo)
	{
		if ((studentNo.startsWith("1") && studentNo.startsWith("2")) || (studentNo.length() < 9 && studentNo.length() > 9)||(studentNo.matches("\\d{9}")))
		{
			return true;
		}else
		{
			return false;
		}
	}
	public static boolean validateSupervisor(String supervisorName)
	{
		return supervisorName.matches("[A-Z]+[a-zA-Z]+|[A-Z]+\\s[a-zA-Z]*");
	}
	public static boolean validateEmailAddress(String emailAddress)
	{
		String emailAddressSt = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailAddressSt);
        return pattern.matcher(emailAddress).matches();
	}
	public static boolean validateCellphoneNo(String cellphone)
	{
		return cellphone.matches("\\d{10}");
	}
	public static boolean validateCourse(String courseName)
	{
		return courseName.matches("[A-Z]+[a-zA-Z]+|[A-Z]+\\s[a-zA-Z]*");
	}
}
