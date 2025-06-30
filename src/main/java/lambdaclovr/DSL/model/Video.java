package lambdaclovr.dsl.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Video")
@XmlAccessorType(XmlAccessType.FIELD)

public class Video {

	private int videoId = 0;
	private int dsId = 0;
	private String name = "";
	private int size = 0;
	private String format = "";
	private String tags = "";
	private String snapshot = "";
	private String creationDate = "";

	public int getVideoId() {
		return videoId;
	}

	public Video setVideoId(int videoId) {
		this.videoId = videoId;
		return this;
	}

	public int getDsId() {
		return dsId;
	}

	public Video setDsId(int dsId) {
		this.dsId = dsId;
		return this;
	}

	public String getName() {
		return name;
		
	}

	public Video setName(String name) {
		this.name = name;
		return this;
		
	}

	public int getSize() {
		return size;
	}

	public Video setSize(int size) {
		this.size = size;
		return this;
	}

	public String getFormat() {
		return format;
	}

	public Video setFormat(String format) {
		this.format = format;
		return this;
	}

	public String getTags() {
		return tags;
	}

	public Video setTags(String tags) {
		this.tags = tags;
		return this;
	}

	public String getSnapshot() {
		return snapshot;
	}

	public Video setSnapshot(String snapshot) {
		this.snapshot = snapshot;
		return this;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public Video setCreationDate(String createDate) {
		this.creationDate = createDate;
		return this;
	}
}
