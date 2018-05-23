package demo.tala.venue.ui.venue;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import demo.tala.venue.R;
import demo.tala.venue.controller.VenueController;
import demo.tala.venue.core.BaseFragment;
import demo.tala.venue.model.ICallBack;
import demo.tala.venue.model.VenueResponse;
import demo.tala.venue.util.Logger;
import demo.tala.venue.util.ProgressDialog;


public class VenueFragment extends BaseFragment {
    private VenueRecyclerAdapter venuesRecyclerAdapter;
    private ProgressDialog progressDialog;
    private Context context;

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.venue_fragment, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.venues_recycler_view);

        assert recyclerView != null;
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        progressDialog = new ProgressDialog(context);
        venuesRecyclerAdapter = new VenueRecyclerAdapter(context);
        recyclerView.setAdapter(venuesRecyclerAdapter);

        //TODO: GetLocation
        getVenueDetails("34.017156,-118.494513");

        return view;
    }


    private void getVenueDetails(String latLong) {
        progressDialog.show();
        new VenueController().getVenuesData(latLong, context, new ICallBack<VenueResponse>() {
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
