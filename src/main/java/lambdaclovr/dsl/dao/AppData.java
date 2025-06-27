package lambdaclovr.dsl.dao;

import java.sql.Timestamp;

import lambdaclovr.dsl.model.Anomaly;
import lambdaclovr.dsl.phoenix.ModelHandler;

public class AppData {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		/*
		 * //Try Create Vendor Vendor vendor = new Vendor();
		 * vendor.setVendorName("IPhone"); try { VendorDAO.create(vendor); } catch
		 * (Exception e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 * 
		 * //Try getAll Vendors try { VendorDAO.getAll(); } catch (SQLException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 * 
		 * //Try update Vendor Function Vendor vendor = new Vendor();
		 * vendor.setVendorId("1"); vendor.setVendorName("Axis Communications");
		 * 
		 * try { VendorDAO.update(vendor); } catch (Exception e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * 
		 * 
		 * //Try Create Vendor Model VendorModel vendorModel = new
		 * VendorModel().setVendorModelId("101").setVendorId("1").
		 * setCameraModel("Updated Success").setDescription("This is testing");
		 * 
		 * try { VendorModelDAO.update(vendorModel); } catch (Exception e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * 
		 * 
		 * //Try getAll Vendors try { VendorModelDAO.get(1); } catch (SQLException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); }
		 * 
		 * 
		 * 
		 * 
		 * //Try getAll Vendors try { VendorDAO.getAll(); } catch (SQLException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 * 
		 * 
		 * // Try Create Feature Feature feature = new Feature(); feature
		 * .setVideoId("1") .setFeatureName("featureName")
		 * .setFeatureVector("featureVector");
		 * 
		 * try { FeatureDAO.create(feature); } catch (Exception e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * 
		 * // Delete a feature FeatureDAO.delete(1);
		 */

		// Try getAll Vendors
		// try {
		// FeatureDAO.get(101);
		// } catch (SQLException e){
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// Try Create new ServiceSubscription
		// ServiceSubscription serviceSubscription = new ServiceSubscription();
		// serviceSubscription.setServiceId("101").setDsId("201").setStatus("1");
		//
		// try {
		// ServiceSubscriptionDAO.create(serviceSubscription);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// ServiceSubscription serviceSubscription = new ServiceSubscription();
		// serviceSubscription.setServiceId("301").setDsId("101");
		// System.out.println(serviceSubscription.getServiceId());
		// System.out.println(serviceSubscription.getDsId());
		//
		// ServiceSubscriptionDAO.get(serviceSubscription.getDsId(),
		// serviceSubscription.getServiceId());

		// Result Table Testing
		// Result result = new Result()
		// .setVideoId("1")
		// .setsSId("1")
		// .setdSId("4")
		//
		// .setName("name")
		// .setPicture("Picture")
		// .setDescription("Description")
		//
		// .setStartFrame("1")
		// .setEndFrame("3")
		// .setPosition("23");
		//
		// ResultDAO.create(result);

		// SchemaHandler Sh = new SchemaHandler();
		// Sh.createSchema("LAMBDA-CLOVR2");

		ModelHandler mH = new ModelHandler();
		//mH.createIntermediateResulTable(); //Create features table
		// mH.createUserTable();
		// mH.createDataSourceTable();
		// mH.createSequence("LAMBDA-CLOVR", "user_pk");

		//mH.deploySiatSchema();

		// mH.createServiceSubscriptionTable();

		// mH.dropSiatSchema();
		// mH.createSourceCodeTable();
		// mH.dropTable("LAMBDA-CLOVR", "ALGORITHM", "algo_id"); //9
		// mH.createAlgorithmTable();

		// Algorithm algorithm = new Algorithm()
		// .setAlgoId("1")
		// .setUserId("1")
		// .setInputTypeId("1")
		// .setScId("1")
		//
		// .setName("AlgoName")
		// .setAlgorithmId("AlgoId")
		// .setResources("Resources");
		// //.setCreationDate("CreationDate");
		//
		// AlgorithmDAO.create(algorithm);
		//
		// AlgorithmDAO.get(1);
		// System.out.println(algorithm.getAlgoId() + " - " + algorithm.getName() );

//		User user = new User();
//		user.setUserName("Numan Khan");
//		user.setFirstName("Numan");
//		user.setLastName("Khan");
//		user.setPassword("880661");
//		user.setEmail("Khan@g.com");
//		user.setUserRole(1);
//		user.setDateOfBirth("");
//		user.setGender(1);
//		user.setDisplayPicture("");
//		user.setCreationDate("");
//		user.setRememberMe(true);
//
//		UserDAO.create(user);
//		List<User> listUsers = UserDAO.getAll();
//
//		for (int i = 0; i < listUsers.size(); i++) {
//			System.out.println(listUsers.get(i).getUserId());
//			System.out.println(listUsers.get(i).getUserName());
//		}

		// ModelHandler mh = new ModelHandler();
		// mh.createVideoTable();
		
//		List<Vendor> list = VendorDAO.getAll();
//
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i).getVendorId());
//			System.out.println(list.get(i).getVendorName());
//		}
		
		//mH.deploySiatSchema();		
		//mH.dropTable("LAMBDA-CLOVR", "ANOMALY", "anomaly_id");
		//mH.createAnomaly();
		
//		Anomaly anomaly = new Anomaly()
//				.setServiceId("1")
//				.setDsId("1")
//				.setTopicName("Alert_Topic")
//				.setTitle("Title")
//				.setTitle("Motion Detected")
//				.setMessage("Motion Detected Message")
//				.setTimestamp(new Timestamp(System.currentTimeMillis()).toString())
//				.setCols(50)
//				.setRows(50)
//				.setType(16)
//				.setData(new byte[12])
//				.setTimestamp(new Timestamp(System.currentTimeMillis()).toString())
//				.setStartFrameTimestamp(new Timestamp(System.currentTimeMillis()).toString())
//				.setEndFrameTimestamp(new Timestamp(System.currentTimeMillis()).toString())
//				.setPosition("position")
//				.setCreationDate(new Timestamp(System.currentTimeMillis()).toString());
//		
//		AnomalyDAO.create(anomaly);
		
		

	}
}
