package com.redhat.emea.example.bfod.model;

import java.util.Date;

/**
 * 
 * The profile of a user placing an order.
 * 
 */
public class Profile
{
	/**
	 * The unique ID associated with a profile.
	 */
	private String id;
	private String firstName;
	private String lastName;
	private String honorificPrefix;
	private String gender;
	private Date birthday;

	/**
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * @return the honorificPrefix
	 */
	public String getHonorificPrefix()
	{
		return honorificPrefix;
	}

	/**
	 * @param honorificPrefix
	 *            the honorificPrefix to set
	 */
	public void setHonorificPrefix(String honorificPrefix)
	{
		this.honorificPrefix = honorificPrefix;
	}

	/**
	 * @return the gender
	 */
	public String getGender()
	{
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender)
	{
		this.gender = gender;
	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday()
	{
		return birthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(Date birthday)
	{
		this.birthday = birthday;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Profile [id=");
		builder.append(id);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", honorificPrefix=");
		builder.append(honorificPrefix);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", birthday=");
		builder.append(birthday);
		builder.append("]");
		return builder.toString();
	}

}
