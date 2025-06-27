/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lambdaclovr.dsl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lambdaclovr.dsl.exception.CustomeException;
import lambdaclovr.dsl.model.DataSource;
import lambdaclovr.dsl.phoenix.Crud;

/**
 *
 * @author Anwar Abir
 */
public class DataSourceDAO {

	private static Crud operation = new Crud();

	public static List<DataSource> getAll() throws SQLException {

		List<DataSource> vList = new ArrayList<DataSource>();

		ResultSet rs = operation.getAllRows("DATA_SOURCE");

		while (rs.next()) {

			DataSource ds = new DataSource()
			.setDsId(rs.getString("DS_ID"))
			.setUserId(rs.getString("USER_ID"))
			.setVendorModelId(rs.getString("VENDOR_MODEL_ID"))
			.setDsTypeId(rs.getString("DSTYPE_ID"))
			.setDsName(rs.getString("DS_NAME"))
			.setDescription(rs.getString("DESCRIPTION"))
			.setCameraUserName(rs.getString("CAMERA_USERNAME"))
			.setCameraPassword(rs.getString("CAMERA_PASSWORD"))
			.setCameraPort(rs.getString("CAMERA_PORT"))
			.setCameraLink(rs.getString("CAMERA_LINK"))
			.setCameraSnapshot(rs.getString("CAMERA_SNAPSHOT"))
			.setCreationDate(rs.getString("CREATION_DATE"));

			vList.add(ds);
		}

		return vList;
	}

	public static DataSource get(int ds_id) throws SQLException {

		String emailColumnName = "ds_id";
		DataSource ds = new DataSource();
		ResultSet rs = operation.getRow("DATA_SOURCE", emailColumnName, ds_id);

		while (rs.next()) {
			ds.setDsId(rs.getString("DS_ID"))
			.setUserId(rs.getString("USER_ID"))
			.setVendorModelId(rs.getString("VENDOR_MODEL_ID"))
			.setDsTypeId(rs.getString("DSTYPE_ID"))
			.setDsName(rs.getString("DS_NAME"))
			.setDescription(rs.getString("DESCRIPTION"))
			.setCameraUserName(rs.getString("CAMERA_USERNAME"))
			.setCameraPassword(rs.getString("CAMERA_PASSWORD"))
			.setCameraPort(rs.getString("CAMERA_PORT"))
			.setCameraLink(rs.getString("CAMERA_LINK"))
			.setCameraSnapshot(rs.getString("CAMERA_SNAPSHOT"))
			.setCreationDate(rs.getString("CREATION_DATE"));
		}

		return ds;
	}

	public static DataSource create(DataSource ds) throws Exception {

		String[] data = new String[12];

		data[0] = "NEXT VALUE FOR lambdaclovr.ds_id";
		data[1] = "" + ds.getUserId() + "";
		data[2] = "" + ds.getVendorModelId() + "";
		data[3] = "" + ds.getDsTypeId() + "";
		data[4] = "\'" + ds.getDsName() + "\'";
		data[5] = "\'" + ds.getDescription() + "\'";
		data[6] = "\'" + ds.getCameraUserName() + "\'";
		data[7] = "\'" + ds.getCameraPassword() + "\'";
		data[8] = "\'" + ds.getCameraPort() + "\'";
		data[9] = "\'" + ds.getCameraLink() + "\'";
		data[10] = "\'" + ds.getCameraSnapshot() + "\'";
		data[11] = "TO_DATE(\'" + ds.getCreationDate() + "\', \'yyyy-MM-dd\', \'GMT+9\')"; 

		operation.insertOrUdate("DATA_SOURCE", data);

		return ds;
	}

	public static DataSource update(DataSource ds) throws CustomeException, SQLException {

		DataSource dsInfoFromDataBase = get(ds.getDsId());
		if (dsInfoFromDataBase.getDsName().length() > 1) {

			String[] data = new String[12];

			data[0] = "" + dsInfoFromDataBase.getDsId();
			data[1] = "" + dsInfoFromDataBase.getUserId() + "";
			data[2] = "" + ds.getVendorModelId() + "";
			data[3] = "" + ds.getDsTypeId() + "";
			data[4] = "\'" + ds.getDsName() + "\'";
			data[5] = "\'" + ds.getDescription() + "\'";
			data[6] = "\'" + ds.getCameraUserName() + "\'";
			data[7] = "\'" + ds.getCameraPassword() + "\'";
			data[8] = "\'" + ds.getCameraPort() + "\'";
			data[9] = "\'" + ds.getCameraLink() + "\'";
			data[10] = "\'" + ds.getCameraSnapshot() + "\'";
			data[11] = "TO_DATE(\'" + ds.getCreationDate() + "\', \'yyyy-MM-dd\', \'GMT+9\')";

			operation.insertOrUdate("DATA_SOURCE", data);
	
		} else {
			throw new CustomeException("This Data Source is not created yet");
		}

		return ds;
	}

	public static ResultSet delete(int ds_id) {

		ResultSet numberOfDeletedRecords;
		// String ds_id = Integer.toString(id); //convert Integer to String
		String emailColumnName = "ds_id";
		numberOfDeletedRecords = operation.removeRow("DATA_SOURCE", emailColumnName, ds_id);
		return numberOfDeletedRecords;

	}
}
