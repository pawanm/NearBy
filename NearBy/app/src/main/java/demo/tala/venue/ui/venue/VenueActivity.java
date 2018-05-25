package demo.tala.venue.ui.venue;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import demo.tala.venue.R;
import demo.tala.venue.controller.LocationController;
import demo.tala.venue.core.IOnSingleClickListener;
import demo.tala.venue.model.VenueModel;
import demo.tala.venue.util.Logger;

public class VenueActivity extends AppCompatActivity {

    private VenueFragment venueFragment;

    private enum FRAGMENT_TAGS {VENUE, VENUE_DETAIL}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.venue_activity);

        venueFragment = new VenueFragment();
        venueFragment.attachOnClick(new IOnSingleClickListener<VenueModel>() {
            @Override
            public void onClick(VenueModel venueModel) {
                Logger.log("OnClick: " + venueModel.getName());
                VenueDetailFragment venueDetailFragment = new VenueDetailFragment();
                venueDetailFragment.setDetails(venueModel);
                startFragment(venueDetailFragment, FRAGMENT_TAGS.VENUE_DETAIL.name());
            }
        });

        startFragment(venueFragment, FRAGMENT_TAGS.VENUE.name());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case LocationController.REQUEST_PERMISSIONS_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Logger.log("Permission Granted");
                    if (venueFragment != null) {
                        venueFragment.locationPermissionGranted();
                    }
                } else {
                    Logger.log("Permission Denied");
                }
                return;
            }

        }
    }

    private void startFragment(Fragment fragment, String tagName) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, fragment, tagName);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}