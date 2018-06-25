package com.skpissay.baseproject.di.modules;

import android.view.WindowManager;

import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.skpissay.baseproject.R;
import com.skpissay.baseproject.di.HomeActivityScope;
import com.skpissay.baseproject.screens.activities.HomeActivity;
import com.skpissay.baseproject.screens.presenters.HomePresenter;
import com.skpissay.baseproject.screens.ui.assist.FragmentHelper;
import com.skpissay.baseproject.screens.ui.assist.NotificationHandler;
import com.skpissay.baseproject.screens.ui.assist.ViewHolderFactory;
import com.skpissay.baseproject.screens.ui.helper.UsersAdapter;
import com.skpissay.baseproject.utils.IntentHelper;

import dagger.Module;
import dagger.Provides;

/**
 * Created by skpissay on 25/06/18.
 */

@Module
public class HomeActivityModule {

    private final HomeActivity homeActivity;

    public HomeActivityModule(HomeActivity homeActivity) {
        this.homeActivity = homeActivity;
    }

    @Provides
    @HomeActivityScope
    HomePresenter provideHomePresenter() {
        return new HomePresenter(homeActivity);
    }

    @Provides
    @HomeActivityScope
    HomeActivity provideHomeActivity() {
        return homeActivity;
    }

    @Provides
    @HomeActivityScope
    FragmentHelper provideFragmentHelper(HomeActivity activity) {
        return new FragmentHelper(activity);
    }

    @Provides
    @HomeActivityScope
    WindowManager.LayoutParams provideLayoutParams() {
        return new WindowManager.LayoutParams();
    }

    /*@Provides
    @HomeActivityScope
    QuantityDialog provideQuantityDialog(HomeActivity activity) {
        return new QuantityDialog(activity, R.style.MyAlertDialogTheme);
    }*/

    @Provides
    @HomeActivityScope
    IntentHelper provideIntentHelper(HomeActivity activity) {
        return new IntentHelper(activity);
    }

    /*@Provides
    @HomeActivityScope
    DistributorCartAdapter provideDistributorCartAdapter(HomeActivity activity) {
        return new DistributorCartAdapter(activity, false);
    }*/

    @Provides
    @HomeActivityScope
    ViewHolderFactory provideViewHolderFactory(HomeActivity baseActivity) {
        return new ViewHolderFactory(baseActivity);
    }

    /*@Provides
    @HomeActivityScope
    DistributorDialog provideDistributorDialog(HomeActivity activity) {
        return new DistributorDialog(activity, R.style.MyAlertDialogTheme);
    }*/

    @Provides
    @HomeActivityScope
    UsersAdapter provideUsersAdapter(HomeActivity activity) {
        return new UsersAdapter(activity);
    }

    @Provides
    @HomeActivityScope
    NotificationHandler provideNotificationHandler(HomeActivity activity){
        return new NotificationHandler(activity);
    }
}
