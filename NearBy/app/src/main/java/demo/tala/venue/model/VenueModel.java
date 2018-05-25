package demo.tala.venue.model;

import demo.tala.venue.util.Logger;

public class VenueModel {

    private String name;
    private String distance;
    private String venueIconUrl;

    public VenueModel(String name, String distance, String iconUrl) {
        this.name = name;
        this.distance = getParsedDistance(distance);
        this.venueIconUrl = iconUrl;
    }

    public String getName() {
        return name;
    }

    public String getDistance() {
        return distance;
    }

    public String getVenueIconUrl() {
        return venueIconUrl;
    }

    private String getParsedDistance(String distance) {
        try {
            int dis = Integer.parseInt(distance)/1000;
            return String.valueOf(dis);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.logE(ex.getMessage());
        }
        return "";
    }


}