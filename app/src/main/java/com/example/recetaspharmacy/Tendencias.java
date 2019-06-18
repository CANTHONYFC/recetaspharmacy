package com.example.recetaspharmacy;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tendencias.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tendencias#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tendencias extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView mRecycler1;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    public ArrayList<String> mDataset;
    public View rootView;
    private String mParam1;
    private String mParam2;



    private OnFragmentInteractionListener mListener;

    public Tendencias() {
        // Required empty public constructor
    }

    public static Tendencias newInstance(String param1, String param2) {
        Tendencias fragment = new Tendencias();
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

rootView=inflater.inflate(R.layout.fragment_tendencias,container,false);
 mRecycler1=(RecyclerView)rootView.findViewById(R.id.recycler1);
 mDataset=new ArrayList<>();
 for (int i =0;i<30;i++){

     mDataset.add("New tittle #"+1);
 }
        mRecycler1.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);
        mRecycler1.setLayoutManager(mLayoutManager);
        mAdapter=new MainAdapter(mDataset);
        mRecycler1.setAdapter(mAdapter);




        return rootView;
    }

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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
