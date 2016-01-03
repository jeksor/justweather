package com.esorokin.justweather.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.esorokin.justweather.R;
import com.esorokin.justweather.models.Forecast;
import com.esorokin.justweather.presenters.MainPresenter;
import com.esorokin.justweather.ui.MainMvpView;
import com.esorokin.justweather.ui.adapters.ForecastAdapter;
import com.esorokin.justweather.ui.base.BaseActivity;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.SerializeableLceViewState;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Date: 30-Dec-15
 * Time: 12:17
 *
 * @author esorokin
 */
public class MainActivity extends BaseActivity<SwipeRefreshLayout, ArrayList<Forecast>, MainMvpView, MainPresenter> implements MainMvpView, SwipeRefreshLayout.OnRefreshListener
{
	private static final String TAG = "MainActivity";

	@Bind(R.id.recyclerView)
	RecyclerView mRecyclerView;

	private ForecastAdapter mAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		contentView.setOnRefreshListener(this);

		mAdapter = new ForecastAdapter(getApplicationContext());
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
		mRecyclerView.setAdapter(mAdapter);
	}

	@NonNull
	@Override
	public MainPresenter createPresenter()
	{
		Log.d(TAG, "createPresenter");
		return MainPresenter.getInstance();
	}

	@Override
	protected void onDestroy()
	{
		Log.d(TAG, "onDestroy");
		super.onDestroy();
	}

	@Override
	protected String getErrorMessage(Throwable e, boolean pullToRefresh)
	{
		return e.getMessage();
	}

	@Override
	public void setData(ArrayList<Forecast> data)
	{
		mAdapter.setCollection(data);
	}

	@Override
	public void loadData(boolean pullToRefresh)
	{
		Log.d(TAG, "loadData");
		getPresenter().loadForecasts(pullToRefresh);
	}

	@Override
	public void onRefresh()
	{
		loadData(true);
	}

	@Override
	public void showError(Throwable e, boolean pullToRefresh)
	{
		contentView.setRefreshing(false);
		super.showError(e, pullToRefresh);
	}

	@Override
	public void showContent()
	{
		contentView.setRefreshing(false);
		super.showContent();
	}

	@Override
	public void showLoading(boolean pullToRefresh)
	{
		super.showLoading(pullToRefresh);

		if (pullToRefresh)
		{
			new Handler().post(new Runnable()
			{
				@Override
				public void run()
				{
					contentView.setRefreshing(true);
				}
			});
		}
	}

	@NonNull
	@Override
	public LceViewState<ArrayList<Forecast>, MainMvpView> createViewState()
	{
		return new ViewState();
	}

	@Override
	public ArrayList<Forecast> getData()
	{
		return mAdapter != null ? (ArrayList<Forecast>) mAdapter.getCollection() : null;
	}

	@SuppressLint("ParcelCreator")
	private static class ViewState extends SerializeableLceViewState<ArrayList<Forecast>, MainMvpView>
	{
		private static final String TAG = "ViewState";

		@Override
		public void setStateShowContent(ArrayList<Forecast> loadedData)
		{
			Log.d(TAG, "setStateShowContent");
			super.setStateShowContent(loadedData);
		}

		@Override
		public void setStateShowError(Throwable e, boolean pullToRefresh)
		{
			Log.d(TAG, "setStateShowError");
			super.setStateShowError(e, pullToRefresh);
		}

		@Override
		public void setStateShowLoading(boolean pullToRefresh)
		{
			Log.d(TAG, "setStateShowLoading");
			super.setStateShowLoading(pullToRefresh);
		}

		@Override
		public void apply(MainMvpView view, boolean retained)
		{
			Log.d(TAG, "apply");
			super.apply(view, retained);
		}
	}
}
