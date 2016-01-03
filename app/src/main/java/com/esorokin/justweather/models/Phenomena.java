package com.esorokin.justweather.models;

import org.simpleframework.xml.Attribute;

import java.io.Serializable;

/**
 * Date: 02.01.2016
 * Time: 14:09
 *
 * @author esorokin
 */
public class Phenomena implements Serializable
{
	private static final long serialVersionUID = 5664758072228142687L;

	@Attribute(name = "cloudiness")
	private int mCloudiness;

	@Attribute(name = "precipitation")
	private int mPrecipitation;

	@Attribute(name = "rpower")
	private int mRpower;

	@Attribute(name = "spower")
	private int mSpower;

	public Phenomena()
	{}

	public int getCloudiness()
	{
		return mCloudiness;
	}

	public void setCloudiness(int cloudiness)
	{
		mCloudiness = cloudiness;
	}

	public int getPrecipitation()
	{
		return mPrecipitation;
	}

	public void setPrecipitation(int precipitation)
	{
		mPrecipitation = precipitation;
	}

	public int getRpower()
	{
		return mRpower;
	}

	public void setRpower(int rpower)
	{
		mRpower = rpower;
	}

	public int getSpower()
	{
		return mSpower;
	}

	public void setSpower(int spower)
	{
		mSpower = spower;
	}
}
