package com.helpinghands.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.helpinghands.fragment.HeroBannerFragment;

/**
 * Created by aloy on 7/10/2016.
 */
public class HeroBannerAdapter extends FragmentStatePagerAdapter {

    private static final int NUM_PAGES = 3;

    public HeroBannerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        HeroBannerFragment heroBannerFragment= HeroBannerFragment.newInstance(position);
        return heroBannerFragment;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}

