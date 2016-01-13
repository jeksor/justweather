package com.esorokin.justweather.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.esorokin.justweather.R;
import com.esorokin.justweather.models.Forecast;
import com.esorokin.justweather.ui.base.BaseActivity;
import com.esorokin.justweather.utils.Effects;
import com.esorokin.justweather.utils.WeatherPictureProvider;
import com.f2prateek.dart.InjectExtra;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

	@Bind(R.id.activity_details_forecast__weather_picture)
	ImageView mWeatherImageView;

	@Bind(R.id.activity_details_forecast__details_linear_layout)
	LinearLayout mDetailsLinearLayout;

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
		mTempTextView.setText(String.format("%s %s", mCurrentForecast.getTemperature().getAverageValue(), getString(R.string.celsius)));
		mWeatherImageView.setImageResource(WeatherPictureProvider.pictureBy(this, mCurrentForecast));

		inflateItem(R.string.cloudness, String.valueOf(mCurrentForecast.getPhenomena().getCloudiness()));
		inflateItem(R.string.precipitation, String.valueOf(mCurrentForecast.getPhenomena().getPrecipitation()));
		inflateItem(R.string.pressure, String.valueOf(mCurrentForecast.getPressure().getAverageValue()));
		inflateItem(R.string.wind, String.valueOf(mCurrentForecast.getWind().getAverageValue()));
		inflateItem(R.string.relwet, String.valueOf(mCurrentForecast.getRelwet().getAverageValue()));
		inflateItem(R.string.heat, String.valueOf(mCurrentForecast.getHeat().getAverageValue()));
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

	private void inflateItem(@StringRes int titleRes, String value)
	{
		View inflateItem = getLayoutInflater().inflate(R.layout.item_details, mDetailsLinearLayout, false);
		((TextView) inflateItem.findViewById(R.id.item_details__info_text_view)).setText(String.format("%s: %s", getString(titleRes), value));

		mDetailsLinearLayout.addView(inflateItem);
	}
}
