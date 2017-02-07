package es.udc.ipm.ipm_p2.view;

import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;

/**
 * Created by Pablo Mariño on 28/10/16.
 */

public class ListItem {

    private ImageView thumbnail;
    private TextView txtView;
    private ImageButton btEdit;
    private ImageButton btRemove;
    private TextView ChildCount;
    private SwipeLayout swipelayout;

    public SwipeLayout getSwipelayout() {
        return swipelayout;
    }

    public void setSwipelayout(SwipeLayout swipelayout) {
        this.swipelayout = swipelayout;
    }

    public ImageView getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(ImageView thumbnail) {
        this.thumbnail = thumbnail;
    }

    public ImageButton getBtEdit() {
        return btEdit;
    }

    public void setBtEdit(ImageButton btEdit) {
        this.btEdit = btEdit;
    }

    public TextView getTxtView() {
        return txtView;
    }

    public void setTxtView(TextView txtView) {
        this.txtView = txtView;
    }

    public ImageButton getBtRemove() {
        return btRemove;
    }

    public void setBtRemove(ImageButton btRemove) {
        this.btRemove = btRemove;
    }

    public TextView getChildCount() {return ChildCount;}

    public void setChildCount(TextView childCount) {ChildCount = childCount;}
}