package com.helpinghands.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.helpinghands.R;
import com.helpinghands.activity.HomeActivity;
import com.helpinghands.constants.AppConstant;
import com.helpinghands.utils.Logger;
import com.helpinghands.utils.SharedPrefUtils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HeroBannerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HeroBannerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HeroBannerFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String POSITION = "position";

    private static final String TAG = HeroBannerFragment.class.getSimpleName();
    // TODO: Rename and change types of parameters
    private int position;

    private OnFragmentInteractionListener mListener;
    private HomeActivity homeActivity;
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;

    public HeroBannerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment HeroBannerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HeroBannerFragment newInstance(int param1) {
        HeroBannerFragment fragment = new HeroBannerFragment();
        Bundle args = new Bundle();
        args.putInt(POSITION, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(POSITION);
        }


        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(homeActivity)
                .enableAutoManage(homeActivity /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hero_banner, container, false);

        view.findViewById(R.id.button_connect_with_google)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        signInWithGoogle();
                    }
                });



        return view;
    }

    private void signInWithGoogle() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {

        Logger.i(TAG, "handleSignInResult ->" + result);
        if(result!=null){

            if (result.isSuccess()) {
                Logger.d(TAG,"Login Success -> "+result.isSuccess());
                // Signed in successfully, show authenticated UI.
                GoogleSignInAccount acct = result.getSignInAccount();
                String fullName=acct.getDisplayName();
                String fname=acct.getGivenName();
                String lname=acct.getFamilyName();
                String email=acct.getEmail();


                Logger.d(TAG,"Account Info -> "+
                        fname+"\n"+
                        lname+"\n"+
                        fullName+"\n"+
                        email
                );


                SharedPrefUtils.saveValue(AppConstant.USER_FIRST_NAME,fname);
                SharedPrefUtils.saveValue(AppConstant.USER_LAST_NAME,lname);
                SharedPrefUtils.saveValue(AppConstant.USER_FULL_NAME,fullName);
                SharedPrefUtils.saveValue(AppConstant.USER_EMAIL,email);


                Toast.makeText(homeActivity,acct.getEmail(),Toast.LENGTH_SHORT).show();

                WelcomeFragment welcomeFragment=new WelcomeFragment();
                homeActivity.pushFragment(welcomeFragment);

            } else {
                // Signed out, show unauthenticated UI.
                Logger.d(TAG,"Login Failed -> "+result.isSuccess());
            }
        }

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        homeActivity=(HomeActivity)context;
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
