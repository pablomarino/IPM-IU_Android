package es.udc.ipm.ipm_p2.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import es.udc.ipm.ipm_p2.R;

/**
 * Created by qojop on 5/12/16.
 */

public class ItemFragment extends Fragment {
    private View v = null;
    private TextView t = null;
    private String itemName;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_selection, container, false);
        if(v!=null){
            t = (TextView) v.findViewById(R.id.itemName);
            t.setText(itemName);
        }
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}