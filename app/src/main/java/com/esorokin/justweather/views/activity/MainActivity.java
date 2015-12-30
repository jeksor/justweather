package com.esorokin.justweather.views.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;

import com.esorokin.justweather.R;
import com.esorokin.justweather.models.ForecastRespone;
import com.esorokin.justweather.presenters.MainPresenter;
import com.esorokin.justweather.views.MainMvpView;
import com.esorokin.justweather.views.base.BaseViewStateLceActivity;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.SerializeableLceViewState;

/**
 * Date: 30-Dec-15
 * Time: 12:17
 *
 * @author esorokin
 */
public class MainActivity extends BaseViewStateLceActivity<SwipeRefreshLayout, ForecastRespone, MainMvpView, MainPresenter> implements MainMvpView
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@NonNull
	@Override
	public MainPresenter createPresenter()
	{
		return MainPresenter.getInstance();
	}

	@NonNull
	@Override
	public LceViewState<ForecastRespone, MainMvpView> createViewState()
	{
		return new SerializeableLceViewState<>();
	}

	@Override
	public ForecastRespone getData()
	{
		return null;
	}

	@Override
	protected String getErrorMessage(Throwable e, boolean pullToRefresh)
	{
		return null;
	}

	@Override
	public void setData(ForecastRespone data)
	{

	}

	@Override
	public void loadData(boolean pullToRefresh)
	{

	}
}
