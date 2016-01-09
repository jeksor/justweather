package com.esorokin.justweather.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.esorokin.justweather.R;
import com.esorokin.justweather.models.Forecast;
import com.esorokin.justweather.ui.activity.DetailsForecastActivity;
import com.esorokin.justweather.ui.common.WeatherPictureProvider;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Date: 31.12.2015
 * Time: 15:11
 *
 * @author esorokin
 */
public class ForecastAdapter extends CollectionRecycleAdapter<Forecast>
{
	private static final DateFormat sDateFormat = new SimpleDateFormat("d MMMM", Locale.getDefault());

	public ForecastAdapter(Context context)
	{
		super(context);
	}

	@Override
	public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		return new RecycleViewHolder<Forecast>(mInflater.inflate(R.layout.item_forecast, parent, false))
		{
			TextView dateTextView;
			TextView timeTextView;
			ImageView weatherImageView;
			TextView tempTextView;

			TextView precipitationTextView;
			TextView humidityTextView;
			TextView windTextView;

			@Override
			protected void create(View rootView)
			{
				dateTextView = (TextView) rootView.findViewById(R.id.item_forecast__date_text_view);
				timeTextView = (TextView) rootView.findViewById(R.id.item_forecast__time_text_view);
				weatherImageView = (ImageView) rootView.findViewById(R.id.item_forecast__current_weather_image_view);
				tempTextView = (TextView) rootView.findViewById(R.id.item_forecast__temperature_text_view);

				precipitationTextView = (TextView) rootView.findViewById(R.id.item_forecast__precipitation_text_view);
				humidityTextView = (TextView) rootView.findViewById(R.id.item_forecast__humidity_text_view);
				windTextView = (TextView) rootView.findViewById(R.id.item_forecast__wind_text_view);
			}

			@Override
			public void bind(final Forecast model)
			{
				dateTextView.setText(sDateFormat.format(model.getDate()));
				timeTextView.setText(String.format("%s : 00", model.getHour()));
				weatherImageView.setImageResource(WeatherPictureProvider.pictureBy(getContext(), model));
				tempTextView.setText(String.valueOf(model.getTemperature().getAverageValue()));

				precipitationTextView.setText(String.valueOf(model.getPhenomena().getPrecipitation()));
				humidityTextView.setText(String.valueOf(model.getRelwet().getAverageValue()));
				windTextView.setText(String.valueOf(model.getWind().getAverageValue()));

				getRoot().setOnClickListener(new View.OnClickListener()
				{
					@Override
					public void onClick(View view)
					{
						DetailsForecastActivity.start(getContext(), model);
					}
				});
			}
		};
	}
}
