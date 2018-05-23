package demo.tala.venue.ui.venue;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import demo.tala.venue.R;
import demo.tala.venue.core.IOnSingleClickListener;
import demo.tala.venue.model.VenueModel;
import demo.tala.venue.util.Logger;

public class VenueActivity extends AppCompatActivity {

    private enum FRAGMENT_TAGS {VENUE, VENUE_DETAIL}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.venue_activity);

        VenueFragment venueFragment = new VenueFragment();
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

    private void startFragment(Fragment fragment, String tagName) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container,fragment, tagName);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}