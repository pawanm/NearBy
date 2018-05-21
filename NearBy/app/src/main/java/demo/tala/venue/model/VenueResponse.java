package demo.tala.venue.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 18/05/18.
 */

public class VenueResponse {
    private List<VenueModel> venueModelList;
    private String message;
    private int messageCode;

    public VenueResponse() {
        venueModelList = new ArrayList<>();
        message = "";
        messageCode = 0;
    }

    public List<VenueModel> getVenueModelList() {
        return venueModelList;
    }

    public String getMessage() {
        return message;
    }

    public int getMessageCode() {
        return messageCode;
    }

    public void setVenueList(List<VenueModel> data) {
        venueModelList=data;
    }

    public void setMessage(String value) {
        message = value;
    }

    public void setMessageCode(int value) {
        messageCode = value;
    }


}
