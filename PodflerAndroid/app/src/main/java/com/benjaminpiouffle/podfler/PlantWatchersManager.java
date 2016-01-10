package com.benjaminpiouffle.podfler;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by piouffb on 10/01/16.
 */
public class PlantWatchersManager {
    private final static String SAVE_FILENAME = "plantwachers";
    private final Context context;
    private List<PlantWatcher> plantWatchers;

    public PlantWatchersManager(Context context) {
        this.context = context;

        // Load plants
        this.plantWatchers = this.load();

        // If not plant exists, create one
        if (this.plantWatchers == null || this.plantWatchers.size() == 0) {
            this.plantWatchers = new ArrayList<>();
            this.plantWatchers.add(new PlantWatcher("[Room] Baobab", "xx.xx.xx.xx"));
            this.save();
        }
    }

    private List<PlantWatcher> load() {
        List<PlantWatcher> plantWatchers = null;

        try {
            FileInputStream stream = this.context.openFileInput(SAVE_FILENAME);
            ObjectInputStream objectInputStream = new ObjectInputStream(stream);
            plantWatchers = (List<PlantWatcher>) objectInputStream.readObject();
            objectInputStream.close();
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return plantWatchers;
    }

    private void save() {
        try {
            FileOutputStream outputStream = this.context.openFileOutput(SAVE_FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(this.plantWatchers);
            objectOutputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final List<PlantWatcher> getList() {
        return this.plantWatchers;
    }

    public final PlantWatcher[] getArray() {
        PlantWatcher[] watchersArray = new PlantWatcher[this.plantWatchers.size()];
        return this.plantWatchers.toArray(watchersArray);
    }
}
