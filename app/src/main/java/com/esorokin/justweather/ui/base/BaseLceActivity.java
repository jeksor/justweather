package com.esorokin.justweather.ui.base;

import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.esorokin.justweather.R;
import com.esorokin.justweather.presenters.base.BasePresenter;
import com.f2prateek.dart.Dart;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Date: 30-Dec-15
 * Time: 11:56
 *
 * @author esorokin
 */
@SuppressWarnings("unused")
public abstract class BaseLceActivity<CV extends View, D, V extends BaseLceMvpView<D>, P extends BasePresenter<V>>
		extends MvpLceViewStateActivity<CV, D, V, P>
		implements BaseLceMvpView<D>
{
	@Nullable
	@Bind(R.id.appbar)
	AppBarLayout mAppBarLayout;

	@Nullable
	@Bind(R.id.toolbar)
	Toolbar mToolbar;

	@Override
	public void setContentView(int layoutResId)
	{
		super.setContentView(layoutResId);
		ButterKnife.bind(this);
		Dart.inject(this);
		initToolbar();
	}

	protected void initToolbar()
	{
		if (mToolbar != null)
		{
			setSupportActionBar(mToolbar);
		}
	}

	@Override
	public void setTitle(CharSequence title)
	{
		super.setTitle(title);
		if (mToolbar != null)
		{
			mToolbar.setTitle(title);
		}
	}

	@Override
	public void setTitle(int title)
	{
		super.setTitle(title);
		if (mToolbar != null)
		{
			mToolbar.setTitle(getString(title));
		}
	}
}
