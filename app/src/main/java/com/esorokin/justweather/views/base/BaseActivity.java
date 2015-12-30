package com.esorokin.justweather.views.base;

import com.esorokin.justweather.presenters.base.BasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.ButterKnife;

/**
 * Date: 30-Dec-15
 * Time: 11:56
 *
 * @author esorokin
 */
public abstract class BaseActivity<V extends BaseMvpView, P extends BasePresenter<V>> extends MvpActivity<V, P>
{
	@Override
	public void setContentView(int layoutResId)
	{
		super.setContentView(layoutResId);
		ButterKnife.bind(this);
	}
}
