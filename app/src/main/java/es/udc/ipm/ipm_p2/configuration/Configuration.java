package es.udc.ipm.ipm_p2.configuration;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import es.udc.ipm.ipm_p2.R;
import es.udc.ipm.ipm_p2.configuration.structs.ConfigurationObject;
import es.udc.ipm.ipm_p2.configuration.structs.DataObject;

/**
 * Created by Pablo Mariño on 28/10/16.
 */

public class Configuration {
    private static String localFilename = "data.json";
    private static ConfigurationObject config;
    private static List<DataObject> data;
    private static ConfigurationObject results;

    /**
     * Returns the name of the configuration file
     * @return string
     */
    public static String getlocalFilename() {
        return localFilename;
    }

    /**
     * Getter function for results
     * @return
     */
    public static ConfigurationObject getResults() {
        return results;
    }

    /**
     * Setter function for results
     * @param results
     */
    public static void setResults(ConfigurationObject results) {
        Configuration.results = results;
    }

    /**
     * Getter function for data
     * @return
     */
    public static List<DataObject> getData() {
        return data;
    }

    /**
     * Setter function for data
     * @param data
     */
    public static void setData(List<DataObject> data) {
        Configuration.data = data;
    }

    /**
     * Loads data from internal storage config file
     * @param ctx
     * @return
     * @throws IOError
     * @throws FileNotFoundException
     */
    public static String loadConfig(Context ctx) throws IOError, FileNotFoundException{
        String temp = "{}";
//printLocalFiles(ctx);
//removeLocalFiles(ctx);
//printLocalFiles(ctx);

        File f=new File(ctx.getFilesDir()+"/"+localFilename);
        InputStream in;
        if(f.exists()){

                StringBuilder text = new StringBuilder();
                try {
                    BufferedReader br = new BufferedReader(new FileReader(f));
                    String line;

                    while ((line = br.readLine()) != null) {
                        text.append(line);
                        text.append('\n');
                    }
                    br.close();
                    temp = text.toString();
                }                catch (IOException e) {
                    throw new IOError(new Throwable("IOException"));
                }


       }else {
            try {
                in =ctx.getAssets().open(localFilename, ctx.MODE_PRIVATE);
                if (in != null) {
                    InputStreamReader tmp = new InputStreamReader(in);
                    BufferedReader reader = new BufferedReader(tmp);
                    String str;
                    StringBuffer buf = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buf.append(str + "\n");
                    }
                    in.close();

                    temp = buf.toString();
                    return temp;
                }
            } catch (java.io.FileNotFoundException e) {
                throw new IOError(new Throwable("FileNotFoundException"));
            } catch (IOException e) {
                throw new IOError(new Throwable("IOException"));
            }
        }
        return temp;
    }

    /**
     * Crea un fichero y escribe el json que obtiene al llamar a JSOnObjectToString
     * @param ctx
     */
    public static void writeConfig(Context ctx) {
        String output = JSOnObjectToString();
        try {
            FileOutputStream fos = ctx.openFileOutput(localFilename, Context.MODE_PRIVATE);
            fos.write(output.getBytes());
            fos.close();
        }catch (FileNotFoundException f){
            f.printStackTrace();
        }catch (IOException i){
            i.printStackTrace();
        }
    }

    /**
     * Elimina los ficheros locales de la aplicacion
     * @param ctx
     */
    private static void removeLocalFiles(Context ctx){
        String [] files = ctx.fileList();
        for (String f: files){
            Log.d("Ficheros locales ","Elimino fichero local /"+f);
            ctx.deleteFile(f);
        }
    }

    /**
     * Log de los ficheros locales de la aplicacion
     * @param ctx
     */
    private static void printLocalFiles(Context ctx){
        String [] files = ctx.fileList();
        for (String f: files){
            Log.d("Ficheros locales ","Fichero en almacenamiento local /"+f);
        }
    }

    /**
     * Returns a gson object after converting a string
     * @param str
     * @return Gson
     */
    public static ConfigurationObject parseConfig(String str) {
        Gson gson = new Gson();
        config = gson.fromJson(str, ConfigurationObject.class);
        return config;
    }

    /**
     * Returns a strin from a Gson
     * @return String
     */
    @NonNull
    public static String JSOnObjectToString() {
        Gson gson = new Gson();
        String tmpJson = gson.toJson(results);
        return tmpJson.toString();
    }

}



