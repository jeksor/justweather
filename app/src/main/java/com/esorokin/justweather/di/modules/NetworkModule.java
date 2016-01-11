package com.esorokin.justweather.di.modules;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.converter.Converter;
import retrofit.converter.SimpleXMLConverter;

/**
 * Date: 10-Jan-16
 * Time: 15:40
 *
 * @author esorokin
 */
@Module
public class NetworkModule
{
	public static final String BASE_URL = "http://informer.gismeteo.ru/";

	private static final int TIMEOUT = 15;
	private static final int WRITE_TIMEOUT = 20;
	private static final int CONNECT_TIMEOUT = 10;

	@Provides
	@Singleton
	OkHttpClient provideOkHttpClient()
	{
		OkHttpClient client = new OkHttpClient();
		client.setConnectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
		client.setWriteTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
		client.setReadTimeout(TIMEOUT, TimeUnit.SECONDS);

		return client;
	}

	@Provides
	@Singleton
	Converter provideConverter()
	{
		return new SimpleXMLConverter();
	}

	@Provides
	@Singleton
	ErrorHandler provideErrorHandler()
	{
		return new ErrorHandler()
		{
			@Override
			public Throwable handleError(RetrofitError cause)
			{
				return new Exception(cause);
			}
		};
	}

	@Provides
	@Singleton
	RestAdapter provideRetrofit(OkHttpClient okHttpClient, Converter converter, ErrorHandler errorHandler)
	{
		return new RestAdapter.Builder()
				.setEndpoint(BASE_URL)
				.setClient(new OkClient(okHttpClient))
				.setConverter(converter)
				.setErrorHandler(errorHandler)
				.setLogLevel(RestAdapter.LogLevel.FULL)
				.build();
	}
}
