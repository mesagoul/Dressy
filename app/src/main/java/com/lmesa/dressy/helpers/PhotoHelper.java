package com.lmesa.dressy.helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by Lucas on 27/05/2017.
 */

public class PhotoHelper {


    public File resizeImage(File imageFile, int width) {
        // we'll start with the original picture already open to a file
        File imgFileOrig = imageFile;
        Bitmap b = BitmapFactory.decodeFile(imgFileOrig.getAbsolutePath());
        // original measurements
        int origWidth = b.getWidth();
        int origHeight = b.getHeight();

        final int destWidth = width;

        if (origWidth > destWidth) {
            // picture is wider than we want it, we calculate its target height
            int destHeight = origHeight / (origWidth / destWidth);
            // we create an scaled bitmap so it reduces the image, not just trim it
            Bitmap b2 = Bitmap.createScaledBitmap(b, destWidth, destHeight, false);
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            // compress to the format you want, JPEG, PNG...
            // 70 is the 0-100 quality percentage
            b2.compress(Bitmap.CompressFormat.JPEG, 85, outStream);
            // we save the file, at least until we have made use of it
            File f = new File(Environment.getExternalStorageDirectory()
                    + File.separator + imageFile.getName());
            try {
                f.createNewFile();
                //write the bytes in file
                FileOutputStream fo = new FileOutputStream(f);
                fo.write(outStream.toByteArray());
                // remember close de FileOutput
                fo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return f;

        }
        return null;
    }
}
