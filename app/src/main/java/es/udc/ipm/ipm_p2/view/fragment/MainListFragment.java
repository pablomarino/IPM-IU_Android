package es.udc.ipm.ipm_p2.view.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import es.udc.ipm.ipm_p2.MainActivity;
import es.udc.ipm.ipm_p2.R;
import es.udc.ipm.ipm_p2.configuration.Configuration;
import es.udc.ipm.ipm_p2.view.adapter.MainListAdapter;


/**
 * Created by qojop on 3/12/16.
 */

public class MainListFragment extends ListFragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new <String>MainListAdapter(getActivity(),R.layout.fragment_list, Configuration.getData()));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // super.onListItemClick(l, v, position, id);
        MainActivity.setSelectedCategory(position);
        MainActivity.setSelectedItem(-1);
        ((MainActivity)getActivity()).layElements();
    }

}