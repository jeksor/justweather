package com.esorokin.justweather.di.modules;

import android.util.Log;

import com.esorokin.justweather.network.GismeteoApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

/**
 * Date: 11-Jan-16
 * Time: 16:36
 *
 * @author esorokin
 */
@Module(includes = NetworkModule.class)
public class GismeteoModule
{
	@Provides
	@Singleton
	public GismeteoApi provideGismeteoApi(RestAdapter restAdapter)
	{
		Log.d("count", "gismeteo instance");
		return restAdapter.create(GismeteoApi.class);
	}
}
