package demo.tala.venue.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import demo.tala.venue.R;
import demo.tala.venue.model.VenueModel;
import demo.tala.venue.util.ImageDownloader;

public class VenueRecyclerAdapter extends RecyclerView.Adapter<VenueRecyclerAdapter.ViewHolder> {
    private List<VenueModel> venueModels;
    private Context context;

    public VenueRecyclerAdapter(Context context) {
        venueModels = new ArrayList<>();
        this.context = context;
    }

    public void setVenueData(List<VenueModel> data) {
        venueModels.clear();
        venueModels.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.venue_recycler_row, parent, false);
        return new ViewHolder(rowView);
    }

    @Override
    public int getItemCount() {
        return venueModels.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        VenueModel selectedVenue = venueModels.get(position);
        ImageDownloader.loadImage(context, selectedVenue.getVenueIconUrl(), holder.venueIcon);
        holder.venueName.setText(selectedVenue.getName());
        holder.venueDistance.setText(selectedVenue.getDistance());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView venueIcon;
        TextView venueName, venueDistance;

        public ViewHolder(View itemView) {
            super(itemView);
            venueIcon = (ImageView) itemView.findViewById(R.id.venue_icon);
            venueName = (TextView) itemView.findViewById(R.id.venue_name);
            venueDistance = (TextView) itemView.findViewById(R.id.venue_distance);

        }
    }
}