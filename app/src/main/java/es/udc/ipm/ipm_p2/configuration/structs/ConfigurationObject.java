package es.udc.ipm.ipm_p2.configuration.structs;

import java.util.ArrayList;

/**
 * Created by Pablo Mariño on 25/10/16.
 */

public class ConfigurationObject {
    ArrayList<DataObject> data;
    ArrayList<ColorObject> colorschemes;

    public ArrayList<DataObject> getData(){
        if(data==null) {
            data = new ArrayList<DataObject>();
        }
        return data;
    }

    public  ArrayList<ColorObject> getColorSchemes(){
        return colorschemes;
    }
}

