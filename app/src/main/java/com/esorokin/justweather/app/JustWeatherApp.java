package com.esorokin.justweather.app;

import android.app.Application;

import com.esorokin.justweather.di.components.DaggerJustWeatherComponent;
import com.esorokin.justweather.di.components.JustWeatherComponent;
import com.esorokin.justweather.di.modules.AppModule;
import com.esorokin.justweather.di.modules.GismeteoModule;
import com.esorokin.justweather.di.modules.NetworkModule;

/**
 * Date: 10-Jan-16
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

		/*@formatter:off*/
		mJustWeatherComponent = DaggerJustWeatherComponent.builder()
				.appModule(new AppModule(this))
				.networkModule(new NetworkModule())
				.gismeteoModule(new GismeteoModule())
				.build();
		/*@formatter:on*/
	}

	public static JustWeatherComponent getJustWeatherComponent()
	{
		return mJustWeatherComponent;
	}
}
