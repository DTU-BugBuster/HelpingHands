package com.helpinghands.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.helpinghands.R;
import com.helpinghands.activity.HomeActivity;
import com.helpinghands.constants.AppConstant;
import com.helpinghands.utils.Logger;
import com.helpinghands.utils.SharedPrefUtils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NominateSMSContactsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NominateSMSContactsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NominateSMSContactsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private HomeActivity homeActivity;
    private static final int RC_READ_CONTACT1=2001;
    private static final int RC_READ_CONTACT2=2002;
    private EditText contact1Name,contact2Name;
    private EditText contact1Phone,contact2Phone;
    public NominateSMSContactsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NominateSMSContactsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NominateSMSContactsFragment newInstance(String param1, String param2) {
        NominateSMSContactsFragment fragment = new NominateSMSContactsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_nominate_sms_contacts, container, false);
        TextView userName=(TextView)view.findViewById(R.id.user_name);

        String userFName= new SharedPrefUtils().getValue(homeActivity,AppConstant.USER_FIRST_NAME);
        userName.setText(userFName);


        contact1Name=(EditText)view.findViewById(R.id.contact1_name);
        contact1Phone=(EditText)view.findViewById(R.id.contact1_phone);

        contact2Name=(EditText)view.findViewById(R.id.contact2_name);
        contact2Phone=(EditText)view.findViewById(R.id.contact2_phone);


        ImageView contact1Button=(ImageView)view.findViewById(R.id.contact1_button);
        contact1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                startActivityForResult(intent,RC_READ_CONTACT1);

            }
        });


        ImageView contact2Button=(ImageView)view.findViewById(R.id.contact2_button);
        contact2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                startActivityForResult(intent,RC_READ_CONTACT2);

            }
        });


        ImageView next=(ImageView)view.findViewById(R.id.nominate_sms_contacts_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO : validate name and phone
                // TODO : save nominated contacts
                homeActivity.pushFragment(new NominateMailContactsFragment());
            }
        });

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK){
            Uri contactData = data.getData();

            switch(requestCode){
                case RC_READ_CONTACT1:
                    setContact(contactData,contact1Name,contact1Phone);
                    break;

                case RC_READ_CONTACT2:
                    setContact(contactData,contact2Name,contact2Phone);
                    break;
            }
        }

    }

    private void setContact(Uri contactData,EditText contactName,EditText contactPhone) {

        Cursor cursor = homeActivity.getContentResolver().query(contactData, null, null, null, null);
        cursor.moveToFirst();

        String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
        String number = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));

        contactName.setText(name);
        contactPhone.setText(number);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}