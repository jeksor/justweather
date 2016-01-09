package com.esorokin.justweather.models;

import org.simpleframework.xml.Attribute;

/**
 * Date: 03.01.2016
 * Time: 21:38
 *
 * @author esorokin
 */
@SuppressWarnings("unused")
public class Wind extends MinMaxParameter
{
	private static final long serialVersionUID = 310989337008624005L;

	@Attribute(name = "direction")
	private int mDirection;

	public int getDirection()
	{
		return mDirection;
	}
}
