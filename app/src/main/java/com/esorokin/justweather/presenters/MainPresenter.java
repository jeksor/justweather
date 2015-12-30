package com.esorokin.justweather.presenters;

import com.esorokin.justweather.presenters.base.BasePresenter;
import com.esorokin.justweather.views.MainMvpView;

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

	private MainPresenter()
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


}
