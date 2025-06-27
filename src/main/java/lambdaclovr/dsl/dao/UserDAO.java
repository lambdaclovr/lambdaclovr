package lambdaclovr.dsl.dao;

import java.math.BigInteger;
import java.net.URI;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import lambdaclovr.dsl.exception.CustomeException;
import lambdaclovr.dsl.model.User;
import lambdaclovr.dsl.phoenix.Crud;

/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer</h2>
 * <h3>Package Name: lambdaclovr.dsl.dao</h3>
 * <h3>Class Name: UserDAO</h3>
 * <p>
 * 
 * @Project This file is part of LAMBDA-CLOVR Project.
 *          </p>
 * 
 *          <p>
 * @Description: This class is used to handle user related CRUD operations.
 *               </p>
 * 
 * @author Ali <ali@khu.ac.kr>
 * 
 * @version 1.0
 * @since 2024-May-05
 */

public class UserDAO {

	private static Crud operation = new Crud();

	/**
	 * Add a new user into lambdaclovr HBase/pheonix database.
	 * 
	 * @param User Object
	 * @return
	 */
	public static boolean create(User user) throws Exception {

		/*
		 * if(user.getEmail().length()<5 || user.getFirstName().length()<3
		 * ||user.getPassword().length()<4) { throw new
		 * CustomeException("Please provide the complete user information"); } //User
		 * existingUser=; if(
		 * getUser(user.getEmail()).getEmail().equals(user.getEmail())) { throw new
		 * CustomeException("This email is already used. Please try another email");
		 * 
		 * }
		 */
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] messageDigest = md.digest(user.getPassword().getBytes());
		BigInteger number = new BigInteger(1, messageDigest);
		String encrypted_password = number.toString(16);

		String[] data = new String[12];

		data[0] = "NEXT VALUE FOR lambdaclovr.user_id";
		data[1] = "\'" + user.getUserName() + "\'";
		data[2] = "\'" + user.getFirstName() + "\'";
		data[3] = "\'" + user.getLastName() + "\'";
		data[4] = "\'" + encrypted_password + "\'";
		data[5] = "\'" + user.getEmail() + "\'";
		data[6] = "" + user.getUserRole() + "";
		data[7] = "TO_DATE(\'" + user.getDateOfBirth() + "\', \'yyyy-MM-dd\', \'GMT+9\')";
		data[8] = "" + user.getGender() + "";
		data[9] = "\'" + user.getDisplayPicture() + "\'";
		data[10] = "TO_DATE(\'" + user.getCreationDate() + "\', \'yyyy-MM-dd\', \'GMT+9\')";
		data[11] = "" + user.getRememberMe() + "";
		operation.insertOrUdate("USER", data);

		// create a folder for user in HDFS. The folder name will be userID
		// get the ID of the just created user to create a folder with such a name

