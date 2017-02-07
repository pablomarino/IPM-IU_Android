package es.udc.ipm.ipm_p2;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOError;
import java.util.List;

import es.udc.ipm.ipm_p2.control.DialogsHandler;
import es.udc.ipm.ipm_p2.control.UserActions;
import es.udc.ipm.ipm_p2.util.ThumbGenerator;
import es.udc.ipm.ipm_p2.view.fragment.ChildListFragment;
import es.udc.ipm.ipm_p2.view.fragment.ItemFragment;
import es.udc.ipm.ipm_p2.view.fragment.MainListFragment;
import es.udc.ipm.ipm_p2.configuration.Configuration;
import es.udc.ipm.ipm_p2.configuration.structs.ConfigurationObject;
import es.udc.ipm.ipm_p2.configuration.structs.DataObject;


public class MainActivity extends AppCompatActivity {

    private static int selectedCategory = -1;
    private static int selectedItem = -1;
    private UserActions uActions;


    @Override
    public void onConfigurationChanged(android.content.res.Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.main_activity);
        layElements();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String loadedConf;
        setContentView(R.layout.main_activity);
        try {
            // cargo json de configuracion desde almacenamiento local
            loadedConf = Configuration.loadConfig(this);
        } catch (IOError | FileNotFoundException e) {
            Log.e("MainActivity", "Error leyendo configuración");
            loadedConf = "{}";
        }
        ConfigurationObject results = Configuration.parseConfig(loadedConf);
        Configuration.setResults(results);
        ThumbGenerator.setColorSchemes(results.getColorSchemes());
        List<DataObject> data = results.getData();
        Configuration.setData(data);
        DialogsHandler.setContext(this);
        DialogsHandler.showStartDialog();
        layElements();
    }


    public void layElements() {
        uActions = new UserActions(this);
        ConfigurationObject results = Configuration.getResults();
        if (results != null) {
            // Actualizo los fragments de la pantalla dependiendo de la orientacion y tamaño
            MainListFragment plf = new MainListFragment();
            ChildListFragment clf = new ChildListFragment();
            ItemFragment ifr = new ItemFragment();
            // uso el tag del xml para saber que layout utilizo
            if (findViewById(R.id.layout).getTag().equals("landscape")) {
                layLandscape(plf, clf, ifr);
            } else {
                layPortrait(plf, clf, ifr);
            }
        }
    }

    private void layPortrait(MainListFragment plf, ChildListFragment clf, ItemFragment ifr){
        // Portrait
        FragmentTransaction ft;
        if (selectedCategory == -1 && selectedItem == -1) {
            // es visible la lista de categorias
            uActions.showAddFloatBt();
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, plf);
            ft.commit();

            ((TextView) findViewById(R.id.tv_footer)).setText(R.string.label_sin_seleccion);

        } else if (selectedItem == -1) {
            // es visible la Lista de subcategorias
            uActions.showBackFloatBt();
            uActions.showAddChildFloatBt();
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, clf);
            ft.commit();
            ((TextView) findViewById(R.id.tv_footer)).setText("/"+Configuration.getData().get(selectedCategory).getName());
        } else{
            // es visible el Landing
            uActions.showBackFloatBt();
            uActions.hideAddChildFloatBt();
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, ifr);
            ft.commit();
            ifr.setItemName(Configuration.getData().get(selectedCategory).getChildren().get(selectedItem).getName());

            ((TextView) findViewById(R.id.tv_footer)).setText(
                    "/"+Configuration.getData().get(selectedCategory).getName()+
                    "/"+Configuration.getData().get(selectedCategory).getChildren().get(selectedItem).getName()
            );

        }

        // acciones de btBack
        ((FloatingActionButton) findViewById(R.id.back)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (selectedItem != -1 && selectedCategory != -1) {
                    // en landing
                    selectedItem = -1;
                    layElements();
                } else if (selectedCategory != -1) {
                    // en subcategoria
                    selectedCategory = -1;
                    selectedItem = -1;
                    layElements();
                }
            }
        });
    }

    private void layLandscape(MainListFragment plf, ChildListFragment clf, ItemFragment ifr){
        FragmentTransaction ft;
        //Landscape
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.parent, plf);
        ft.commit();
        // Lista de subcategorias
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.children, clf);
        ft.commit();
        // Landing
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.landing, ifr);

        // botones add
        if (selectedCategory == -1 && selectedItem == -1) {
            // sin seleccion 
            uActions.showAddFloatBtLand();
            ((TextView) findViewById(R.id.tv_footer_0)).setText(R.string.label_sin_seleccion);
            ((TextView) findViewById(R.id.tv_footer_1)).setText("");
            ((TextView) findViewById(R.id.tv_footer_2)).setText("");
        } else if (selectedItem == -1) {
            //Categoria seleccionada
            uActions.showAddChildFloatBtLand();
            ((TextView) findViewById(R.id.tv_footer_0)).setText("");
            ((TextView) findViewById(R.id.tv_footer_1)).setText("‣ "+Configuration.getData().get(selectedCategory).getName());
            ((TextView) findViewById(R.id.tv_footer_2)).setText("");
        }

        // texto seleccion
        if(selectedItem!=-1){
            ifr.setItemName(Configuration.getData().get(selectedCategory).getChildren().get(selectedItem).getName());
            ((TextView) findViewById(R.id.tv_footer_0)).setText("");
            ((TextView) findViewById(R.id.tv_footer_1)).setText("");
            ((TextView) findViewById(R.id.tv_footer_2)).setText("‣ "+Configuration.getData().get(selectedCategory).getName()+" ‣ "+Configuration.getData().get(selectedCategory).getChildren().get(selectedItem).getName());
        }else {
            ifr.setItemName("");
        }
        ft.commit();
    }


    public static int getSelectedCategory() {
        return selectedCategory;
    }

    public static void setSelectedCategory(int selectedCategory) {
        MainActivity.selectedCategory = selectedCategory;
        android.content.res.Configuration newConfig = new android.content.res.Configuration();
    }

    public static void setSelectedItem(int selectedItem) {
        MainActivity.selectedItem = selectedItem;
    }

}