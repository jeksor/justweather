package com.esorokin.justweather.network;

import com.esorokin.justweather.models.ForecastResponse;

import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Date: 02.01.2016
 * Time: 13:33
 *
 * @author esorokin
 */
public interface GismeteoApi
{
	String NODOSIBIRSK_ID = "29634_1";

	@GET("/xml/{city_id}.xml")
	ForecastResponse getForecasts(@Path("city_id") String cityId);
}
