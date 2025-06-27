package lambdaclovr.dsl.dao;

import java.sql.Timestamp;
import java.util.Random;

import lambdaclovr.dsl.model.Algorithm;
import lambdaclovr.dsl.model.IntermediateResult;

public class App {

	public static void main(String[] args) throws Exception {
		Random rand = new Random();
				
		int i = 0;	
		for (i=0; i<= 5000000; i++)
		{
			IntermediateResult  intermediateResult = new IntermediateResult()
			.setVideoId(Integer.toString(rand.nextInt(20)))
			.setName("objectName")
			.setPicture("PicName")
			.setDescription("Testing desc")
			.setStartFrame("12")
			.setEndFrame("34")
			.setPosition("66")
			.setCreationDate(new Timestamp(System.currentTimeMillis()).toString())
			.setAlgoId(Integer.toString(rand.nextInt(4)))
			.setFeatures("features");
			IntermediateResultDAO.create(intermediateResult);
		}		
		
		Algorithm algorithm = new Algorithm()
			.setName("VLBP")
			.setAlgorithmId("1")
			.setResources("CPU")
			.setCreationDate(new Timestamp(System.currentTimeMillis()).toString())
			.setInputTypeId("1")
			.setUserId("")
			.setScId("");
			
		lgorithmDAO.create(algorithm);
	}
}
