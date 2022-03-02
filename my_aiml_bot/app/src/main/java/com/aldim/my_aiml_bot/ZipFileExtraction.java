package com.aldim.my_aiml_bot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import android.content.Context;
import android.util.Log;

public class ZipFileExtraction
{
    public void unZipIt(InputStream zipFile, String outputFolder)
    {
        try
        {
            //get the zip file content
            ZipInputStream zin = new ZipInputStream(zipFile);
            //get the zipped file list entry
            ZipEntry entry = null;
            int bytesRead;
            byte[] buffer = new byte[4096];

            while ((entry = zin.getNextEntry()) != null) {
                if (entry.isDirectory()) {
                    File dir = new File(outputFolder, entry.getName());
                    if (!dir.exists()) {
                        dir.mkdir();
                    }
                    Log.d("Zip Extractor", "[DIR] "+entry.getName());
                } else {
                    FileOutputStream fos = new FileOutputStream(outputFolder + entry.getName());
                    while ((bytesRead = zin.read(buffer)) != -1) {
                        fos.write(buffer, 0, bytesRead);
                    }
                    fos.close();
                    Log.d("Zip Extractor", "[FILE] "+entry.getName());
                }
            }
            zin.close();
            System.out.println("Done");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public String ExtractBot (Context context){
        File fileExt = new File(context.getExternalFilesDir(null).getAbsolutePath()+"/bots");
        if(!fileExt.exists())
        {
            ZipFileExtraction extract = new ZipFileExtraction();
            try
            {
                extract.unZipIt(context.getAssets().open("bots.zip"), context.getExternalFilesDir(null).getAbsolutePath()+"/");
            } catch (Exception e) { e.printStackTrace(); }
        }
        return context.getExternalFilesDir(null).getAbsolutePath();
    }

}