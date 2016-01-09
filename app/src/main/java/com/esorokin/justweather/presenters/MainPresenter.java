package com.esorokin.justweather.presenters;

import com.esorokin.justweather.models.Forecast;
import com.esorokin.justweather.network.GismeteoApi;
import com.esorokin.justweather.network.ObservableCreator;
import com.esorokin.justweather.presenters.base.BasePresenter;
import com.esorokin.justweather.ui.MainMvpView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
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
	private static MainPresenter sInstance;

	public MainPresenter()
	{/*singleton*/}

	public static MainPresenter getInstance()
	{
		if (sInstance == null)
		{
			synchronized (MainPresenter.class)
			{
				if (sInstance == null)
				{
					sInstance = new MainPresenter();
				}
			}
		}

		return sInstance;
	}

	public void loadForecasts(final boolean pullOnRefresh)
	{
		getView().showLoading(pullOnRefresh);

		String forecastLocation = GismeteoApi.NODOSIBIRSK_ID;

		/*@formatter:off*/
		Observable.create(ObservableCreator.getForecastsFor(forecastLocation))
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
}
