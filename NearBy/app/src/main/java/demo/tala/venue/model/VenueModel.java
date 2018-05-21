package demo.tala.venue.model;

public class VenueModel {

  private String name;
  private String distance;
  private String venueIconUrl;

  public VenueModel(String name, String distance, String iconUrl) {
    this.name = name;
    this.distance = distance;
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

}