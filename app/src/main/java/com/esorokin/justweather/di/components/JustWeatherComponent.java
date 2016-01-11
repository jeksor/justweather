package com.esorokin.justweather.di.components;

import com.esorokin.justweather.di.modules.GismeteoModule;
import com.esorokin.justweather.presenters.MainPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Date: 11-Jan-16
 * Time: 16:33
 *
 * @author esorokin
 */
@Singleton
@Component(modules = GismeteoModule.class)
public interface JustWeatherComponent
{
	void inject(MainPresenter mainPresenter);
}
