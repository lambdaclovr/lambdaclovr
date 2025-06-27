package lambdaclovr.dsl.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)

public class User {
	private int userId = 0;
	private String userName = "";
	private String firstName = "";
	private String lastName = "";
	private String password = "";
	private String email = "";
	private int userRole = 1; // 1 = User
	private String dateOfBirth;
	private int gender = 0;
	private String displayPicture = "";
	private String creationDate;
	private Boolean rememberMe = false;

	// This default constructor is required if there are other constructors.
	public User() {

	}

	public User(String firstName, String lastName, String password, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
	}

	public int getUserId() {
		return userId;
	}

	public User setUserId(int userId) {
		this.userId = userId;
		return this;
	}

	public User setUserId(String userId) {
		this.userId = Integer.parseInt(userId);
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public User setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public User setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public User setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	public int getUserRole() {
		return userRole;
	}

	public User setUserRole(int userRole) {
		this.userRole = userRole;
		return this;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public User setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
		return this;
	}

	public int getGender() {
		return gender;
	}

	public User setGender(int gender) {
		this.gender = gender;
		return this;
	}

	public String getDisplayPicture() {
		return displayPicture;
	}

	public User setDisplayPicture(String displayPicture) {
		this.displayPicture = displayPicture;
		return this;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public User setCreationDate(String creationDate) {
		this.creationDate = creationDate;
		return this;
	}

	public Boolean getRememberMe() {
		return rememberMe;
	}

	public User setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
		return this;
	}
}
