package com.esorokin.justweather.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * Date: 30-Dec-15
 * Time: 18:55
 *
 * @author esorokin
 */
@Root(name = "FORECAST", strict = false)
public class Forecast implements Serializable
{
	private static final long serialVersionUID = 2400544621074989541L;

	/*
	*   <FORECAST day="02" month="01" year="2016" hour="15" tod="2" predict="0" weekday="7">
		<PHENOMENA cloudiness="3" precipitation="10" rpower="0" spower="0"/>
		<PRESSURE max="762" min="760"/>
		<TEMPERATURE max="-26" min="-24"/>
		<WIND min="3" max="5" direction="0"/>
		<RELWET max="75" min="73"/>
		<HEAT min="-32" max="-30"/>
		</FORECAST>
*/

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

	public Forecast()
	{}

	public int getDay()
	{
		return mDay;
	}

	public void setDay(int day)
	{
		mDay = day;
	}

	public int getMonth()
	{
		return mMonth;
	}

	public void setMonth(int month)
	{
		mMonth = month;
	}

	public int getYear()
	{
		return mYear;
	}

	public void setYear(int year)
	{
		mYear = year;
	}

	public int getHour()
	{
		return mHour;
	}

	public void setHour(int hour)
	{
		mHour = hour;
	}

	public int getTod()
	{
		return mTod;
	}

	public void setTod(int tod)
	{
		mTod = tod;
	}

	public int getPredict()
	{
		return mPredict;
	}

	public void setPredict(int predict)
	{
		mPredict = predict;
	}

	public int getWeekday()
	{
		return mWeekday;
	}

	public void setWeekday(int weekday)
	{
		mWeekday = weekday;
	}

	public Phenomena getPhenomena()
	{
		return mPhenomena;
	}

	public void setPhenomena(Phenomena phenomena)
	{
		mPhenomena = phenomena;
	}
}
