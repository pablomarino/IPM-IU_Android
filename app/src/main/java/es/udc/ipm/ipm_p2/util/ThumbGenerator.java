package es.udc.ipm.ipm_p2.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import java.util.List;

import es.udc.ipm.ipm_p2.R;
import es.udc.ipm.ipm_p2.configuration.structs.ColorObject;
import es.udc.ipm.ipm_p2.view.ChildListItem;
import es.udc.ipm.ipm_p2.view.ListItem;

/**
 * Created by qojop on 20/11/16.
 */

public final class ThumbGenerator {
    private static List <ColorObject> colorSchemes;

    public static List<ColorObject> getColorSchemes() {
        return colorSchemes;
    }

    public static void setColorSchemes(List<ColorObject> colorSchemes) {
        ThumbGenerator.colorSchemes = colorSchemes;
    }

    public static void drawTriangularThumb(ListItem view, int hash){
        Bitmap bitmap = Bitmap.createBitmap(96, 96, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.getThumbnail().setImageBitmap(bitmap);
        int key = Math.abs(hash % colorSchemes.size());
        ColorObject cs = colorSchemes.get(key);
        Paint paint = new Paint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setShadowLayer(1,0,0, Color.argb(80,60,60,60));

        paint.setColor(cs.getColor0());

        Path path = new Path();
        path.moveTo(48,0);
        path.lineTo(96,78);
        path.lineTo(0,78);
        path.close();
        canvas.drawPath(path, paint);

        path = new Path();
        paint.setColor(cs.getColor1());
        path.moveTo(48,15);
        path.lineTo(81,68);
        path.lineTo(15,68);
        path.close();
        canvas.drawPath(path, paint);

        path = new Path();
        paint.setColor(cs.getColor2());
        path.moveTo(48,30);
        path.lineTo(66,60);
        path.lineTo(30,60);
        path.close();
        canvas.drawPath(path, paint);

        path = new Path();
        paint.setColor(cs.getColor3());
        path.moveTo(48,40);
        path.lineTo(56,56);
        path.lineTo(40,56);
        path.close();
        canvas.drawPath(path, paint);
    }


    public static void drawCircularThumb(ChildListItem view, int hash){
        Bitmap bitmap = Bitmap.createBitmap(96, 96, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.getThumbnail().setImageBitmap(bitmap);
        int key = Math.abs(hash % colorSchemes.size());
        ColorObject cs = colorSchemes.get(key);
        Paint paint = new Paint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setShadowLayer(1,0,0, Color.argb(80,60,60,60));
        paint.setColor(cs.getColor0());
        canvas.drawCircle(48,48,32,paint);
        paint.setColor(cs.getColor1());
        canvas.drawCircle(48,48,18,paint);
        paint.setColor(cs.getColor2());
        canvas.drawCircle(48,48,9,paint);
        paint.setColor(cs.getColor3());
        canvas.drawCircle(48,48,5,paint);
    }

    public static void drawSquareThumb(ListItem li, int hash){
        Bitmap bitmap = Bitmap.createBitmap(96, 96, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        li.getThumbnail().setImageBitmap(bitmap);

        int key = Math.abs(hash % colorSchemes.size());
        ColorObject cs = colorSchemes.get(key);
        Paint paint = new Paint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setShadowLayer(1,0,0, Color.argb(80,60,60,60));
        paint.setColor(cs.getColor0());
        canvas.drawRect(10,  10, 86, 50, paint);
        paint.setColor(cs.getColor1());
        canvas.drawRect(10, 50, 86, 66, paint);
        paint.setColor(cs.getColor2());
        canvas.drawRect(10, 66, 86, 82, paint);
        paint.setColor(cs.getColor3());
        canvas.drawRect(10, 82, 86, 86, paint);
    }



}
