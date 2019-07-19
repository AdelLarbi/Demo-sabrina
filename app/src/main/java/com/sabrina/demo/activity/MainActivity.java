package com.sabrina.demo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sabrina.demo.ObjectBox;
import com.sabrina.demo.R;
import com.sabrina.demo.model.entity.Purchase;
import com.sabrina.demo.model.entity.User;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_USERNAME = "com.sabrina.demo.activity.MainActivity.username";

    private ProgressBar mLoading;
    private TextView mLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Init ObjectBox with data */
        addUsers();
        addPurchases();

        /* Resources binging */
        Button mLogin = findViewById(R.id.bt_login);
        final EditText mUsername = findViewById(R.id.et_username);
        final EditText mPassword = findViewById(R.id.et_password);
        mLoading = findViewById(R.id.pb_loading);
        mLog = findViewById(R.id.tv_log);

        /* Action login */
        mLogin.setOnClickListener(view -> {
            hideSoftKeyboard(MainActivity.this);
            mLog.setVisibility(View.INVISIBLE);
            mLoading.setVisibility(View.VISIBLE);

            String username = mUsername.getText().toString();
            String password = mPassword.getText().toString();

            login(username, password);
        });
    }

    /**
     * Add users to ObjectBox
     */
    private void addUsers() {
        ObjectBox.users.put(new User("user1","password1"));
        ObjectBox.users.put(new User("user2","password2"));
        ObjectBox.users.put(new User("user3","password3"));
        ObjectBox.users.put(new User("user4","password4"));
    }

    /**
     * Add purchases to ObjectBox
     */
    private void addPurchases() {
        User user1 = ObjectBox.users.findUserByUsername("user1");
        ObjectBox.purchases.put(new Purchase("1/01/2001", "product0", 0.99, user1));

        User user2 = ObjectBox.users.findUserByUsername("user2");
        ObjectBox.purchases.put(new Purchase("11/01/2011", "product1", 11.11, user2));
        ObjectBox.purchases.put(new Purchase("12/01/2011", "product2", 22.2, user2));
        ObjectBox.purchases.put(new Purchase("13/01/2011", "product3", 3.3, user2));
        ObjectBox.purchases.put(new Purchase("14/01/2011", "product4", 44.4, user2));
        ObjectBox.purchases.put(new Purchase("15/01/2011", "product5", 5.55, user2));

        User user3 = ObjectBox.users.findUserByUsername("user3");
        ObjectBox.purchases.put(new Purchase("21/02/2012", "product6", 666.6, user3));
        ObjectBox.purchases.put(new Purchase("22/02/2012", "product7", 77.77, user3));
        ObjectBox.purchases.put(new Purchase("23/02/2012", "product8", 8, user3));

        User user4 = ObjectBox.users.findUserByUsername("user4");
        ObjectBox.purchases.put(new Purchase("30/03/2013", "product9", 99.9, user4));
        ObjectBox.purchases.put(new Purchase("31/03/2013", "product10", 10, user4));
    }

    /**
     * Try to login using the credentials below
     * @param username the username
     * @param password the password
     */
    private void login(String username, String password) {
        boolean isLoginSuccess = ObjectBox.users.isExist(username, password);
        mLoading.setVisibility(View.INVISIBLE);
        if (isLoginSuccess) {
            Intent intent = new Intent(getApplicationContext(), PurchaseActivity.class);
            intent.putExtra(EXTRA_USERNAME, username);
            startActivity(intent);
        } else {
            mLog.setVisibility(View.VISIBLE);
            mLog.setText(R.string.error_log_in);
        }
    }

    /**
     * Hide keyboard
     * @param activity context
     */
    private void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(
                    Objects.requireNonNull(activity.getCurrentFocus()).getWindowToken(), 0);
        }
    }
}
