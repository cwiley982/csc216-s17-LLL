package edu.ncsu.csc216.pack_scheduler.user;

/**
 * An abstract class that holds the common information and methods for all users.
 * 
 * @author Sam Weninger
 * @author Claire Brown
 * @author Caitlyn Wiley
 */
public abstract class User {

	/** Student's first name */
	private String firstName;
	/** Student's last name */
	private String lastName;
	/** Student's id */
	private String id;
	/** Student's email */
	private String email;
	/** Student's password */
	private String password;

	/**
	 * The constructor of user, sets all the main fields of user.
	 * 
	 * @param firstName the first name to set
	 * @param lastName the last name to set
	 * @param id the id to set
	 * @param email the email to set
	 * @param password the password to set
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}


	/**
	 * Returns the Student's first name
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the student's first name
	 * @param firstName the first name to set
	 * @throws IllegalArgumentException if firstName is null or empty
	 */
	public void setFirstName(String firstName) {
		if(firstName == null || firstName.equals("")){
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}

	/**
	 * Returns the Student's last name
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the student's last name
	 * @param lastName the last name to set
	 * @throws IllegalArgumentException if lastName is null or empty
	 */
	public void setLastName(String lastName) {
		if(lastName == null || lastName.equals("")){
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	/**
	 * Returns the Student's id
	 * @return the id
	 */
	public String getId() {
	
		return id;
	}

	/**
	 * Sets the student's id
	 * @param id the id to set
	 * @throws IllegalArgumentException if id is null or empty
	 */
	protected void setId(String id) {
		if(id == null || id.equals("")){
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	/**
	 * Returns the Student's email
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Sets the student's email
	 * @param email the email to set
	 * @throws IllegalArgumentException if email is null, empty, does not contain '@' or '.', or if '.' appears before '@'
	 */
	public void setEmail(String email) {
		if(email == null || email.equals("") || email.indexOf('@') == -1 || email.indexOf('.') == -1 || email.lastIndexOf('.') < email.indexOf('@'))
		{
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
	}

	/**
	 * Returns the Student's password
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the student's password
	 * @param password the password to set
	 * @throws IllegalArgumentException if password is null or empty
	 */
	public void setPassword(String password) {
		if(password == null || password.equals("")){
			throw new IllegalArgumentException("Invalid password");
		} 
		this.password = password;
	}

}