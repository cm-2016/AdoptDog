package com.example.karim.mascotasapp;

import android.app.*;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.karim.mascotasapp.api.models.Dog;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LstFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LstFragment extends ListFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ProgressDialog mProgressDialog;
    List<ParseObject> ob;
    Boolean onClick = true;



    private OnFragmentInteractionListener mListener;

    public LstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LstFragment newInstance(String param1, String param2) {
        LstFragment fragment = new LstFragment();
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

        new RemoteDataTask().execute();
        //loadInitList();

    }

    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressDialog = new ProgressDialog(getActivity());
            // Set progressdialog get
            mProgressDialog.setTitle("AdoptDog");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                    "Dogs");
            try {
                ob = query.find();
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1);

            for (ParseObject dog : ob) {
                adapter.add((String) dog.get("name"));
            }

            setListAdapter(adapter);
            mProgressDialog.dismiss();


           // adapter.notifyDataSetChanged();


        }
    }
        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       // String[] datasource={"English","French","Khmer","Japanese","Russian","Chinese","English","French","Khmer","Japanese","Russian","Chinese"};
        //ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,datasource);
        //setListAdapter(adapter);
        //setRetainInstance(true);

        return inflater.inflate(R.layout.fragment_lst, container, false);
    }

    private void loadInitList() {

        onClick = true;
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1);

        List<String> list = new ArrayList<>();
        list.add("Adultos");
        list.add("Cachorros");

        for (String str : list) {
            adapter.add((String) str);
        }
        setListAdapter(adapter);
    }
    private void getOffersFromApi(){

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Dogs");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                List<String> names = new ArrayList<>();
                for (ParseObject parseObject : list) {
                    String name = parseObject.getString("name");
                    names.add(name);
                    Log.d("NAME",name);
                }

            }
        });

    }


    public void onListItemClick(ListView l, View view, int position, long id){

/*
        Fragment newFragment = new DivFragment();
        Bundle args = new Bundle();
        args.putInt(DivFragment.ARG_P, position);
        newFragment.setArguments(args);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

// Commit the transaction
        transaction.commit();

        */

        // String str = datasource[position];
        //Toast.makeText(getActivity(), str, Toast.LENGTH_LONG).show();

        if (onClick) {


            new RemoteDataTask().execute();

            onClick = false;

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
