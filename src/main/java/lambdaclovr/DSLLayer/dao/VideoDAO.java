package lambdaclovr.dsl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lambdaclovr.dsl.model.Video;
import lambdaclovr.dsl.phoenix.Crud;

/**
 *
 * @author Anwar Abir
 */

public class VideoDAO {

    private static Crud operation = new Crud();
    		
    
    /**
     * This function will return a video from a given
     * @author Anwar Abir
     * @param video_id
     * 
     * @return Video
     * @throws SQLException
     */
    public static Video get(int videoId) throws SQLException {

        String colmnName = "video_id";
        ResultSet rs = operation.getRow("VIDEO", colmnName, videoId);
        Video video = new Video();

        while (rs.next()) {
            video.setVideoId(Integer.parseInt(rs.getString("VIDEO_ID")))
	            .setDsId(Integer.parseInt(rs.getString("DS_ID")))
	            .setName(rs.getString("NAME"))
	            .setSize(Integer.parseInt(rs.getString("SIZE")))
	            .setFormat(rs.getString("FORMAT"))
	            .setTags(rs.getString("TAGS"))
	            .setSnapshot(rs.getString("SNAPSHOT"))
	            .setCreationDate(rs.getString("CREATION_DATE"));
        }

        return video;
    }

    /**
     * This function will return all the video for a given data source
     *
     * @param (int) video_id
     * @return Video
     * @throws SQLException
     */
    public static List<Video> getDsVideos(int dsId) throws SQLException {

        List<Video> vList = new ArrayList<>();

        String colmnName = "ds_id";
        ResultSet rs = operation.getRow("VIDEO", colmnName, dsId);

        while (rs.next()) {
            Video video = new Video()
            		.setVideoId(Integer.parseInt(rs.getString("VIDEO_ID")))
		            .setDsId(Integer.parseInt(rs.getString("DS_ID")))
		            .setName(rs.getString("NAME"))
		            .setSize(Integer.parseInt(rs.getString("SIZE")))
		            .setFormat(rs.getString("FORMAT"))
		            .setTags(rs.getString("TAGS"))
		            .setSnapshot(rs.getString("SNAPSHOT"))
		            .setCreationDate(rs.getString("CREATION_DATE"));

            vList.add(video);
        }

        return vList;
    }

    /**
     * This method provide all the video in our existing database
     *
     * @return vList list of all the videos for admin
     * @throws Exception
     */
    public static List<Video> getAll() throws Exception {

        List<Video> vList = new ArrayList<>();

        ResultSet rs = operation.getAllRows("VIDEO");

        while (rs.next()) {
            Video video = new Video()
            		.setVideoId(Integer.parseInt(rs.getString("VIDEO_ID")))
    	            .setDsId(Integer.parseInt(rs.getString("DS_ID")))
    	            .setName(rs.getString("NAME"))
    	            .setSize(Integer.parseInt(rs.getString("SIZE")))
    	            .setFormat(rs.getString("FORMAT"))
    	            .setTags(rs.getString("TAGS"))
    	            .setSnapshot(rs.getString("SNAPSHOT"))
    	            .setCreationDate(rs.getString("CREATION_DATE"));

            vList.add(video);
        }

        return vList;

    }

    /**
     *
     * Insert a new video into database
     *
     * @param video
     * @return boolean
     * @throws SQLException
     */
    public static boolean create(Video video) throws SQLException {

        String[] data = new String[8];

        data[0] = "NEXT VALUE FOR lambdaclovr.video_id";
        data[1] = "" + video.getDsId() + "";
        data[2] = "\'" + video.getName() + "\'";
        data[3] = "" + video.getSize() + "";
        data[4] = "\'" + video.getFormat() + "\'";
        data[5] = "\'" + video.getTags() + "\'";
        data[6] = "\'" + video.getSnapshot() + "\'";
        data[7] = "TO_DATE(\'" + video.getCreationDate() + "\', \'yyyy-MM-dd\', \'GMT+9\')";

        operation.insertOrUdate("VIDEO", data);

        return true;
    }

    /**
     *
     * Update the video information into database
     *
     * @param video
     * @return boolean
     * @throws SQLException
     */
    public static Video update(Video video) throws SQLException {

    	 String[] data = new String[8];

    	 data[0] = "" + video.getVideoId() + "";
         data[1] = "" + video.getDsId() + "";
         data[2] = "\'" + video.getName() + "\'";
         data[3] = "" + video.getSize() + "";
         data[4] = "\'" + video.getFormat() + "\'";
         data[5] = "\'" + video.getTags() + "\'";
         data[6] = "\'" + video.getSnapshot() + "\'";
         data[7] = "TO_DATE(\'" + video.getCreationDate() + "\', \'yyyy-MM-dd\', \'GMT+9\')";

        operation.insertOrUdate("VIDEO", data);

        return video;
    }

    /**
     * Remove a video entry from the LAMBDA-CLOVR database;
     *
     * @param email
     */
    public static ResultSet delete(int videoId) {
        ResultSet numberOfDeletedRecords;
        String columnName = "video_id";
        numberOfDeletedRecords = operation.removeRow("VIDEO", columnName, videoId);
        return numberOfDeletedRecords;
    }

}
