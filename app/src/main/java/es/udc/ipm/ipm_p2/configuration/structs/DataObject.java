package es.udc.ipm.ipm_p2.configuration.structs;

import java.util.ArrayList;

/**
 * Created by Pablo Mariño on 27/10/16.
 */

public class DataObject {
    String name;
    ArrayList<DataObject> children;

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<DataObject>  getChildren(){
        return children;
    }

    public void setChildren(ArrayList<DataObject> children) {
        this.children = children;
    }
}