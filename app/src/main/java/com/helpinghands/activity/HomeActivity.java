package com.helpinghands.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.helpinghands.fragment.HeroBannerFragment;
import com.helpinghands.fragment.NominateSMSContactsFragment;

public class HomeActivity extends FragmentActivity implements
        HeroBannerFragment.OnFragmentInteractionListener,
        NominateSMSContactsFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        HeroBannerFragment nsmsc=new HeroBannerFragment();
        pushFragment(nsmsc);
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
