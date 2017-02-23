package com.helpinghands.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.helpinghands.R;
import com.helpinghands.fragment.HeroBannerFragment;
import com.helpinghands.fragment.NominateMailContactsFragment;
import com.helpinghands.fragment.NominateSMSContactsFragment;
import com.helpinghands.fragment.RegisterPinFragment;
import com.helpinghands.fragment.SetShakeLevelFragment;
import com.helpinghands.fragment.WelcomeFragment;
import com.helpinghands.utils.SharedPrefUtils;

public class HomeActivity extends FragmentActivity implements
        HeroBannerFragment.OnFragmentInteractionListener,
        NominateSMSContactsFragment.OnFragmentInteractionListener,
        WelcomeFragment.OnFragmentInteractionListener,
        RegisterPinFragment.OnFragmentInteractionListener,
        NominateMailContactsFragment.OnFragmentInteractionListener,
        SetShakeLevelFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        HeroBannerFragment heroBannerFragment=new HeroBannerFragment();
        pushFragment(heroBannerFragment);
    }



    public void pushFragment(Fragment fragment){

        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container,fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
