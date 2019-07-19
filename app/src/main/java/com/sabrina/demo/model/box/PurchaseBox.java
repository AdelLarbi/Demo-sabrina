package com.sabrina.demo.model.box;

import com.sabrina.demo.ObjectBox;
import com.sabrina.demo.model.entity.Purchase;

import io.objectbox.Box;

public class PurchaseBox {

    private Box<Purchase> purchaseBox;

    public PurchaseBox() {
        purchaseBox = ObjectBox.get().boxFor(Purchase.class);
    }

    public void put(Purchase purchase) {
        purchaseBox.put(purchase);
    }
}
