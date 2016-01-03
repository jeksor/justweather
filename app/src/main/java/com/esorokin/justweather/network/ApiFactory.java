package com.esorokin.justweather.network;

import android.support.annotation.NonNull;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.squareup.okhttp.OkHttpClient;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.converter.SimpleXMLConverter;

/**
 * Date: 02.01.2016
 * Time: 13:33
 *
 * @author esorokin
 */
public class ApiFactory
{
	public static final String BASE_URL = "http://informer.gismeteo.ru/";

	private static final int TIMEOUT = 15;
	private static final int WRITE_TIMEOUT = 20;
	private static final int CONNECT_TIMEOUT = 10;

	/*@formatter:off*/
	private static final String[] DATE_FORMATS = new String[]
		{
			"EEE, dd MMM yyyy hh:mm:ss zzz",
			"yyyy-MM-dd'T'HH:mm:ss"
		};
	/*@formatter:on*/

	private static final OkHttpClient CLIENT = new OkHttpClient();

	static
	{
		CLIENT.setConnectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
		CLIENT.setWriteTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
		CLIENT.setReadTimeout(TIMEOUT, TimeUnit.SECONDS);
	}

	/*@formatter:off*/
	/*private static final Gson GSON = new GsonBuilder()
			.registerTypeAdapter(Date.class, new DateDeserializer())
			.serializeSpecialFloatingPointValues()
			.create();*/
	/*@formatter:on*/

	/*@formatter:on*/
	private static final RestAdapter sRestAdapter =
			new RestAdapter.Builder()
					.setEndpoint(BASE_URL)
					.setClient(new OkClient(CLIENT))
					//.setConverter(new GsonConverter(GSON))
					.setConverter(new SimpleXMLConverter())
					.setErrorHandler(new ErrorHandler()
					{
						@Override
						public Throwable handleError(RetrofitError cause)
						{
							return new Exception(cause);
						}
					})
					.setLogLevel(RestAdapter.LogLevel.FULL)
					.build();
	/*@formatter:on*/

	private ApiFactory()
	{/*not instance*/}

	@NonNull
	public static <S> S createService(final Class<S> serviceClass)
	{
		return sRestAdapter.create(serviceClass);
	}

	private static class DateDeserializer implements JsonDeserializer<Date>
	{
		@Override
		public Date deserialize(JsonElement jsonElement, Type typeOF, JsonDeserializationContext context) throws JsonParseException
		{
			for (String format : DATE_FORMATS)
			{
				try
				{
					return new SimpleDateFormat(format, Locale.US).parse(jsonElement.getAsString());
				}
				catch (ParseException e)
				{
					// using high level abstraction exception
				}
			}
			throw new JsonParseException("Unparseable date: \"" + jsonElement.getAsString() + "\". Supported formats: " + Arrays.toString(DATE_FORMATS));
		}
	}
}
