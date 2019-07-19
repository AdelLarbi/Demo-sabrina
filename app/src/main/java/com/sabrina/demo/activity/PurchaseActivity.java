package com.sabrina.demo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.sabrina.demo.ObjectBox;
import com.sabrina.demo.R;
import com.sabrina.demo.model.entity.Purchase;
import com.sabrina.demo.model.entity.User;

import io.objectbox.relation.ToMany;

public class PurchaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        /* Resources binding */
        TextView mHeader = findViewById(R.id.tv_header);
        ListView mPurchases = findViewById(R.id.lv_purchases);

        Intent intent = getIntent();
        if (intent != null) {
            String username = intent.getStringExtra(MainActivity.EXTRA_USERNAME);

            /* Show header */
            Resources res = getResources();
            String userPurchases = res.getString(R.string.text_user_purchases, username);
            mHeader.setText(userPurchases);

            /* Show purchases list */
            User user = ObjectBox.users.findUserByUsername(username);
            final ToMany<Purchase> purchases = user.getPurchases();
            String[] purchasesArray = new String[purchases.size()];
            for (int i = 0; i < purchases.size(); i++) {
                purchasesArray[i] = purchases.get(i).getProduct();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(PurchaseActivity.this,
                    android.R.layout.simple_list_item_1, purchasesArray);
            mPurchases.setAdapter(adapter);

            /* Action show purchase details */
            mPurchases.setOnItemClickListener((adapterView, view, i, l)
                    -> showPurchaseDetails(purchases.get(i)));
        }
    }

    /**
     * Show purchase details using an alert dialog
     * @param purchase the product to show
     */
    private void showPurchaseDetails(Purchase purchase) {
        if (purchase != null) {
            String details = "";
            details += "- Date : " + purchase.getDate();
            details += "\n";
            details += "- Price : " + purchase.getPrice() + "€";

            new MaterialAlertDialogBuilder(this)
                    .setTitle(purchase.getProduct())
                    .setMessage(details)
                    .setPositiveButton(R.string.alert_positive_ok, (dialog, i) -> dialog.dismiss())
                    .show();
        }
    }
}
