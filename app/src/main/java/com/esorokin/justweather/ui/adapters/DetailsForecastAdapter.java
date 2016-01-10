package com.esorokin.justweather.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.esorokin.justweather.R;

/**
 * Date: 31.12.2015
 * Time: 15:11
 *
 * @author esorokin
 */
public class DetailsForecastAdapter extends CollectionRecycleAdapter<DetailsForecastAdapter.ForecastDetailsItem>
{
	public DetailsForecastAdapter(Context context)
	{
		super(context);
	}

	@Override
	public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		return new RecycleViewHolder<ForecastDetailsItem>(mInflater.inflate(R.layout.item_details, parent, false))
		{
			TextView infoTextView;

			@Override
			protected void create(View rootView)
			{
				infoTextView = (TextView) rootView.findViewById(R.id.item_details__info_text_view);
			}

			@Override
			public void bind(final ForecastDetailsItem model)
			{
				infoTextView.setText(model.getText());
			}
		};
	}

	public static class ForecastDetailsItem
	{
		private String mText;

		public ForecastDetailsItem(String text)
		{
			mText = text;
		}

		public String getText()
		{
			return mText;
		}

		public void setText(String text)
		{
			mText = text;
		}
	}
}
