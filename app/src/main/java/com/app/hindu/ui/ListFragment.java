package com.app.hindu.ui;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.hindu.R;
import com.app.hindu.background.FetchFeed;
import com.app.hindu.constants.HTTPConstants;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView mSpeakerListRecyclerView;
    private LinearLayoutManager mSpeakerLayoutManager;

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static ListFragment newInstance(int sectionNumber) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //ListView prod = (ListView) view.findViewById(R.id.article_list);
        TextView emptyMessage = (TextView) view.findViewById(R.id.emptyMessage);
        ProgressBar spinner = (ProgressBar) view.findViewById(R.id.google_progress);

        //TextView count = (TextView) view.findViewById(R.id.count);
        Bundle args = getArguments();
        int index = args.getInt(ARG_SECTION_NUMBER);

        //Log.d("index received",String.valueOf(index));

        String url = HTTPConstants.HOME_SERVICE_FEED;
        if(index == 0 ){
            url = HTTPConstants.HOME_SERVICE_FEED;
        } else if(index == 1){
            url = HTTPConstants.NEWS_SERVICE_FEED;
        } else if(index == 2){
            url = HTTPConstants.OPINION_SERVICE_FEED;
        } else if(index == 3){
            url = HTTPConstants.BUSINESS_SERVICE_FEED;
        } else if(index == 4){
            url = HTTPConstants.SPORT_SERVICE_FEED;
        } else if(index == 5){
            url = HTTPConstants.NEWS_SERVICE_FEED;
        }

        mSpeakerListRecyclerView = (RecyclerView) view.findViewById(R.id.article_list);
        mSpeakerLayoutManager = new LinearLayoutManager(getActivity());
        mSpeakerLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mSpeakerListRecyclerView.setLayoutManager(mSpeakerLayoutManager);

        new FetchFeed(getActivity(),spinner,getActivity(),emptyMessage,url,mSpeakerListRecyclerView,mSpeakerLayoutManager,index).execute("");
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
