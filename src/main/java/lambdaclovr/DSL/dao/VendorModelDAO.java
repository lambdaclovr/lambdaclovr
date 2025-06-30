package lambdaclovr.dsl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lambdaclovr.dsl.model.VendorModel;
import lambdaclovr.dsl.phoenix.Crud;

/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer</h2>
 * <h3>Package Name: lambdaclovr.dsl.dao</h3>
 * <h3>Class Name: VendorModleDAO</h3>
 * <p>
 * 
 * @Project This file is part of LAMBDA-CLOVR Project.
 *          </p>
 *          <p>
 * @Description: This class is used to handel VendorModel CRUD Operations.
 *               </p>
 * 
 * @author Numan Khan
 * 
 * @version 1.0
 * @since 2024-05-27
 **/
public class VendorModelDAO {
	private static Crud operation = new Crud();

	/**
	 * Create a new vendor model.
	 * 
	 * @param vendorModel
	 * @return vendorModel
	 * @throws SQLException
	 */
	public static VendorModel create(VendorModel vendorModel) throws Exception {

		String[] data = new String[4];
		
		data[0] = "NEXT VALUE FOR lambdaclovr.vendor_model_id";
		data[1] = "" + vendorModel.getVendorId() + "";
		data[2] = "\'" + vendorModel.getCameraModel() + "\'";
		data[3] = "\'" + vendorModel.getDescription() + "\'";
		

		System.out.println("Data = " + Arrays.toString(data));
		operation.insertOrUdate("VENDOR_MODEL", data);

		return vendorModel;
	}

	////////////////////////////////////////////////////

	/**
	 * Update existing vendor model.
	 * 
	 * @param vendorModel
	 *            Object
	 * @return vendorModel Object
	 * @throws SQLException
	 */
	public static VendorModel update(VendorModel vendorModel) throws Exception {

		String[] data = new String[4];

		data[0] = "" + vendorModel.getVendorModelId() + "";
		data[1] = "" + vendorModel.getVendorId() + "";
		data[2] = "\'" + vendorModel.getCameraModel() + "\'";
		data[3] = "\'" + vendorModel.getDescription() + "\'";
		

		System.out.println("Data = " + Arrays.toString(data));
		operation.insertOrUdate("VENDOR_MODEL", data);

		return vendorModel;
	}

	////////////////////////////////////////////////////

	/**
	 * This function fetch a list of all vendors models from DB.
	 * 
	 * @param null
	 * @return list of vendors models
	 * @throws SQLException
	 */
	public static List<VendorModel> getAll() throws SQLException {

		ResultSet rs = operation.getAllRows("VENDOR_MODEL");
		List<VendorModel> list = new ArrayList<VendorModel>();

		while (rs.next()) {
			VendorModel vendorModel = new VendorModel()
			.setVendorModelId(rs.getString("VENDOR_MODEL_ID"))
			.setVendorId(rs.getString("VENDOR_ID"))
			.setCameraModel(rs.getString("CAMERA_MODEL"))
			.setDescription(rs.getString("DESCRIPTION"));

			list.add(vendorModel);
		}

		// System.out.println(list.size());
		return list;
	}

	////////////////////////////////////////////////////

	/**
	 * This function fetch a list of all the models of a vendor from the DB (1:m).
	 * id is the foreigh key from vendor
	 * 
	 * @param id
	 * @return list of vendors models
	 * @throws SQLException
	 */
	public static List<VendorModel> getList(int vendorId) throws SQLException {

		String vendorIdColumnName = "VENDOR_ID";
		ResultSet rs = operation.getRow("VENDOR_MODEL", vendorIdColumnName, vendorId);
		// ResultSet rs = operation.getAllRows("VENDOR_MODEL");
		List<VendorModel> list = new ArrayList<VendorModel>();
		
		while (rs.next()) {
			VendorModel vendorModel = new VendorModel()
			.setVendorModelId(rs.getString("VENDOR_MODEL_ID"))
			.setVendorId(rs.getString("VENDOR_ID"))
			.setCameraModel(rs.getString("CAMERA_MODEL"))
			.setDescription(rs.getString("DESCRIPTION"));

			list.add(vendorModel);
		}

		System.out.println(list.size());
		return list;
	}

	////////////////////////////////////////////////////

	/**
	 * This function fetch a single record of a vendor model from DB. <br> 
	 * id is the primary key of the vendor model
	 * 
	 * @param id
	 * @return VendorsModel object
	 * @throws SQLException
	 */
	public static VendorModel get(int vendorModelId) throws SQLException {

		String vendorModelIdColumnName = "VENDOR_MODEL_ID";
		ResultSet rs = operation.getRow("VENDOR_MODEL", vendorModelIdColumnName, vendorModelId);
		VendorModel vendorModel = new VendorModel();
		while (rs.next()) {
			vendorModel
			.setVendorModelId(rs.getString("VENDOR_MODEL_ID"))
			.setVendorId(rs.getString("VENDOR_ID"))
			.setCameraModel(rs.getString("CAMERA_MODEL"))
			.setDescription(rs.getString("DESCRIPTION"));
		}
		return vendorModel;
	}
}
