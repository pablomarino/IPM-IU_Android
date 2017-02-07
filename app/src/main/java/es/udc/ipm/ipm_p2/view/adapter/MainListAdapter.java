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
import es.udc.ipm.ipm_p2.configuration.structs.DataObject;
import es.udc.ipm.ipm_p2.R;
import es.udc.ipm.ipm_p2.control.DialogsHandler;
import es.udc.ipm.ipm_p2.util.ThumbGenerator;
import es.udc.ipm.ipm_p2.view.ListItem;

/**
 * Created by qojop on 28/10/16.
 */

public class MainListAdapter extends ArrayAdapter <DataObject>{

    public MainListAdapter(Context context, int resource, List<DataObject> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DataObject dob = getItem(position);
            if (dob != null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.listitem_parent, null);
                ListItem listItem = new ListItem();
                listItem.setThumbnail((ImageView) convertView.findViewById(R.id.ListItemThumbnail));
                listItem.setTxtView((TextView) convertView.findViewById(R.id.ListItemTextView));
                listItem.setChildCount((TextView) convertView.findViewById(R.id.ListItemChildCount));
                listItem.setBtEdit((ImageButton) convertView.findViewById(R.id.ListItemButtonEdit));
                listItem.setBtRemove((ImageButton) convertView.findViewById(R.id.ListItemButtonRemove));
                listItem.setSwipelayout((SwipeLayout) convertView.findViewById(R.id.swipe_layout));
                listItem.getTxtView().setText(dob.getName());
                listItem.getBtEdit().setOnClickListener(onEditListener(position, listItem));
                listItem.getBtRemove().setOnClickListener(onDeleteListener(position, listItem));
                listItem.getSwipelayout().setShowMode(SwipeLayout.ShowMode.PullOut);
                // click de este boton en MainListFragment
                ThumbGenerator.drawSquareThumb(listItem,Math.abs(dob.getName().hashCode()));
                List <DataObject> children =  dob.getChildren();
                int numChild;
                if(children!=null) numChild = dob.getChildren().size(); else numChild = -1;
                if(numChild>0) {
                    listItem.getChildCount().setText(String.valueOf(numChild));
                    listItem.getChildCount().setVisibility(View.VISIBLE);
                }else listItem.getChildCount().setVisibility(View.INVISIBLE);
                convertView.setTag(listItem);
            }
        return convertView;
    }

    private View.OnClickListener onEditListener(final int position, final ListItem holder) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogsHandler.showEditDialog(position, holder);
            }
        };
    }

    private View.OnClickListener onDeleteListener(final int position, final ListItem holder) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogsHandler.showDeleteDialog(position, holder);
            }
        };
    }

    private View.OnClickListener onAddListener(final ListItem holder) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogsHandler.showAddDialog( holder);
            }
        };
    }
}