		BigInteger userId = new BigInteger(get(user.getEmail()).getUserId() + "");
		Configuration conf = new Configuration();
		// conf.set("user", "user");
		// conf.set("password", "1234");
		// System.setProperty("HADOOP_USER_NAME", "root");
		// conf.addResource(new Path(System.getProperty("user.dir") +
		// "\\core-site.xml"));
		// conf.addResource(new Path(System.getProperty("user.dir") +
		// "\\hdfs-site.xml"));

//		try {
//			FileSystem fs = FileSystem.get(new URI("hdfs://163.180.116.101:8020/lambdaclovr/"), conf);
//
//			Path src = new Path(fs.getWorkingDirectory() + "/" + userId);
//
//			fs.mkdirs(src);
//
//			FileStatus[] fileStatus = fs.listStatus(new Path("hdfs://163.180.116.101:8020/"));
//			for (FileStatus status : fileStatus) {
//				System.out.println(status.getPath().toString());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		return true;
	}
	
	//////////////////////////////////////////////////////////////
	
	/**
	 * User login
	 * @param email
	 * @param password
	 * @return User Object
	 * @throws Exception
	 */
	public static User login(String email, String password) throws Exception {

		User user = new User();
		user = UserDAO.get(email);
		// encrypt the password provided by the user in order to compare
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] messageDigest = md.digest(password.getBytes());
		BigInteger number = new BigInteger(1, messageDigest);
		String encrypted_password = number.toString(16);
		System.out.println("encrypted_password  " + encrypted_password);
		System.out.println("user Password is  " + user.getPassword());

		
		if (user.getFirstName().length() > 1) {

			if (user.getPassword().equals(encrypted_password)) {
				System.out.println("Logged in successfully.");
				
			} else {
				throw new CustomeException("Incorrect email or password. Please provide corect information.");
			}
		} else {
			throw new CustomeException("Incorrect email or password. Please provide corect information.");
		}
		return user;
	}

	
	//////////////////////////////////////////////////////////////

	/**
	 * get a User Object
	 * 
	 * @param email
	 * @return User Object
	 * @throws SQLException
	 */
	public static User get(String email) throws SQLException {

		String emailColumnName = "email";

		User user = new User();

		ResultSet rs = operation.getRow("USER", emailColumnName, email);

		while (rs.next()) {
			user.setUserId(rs.getString("USER_ID"))
				.setUserName(rs.getString("USER_NAME"))
				.setFirstName(rs.getString("FIRST_NAME")) 
				.setLastName(rs.getString("LAST_NAME"))
				.setPassword(rs.getString("PASSWORD"))
				.setEmail(rs.getString("EMAIL"))
				.setUserRole(rs.getInt("USER_ROLE"))
				.setDateOfBirth(rs.getString("DATE_OF_BIRTH"))
				.setGender(rs.getInt("GENDER"))
				.setDisplayPicture(rs.getString("DISPLAY_PICTURE")) 
				.setCreationDate(rs.getString("CREATION_DATE"))
				.setRememberMe(rs.getBoolean("REMEMBER_ME"));
		}

		return user;
	}

	//////////////////////////////////////////////////////////////
	
	/**
	 * Update a user information into LAMBDA-CLOVR database.
	 * 
	 * @param User Object
	 * @return Updated User Object 
	 * @throws CustomeException
	 * @throws SQLException
	 */
	public static boolean update(User user) throws CustomeException, SQLException {

		if (user.getEmail().length() < 5 || user.getFirstName().length() < 3 || user.getPassword().length() < 4) {
			throw new CustomeException("Please provide complete user information");
		}

		User userInfoFromDataBase = get(user.getEmail());
		if (userInfoFromDataBase.getFirstName().length() > 1) {

			String[] data = new String[12];

			data[0] = "" + userInfoFromDataBase.getUserId();
			data[1] = "\'" + user.getUserName() + "\'";
			data[2] = "\'" + user.getFirstName() + "\'";
			data[3] = "\'" + user.getLastName() + "\'";
			data[4] = "\'" + userInfoFromDataBase.getPassword() + "\'"; // Password should be Updated separately for
																		// security reasons
			data[5] = "\'" + userInfoFromDataBase.getEmail() + "\'"; // Email should be handled separately since it
																		// should be Unique
			data[6] = "" + userInfoFromDataBase.getUserRole() + ""; // user Role should be handled separately since it
																	// should be authorized by admin.

			data[7] = "TO_DATE(\'" + user.getDateOfBirth() + "\', \'yyyy-MM-dd\', \'GMT+9\')";
			data[8] = "" + user.getGender() + "";
			data[9] = "\'" + user.getDisplayPicture() + "\'";
			data[10] = "TO_DATE(\'" + user.getCreationDate() + "\', \'yyyy-MM-dd\', \'GMT+9\')";
			data[11] = "" + user.getRememberMe() + "";

			operation.insertOrUdate("USER", data);
		
		} else {
			throw new CustomeException("This user is not registered yet");
		}

		return true;
	}

	//////////////////////////////////////////////////////////////
	
	/**
	 * get a list of All users. 
	 * @return List of Users
	 * @throws SQLException
	 */
	public static List<User> getAll() throws SQLException {

		ResultSet rs = operation.getAllRows("USER");
		List<User> list = new ArrayList<User>();

		while (rs.next()) {
			
			User user = new User()
					.setUserId(rs.getString("USER_ID"))
					.setUserName(rs.getString("USER_NAME"))
					.setFirstName(rs.getString("FIRST_NAME")) 
					.setLastName(rs.getString("LAST_NAME"))
					.setPassword(rs.getString("PASSWORD"))
					.setEmail(rs.getString("EMAIL"))
					.setUserRole(rs.getInt("USER_ROLE"))
					.setDateOfBirth(rs.getString("DATE_OF_BIRTH"))
					.setGender(rs.getInt("GENDER"))
					.setDisplayPicture(rs.getString("DISPLAY_PICTURE")) 
					.setCreationDate(rs.getString("CREATION_DATE"))
					.setRememberMe(rs.getBoolean("REMEMBER_ME"));

			list.add(user);
		}

		return list;
	}
}
