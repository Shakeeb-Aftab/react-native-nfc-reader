// NfcReaderModule.java

package com.reactlibrary;


import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.nfc_test.newarchitecture.nfc_utils.NfcCardReaderActivity;

public class NfcReaderModule extends ReactContextBaseJavaModule implements ActivityEventListener {

    private final ReactApplicationContext reactContext;
        private Callback mCallback;


    public NfcReaderModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "NfcReader";
    }

     @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, @Nullable Intent intent) {
        if (requestCode == 101) {
            if (resultCode == Activity.RESULT_OK) {
                if (mCallback != null) {
                    mCallback.invoke(intent.getStringExtra("data"));
                }
            }
        }
    }

    @Override
    public void onNewIntent(Intent intent) {

    }


    @ReactMethod
    public void startNFCScan(Callback callback) {
        mCallback = callback;

        Activity activity = getCurrentActivity();
        Intent scanIntent = new Intent(activity, NfcCardReaderActivity.class);
        if (activity != null) {
            activity.startActivityForResult(scanIntent, 101);
        }
    }
}
