package com.skpissay.baseproject.di.components;

import com.skpissay.baseproject.di.LandingScope;
import com.skpissay.baseproject.di.modules.LandingModule;
import com.skpissay.baseproject.screens.fragments.LandingFragment;

import javax.inject.Scope;

import dagger.Component;

/**
 * Created by skpissay on 25/06/18.
 */

@LandingScope
@Component(modules = LandingModule.class, dependencies = HomeActivityComponent.class)
public interface LandingComponent {
    void inject(LandingFragment landingFragment);
}
