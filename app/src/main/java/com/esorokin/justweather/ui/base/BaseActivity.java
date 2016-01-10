package com.esorokin.justweather.ui.base;

import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.esorokin.justweather.R;
import com.f2prateek.dart.Dart;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Date: 08.01.2016
 * Time: 19:22
 *
 * @author esorokin
 */
@SuppressWarnings("unused")
public abstract class BaseActivity extends AppCompatActivity
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

	@Nullable
	public AppBarLayout getAppBarLayout()
	{
		return mAppBarLayout;
	}

	@Nullable
	public Toolbar getToolbar()
	{
		return mToolbar;
	}
}
