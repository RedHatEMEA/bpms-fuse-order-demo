package com.redhat.emea.example.bfod.model;

import java.util.List;

/**
 * 
 * Represents an Order to be processed.
 * 
 */
public class Order
{

	/**
	 * The unique id associated with and Order.
	 */
	private String id;
	/**
	 * The campaign to be applied to an Order.
	 */
	private Campaign campaign;

	/**
	 * The end customer information for the order.
	 */
	private Profile profile;

	/**
	 * A list of the items ordered
	 */
	private List<CatalogueItem> catalogueItems;

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
	 * @return the campaign
	 */
	public Campaign getCampaign()
	{
		return campaign;
	}

	/**
	 * @param campaign
	 *            the campaign to set
	 */
	public void setCampaign(Campaign campaign)
	{
		this.campaign = campaign;
	}

	/**
	 * @return the profile
	 */
	public Profile getProfile()
	{
		return profile;
	}

	/**
	 * @param profile
	 *            the profile to set
	 */
	public void setProfile(Profile profile)
	{
		this.profile = profile;
	}

	/**
	 * @return the catalogueItems
	 */
	public List<CatalogueItem> getCatalogueItems()
	{
		return catalogueItems;
	}

	/**
	 * @param catalogueItems
	 *            the catalogueItems to set
	 */
	public void setCatalogueItems(List<CatalogueItem> catalogueItems)
	{
		this.catalogueItems = catalogueItems;
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
		builder.append("Order [id=");
		builder.append(id);
		builder.append(", campaign=");
		builder.append(campaign);
		builder.append(", profile=");
		builder.append(profile);
		builder.append(", catalogueItems=");
		builder.append(catalogueItems);
		builder.append("]");
		return builder.toString();
	}

}
