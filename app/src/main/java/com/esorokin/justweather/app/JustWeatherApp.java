package com.esorokin.justweather.app;

import android.app.Application;

import com.esorokin.justweather.di.components.DaggerJustWeatherComponent;
import com.esorokin.justweather.di.components.JustWeatherComponent;

/**
 * Date: 11-Jan-16
 * Time: 14:19
 *
 * @author esorokin
 */
public class JustWeatherApp extends Application
{
	private static JustWeatherComponent mJustWeatherComponent;

	@Override
	public void onCreate()
	{
		super.onCreate();

		mJustWeatherComponent = DaggerJustWeatherComponent.create();
	}

	public static JustWeatherComponent getJustWeatherComponent()
	{
		return mJustWeatherComponent;
	}
}
