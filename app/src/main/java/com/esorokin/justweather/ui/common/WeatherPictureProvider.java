package com.esorokin.justweather.ui.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;

import com.esorokin.justweather.R;
import com.esorokin.justweather.models.Forecast;

import java.util.Random;

/**
 * Date: 08.01.2016
 * Time: 17:15
 *
 * @author esorokin
 */
public class WeatherPictureProvider
{
	/**
	 * Get image (sun, clouds etc.) using forecast info.
	 *
	 *
	 * @param context Context for tested drawable array.
	 * @param forecast Forecast info.
	 * @return Drawable resources.
	 */
	@DrawableRes
	public static int pictureBy(Context context, Forecast forecast)
	{
		//TODO Replace to real logic.
		TypedArray imgs = context.getResources().obtainTypedArray(R.array.weather_pictures);
		int resourceId = imgs.getResourceId(new Random().nextInt(imgs.length()), -1);

		imgs.recycle();
		return resourceId;
	}
}
