package lambdaclovr.dsl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lambdaclovr.dsl.model.DsType;
import lambdaclovr.dsl.phoenix.Crud;

/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer</h2>
 * <h3>Package Name: lambdaclovr.dsl.dao</h3>
 * <h3>Class Name: DsTypeDAO</h3>
 * <p>
 * 
 * @Project This file is part of LAMBDA-CLOVR Project.
 *          </p>
 *          <p>
 * @Description: This class is used to handel DsTypeDAO CRUD Operations.
 *               </p>
 * 
 * @author Numan Khan
 * 
 * @version 0.4
 * @since 0.4
 **/
public class DsTypeDAO {
	private static Crud operation = new Crud();

	/**
	 * Create a new DsType.
	 * 
	 * @param DsType
	 *            object
	 * @return DsType object
	 * @throws SQLException
	 */
	public static DsType create(DsType dsType) throws SQLException {

		String[] data = new String[3];

		data[0] = "NEXT VALUE FOR lambdaclovr.dstype_id";
		data[1] = "\'" + dsType.getName() + "\'";
		data[2] = "\'" + dsType.getDescription() + "\'";

		// System.out.println("Data = " + Arrays.toString(data));

		operation.insertOrUdate("DS_TYPE", data);
		return dsType;
	}

	////////////////////////////////////////////////////

	/**
	 * This function fetch a list of all the DsType from DB.
	 * 
	 * @param null
	 * @return list of DsType objects
	 * @throws SQLException
	 */
	public static List<DsType> getAll() throws SQLException {

		ResultSet rs = operation.getAllRows("DS_TYPE");
		List<DsType> list = new ArrayList<DsType>();

		while (rs.next()) {
			DsType DsType = new DsType()
					.setDsTypeId(rs.getString("DSTYPE_ID"))
					.setName(rs.getString("NAME"))
					.setDescription(rs.getString("DESCRIPTION"));
			list.add(DsType);
		}
		// System.out.println(list.size());
		return list;
	}

	////////////////////////////////////////////////////

	/**
	 * Update DsType.
	 * @param DsType Object
	 * @return DsType Object
	 */
	public static DsType update(DsType dsType) {

		String[] data = new String[3];

		data[0] = "" + dsType.getDsTypeId() + "";
		data[1] = "\'" + dsType.getName() + "\'";
		data[2] = "\'" + dsType.getDescription() + "\'";

		operation.insertOrUdate("DS_TYPE", data);

		return dsType;
	}
}
