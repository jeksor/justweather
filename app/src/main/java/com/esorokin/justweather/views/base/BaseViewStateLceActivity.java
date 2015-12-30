package com.esorokin.justweather.views.base;

import android.view.View;

import com.esorokin.justweather.presenters.base.BasePresenter;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateActivity;

import butterknife.ButterKnife;

/**
 * Date: 30-Dec-15
 * Time: 18:54
 *
 * @author esorokin
 */
public abstract class BaseViewStateLceActivity<CV extends View, M, V extends BaseMvpLceView<M>, P extends BasePresenter<V>>
		extends MvpLceViewStateActivity<CV, M, V, P>
{
	@Override
	public void setContentView(int layoutResId)
	{
		super.setContentView(layoutResId);
		ButterKnife.bind(this);
	}
}
