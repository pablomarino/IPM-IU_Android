package es.udc.ipm.ipm_p2.configuration.structs;
import android.graphics.Color;
import java.util.ArrayList;
/**
 * Created by Pablo Mariño on 27/10/16.
 */


public class ColorObject {

    private ArrayList<String> scheme;
    private Color color= new Color();

    public void setScheme(ArrayList<String> scheme) {
        this.scheme = scheme;
    }

    public ArrayList<String> getScheme(){
        return scheme;
    }

    public int getColor0() { return color.parseColor(scheme.get(0)); }

    public int getColor1() {
        return color.parseColor(scheme.get(1));
    }

    public int getColor2() {
        return color.parseColor(scheme.get(2));
    }

    public int getColor3() {
        return color.parseColor(scheme.get(3));
    }

}
