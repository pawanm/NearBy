package demo.tala.venue.controller;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import demo.tala.venue.core.ICallBack;
import demo.tala.venue.util.Logger;

/**
 * Created by admin on 24/05/18.
 */

public class LocationController {
    private final Activity activityContext;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;

    public LocationController(Activity activity) {
        activityContext = activity;
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activityContext);
    }

    public void requestPermissions() {
        ActivityCompat.requestPermissions(activityContext,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                REQUEST_PERMISSIONS_REQUEST_CODE);
    }

    @SuppressLint("MissingPermission")
    public void getLastLocation(final ICallBack<Location> locationCallBack) {
        if(checkPermissions()) {
            Logger.log("Permission Available");
            fusedLocationProviderClient.getLastLocation()
                    .addOnCompleteListener(activityContext, new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            Logger.log("onComplete: " + task.isSuccessful());
                            if (task.isSuccessful() && task.getResult() != null) {
                                locationCallBack.response(task.getResult());
                            }
                        }
                    });
        }
    }

    public boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(activityContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activityContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Logger.log("Location Permission not available");
            requestPermissions();
            return false;
        }
        return true;
    }


}
