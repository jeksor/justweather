package com.esorokin.justweather.ui.base.delegates;

import android.support.annotation.NonNull;

import com.esorokin.justweather.presenters.base.BasePresenter;
import com.esorokin.justweather.ui.base.BaseMvpView;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.hannesdorfmann.mosby.mvp.delegate.ActivityMvpDelegate;
import com.hannesdorfmann.mosby.mvp.delegate.ActivityMvpViewStateDelegateCallback;
import com.hannesdorfmann.mosby.mvp.delegate.ActivityMvpViewStateDelegateImpl;
import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

/**
 * Date: 13-Jan-16
 * Time: 18:30
 *
 * @author esorokin
 */
public abstract class BaseMvpViewStateActivity<V extends MvpView, P extends BasePresenter<V>>
		extends BaseMvpActivity<V, P>
		implements ActivityMvpViewStateDelegateCallback<V, P>, BaseMvpView
{
	protected ViewState<V> viewState;

	/**
	 * A simple flag that indicates if the restoring ViewState  is in progress right now.
	 */
	protected boolean restoringViewState = false;

	@NonNull
	@Override
	protected ActivityMvpDelegate<V, P> getMvpDelegate()
	{
		if (mvpDelegate == null)
		{
			//noinspection unchecked
			mvpDelegate = new ActivityMvpViewStateDelegateImpl<>(this);
		}

		return mvpDelegate;
	}

	@Override
	public ViewState<V> getViewState()
	{
		return viewState;
	}

	@Override
	public void setViewState(ViewState<V> viewState)
	{
		this.viewState = viewState;
	}

	@Override
	public void setRestoringViewState(boolean restoringViewState)
	{
		this.restoringViewState = restoringViewState;
	}

	@Override
	public boolean isRestoringViewState()
	{
		return restoringViewState;
	}

	@Override
	public void onViewStateInstanceRestored(boolean instanceStateRetained)
	{
		// not needed. You could override this is subclasses if needed
	}

	/**
	 * Creates the ViewState instance
	 */
	@NonNull
	public abstract ViewState<V> createViewState();
}
