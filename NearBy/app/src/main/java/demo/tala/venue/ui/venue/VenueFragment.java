package demo.tala.venue.ui.venue;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import demo.tala.venue.R;
import demo.tala.venue.controller.VenueController;
import demo.tala.venue.core.BaseFragment;
import demo.tala.venue.core.ICallBack;
import demo.tala.venue.core.IClickListener;
import demo.tala.venue.core.IOnSingleClickListener;
import demo.tala.venue.controller.LocationController;
import demo.tala.venue.core.RecyclerTouchListener;
import demo.tala.venue.model.VenueModel;
import demo.tala.venue.model.VenueResponse;
import demo.tala.venue.util.Logger;
import demo.tala.venue.util.ProgressDialog;


public class VenueFragment extends BaseFragment {
    private VenueRecyclerAdapter venuesRecyclerAdapter;
    private ProgressDialog progressDialog;
    private Context context;
    private IOnSingleClickListener onSingleClickListener;
    private LocationController locationController;

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.venue_fragment, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.venues_recycler_view);
        locationController = new LocationController(getActivity());

        assert recyclerView != null;
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        progressDialog = new ProgressDialog(context);
        venuesRecyclerAdapter = new VenueRecyclerAdapter(context);
        recyclerView.setAdapter(venuesRecyclerAdapter);
        getVenueDetails(34.017156, -118.494513);
        setClickListener(recyclerView);
        getCurrentLocationVenues();
        return view;
    }

    private void getCurrentLocationVenues() {
        locationController.getLastLocation(new ICallBack<Location>() {
            @Override
            public void response(Location location) {
                if (location != null) {
                    Logger.log("Lat: " + location.getLatitude() + ", Long: " + location.getLongitude());
                    getVenueDetails(location.getLatitude(), location.getLongitude());
                }
            }
        });
    }

    public void attachOnClick(IOnSingleClickListener clickListener) {
        this.onSingleClickListener = clickListener;
    }

    private void setClickListener(RecyclerView recyclerView) {
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(context,
                recyclerView, new IClickListener() {
            @Override
            public void onClick(View view, final int position) {
                if (onSingleClickListener != null) {
                    VenueModel model = venuesRecyclerAdapter.getItem(position);
                    onSingleClickListener.onClick(model);
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }


    private void getVenueDetails(Double lat, Double lng) {
        progressDialog.show();
        String latLong = lat + "," + lng;
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
