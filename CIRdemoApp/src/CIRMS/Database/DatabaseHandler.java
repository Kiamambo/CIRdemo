package CIRMS.Database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import CIRMS.Components.CompBProperty;
import CIRMS.Components.CompCProperty;
import CIRMS.Components.CompProperty;
import CIRMS.Components.Component;
import CIRMS.Components.ComponentTypeB;
import CIRMS.Components.ComponentTypeC;
import CIRMS.Equpments.Equipment;
import CIRMS.Equpments.EquipmentProperty;
import CIRMS.Student.StudentTableController.StudentProperty;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class DatabaseHandler
{
	private static final String DB_URL = "jdbc:derby:DBCIRMS;create=true"; // creating the URL to the database
	private static Connection conn = null;
	private static Statement stmt = null;
	private static DatabaseHandler handler = null;
	private static Equipment equip;
	private static Component component;
	private static ComponentTypeB compTypeB;
	private static ComponentTypeC compTypeC;

	/**
	 * Create Constructor
	 */
	private DatabaseHandler()
	{
		createConnection();

		// Student
		createBtechStudentTable();
		createMasterStudentTable();
		createSettingTable();

		// Equipment
		createBreadboardTable();
		createComputerTable();
		createFunctionGeneratorTable();
		createMultimeterTable();
		createOscilloscopeTable();
		createPowerSuppliersTable();
		createToolsTable();
		createIssueEquipmentsTable();

		// Components
		createBananaPlugsTable();
		createDataSocketsTable();
		createDinConnectorsTable();
		createlLedsTable();
		createDisplayTable();
		createCrocClipsTable();
		createETDClipsTable();
		createBNCTable();
		createInsulatorsTable();
		createFusesHoldersTable();
		createLogicGatesTable();
		createMicrophoneTable();
		createICTable();
		createIGBTTable();
		createHighSpeedDiodesTable();
		createDCMotorsTable();
		createPowerHoleConnectorsTable();
		createOpAmpsTable();
		createTemperatureDeviceTable();
		createTransistorsTable();
		createZenerDiodesTable();
	}
	/**
	 *
	 * @return
	 */
	public static DatabaseHandler getInstance()
	{
		if (handler == null)
		{
			handler = new DatabaseHandler();
		}
		return handler;
	}

	/**
	 * Method CreateConnnection will log the drivers and create the database
	 * connection.
	 */
	public void createConnection()
	{
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			conn = DriverManager.getConnection(DB_URL);
		} catch (Exception e) {
		dialogBox("Error Occured", "Error: " +e.getCause()+" Please close the program and open again");
		}
	}

	public static Equipment getEquip() {
		return equip;
	}
	public static void setEquip(Equipment equip) {
		DatabaseHandler.equip = equip;
	}
	public static Component getComponent() {
		return component;
	}
	public static void setComponent(Component component) {
		DatabaseHandler.component = component;
	}

	public static ComponentTypeB getCompTypeB() {
		return compTypeB;
	}
	public static void setCompTypeB(ComponentTypeB compTypeB) {
		DatabaseHandler.compTypeB = compTypeB;
	}
	public static ComponentTypeC getCompTypeC() {
		return compTypeC;
	}
	/**
	 * method setCompTypeC will set the component type c.
	 * @param compTypeC
	 */
	public static void setCompTypeC(ComponentTypeC compTypeC) {
		DatabaseHandler.compTypeC = compTypeC;
	}
	/**
	 * Method createSettingTable will deal with settings button to create user and password
	 */
	public void createSettingTable()
	{
		String TABLE_NAME = "SETTING";
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			if (tables.next())
			{
				//System.out.println("Table " + TABLE_NAME +" already exits. Ready for go!");
			}else
			{
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ " Username varchar(50), \n"
						+ " Password varchar(30), \n"
						+ "	primary key(Username)"
						+ "  )");
			}
		} catch (SQLException e) {
		dialogBox("Error Occured", "Error: " +e.getCause());
		}finally{
		}
	}
	/**
	 * Method createZenerDiodesTable will create the zener diodes table.
	 */
	public void createZenerDiodesTable()
	{
		String TABLE_NAME = "ZENERDIODES";
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			if (tables.next())
			{
				//System.out.println("Table " + TABLE_NAME +" already exits. Ready for go!");
			}else
			{
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ " ZENEReferece varchar(50), \n"
						+ " ZENEPartNumber varchar(30), \n"
						+ " ZENEPackage varchar(30), \n"
						+ " ZENEBin varchar(30), \n "
						+ " ZENETotal integer, \n"
						+ " ZENEOrder boolean default false, \n"
						+ " ZENEDataSheet varchar(15), \n"
						+ " ZENELabelColour varchar(20), \n"
						+ " ZENERsComponents varchar(30), \n"
						+ " ZENEMantech varchar(30), \n"
						+ " ZENECommunica varchar(30), \n"
						+ " ZENEJpElectronics varchar(30), \n"
						+ " ZENEElectrocComp varchar(30), \n"
						+ "	primary key(ZENEReferece, ZENEBin)"
						+ "  )");
			}
		} catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getCause());
		}finally{
		}
	}
	/**
	 * Method will create the transistor table
	 */
	public void createTransistorsTable()
	{
		String TABLE_NAME = "TRANSISTORS";
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			if (tables.next())
			{
				//System.out.println("Table " + TABLE_NAME +" already exits. Ready for go!");
			}else
			{
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ " TRANSIReferece varchar(50), \n"
						+ " TRANSIPartNumber varchar(30), \n"
						+ " TRANSIPackage varchar(30), \n"
						+ " TRANSIBin varchar(30), \n "
						+ " TRANSITotal integer, \n"
						+ " TRANSIOrder boolean default false, \n"
						+ " TRANSIDataSheet varchar(15), \n"
						+ " TRANSILabelColour varchar(20), \n"
						+ " TRANSIRsComponents varchar(30), \n"
						+ " TRANSIMantech varchar(30), \n"
						+ " TRANSICommunica varchar(30), \n"
						+ " TRANSIJpElectronics varchar(30), \n"
						+ " TRANSIElectrocComp varchar(30), \n"
						+ "	primary key(TRANSIReferece, TRANSIBin)"
						+ "  )");
			}
		} catch (SQLException e) {
		dialogBox("Error Occured", "Error: " +e.getCause());
		}finally{
		}
	}
	/**
	 * Method createTemperatureDeviceTable will create temperature device table
	 */
	public void createTemperatureDeviceTable()
	{
		String TABLE_NAME = "TEMPERATUREDEVICE";
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			if (tables.next())
			{
				//System.out.println("Table " + TABLE_NAME +" already exits. Ready for go!");
			}else
			{
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ " TEMPEReferece varchar(50), \n"
						+ " TEMPEPartNumber varchar(30), \n"
						+ " TEMPEPackage varchar(30), \n"
						+ " TEMPEBin varchar(30), \n "
						+ " TEMPETotal integer, \n"
						+ " TEMPEOrder boolean default false, \n"
						+ " TEMPEDataSheet varchar(15), \n"
						+ " TEMPESupplier varchar(30), \n"
						+ "	primary key(TEMPEReferece, TEMPEBin)"
						+ "  )");
			}
		} catch (SQLException e) {
		dialogBox("Error Occured", "Error: " +e.getCause());
		}finally{
		}
	}
	/**
	 * Method createOpAmpsTable will create Op-Amps table
	 */
	public void createOpAmpsTable()
	{
		String TABLE_NAME = "OPAMPS";
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			if (tables.next())
			{
				//System.out.println("Table " + TABLE_NAME +" already exits. Ready for go!");
			}else
			{
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ " OPAReferece varchar(50), \n"
						+ " OPAPartNumber varchar(30), \n"
						+ " OPAPackage varchar(30), \n"
						+ " OPABin varchar(30), \n "
						+ " OPATotal integer, \n"
						+ " OPAOrder boolean default false, \n"
						+ " OPADataSheet varchar(15), \n"
						+ " OPASupplier varchar(30), \n"
						+ "	primary key(OPAReferece, OPABin)"
						+ "  )");
			}
		} catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getCause());
		}finally{
		}
	}
	/**
	 * Method createLogicGatesTable will create the Logic Gates table
	 */
	public void createLogicGatesTable()
	{
		String TABLE_NAME = "LOGICGATES";
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			if (tables.next())
			{
				//System.out.println("Table " + TABLE_NAME +" already exits. Ready for go!");
			}else
			{
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ " LGReferece varchar(50), \n"
						+ " LGPartNumber varchar(30), \n"
						+ " LGPackage varchar(30), \n"
						+ " LGBin varchar(30), \n "
						+ " LGTotal integer, \n"
						+ " LGOrder boolean default false, \n"
						+ " LGDataSheet varchar(15), \n"
						+ " LGSupplier varchar(30), \n"
						+ "	primary key(LGReferece, LGBin)"
						+ "  )");
			}
		} catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getCause());
		}finally{
		}
	}
	/**
	 * Method createICTable will create integrated circuit table
	 */
	public void createICTable()
	{
		String TABLE_NAME = "INTEGRATEDCIRCUIT";
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			if (tables.next())
			{
				//System.out.println("Table " + TABLE_NAME +" already exits. Ready for go!");
			}else
			{
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ " ICTReferece varchar(200), \n"
						+ " ICTPartNumber varchar(30), \n"
						+ " ICTPackage varchar(30), \n"
						+ " ICTBin varchar(30), \n "
						+ " ICTTotal integer, \n"
						+ " ICTOrder boolean default false, \n"
						+ " ICTDataSheet varchar(15), \n"
						+ " ICTSupplier varchar(30), \n"
						+ "	primary key(ICTReferece, ICTBin)"
						+ "  )");
			}
		}catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getCause());
		}finally{
		}
	}
	/**
	 * Method createIGBTTable will create the IGBT table
	 */
	public void createIGBTTable()
	{
		String TABLE_NAME = "IGBTtable";
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			if (tables.next())
			{
				//System.out.println("Table " + TABLE_NAME +" already exits. Ready for go!");
			}else
			{
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ " IGBReferece varchar(200), \n"
						+ " IGBPartNumber varchar(30), \n"
						+ " IGBPackage varchar(30), \n"
						+ " IGBBin varchar(30), \n "
						+ " IGBTotal integer, \n"
						+ " IGBOrder boolean default false, \n"
						+ " IGBDataSheet varchar(15), \n"
						+ " IGBLabelColour varchar(20), \n"
						+ " IGBRsComponents varchar(30), \n"
						+ " IGBMantech varchar(300), \n"
						+ " IGBCommunica varchar(300), \n"
						+ " IGBJpElectronics varchar(300), \n"
						+ " IGBElectrocComp varchar(300), \n"
						+ "	primary key(IGBReferece, IGBBin)"
						+ "  )");
			}
		}catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getCause());
		}finally{
		}
	}
	/**
	 * Method createHighSpeedDiodesTable will create high speed diodes table
	 */
	public void createHighSpeedDiodesTable()
	{
		String TABLE_NAME = "HIGHSPEEDDIODES";
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			if (tables.next())
			{
				//System.out.println("Table " + TABLE_NAME +" already exits. Ready for go!");
			}else
			{
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ " HSDReferece varchar(50), \n"
						+ " HSDPartNumber varchar(30), \n"
						+ " HSDPackage varchar(30), \n"
						+ " HSDBin varchar(30), \n "
						+ " HSDTotal integer, \n"
						+ " HSDOrder boolean default false, \n"
						+ " HSDataSheet varchar(15), \n"
						+ " HSDLabelColour varchar(20), \n"
						+ " HSDRsComponents varchar(30), \n"
						+ " HSDMantech varchar(30), \n"
						+ " HSDCommunica varchar(30), \n"
						+ " HSDJpElectronics varchar(30), \n"
						+ " HSDElectrocComp varchar(30), \n"
						+ "	primary key(HSDReferece, HSDBin)"
						+ "  )");
			}
		} catch (SQLException e) {
		dialogBox("Error Occured", "Error: " +e.getCause());
		}finally{
		}
	}

	/**
	 * Method createPowerHoleConnectorsTable will create power hole connectors table
	 */
	public void createPowerHoleConnectorsTable()
	{
		String TABLE_NAME = "POWERHOLECONNECTOR";
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			if (tables.next())
			{
				//System.out.println("Table " + TABLE_NAME +" already exits. Ready for go!");
			}else
			{
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ "  PHReferece varchar(200) , \n"
						+ "  PHTotal integer, \n"
						+ "  PHBin varchar(30),\n "
						+ "  PHLabelColour varchar(20),"
						+ "  PHOrder boolean default false,\n"
						+ "	primary key(PHReferece, PHBin)"
						+ "  )");
			}
		}catch (SQLException e) {
		dialogBox("Error Occured", "Error: " +e.getCause());
		}finally{
		}
	}
	/**
	 *Method createDCMotorsTable will create the DC Motos table
	 */
	public void createDCMotorsTable()
	{
		String TABLE_NAME = "DC_MOTORS";
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			if (tables.next())
			{
				//System.out.println("Table " + TABLE_NAME +" already exits. Ready for go!");
			}else
			{
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ "  DCMReferece varchar(50) , \n"
						+ "  DCMTotal integer, \n"
						+ "  DCMBin varchar(30),\n "
						+ "  DCMLabelColour varchar(20),"
						+ "  DCMOrder boolean default false,\n"
						+ "	primary key(DCMReferece, DCMBin)"
						+ "  )");
			}
		} catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getCause());
		}finally{
		}
	}
	/**
	 *Method createMicrophoneTable will create the Microphone table
	 */
	public void createMicrophoneTable()
	{
		String TABLE_NAME = "MICROPHONE";
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			if (tables.next())
			{
				//System.out.println("Table " + TABLE_NAME +" already exits. Ready for go!");
			}else
			{
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ "  MICROReferece varchar(50) , \n"
						+ "  MICROTotal integer, \n"
						+ "  MICROBin varchar(30),\n "
						+ "  MICROLabelColour varchar(20),"
						+ "  MICROOrder boolean default false,\n"
						+ "	primary key(MICROReferece, MICROBin)"
						+ "  )");
			}
	} catch (SQLException e) {
		dialogBox("Error Occured", "Error: " +e.getCause());
		}finally{
		}
	}
	/**
	 *Method createFusesHoldersTable will create the fuse holders tables
	 */
	public void createFusesHoldersTable()
	{
		String TABLE_NAME = "FUSES_HOLDERS";
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			if (tables.next())
			{
				//System.out.println("Table " + TABLE_NAME +" already exits. Ready for go!");
			}else
			{
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ "  FHReferece varchar(50) , \n"
						+ "  FHTotal integer, \n"
						+ "  FHBin varchar(30),\n "
						+ "  FHLabelColour varchar(20),"
						+ "  FHOrder boolean default false,\n"
						+ "	primary key(FHReferece, FHBin)"
						+ "  )");
			}
		} catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getCause());
		}finally{
		}
	}
	/**
	 *Method createInsulatorsTable will create the Insulator table.
	 */
	public void createInsulatorsTable()
	{
		String TABLE_NAME = "INSULATORS";
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			if (tables.next())
			{
				//System.out.println("Table " + TABLE_NAME +" already exits. Ready for go!");
			}else
			{
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ "  INSReferece varchar(50) , \n"
						+ "  INSTotal integer, \n"
						+ "  INSBin varchar(30),\n "
						+ "  INSLabelColour varchar(20),"
						+ "  INSOrder boolean default false,\n"
						+ "	primary key(INSReferece, INSBin)"
						+ "  )");
			}
		} catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getCause());
		}finally{
		}
	}
	/**
	 * Method createBNCTable will create the BNC connectors
	 */
	public void createBNCTable()
	{
		String TABLE_NAME = "BNC";
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			if (tables.next())
			{
				//System.out.println("Table " + TABLE_NAME +" already exits. Ready for go!");
			}else
			{
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ "  BNCReferece varchar(60) , \n"
						+ "  BNCTotal integer, \n"
						+ "  BNCBin varchar(20),\n "
						+ "  BNCLabelColour varchar(20),"
						+ "  BNCOrder boolean default false,\n"
						+ "	primary key(BNCReferece, BNCBin)"
						+ "  )");
			}
	} catch (SQLException e) {
		dialogBox("Error Occured", "Error: " +e.getCause());
		}finally{
		}
	}
	/**
	 *Method createETDClipsTable will create the ETD Clips table
	 */
	public void createETDClipsTable()
	{
		String TABLE_NAME = "ETD_CLIPS";
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			if (tables.next())
			{
				//System.out.println("Table " + TABLE_NAME +" already exits. Ready for go!");
			}else
			{
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ "  ECReferece varchar(50) , \n"
						+ "  ECTotal integer, \n"
						+ "  ECBin varchar(50),\n "
						+ "  ECLabelColour varchar(30),"
						+ "  ECOrder boolean default false,\n"
						+ "	primary key(ECReferece, ECBin)"
						+ "  )");
			}
		} catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getCause());
			}finally{
			}
	}
	/**
	 *Method createCrocClipsTable will create the Croc clips table
	 */
	public void createCrocClipsTable()
	{
		String TABLE_NAME = "CROC_CLIPS";
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			if (tables.next())
			{
				//System.out.println("Table " + TABLE_NAME +" already exits. Ready for go!");
			}else
			{
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ "  CCReferece varchar(50) , \n"
						+ "  CCTotal integer, \n"
						+ "  CCBin varchar(50),\n "
						+ "  CCLabelColour varchar(30),"
						+ "  CCOrder boolean default false,\n"
						+ "	primary key(CCReferece, CCBin)"
						+ "  )");
			}
	} catch (SQLException e) {
		dialogBox("Error Occured", "Error: " +e.getCause());
		}finally{
		}
	}
	/**
	 *Method createDataSocketsTable will create the data socket table
	 */
	public void createDataSocketsTable()
	{
		String TABLE_NAME = "DATASOCKETS";
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			if (tables.next())
			{
				//System.out.println("Table " + TABLE_NAME +" already exits. Ready for go!");
			}else
			{
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ "  DAReferece varchar(50) , \n"
						+ "  DATotal integer, \n"
						+ "  DABin varchar(50),\n "
						+ "  DALabelColour varchar(30),"
						+ "  DAOrder boolean default false,\n"
						+ "	primary key(DAReferece, DABin)"
						+ "  )");
			}
		} catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getCause());
			}finally{
			}
	}
	/**
	 *Method createDinConnectorsTable will create the din connectors table
	 */
	public void createDinConnectorsTable()
	{
		String TABLE_NAME = "DINCONNECTORS";
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			if (tables.next())
			{
				//System.out.println("Table " + TABLE_NAME +" already exits. Ready for go!");
			}else
			{
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ "  DINReferece varchar(50) , \n"
						+ "  DINTotal integer, \n"
						+ "  DINBin varchar(50),\n "
						+ "  DINLabelColour varchar(30),"
						+ "  DINOrder boolean default false,\n"
						+ "	primary key(DINReferece, DINBin)"
						+ "  )");
			}
	} catch (SQLException e) {
		dialogBox("Error Occured", "Error: " +e.getCause());
		}finally{
		}
	}
	/**
	 *Method createBananaPlugsTable will create the banana plugs table
	 */
	public void createBananaPlugsTable()
	{
		String TABLE_NAME = "BANANAPLUGS";
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			if (tables.next())
			{
				//System.out.println("Table " + TABLE_NAME +" already exits. Ready for go!");
			}else
			{
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ "  BANPReferece varchar(50) , \n"
						+ "  BANPTotal integer, \n"
						+ "  BANPBin varchar(50),\n "
						+ "  BANPLabelColour varchar(30),"
						+ "  BANPOrder boolean default false,\n"
						+ "	primary key(BANPReferece, BANPBin)"
						+ "  )");
			}
		} catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getCause());
		}finally{
			}
	}
	/**
	 *Method createDisplayTable will create the display
	 */
	public void createDisplayTable()
	{
		String TABLE_NAME = "DISPLAY";
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			if (tables.next())
			{
				//System.out.println("Table " + TABLE_NAME +" already exits. Ready for go!");
			}else
			{
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ "  disReferece varchar(50) , \n"
						+ "  disTotal integer, \n"
						+ "  disBin varchar(50),\n "
						+ "  disLabelColour varchar(30),"
						+ "  disOrder boolean default false,\n"
						+ "	primary key(disReferece, disBin)"
						+ "  )");
			}
	} catch (SQLException e) {
		dialogBox("Error Occured", "Error: " +e.getCause());
		}finally{
		}
	}
	/**
	 *Method createlLedsTable will create the leds table
	 */
	public void createlLedsTable()
	{
		String TABLE_NAME = "LEDS";
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			if (tables.next())
			{
				//System.out.println("Table " + TABLE_NAME +" already exits. Ready for go!");
			}else
			{
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ "  ledReferece varchar(50) , \n"
						+ "  ledTotal integer, \n"
						+ "  ledBin varchar(50),\n "
						+ "  ledLabelColour varchar(30),"
						+ "  ledOrder boolean default false,\n"
						+ "	primary key(ledReferece, ledBin)"
						+ "  )");
			}
	} catch (SQLException e) {
		dialogBox("Error Occured", "Error: " +e.getCause());
		}finally{
		}
	}
	public boolean deleteComponent(String tableName, String key1, String key2, CompProperty comptProperty)
	{
		String deleteStatement = "DELETE FROM " + tableName + " WHERE " + key1 + " = ? AND " + key2 + " = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(deleteStatement);
			stmt.setString(1, comptProperty.getReference());
			stmt.setString(2, comptProperty.getBin());
			int res = stmt.executeUpdate();
			if (res == 1)
				return true;
		} catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getCause());
		}
		return false;
	}

	/**_____________________(Equipments Tables)____________________________*/

	/**
	 *Method createToolsTable will create tools table
	 */
	public void createToolsTable()
	{
		String TABLE_NAME = "TOOLS";
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			if (tables.next())
			{
				//System.out.println("Table " + TABLE_NAME +" already exits. Ready for go!");
			}else
			{
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ "  TAssetNumber varchar(50) , \n"
						+ "  TName varchar(200),\n"
						+ "  TModel varchar(50),\n"
						+ "  TSerialNumber varchar(50),\n "
						+ "  TNotes varchar(100), \n"
						+ "  TTotal integer,\n"
						+ "  TAvailable boolean default true,\n"
						+ "	primary key(TModel, TAssetNumber)"
						+ "  )");
			}
		} catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getCause());
			}finally{
		}
	}
	/**
	 *Method createComputerTable will create computer table.
	 */
	public void createComputerTable()
	{
		String TABLE_NAME = "COMPUTER";
		try
		{
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			if (tables.next())
			{
				//System.out.println("Table " + TABLE_NAME +" already exits. Ready for go!");
			}else
			{
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ "  CompAssetNumber varchar(50), \n"
						+ "  CompName varchar(200),\n"
						+ "  CompModel varchar(50),\n"
						+ "  CompSerialNumber varchar(50),\n "
						+ "  CompNotes varchar(100), \n"
						+ "  CompTotal integer, \n"
						+ "  CompAvailable boolean default true,\n"
						+ "  primary key(CompModel, CompAssetNumber)"
						+ ")");
			}
		} catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getCause());
			}finally{
			}
	}
	/**
	 *Method createPowerSuppliersTable will create a power supplier table
	 */
	public void createPowerSuppliersTable()
	{
		String TABLE_NAME = "POWERSUPPLY";
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);

			if (tables.next())
			{
				//System.out.println("Table " + TABLE_NAME +" already exits. Ready for go!");
			}else
			{
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ "  PSAssetNumber varchar(50), \n"
						+ "  PSName varchar(30),\n"
						+ "  PSModel varchar(50),\n"
						+ "  PSSerialNumber varchar(50),\n "
						+ "  PSNotes varchar(200), \n"
						+ "  PSTotal integer,\n"
						+ "  PSAvailable boolean default true,\n"
						+ "  PRIMARY KEY(PSModel, PSAssetNumber)"
						+ "  )");
			}
		} catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getCause());
		}finally{
		}
	}
	/**
	 * Method createOscilloscopeTable will create oscilloscope table
	 */
	public void createOscilloscopeTable()
	{
		String TABLE_NAME = "OSCILLOSCOPE";
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);

			if (tables.next())
			{
				//System.out.println("Table " + TABLE_NAME +" already exits. Ready for go!");
			}else
			{
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ "  OSCIAssetNumber varchar(50), \n"
						+ "  OSCIName varchar(30) ,\n"
						+ "  OSCIModel varchar(50) ,\n"
						+ "  OSCISerialNumber varchar(50) ,\n "
						+ "  OSCINotes varchar(200), \n"
						+ "  OSCITotal integer, \n"
						+ "  OSCIAvailable boolean default true,\n"
						+ "  PRIMARY KEY(OSCIModel, OSCIAssetNumber)"
						+ "  )");
			}
		} catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getCause());
		}finally{
		}
	}
	/**
	 *Method createMultimeterTable will create Multimeter table
	 */
	public void createMultimeterTable()
	{
		String TABLE_NAME = "MULTIMETER";
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);

			if (tables.next())
			{
				//System.out.println("Table " + TABLE_NAME +" already exits. Ready for go!");
			}else
			{
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ "  MAssetNumber varchar(50), \n"
						+ "  MName varchar(60),\n"
						+ "  MModel varchar(50) ,\n"
						+ "  MSerialNumber varchar(50) ,\n "
						+ "  MNotes varchar(200), \n"
						+ "  MdTotal integer, \n"
						+ "  MdAvailable boolean default true,\n"
						+ "  PRIMARY KEY(MModel, MAssetNumber)"
						+ "  )");
			}
		} catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getCause());
		}finally{
		}
	}
	/**
	 * Method createBreadboardTable will create a breadboard
	 */
	public void createBreadboardTable()
	{
		String TABLE_NAME = "BREADBOARD";
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);

			if (tables.next())
			{
				//System.out.println("Table " + TABLE_NAME +" already exits. Ready for go!");
			}else
			{
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ "  BdAssetNumber varchar(50), \n"
						+ "  BdName varchar(200),\n"
						+ "  BdModel varchar(50) ,\n"
						+ "  BdSerialNumber varchar(50),\n "
						+ "  BdNotes varchar(200), \n"
						+ "  BdTotal integer, \n"
						+ "  BdAvailable boolean default true,\n"
						+ "  PRIMARY KEY(BdModel, BdAssetNumber)"
						+ "  )");
			}
		} catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getCause());
		}finally{
		}
	}
	/**
	 *Method createFunctionGeneratorTable will create a function generator
	 */
	public void createFunctionGeneratorTable()
	{
		String TABLE_NAME = "FUNCTIONGENERATOR";
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);

			if (tables.next())
			{
				//System.out.println("Table " + TABLE_NAME +" already exits. Ready for go!");
			}else
			{
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ "  FgAssetNumber varchar(50),\n"
						+ "  FgName varchar(30),\n"
						+ "  FgModel varchar(50),\n"
						+ "  FgSerialNumber varchar(50),\n "
						+ "  FgNotes varchar(200), \n"
						+ "  FgTotal integer, \n"
						+ "  FgAvailable boolean default true,\n"
						+ "  PRIMARY KEY(FgModel, FgAssetNumber)"
						+ "  )");
			}
		} catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getCause());
		}finally{
		}
	}
	/**
	 * Method createIssueEquipmentsTable will create Issue table
	 */
	public void createIssueEquipmentsTable()
	{
		String TABLE_NAME = "ISSUE";
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			if (tables.next())
			{
				//System.out.println("Table " + TABLE_NAME +" already exits. Ready for go!");
			}else
			{
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ "  modelNumber varchar(50) primary key,\n"
						+ "  assetNumber varchar(50),\n"
						+ "  studentNo varchar(25),\n"
						+ "  issueTime timestamp default CURRENT_TIMESTAMP,\n"
						+ "  renewCount integer default 0,\n"
						+ "  FOREIGN KEY (studentNo) REFERENCES BTECHSTUDENT(studentNoB),\n"
						+ "  FOREIGN KEY (modelNumber, assetNumber) REFERENCES FUNCTIONGENERATOR(FgModel, FgAssetNumber)"
						+ "  )");
			}
	} catch (SQLException e) {
		dialogBox("Error Occured", "Error: " +e.getCause());
		}finally{
		}
	}
	public boolean deleteComponentTypeC(String tableName, String reference, String bin, CompCProperty selectedForDeletion) {

		String deleteCstatement = "DELETE FROM " +tableName+ " WHERE " + reference +" = ? AND " + bin + " = ?";
		System.out.println(deleteCstatement);
		try
		{
			PreparedStatement stmt = conn.prepareStatement(deleteCstatement);
			stmt.setString(1, selectedForDeletion.getReference());
			stmt.setString(2, selectedForDeletion.getBin());
			int res = stmt.executeUpdate();
			if (res == 1)
				return true;
		} catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getCause());
		}
		return false;
	}
	public boolean deleteEquipment(String tableName, String key1, String key2, EquipmentProperty equipmentProperty)
	{
		String deleteStatement = "DELETE FROM " + tableName + " WHERE " + key1 + " = ? AND " + key2 + " = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(deleteStatement);
			stmt.setString(1, equipmentProperty.getModel());
			stmt.setString(2, equipmentProperty.getAssetNumber());
			int res = stmt.executeUpdate();
			if (res == 1)
				return true;
		} catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getCause());
		}
		return false;
	}
	/**______________________________________(End of Equipments Tables)____________________________*/

	/**
	 * Method execQuery this method is usefully to execute query like select statement
	 * and return the query.
	 * @param query
	 * @return  <code>ResultSet</code> specify statement.
	 */
	public ResultSet execQuery(String query)
	{
		ResultSet result;
		try{
			stmt = conn.createStatement();
			result = stmt.executeQuery(query);
		}catch (SQLException ex){
			dialogBox("Error Occured", "Error: " +ex.getLocalizedMessage());
			return null;
		}finally{
		}
		return result;
	}
	/**
	 * Method execAction is usefully to doing something
	 */
	public boolean execAction(String qu)
	{
		try {
			stmt = conn.createStatement();
			stmt.execute(qu);
			return true;
		} catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getCause());
			return false;
		}finally {
		}
	}
	/**
	 * Method createBtechStudentTable will create a table in the database to store
	 * information about the Btech students.
	 */
	public void createBtechStudentTable()
	{
		String TABLE_NAME = "BTECHSTUDENT";
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			if (tables.next())
			{
				//System.out.println("Table " + TABLE_NAME +" already exits. Ready for go!");
			}else
			{
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ "  studentNoB varchar(25) primary key, \n"
						+ "  nameB varchar(50),\n"
						+ "  surnameB varchar(50),\n"
						+ "  supervisorB varchar(50),\n "
						+ "  emailB varchar(100), \n"
						+ "  cellphoneNoB varchar(20),\n"
						+ "  stationB varchar(20),\n"
						+ "  courseB varchar(20)"
						+ "  )");
			}
		} catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getMessage());
		}finally{
		}
	}
	/**
	 *Method createMasterStudentTable will create a table to store information about
	 *the master students
	 */
	public void createMasterStudentTable()
	{
		String TABLE_NAME = "MASTERSTUDENT";
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			if (tables.next())
			{
				//System.out.println("Table " + TABLE_NAME +" already exits. Ready for go!");
			}else
			{
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ "  studentNoM varchar(25) primary key, \n"
						+ "  nameM varchar(50),\n"
						+ "  surnameM varchar(50),\n"
						+ "  supervisorM varchar(50),\n "
						+ "  emailM varchar(100), \n"
						+ "  cellphoneNoM varchar(20),\n"
						+ "  stationM varchar(20),\n"
						+ "  courseM varchar(20)"
						+ "  )");
			}
		} catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getMessage());
		}finally{
		}
	}

	/**
	 * Method deleteStudentB will receive a selected row and
	 * delete from the database table.
	 * @param studentB
	 * @return<code> boolean </code> specify statement.
	 */
	public boolean deleteStudentB(StudentProperty studentB)
	{
		try
		{
			String deleteStmt ="DELETE FROM BTECHSTUDENT WHERE STUDENTNOB = ?";
			PreparedStatement stmt = conn.prepareStatement(deleteStmt);
			stmt.setString(1, studentB.getStudendID());
			int result = stmt.executeUpdate();
			if(result == 1)
			     return true;
		} catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getMessage());
		}
		return false;
	}
	/**
	 * Method deleteMasterStudent will receive the selected row from the table
	 * and delete from the database table.
	 * @param masterStudent
	 * @return<code>boolean</code> specify statement.
	 */
	public boolean deleteMasterStudent(StudentProperty masterStudent)
	{
		try
		{
			String deleteStmt ="DELETE FROM MASTERSTUDENT WHERE STUDENTNOM = ?";
			PreparedStatement stmt = conn.prepareStatement(deleteStmt);
			stmt.setString(1, masterStudent.getStudendID());
			int result = stmt.executeUpdate();
			if(result == 1)
			     return true;
		} catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getMessage());
		}
		return false;
	}
	/**
	 *Method updateBstudent will update the table with edit value in the selected row on
	 *the Btech student table.
	 * @param Bstudent
	 * @return <code> boolean </code> specify statement.
	 */
	public boolean updateBstudent(StudentProperty Bstudent)
	{
		try {
			String update = "UPDATE BTECHSTUDENT SET NAMEB=?, SURNAMEB=?, SUPERVISORB=?, EMAILB=?, CELLPHONENOB=?, STATIONB=? , COURSEB=? WHERE STUDENTNOB=?";

			PreparedStatement stmt = conn.prepareStatement(update);

			stmt.setString(1, Bstudent.getName());
			stmt.setString(2, Bstudent.getSurname());
			stmt.setString(3, Bstudent.getSupervisor());
			stmt.setString(4, Bstudent.getEmail());
			stmt.setString(5, Bstudent.getCellphone());
			stmt.setString(6, Bstudent.getStation());
			stmt.setString(7, Bstudent.getCourse());
			stmt.setString(8, Bstudent.getStudendID());
			int result = stmt.executeUpdate();
			return (result > 0);

		} catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getMessage());
		}
		return false;
	}
	/**
	 *Method updateMasterstudent will update the table with edit value in the selected row on
	 *the Master student table.
	 * @param Mstudent
	 * @return <code> boolean </code> specify statement.
	 */
	public boolean updateMasterstudent(StudentProperty Mstudent)
	{
		try {
			String update = "UPDATE MASTERSTUDENT SET NAMEM=?, SURNAMEM=?, SUPERVISORM=?, EMAILM=?, CELLPHONENOM=?, STATIONM=? , COURSEM=? WHERE STUDENTNOM=?";

			PreparedStatement stmt = conn.prepareStatement(update);
			stmt.setString(1, Mstudent.getName());
			stmt.setString(2, Mstudent.getSurname());
			stmt.setString(3, Mstudent.getSupervisor());
			stmt.setString(4, Mstudent.getEmail());
			stmt.setString(5, Mstudent.getCellphone());
			stmt.setString(6, Mstudent.getStation());
			stmt.setString(7, Mstudent.getCourse());
			stmt.setString(8, Mstudent.getStudendID());
			int result = stmt.executeUpdate();
			return (result > 0);

		} catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getMessage());
		}
		return false;
	}
	/**
	 * Method updateEquipments will update the equipment information in the table
	 * @param equipProperty
	 * @return
	 */
	public boolean updateEquipments(EquipmentProperty equipProperty)
	{
		String update = "UPDATE "+ getEquip().getTableName()+ " SET "+ getEquip().getEquipName()+ " =?, "
					+getEquip().getSerialNumber()+ " =?, "+getEquip().getNotes()+" =?, "+getEquip().getTotal()
					+" =?, "+getEquip().getAvail()+" =? WHERE "+getEquip().getModel()+" =? AND "+getEquip().getAssetNumber()+" =?";
		try {
			PreparedStatement stmt = conn.prepareStatement(update);
			stmt.setString(1, equipProperty.getName());
			stmt.setString(2, equipProperty.getSerialNumber());
			stmt.setString(3, equipProperty.getNotes());
			stmt.setInt(4, equipProperty.getTotal());
			stmt.setBoolean(5, equipProperty.getAvailable());
			stmt.setString(6, equipProperty.getModel());
			stmt.setString(7, equipProperty.getAssetNumber());
			int result = stmt.executeUpdate();
			return (result > 0);

		} catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getMessage());
		}
		return false;
	}
	/**
	 * Method updateComponentTypeA will update the component information type A.
	 * @param editCompProperty
	 * @return
	 */
	public boolean updateComponentTypeA(CompProperty editCompProperty)
	{
		try
		{
			String update = "UPDATE "+ getComponent().getTableName() +" SET "+ getComponent().getTotal() +"=?, "+ getComponent().getLabelColour() +"=?, "
					+getComponent().getOrder()+ " =? WHERE " + getComponent().getReference() +" =? AND "+ getComponent().getBin() + "=?";

			PreparedStatement stmt = conn.prepareStatement(update);
			stmt.setInt(1, editCompProperty.getTotal());
			stmt.setString(2, editCompProperty.getLabelColour());
			stmt.setBoolean(3, Boolean.valueOf(editCompProperty.getOrder()));
			stmt.setString(4, editCompProperty.getReference());
			stmt.setString(5, editCompProperty.getBin());
			int result = stmt.executeUpdate();
			return (result > 0);

		} catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getMessage());
		}
		return false;
	}
	/**
	 *Method updateComponentTypeC will update the information of component
	 *named by type C.
	 * @param editTypeCPropeerty
	 * @return
	 */
	public boolean updateComponentTypeC(CompCProperty editTypeCPropeerty)
	{
		String update = "UPDATE "+getCompTypeC().getTableName()+ " SET " +getCompTypeC().getTotal() +" =?, "+
				getCompTypeC().getLabelColour()+ "= ?, "+getCompTypeC().getOrder()+ " =?, "+ getCompTypeC().getPartNumber()+
				" =?, "+getCompTypeC().getPaCkage()+ " =?, "+ getCompTypeC().getDatasheet()+ "=?, "+getCompTypeC().getRsComponents()+
				"=?, "+getCompTypeC().getMantech()+ "=?, "+ getCompTypeC().getCommunic()+ "=?, "+getCompTypeC().getJpElectronics()+
				"=?, "+getCompTypeC().getElectronicComp()+ " =? WHERE " +getCompTypeC().getReference()+ " =? AND "+getCompTypeC().getBin()+
				" =?";
		try {
			PreparedStatement stmt = conn.prepareStatement(update);
			stmt.setInt(1, editTypeCPropeerty.getTotal());
			stmt.setString(2, editTypeCPropeerty.getLabelColour());
			stmt.setBoolean(3, editTypeCPropeerty.getOrder());
			stmt.setString(4, editTypeCPropeerty.getPartNumber());
			stmt.setString(5, editTypeCPropeerty.getPacKage());
			stmt.setString(6, editTypeCPropeerty.getDatasheet());
			stmt.setString(7, editTypeCPropeerty.getRsComponent());
			stmt.setString(8, editTypeCPropeerty.getMantech());
			stmt.setString(9, editTypeCPropeerty.getCommunica());
			stmt.setString(10, editTypeCPropeerty.getJpElectonics());
			stmt.setString(11, editTypeCPropeerty.getElectrocComp());
			stmt.setString(12, editTypeCPropeerty.getReference());
			stmt.setString(13, editTypeCPropeerty.getBin());
			int result = stmt.executeUpdate();
			return (result > 0);
		} catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getMessage());
		}
		return false;
	}
	/**
	 * Method updateComponenTypeB will update the component named type B
	 * @param editTypeBProperty
	 * @return
	 */
	public boolean updateComponenTypeB(CompBProperty editTypeBProperty)
	{
		try
		{
			String update = "UPDATE "+getCompTypeB().getTableName()+" SET "+getCompTypeB().getPartNumber()+
								" =?, "+getCompTypeB().getPaCkage()+" =?, "+getCompTypeB().getTotal()+" =?, "+
								getCompTypeB().getOrdar()+" =?, "+getCompTypeB().getDatasheet()+" =?, "+
								getCompTypeB().getSupplier()+" =? WHERE "+getCompTypeB().getReference()+" =? AND "
								+getCompTypeB().getBin()+" =?";


			PreparedStatement stmt = conn.prepareStatement(update);
			stmt.setString(1, editTypeBProperty.getPartNumber());
			stmt.setString(2, editTypeBProperty.getPackageComp());
			stmt.setInt(3, editTypeBProperty.getTotal());
			stmt.setBoolean(4, editTypeBProperty.getOrder());
			stmt.setString(5, editTypeBProperty.getDatasheet());
			stmt.setString(6, editTypeBProperty.getSupplier());
			stmt.setString(7, editTypeBProperty.getReference());
			stmt.setString(8, editTypeBProperty.getBin());
			int result = stmt.executeUpdate();
			return (result > 0);
		} catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getMessage());
		}
		return false;
	}
	/**
	 * Method deleteComponentTypeB will delete the component named type B
	 * @param tableName
	 * @param reference
	 * @param bin
	 * @param selectedForDeletion
	 * @return
	 */
	public boolean deleteComponentTypeB(String tableName, String reference, String bin, CompBProperty selectedForDeletion)
	{
		String deleteCstatement = "DELETE FROM " +tableName+ " WHERE " + reference +" = ? AND " + bin + " = ?";
		System.out.println(deleteCstatement);
		try
		{
			PreparedStatement stmt = conn.prepareStatement(deleteCstatement);
			stmt.setString(1, selectedForDeletion.getReference());
			stmt.setString(2, selectedForDeletion.getBin());
			int res = stmt.executeUpdate();
			if (res == 1)
				return true;
		} catch (SQLException e) {
			dialogBox("Error Occured", "Error: " +e.getMessage());
		}
		return false;
	}

	 private void dialogBox(String title, String context) {
			Platform.runLater(()->{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle(title);
				alert.setContentText(context);
				alert.show();
			});
	}
}
