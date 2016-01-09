package com.esorokin.justweather.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.esorokin.justweather.R;
import com.esorokin.justweather.models.Forecast;
import com.esorokin.justweather.ui.base.BaseActivity;
import com.f2prateek.dart.InjectExtra;

/**
 * Date: 08.01.2016
 * Time: 18:54
 *
 * @author esorokin
 */
public class DetailsForecastActivity extends BaseActivity
{
	private static final String TAG = "DetailsForecastActivity";

	public static final String EXTRA_FORECAST = TAG + ".extra.forecast";

	@InjectExtra(EXTRA_FORECAST)
	Forecast mCurrentForecast;

	public static void start(Context context, @NonNull Forecast forecast)
	{
		Intent intent = new Intent(context, DetailsForecastActivity.class);
		intent.putExtra(EXTRA_FORECAST, forecast);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details_forecast);
		setTitle(R.string.nsk);
	}

	@Override
	protected void initToolbar()
	{
		super.initToolbar();

		if (getSupportActionBar() != null)
		{
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		if (item.getItemId() == android.R.id.home)
		{
			finish();
			return true;
		}
		else
		{
			return super.onOptionsItemSelected(item);
		}
	}
}
