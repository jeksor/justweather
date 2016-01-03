package com.esorokin.justweather.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Date: 02.01.2016
 * Time: 13:35
 *
 * @author esorokin
 */
@Root(name = "MMWEATHER", strict = false)
public class ForecastResponse
{
	@Element(name = "REPORT")
	private Report mReport;

	public List<Forecast> getForecasts()
	{
		if (mReport != null && mReport.town != null && mReport.town.forecasts != null)
		{
			return mReport.town.forecasts;
		}
		else
		{
			return null;
		}
	}

	@Root(strict = false)
	private static class Report
	{
		@Element(name = "TOWN")
		Town town;
	}

	@Root(strict = false)
	private static class Town
	{
		@ElementList(inline = true)
		List<Forecast> forecasts;
	}
}
