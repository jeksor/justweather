package com.esorokin.justweather.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 30-Dec-15
 * Time: 19:51
 *
 * @author esorokin
 */
public class ForecastRespone implements Serializable
{
	private ArrayList<Forecast> mForecasts;

	public ForecastRespone(List<Forecast> forecasts)
	{
		mForecasts = new ArrayList<>(forecasts);
	}

	public ArrayList<Forecast> getForecasts()
	{
		return mForecasts;
	}

	public void setForecasts(ArrayList<Forecast> forecasts)
	{
		mForecasts = forecasts;
	}
}
