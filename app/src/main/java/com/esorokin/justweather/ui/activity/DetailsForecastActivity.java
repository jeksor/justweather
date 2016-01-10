package com.esorokin.justweather.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.esorokin.justweather.R;
import com.esorokin.justweather.models.Forecast;
import com.esorokin.justweather.ui.adapters.DetailsForecastAdapter;
import com.esorokin.justweather.ui.base.BaseActivity;
import com.esorokin.justweather.ui.common.Effects;
import com.f2prateek.dart.InjectExtra;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;

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

	private static final DateFormat sDateFormat = new SimpleDateFormat("d MMMM hh:mm", Locale.getDefault());

	@InjectExtra(EXTRA_FORECAST)
	Forecast mCurrentForecast;

	@Bind(R.id.activity_details_forecast__background)
	ImageView mBgImageView;

	@Bind(R.id.activity_details_forecast__date_text_view)
	TextView mDateTextView;

	@Bind(R.id.activity_details_forecast__temp_text_view)
	TextView mTempTextView;

	@Bind(R.id.activity_details_forecast__details_recycler_view)
	RecyclerView mDetailsForecastRecyclerView;

	private DetailsForecastAdapter mDetailsForecastAdapter;

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

		Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.novosibirsk);
		mBgImageView.setImageBitmap(Effects.doBlur(picture, 9, false));

		mDateTextView.setText(sDateFormat.format(mCurrentForecast.getDate()));
		mTempTextView.setText(String.valueOf(mCurrentForecast.getTemperature().getAverageValue()));

		mDetailsForecastAdapter = new DetailsForecastAdapter(getApplicationContext());
		mDetailsForecastRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
		mDetailsForecastRecyclerView.setAdapter(mDetailsForecastAdapter);

		List<DetailsForecastAdapter.ForecastDetailsItem> items = new ArrayList<>();
		items.add(createItem(R.string.cloudness, String.valueOf(mCurrentForecast.getPhenomena().getCloudiness())));
		items.add(createItem(R.string.precipitation, String.valueOf(mCurrentForecast.getPhenomena().getPrecipitation())));
		items.add(createItem(R.string.pressure, String.valueOf(mCurrentForecast.getPressure().getAverageValue())));
		items.add(createItem(R.string.wind, String.valueOf(mCurrentForecast.getWind().getAverageValue())));
		items.add(createItem(R.string.relwet, String.valueOf(mCurrentForecast.getRelwet().getAverageValue())));
		items.add(createItem(R.string.heat, String.valueOf(mCurrentForecast.getHeat().getAverageValue())));

		mDetailsForecastAdapter.setCollection(items);
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

	private DetailsForecastAdapter.ForecastDetailsItem createItem(@StringRes int titleRes, String value)
	{
		return new DetailsForecastAdapter.ForecastDetailsItem(String.format("%s: %s", getString(titleRes), value));
	}
}
