package es.udc.ipm.ipm_p2.view.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import es.udc.ipm.ipm_p2.MainActivity;
import es.udc.ipm.ipm_p2.R;
import es.udc.ipm.ipm_p2.configuration.Configuration;
import es.udc.ipm.ipm_p2.configuration.structs.DataObject;
import es.udc.ipm.ipm_p2.view.adapter.ChildListAdapter;


/**
 * Created by qojop on 3/12/16.
 */

public class ChildListFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<DataObject> data;
        if(MainActivity.getSelectedCategory()>=0) {
            data = Configuration.getData().get(MainActivity.getSelectedCategory()).getChildren();
        }else{
            data = new ArrayList<DataObject>();
        }
        setListAdapter(new <String>ChildListAdapter(getActivity(),R.layout.fragment_list, data) );//.getData().get(MainActivity.getSelectedCategory()).getChildren())
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // super.onListItemClick(l, v, position, id);
        MainActivity.setSelectedItem(position);
        ((MainActivity)getActivity()).layElements();
      }

}
