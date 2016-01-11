package com.esorokin.justweather.presenters;

import android.support.annotation.NonNull;

import com.esorokin.justweather.app.JustWeatherApp;
import com.esorokin.justweather.models.Forecast;
import com.esorokin.justweather.network.GismeteoApi;
import com.esorokin.justweather.presenters.base.BasePresenter;
import com.esorokin.justweather.ui.MainMvpView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Date: 30-Dec-15
 * Time: 12:18
 *
 * @author esorokin
 */
@SuppressWarnings("ConstantConditions")
public class MainPresenter extends BasePresenter<MainMvpView>
{
	@Inject GismeteoApi mGismeteoApi;

	public MainPresenter()
	{
		JustWeatherApp.getJustWeatherComponent().inject(this);
	}

	public void loadForecasts(final boolean pullOnRefresh)
	{
		getView().showLoading(pullOnRefresh);

		String forecastLocation = GismeteoApi.NODOSIBIRSK_ID;

		/*@formatter:off*/
		Observable.create(getForecastsFor(forecastLocation))
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Observer<List<Forecast>>()
                {
                    @Override
                    public void onCompleted()
                    {
                        //ok, completed
                    }

                    @Override
                    public void onError(Throwable e)
                    {
	                    if (isViewAttached())
	                    {
		                    getView().showError(e, pullOnRefresh);
	                    }
                    }

                    @Override
                    public void onNext(List<Forecast> forecasts)
                    {
	                    if (isViewAttached())
	                    {
		                    getView().setData(new ArrayList<>(forecasts));
		                    getView().showContent();
	                    }
                    }
                }
		/*@formatter:on*/);
	}

	private Observable.OnSubscribe<List<Forecast>> getForecastsFor(@NonNull final String locationId)
	{
		return new Observable.OnSubscribe<List<Forecast>>()
		{
			@Override
			public void call(Subscriber<? super List<Forecast>> subscriber)
			{
				try
				{
					subscriber.onNext(mGismeteoApi.getForecasts(locationId).getForecasts());
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
