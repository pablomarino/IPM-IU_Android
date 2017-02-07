package es.udc.ipm.ipm_p2.control;

/**
 * Created by Pablo Mariño on 8/11/16.
 */
import android.app.Activity;
import android.content.Context;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.Sensor;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.hardware.SensorEvent;
import android.view.View;
import android.widget.ListView;

import java.util.List;
import java.util.Random;

import es.udc.ipm.ipm_p2.MainActivity;
import es.udc.ipm.ipm_p2.R;
import es.udc.ipm.ipm_p2.configuration.Configuration;
import es.udc.ipm.ipm_p2.configuration.structs.DataObject;
import es.udc.ipm.ipm_p2.util.Feedback;
import es.udc.ipm.ipm_p2.view.ChildListItem;
import es.udc.ipm.ipm_p2.view.ListItem;

import com.squareup.seismic.ShakeDetector;

public class UserActions implements ShakeDetector.Listener, SensorEventListener{
    private int lastSelection = -1;
    private Context context;
    private SensorManager sensorMan;
    private float accelZ = 0;
    private int tics =0;
    private final int MAX_TICS=4;
    private List <Sensor> sensors;

    public Context getContext() {
        return context;
    }

    /**

     *
     * @param context
     */
    public UserActions(Context context){
        this.context = context;
        DialogsHandler.setContext(this.context);
        DialogsHandler.setUserActions(this);
        sensorMan = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        sensors = sensorMan.getSensorList(Sensor.TYPE_ALL);
        ShakeDetector sd = new ShakeDetector(this);
        sd.setSensitivity(sd.SENSITIVITY_LIGHT);
        sd.start(sensorMan);
        sensorMan.registerListener(this, sensorMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
    }

    /**
     * metodo que detecta el giro del terminal extiende de SensorEventListener
     * @param event
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float _accelZ = event.values[2];
            if (accelZ == 0) {
                accelZ = _accelZ;
            } else {
                if ((accelZ * _accelZ) < 0) {
                    tics++;
                    if (tics >= MAX_TICS) {
                        accelZ = _accelZ;
                        tics = 0;
                        if (_accelZ > 0) {
                        } else if (_accelZ < 0) {
                            randomize();
                        }
                    }
                } else {
                    if (tics > 0) {
                        accelZ = _accelZ;
                        tics = 0;
                    }
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    public void hearShake() {randomize();}

    /**
     *  selecciona al azar una categoria
     */
    public void randomize() {
        ListView listView;
        int randCat=0;
        List<DataObject> catDatos = Configuration.getData();
        Random rand = new Random();
        MainActivity main = (MainActivity) context;
        listView = (ListView) main.findViewById(android.R.id.list);

        switch (catDatos.size()){
            case 0:
                Log.d("Seleccion Aleatoria ","No es posible realizar la seleccion, la lista esta vacia.");
                Feedback.showTip(listView,context.getResources().getString(R.string.lista_vacia),2);
                break;
            case 1:
                Feedback.showTip(listView,context.getResources().getString(R.string.rand_selecion)+"0",2);
                main.setSelectedCategory(0);
                main.layElements();
                break;
            default:
                randCat = rand.nextInt(catDatos.size());
                while(lastSelection==randCat) {
                    randCat = rand.nextInt(catDatos.size());
                }
                Feedback.showTip(listView,context.getResources().getString(R.string.rand_selecion)+String.valueOf(randCat),2);
                listView.setSelection(randCat);
                lastSelection= randCat;
                main.setSelectedCategory(randCat);
                main.layElements();
                break;
        }

    }

    public void hideAddFloatBt() {
        FloatingActionButton btn = (FloatingActionButton) ((MainActivity)context).findViewById(R.id.add);
        if (btn != null) btn.hide();
    }

    public void showAddFloatBt() {
        FloatingActionButton btn = (FloatingActionButton) ((MainActivity)context).findViewById(R.id.add);
        btn.setOnClickListener(onAddListener(null));
        if (btn != null) btn.show();
        hideBackFloatBt();
        hideAddChildFloatBt();
    }

    public void hideAddChildFloatBt() {
        FloatingActionButton btn = (FloatingActionButton) ((MainActivity)context).findViewById(R.id.add_child);
        if (btn != null) btn.hide();
    }

    public void showAddChildFloatBt() {
        FloatingActionButton btn = (FloatingActionButton) ((MainActivity)context).findViewById(R.id.add_child);
        btn.setOnClickListener(onAddChildListener(null));
        if (btn != null) btn.show();
    }

    public void hideBackFloatBt() {
        FloatingActionButton btn = (FloatingActionButton) ((MainActivity)context).findViewById(R.id.back);
        if (btn != null) btn.hide();
    }

    public void showBackFloatBt() {
        FloatingActionButton btn = (FloatingActionButton) ((MainActivity)context).findViewById(R.id.back);
        if (btn != null) btn.show();
        hideAddFloatBt();
    }

    public void hideAddFloatBtLand() {
        FloatingActionButton btn = (FloatingActionButton) ((MainActivity)context).findViewById(R.id.land_float_add);
        if (btn != null) btn.hide();
    }

    public void showAddFloatBtLand() {
        FloatingActionButton btn = (FloatingActionButton) ((MainActivity)context).findViewById(R.id.land_float_add);
        btn.setOnClickListener(onAddListener(null));
        if (btn != null) btn.show();
        hideAddChildFloatBtLand();
    }

    public void hideAddChildFloatBtLand() {
        FloatingActionButton btn = (FloatingActionButton) ((MainActivity)context).findViewById(R.id.land_float_add_child);
        if (btn != null) btn.hide();
    }

    public void showAddChildFloatBtLand() {
        FloatingActionButton btn = (FloatingActionButton) ((MainActivity)context).findViewById(R.id.land_float_add_child);
        btn.setOnClickListener(onAddChildListener(null));
        if (btn != null) btn.show();
    }

    private View.OnClickListener onAddListener(final ListItem holder) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogsHandler.showAddDialog(holder);
            }
        };
    }

    private View.OnClickListener onAddChildListener(final ChildListItem holder) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogsHandler.showAddChildDialog(MainActivity.getSelectedCategory(),holder);
            }
        };
    }

}