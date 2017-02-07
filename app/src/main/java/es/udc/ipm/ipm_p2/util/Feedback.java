package es.udc.ipm.ipm_p2.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import es.udc.ipm.ipm_p2.configuration.Configuration;

/**
 * Created by Pablo Mariño on 8/11/16.
 */

public class Feedback {

    /**
     * Shows info on screen
     * @param view
     * @param string
     * @param duration Toast.LENGTH_SHORT,LENGTH_LONG or an integer
     */
    public static void showTip(View view, String string, Integer duration){
        Context context = view.getContext();
        duration = duration != null ? duration : Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, string, duration);
        toast.show();
    }



    /**
     * Returns path to the storage directory from the local filesystem
     * @param  activity
     * @return string
     */
    public static String getFullPath(Activity activity){
        String fullPath = activity.getFilesDir() + "/" + Configuration.getlocalFilename();
        return fullPath.replace("/","_");
    }

    /*
    public static void showTutorial(View v){

    }
    */

    /*
    public void write(String file, String content){
        Log.d("#####",getFilesDir() + "/" +Configuration.getLocalFilename());
       try {
            FileOutputStream fs = openFileOutput(getFullPath(), MODE_PRIVATE);
            fs.write(content.getBytes());
            fs.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    */
}
