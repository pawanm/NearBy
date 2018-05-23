package demo.tala.venue.ui.venue;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import demo.tala.venue.util.Logger;
import demo.tala.venue.R;
import demo.tala.venue.model.ICallBack;
import demo.tala.venue.controller.VenueController;
import demo.tala.venue.model.VenueResponse;
import demo.tala.venue.util.ProgressDialog;

public class VenueActivity extends AppCompatActivity {

    private enum FRAGMENT_TAGS {VENUE, VENUE_DETAIL}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startFragment(new VenueFragment(), FRAGMENT_TAGS.VENUE.name());
        setContentView(R.layout.venue_activity);
    }

    private void startFragment(Fragment fragment, String tagName) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container,fragment, tagName);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}