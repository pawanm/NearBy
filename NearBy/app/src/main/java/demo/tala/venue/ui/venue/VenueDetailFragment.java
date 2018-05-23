package demo.tala.venue.ui.venue;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import demo.tala.venue.R;
import demo.tala.venue.core.BaseFragment;
import demo.tala.venue.model.VenueModel;
import demo.tala.venue.util.ImageDownloader;
import demo.tala.venue.util.Logger;
import demo.tala.venue.util.ProgressDialog;

/**
 * Created by admin on 23/05/18.
 */

public class VenueDetailFragment extends BaseFragment {
    private VenueModel venueDetails;
    private Context context;

    public void setDetails(VenueModel venueModel) {
        this.venueDetails = venueModel;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.venue_detail_fragment, container, false);
        ImageView imageView =  (ImageView) view.findViewById(R.id.venue_icon);
        TextView venueName = (TextView) view.findViewById(R.id.venue_name);

        ImageDownloader.loadImage(context,venueDetails.getVenueIconUrl(),imageView);
        venueName.setText(venueDetails.getName());

        return view;
    }

}
