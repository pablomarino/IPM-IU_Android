package es.udc.ipm.ipm_p2.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;

import java.util.List;

import es.udc.ipm.ipm_p2.MainActivity;
import es.udc.ipm.ipm_p2.R;
import es.udc.ipm.ipm_p2.configuration.structs.DataObject;
import es.udc.ipm.ipm_p2.control.DialogsHandler;
import es.udc.ipm.ipm_p2.util.ThumbGenerator;
import es.udc.ipm.ipm_p2.view.ChildListItem;

/**
 * Created by qojop on 28/10/16.
 */

public class ChildListAdapter extends ArrayAdapter <DataObject>{

    public ChildListAdapter(Context context, int resource, List<DataObject> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DataObject dob = getItem(position);
            if (dob != null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.listitem_child, null);
                ChildListItem childListItem = new ChildListItem();
                childListItem.setThumbnail((ImageView) convertView.findViewById(R.id.child_list_thumb));
                childListItem.setTxtView((TextView) convertView.findViewById(R.id.child_list_label));
                childListItem.setBtEdit((ImageButton) convertView.findViewById(R.id.child_list_edit));
                childListItem.setBtRemove((ImageButton) convertView.findViewById(R.id.child_list_remove));
                childListItem.setSwipelayout((SwipeLayout) convertView.findViewById(R.id.swipe_child_layout));

                childListItem.getTxtView().setText(dob.getName());
                childListItem.getBtEdit().setOnClickListener(onEditChildListener(position, childListItem));
                childListItem.getBtRemove().setOnClickListener(onDeleteChildListener(position, childListItem));
                childListItem.getSwipelayout().setShowMode(SwipeLayout.ShowMode.PullOut);
                // click de este boton en MainListFragment
                ThumbGenerator.drawCircularThumb(childListItem,Math.abs(dob.getName().hashCode()));
                convertView.setTag(childListItem);
            }
        return convertView;
    }

    private View.OnClickListener onEditChildListener(final int position, final ChildListItem holder) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogsHandler.showEditChildDialog(MainActivity.getSelectedCategory(), position, holder);
            }
        };
    }

    private View.OnClickListener onDeleteChildListener(final int position, final ChildListItem holder) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogsHandler.showDeleteChildDialog(MainActivity.getSelectedCategory(),position);
            }
        };
    }
}
