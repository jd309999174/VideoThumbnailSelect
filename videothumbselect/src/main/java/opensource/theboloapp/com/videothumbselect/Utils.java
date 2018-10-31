package opensource.theboloapp.com.videothumbselect;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Utils {

    public static int dpToPixels(float dp) {
        Resources r = Resources.getSystem();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }

    public static float pixelToDp(int pixel) {
        Resources r = Resources.getSystem();
        return (float) pixel / r.getDisplayMetrics().density;
    }

    public static String createFileForBitmap(Context context, Bitmap bitmap) {

        String filename = System.currentTimeMillis() + "_Thumb_Bitmap";

        Log.d("Utils", "Writing thumb bitmap : " + filename);

        try {
            FileOutputStream fileOutputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.close();
            bitmap.recycle();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            filename = "";
            Toast.makeText(context, "Failed to write thumb bitmap", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            filename = "";
            Toast.makeText(context, "Failed to write thumb bitmap", Toast.LENGTH_SHORT).show();
        }

        return filename;

    }

}
