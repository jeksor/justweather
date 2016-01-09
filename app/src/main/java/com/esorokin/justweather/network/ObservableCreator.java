package com.esorokin.justweather.network;

import android.support.annotation.NonNull;

import com.esorokin.justweather.models.Forecast;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

/**
 * Date: 02.01.2016
 * Time: 14:18
 *
 * @author esorokin
 */
public class ObservableCreator
{
	public static Observable.OnSubscribe<List<Forecast>> getForecastsFor(@NonNull final String locationId)
	{
		return new Observable.OnSubscribe<List<Forecast>>()
		{
			@Override
			public void call(Subscriber<? super List<Forecast>> subscriber)
			{
				try
				{
					TimeUnit.SECONDS.sleep(5);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
					subscriber.onError(e);
					return;
				}

				try
				{
					subscriber.onNext(ApiFactory.createService(GismeteoApi.class).getForecasts(locationId).getForecasts());
				}
				catch (Throwable e)
				{
					subscriber.onError(e.getCause());
					return;
				}

				subscriber.onCompleted();
			}
		};
	}
}
