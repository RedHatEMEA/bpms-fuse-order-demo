package com.redhat.emea.example.bfod.model;

import java.util.Date;
import java.util.UUID;

/**
 * 
 * An offer available to be applied to an {@link Order} with a specific time
 * validity and discount percentage.
 * 
 * 
 */
public class Campaign
{

	/**
	 * The unique identifier of the campaign.
	 */
	private UUID id;

	/**
	 * The code associated with the campaign.
	 */
	private String code;

	/**
	 * The Friendly name of the campaign.
	 */
	private String name;

	/**
	 * The date the campaign validity starts from.
	 */
	private Date startDate;

	/**
	 * The date the campaign finishes.
	 */
	private Date endDate;

	/**
	 * The discount percentage the campaign applies to the order.
	 */
	private float discountPercentage;

	/**
	 * @return the id
	 */
	public UUID getId()
	{
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(UUID id)
	{
		this.id = id;
	}

	/**
	 * @return the code
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code)
	{
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate()
	{
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate()
	{
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	/**
	 * @return the discountPercentage
	 */
	public float getDiscountPercentage()
	{
		return discountPercentage;
	}

	/**
	 * @param discountPercentage
	 *            the discountPercentage to set
	 */
	public void setDiscountPercentage(float discountPercentage)
	{
		this.discountPercentage = discountPercentage;
	}

}
