package com.esorokin.justweather.models;

import android.support.annotation.IdRes;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Date: 30-Dec-15
 * Time: 18:55
 *
 * @author esorokin
 */
@SuppressWarnings("unused")
@Root(name = "FORECAST", strict = false)
public class Forecast implements Serializable
{
	private static final long serialVersionUID = 2400544621074989541L;

	@Attribute(name = "day")
	private int mDay;

	@Attribute(name = "month")
	private int mMonth;

	@Attribute(name = "year")
	private int mYear;

	@Attribute(name = "hour")
	private int mHour;

	@Attribute(name = "tod")
	private int mTod;

	@Attribute(name = "predict")
	private int mPredict;

	@Attribute(name = "weekday")
	private int mWeekday;

	@Element(name = "PHENOMENA")
	private Phenomena mPhenomena;

	@Element(name = "PRESSURE")
	private MinMaxParameter mPressure;

	@Element(name = "TEMPERATURE")
	private MinMaxParameter mTemperature;

	@Element(name = "WIND")
	private Wind mWind;

	@Element(name = "RELWET")
	private MinMaxParameter mRelwet;

	@Element(name = "HEAT")
	private MinMaxParameter mHeat;

	/**
	 * Need only in debug. Remove and replace to logic based on weather data.
	 */
	@IdRes
	private final int mDebugIconIndex;

	public Forecast()
	{
		//27 is debug count of images.
		mDebugIconIndex = new Random().nextInt(27);
	}

	public int getDebugIconIndex()
	{
		return mDebugIconIndex;
	}

	public int getDay()
	{
		return mDay;
	}

	public int getMonth()
	{
		return mMonth;
	}

	public int getYear()
	{
		return mYear;
	}

	public int getHour()
	{
		return mHour;
	}

	public int getTod()
	{
		return mTod;
	}

	public int getPredict()
	{
		return mPredict;
	}

	public int getWeekday()
	{
		return mWeekday;
	}

	public Phenomena getPhenomena()
	{
		return mPhenomena;
	}

	public MinMaxParameter getPressure()
	{
		return mPressure;
	}

	public MinMaxParameter getTemperature()
	{
		return mTemperature;
	}

	public Wind getWind()
	{
		return mWind;
	}

	public MinMaxParameter getRelwet()
	{
		return mRelwet;
	}

	public MinMaxParameter getHeat()
	{
		return mHeat;
	}

	public Date getDate()
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, mYear);
		calendar.set(Calendar.MONTH, mMonth);
		calendar.set(Calendar.DAY_OF_MONTH, mDay);
		calendar.set(Calendar.HOUR_OF_DAY, mHour);

		return calendar.getTime();
	}
}
