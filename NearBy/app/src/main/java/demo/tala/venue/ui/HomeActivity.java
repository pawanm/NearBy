package demo.tala.venue.ui;

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

public class HomeActivity extends AppCompatActivity {
    private VenueRecyclerAdapter venuesRecyclerAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.venues_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        assert recyclerView != null;

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        progressDialog = new ProgressDialog(this);
        venuesRecyclerAdapter = new VenueRecyclerAdapter(HomeActivity.this);
        recyclerView.setAdapter(venuesRecyclerAdapter);


        //TODO: GetLocation

        progressDialog.show();
        new VenueController().getVenuesData("34.017156,-118.494513", this, new ICallBack<VenueResponse>() {
            @Override
            public void response(VenueResponse venueResponse) {
                venuesRecyclerAdapter.setVenueData(venueResponse.getVenueModelList());
                if (venueResponse.getMessageCode() == 1) {
                    Logger.log("Success: " + venueResponse.getVenueModelList().size());
                } else {
                    Logger.log("Error: " + venueResponse.getMessage());
                }
                progressDialog.dismiss();
            }
        });
    }
}