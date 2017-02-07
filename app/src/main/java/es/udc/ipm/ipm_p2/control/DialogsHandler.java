package es.udc.ipm.ipm_p2.control;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import es.udc.ipm.ipm_p2.MainActivity;
import es.udc.ipm.ipm_p2.R;
import es.udc.ipm.ipm_p2.configuration.Configuration;
import es.udc.ipm.ipm_p2.configuration.structs.DataObject;
import es.udc.ipm.ipm_p2.view.ChildListItem;
import es.udc.ipm.ipm_p2.view.ListItem;

/**
 * Created by qojop on 1/11/16.
 */

public class DialogsHandler {

    private static Context context;
    private static MainActivity main;


    public static UserActions getUserActions() {
        return userActions;
    }

    public static void setContext(Context context) {
        DialogsHandler.context = context;
    }

    public static void setUserActions(UserActions userActions) {
        DialogsHandler.userActions = userActions;
    }

    private static UserActions userActions;

    /**
     */
    public static void showStartDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.start_tip));

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        builder.setCancelable(false);
        builder.setPositiveButton(context.getString(R.string.bt_aceptar),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }



    /**
     * Group edit confirm dialog
     * @param position
     * @param holder
     */
    public static void showEditDialog(final int position, final ListItem holder) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.label_editar));
        final EditText input = new EditText(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setText(Configuration.getData().get(position).getName());
        input.setLayoutParams(lp);
        builder.setView(input);
        builder.setCancelable(false);
        builder.setPositiveButton(context.getString(R.string.bt_aceptar),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Configuration.getData().get(position).setName(input.getText().toString().trim());
                        holder.getTxtView().setText(Configuration.getData().get(position).getName());
                        holder.getSwipelayout().close();
                        Configuration.writeConfig(context);
                    }
                });
        builder.setNegativeButton(context.getString(R.string.bt_cancelar),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * Group delete confirm dialog
     * @param position
     * @param holder
     */
    public static void showDeleteDialog(final int position, final ListItem holder) {
        context = getUserActions().getContext();
        main = (MainActivity) context;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.title_eliminar));
        builder.setMessage(context.getString(R.string.label_confirmar));
        builder.setPositiveButton(context.getString(R.string.bt_aceptar),
                new DialogInterface.OnClickListener()  {
                    public void onClick(DialogInterface dialog, int id) {
                        Configuration.getData().remove(position);
                        Configuration.writeConfig(context);
                        // actualizo la vista
                       ((MainActivity) context).layElements();
                    }
                });
        builder.setNegativeButton(context.getString(R.string.bt_cancelar),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * Group add confirm dialog
     * @param holder
     */
    public static void showAddDialog(final ListItem holder) {
        context = getUserActions().getContext();
        main = (MainActivity) context;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        final EditText input = new EditText(context);
        input.setLayoutParams(lp);
        builder.setTitle(context.getString(R.string.title_anadir));
        builder.setMessage(context.getString(R.string.label_anadir));
        builder.setView(input);
        builder.setPositiveButton(context.getString(R.string.bt_aceptar),
                new DialogInterface.OnClickListener()  {
                    public void onClick(DialogInterface dialog, int id) {
                        DataObject tmp = new DataObject();
                        tmp.setName(input.getText().toString().trim());
                        tmp.setChildren(new ArrayList<DataObject>());
                        Configuration.getData().add(tmp);
                        Configuration.writeConfig(context);
                        // actualizo la vista
                        ((MainActivity) context).layElements();
                    }
                })
                .setNegativeButton(context.getString(R.string.bt_cancelar),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * Child add confirm dialog
     * @param position
     * @param holder
     */
    public static void showAddChildDialog(final int position, final ChildListItem holder) {
        context = getUserActions().getContext();
        main = (MainActivity) context;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        final EditText input = new EditText(context);
        input.setLayoutParams(lp);
        builder.setTitle(context.getString(R.string.title_anadir));
        builder.setMessage(context.getString(R.string.label_anadir_el));
        builder.setView(input);
        builder.setPositiveButton(context.getString(R.string.bt_aceptar),
                new DialogInterface.OnClickListener()  {
                    public void onClick(DialogInterface dialog, int id) {
                        DataObject tmp = new DataObject();
                        tmp.setName(input.getText().toString().trim());
                        tmp.setChildren(new ArrayList<DataObject>());
                        Configuration.getData().get(position).getChildren().add(tmp);
                        Configuration.writeConfig(context);
                        // actualizo la vista
                        ((MainActivity) context).layElements();
                    }
                })
                .setNegativeButton(context.getString(R.string.bt_cancelar),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    /**
     * Child edit confirm dialog
     * @param groupPos
     * @param childPos
     * @param holder
     */
    public static void showEditChildDialog(final int groupPos, final int childPos, final ChildListItem holder) {
        context = getUserActions().getContext();
        main = (MainActivity) context;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.label_editar));
        final EditText input = new EditText(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setText(Configuration.getData().get(groupPos).getChildren().get(childPos).getName());
        input.setLayoutParams(lp);
        builder.setView(input);
        builder.setCancelable(false);
        builder.setPositiveButton(context.getString(R.string.bt_aceptar),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Configuration.getData().get(groupPos).getChildren().get(childPos).setName(input.getText().toString().trim());
                        holder.getTxtView().setText(Configuration.getData().get(groupPos).getChildren().get(childPos).getName());
                        DataObject child =  Configuration.getData().get(groupPos).getChildren().get(childPos);
                        child.setName(input.getText().toString().trim());
                        holder.getTxtView().setText(child.getName());
                        Configuration.setData((List<DataObject>) Configuration.getData());
                        Configuration.writeConfig(context);
                    }
                });
        builder.setNegativeButton(context.getString(R.string.bt_cancelar),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    /**
     * Child delete confirm dialog
     * @param groupPos
     * @param childPos
     */
    public static void showDeleteChildDialog(final int groupPos, final int childPos) {
        context = getUserActions().getContext();
        main = (MainActivity) context;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.title_eliminar));
        builder.setMessage(context.getString(R.string.label_confirmar));
        builder.setPositiveButton(context.getString(R.string.bt_aceptar),
                new DialogInterface.OnClickListener()  {
                    public void onClick(DialogInterface dialog, int id) {
                       List<DataObject> data = Configuration.getData().get(groupPos).getChildren();
                       data.remove(childPos);
                       Configuration.writeConfig(context);
                       ((MainActivity) context).layElements();
                    }
                });
        builder.setNegativeButton(context.getString(R.string.bt_cancelar),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}