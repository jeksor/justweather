package com.esorokin.justweather.di.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Date: 11-Jan-16
 * Time: 19:35
 *
 * @author esorokin
 */
@Module
public class AppModule
{
	private Application mApp;

	public AppModule(Application app)
	{
		mApp = app;
	}

	@Provides
	@Singleton
	public Application provideApplication()
	{
		return mApp;
	}
}
