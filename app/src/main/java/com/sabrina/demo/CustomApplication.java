/*
 * Copyright (c) 2019 Segula Technologies - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited,
 * proprietary and confidential.
 */

package com.sabrina.demo;

import android.app.Application;

public class CustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ObjectBox.init(this);
    }
}
