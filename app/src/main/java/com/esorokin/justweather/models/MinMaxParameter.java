package com.esorokin.justweather.models;

import org.simpleframework.xml.Attribute;

import java.io.Serializable;

/**
 * Date: 03.01.2016
 * Time: 21:36
 *
 * @author esorokin
 */
@SuppressWarnings("unused")
public class MinMaxParameter implements Serializable
{
	private static final long serialVersionUID = 5988756643060017245L;

	@Attribute(name = "max")
	private int mMaxValue;

	@Attribute(name = "min")
	private int mMinValue;

	public int getMaxValue()
	{
		return mMaxValue;
	}

	public int getMinValue()
	{
		return mMinValue;
	}

	public int getAverageValue()
	{
		return (mMinValue + mMaxValue) / 2;
	}
}
