package com.sabrina.demo;

import android.content.Context;

import androidx.annotation.NonNull;

import com.sabrina.demo.model.box.PurchaseBox;
import com.sabrina.demo.model.box.UserBox;
import com.sabrina.demo.model.entity.MyObjectBox;

import java.io.File;

import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;

public abstract class ObjectBox {

    private static BoxStore boxStore;
    public static UserBox users;
    public static PurchaseBox purchases;

    static void init(Context context) {
        BoxStore.deleteAllFiles(getDir(context)); // this may be removed to avoid resetting database
        boxStore = MyObjectBox.builder()
                .androidContext(context)
                .directory(getDir(context))
                .build();
        if (BuildConfig.DEBUG) {
            new AndroidObjectBrowser(boxStore).start(context);
        }
        initBoxes();
    }

    private static void initBoxes() {
        users = new UserBox();
        purchases = new PurchaseBox();
    }

    public static BoxStore get() {
        return boxStore;
    }

    @NonNull
    private static File getDir(Context context) {
        File targetFilesDir = context.getFilesDir();
        return new File(targetFilesDir, "objectbox/data");
    }
}