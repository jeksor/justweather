package com.esorokin.justweather.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.esorokin.justweather.R;
import com.esorokin.justweather.models.Forecast;

/**
 * Date: 31.12.2015
 * Time: 15:11
 *
 * @author esorokin
 */
public class ForecastAdapter extends CollectionRecycleAdapter<Forecast>
{
	public ForecastAdapter(Context context)
	{
		super(context);
	}

	@Override
	public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		return new RecycleViewHolder<Forecast>(mInflater.inflate(R.layout.item_forecast, parent, false))
		{
			TextView nameTextView;

			@Override
			protected void create(View rootView)
			{
				nameTextView = (TextView) rootView.findViewById(R.id.item_forecast__name);
			}

			@Override
			public void bind(Forecast model)
			{
				nameTextView.setText(new StringBuilder().append(model.getMonth()).append(" ").append(model.getDay()).append(" ").append(model.getHour()));
			}
		};
	}
}
