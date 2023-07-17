 package org.apache.cordova.securityprovider;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.widget.Toast;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.security.ProviderInstaller;

public class SecurityProvider extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("providerInstallerCheck")) {
            this.providerInstallerCheck(cordova.getActivity().getApplicationContext(), callbackContext);
            return true;
        }
        return false;
    }

    private void providerInstallerCheck(Context context, CallbackContext callbackContext) {
        try {
            ProviderInstaller.installIfNeeded(context);
            Toast.makeText(context, "Provider installed and updated!", Toast.LENGTH_LONG).show();
            callbackContext.success("Provider installed and updated!");
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
            callbackContext.error("Provider it outdated. Please update your Google Play Service");
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
            callbackContext.error("Google Play Services not available.");
        }
    }
}
