package com.skpissay.baseproject.di.modules;

import android.content.Context;

import com.skpissay.baseproject.di.LandingScope;
import com.skpissay.baseproject.models.User;
import com.skpissay.baseproject.screens.fragments.LandingFragment;
import com.skpissay.baseproject.screens.presenters.LandingPresenter;
import com.skpissay.baseproject.screens.ui.helper.UsersAdapter;
import com.skpissay.baseproject.screens.ui.interfaces.RecyclerUsersListener;

import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by skpissay on 25/06/18.
 */

@Module
public class LandingModule {

    final LandingFragment mLandingFragment;

    public LandingModule(LandingFragment mLandingFragment) {
        this.mLandingFragment = mLandingFragment;
    }

    @Provides
    @LandingScope
    LandingPresenter provideLandingPresenter(){
        return new LandingPresenter(mLandingFragment);
    }
}
