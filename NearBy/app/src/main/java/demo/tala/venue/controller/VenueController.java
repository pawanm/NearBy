package demo.tala.venue.controller;

import android.content.Context;

import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import demo.tala.venue.contants.Parser;
import demo.tala.venue.model.ICallBack;
import demo.tala.venue.model.VenueModel;
import demo.tala.venue.model.VenueResponse;
import demo.tala.venue.util.Logger;

import static demo.tala.venue.contants.API.clientId;
import static demo.tala.venue.contants.API.clientSecret;
import static demo.tala.venue.contants.API.fsAPIUrl;

public class VenueController {

    public void getVenuesData(String latLong, Context mainContext, final ICallBack<VenueResponse> venueResponseICallBack) {
        final Context context = mainContext;
        final VenueResponse venueResponse = new VenueResponse();

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String foursquareRequestUrl = fsAPIUrl + "client_id=" + clientId +
                "&client_secret=" + clientSecret + "&v=20170801&ll=" + latLong;

        Logger.log("RequestUrl: " + foursquareRequestUrl);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, foursquareRequestUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        venueResponse.setVenueList(getVenueList(response));
                        venueResponse.setMessageCode(1);
                        venueResponse.setMessage("success");
                        venueResponseICallBack.response(venueResponse);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        venueResponse.setMessage(error.getMessage());
                        venueResponse.setMessageCode(0);
                        venueResponseICallBack.response(venueResponse);
                    }
                });


        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsObjRequest);
    }


    private List<VenueModel> getVenueList(JSONObject response) {
        List<VenueModel> venueList = new ArrayList<>();
        try {
            JSONArray groups = response.getJSONObject(Parser.RES).getJSONArray(Parser.GRP);
            Logger.log("Groups: " + groups.length());
            JSONArray items = groups.getJSONObject(0).getJSONArray(Parser.ITM);
            Logger.log("Items: " + items.length());
            for (int i = 0; i < items.length(); i++) {
                JSONObject venuesJson = items.getJSONObject(i).getJSONObject(Parser.VEN);
                JSONObject location = venuesJson.getJSONObject(Parser.LOC);
                String name = venuesJson.getString(Parser.NAM);
                String distance = location.getString(Parser.DIS);
                String iconUrl = getIconUri(venuesJson.getJSONArray(Parser.CAT));
                VenueModel model = new VenueModel(name, distance, iconUrl);
                venueList.add(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.log("getVenueList: " + e.getMessage());
        }
        Logger.log("Success: " + venueList.size());
        return venueList;
    }

    private String getIconUri(JSONArray venueCategories) {
        try {
            return venueCategories.getJSONObject(0).getJSONObject(Parser.ICO).getString("prefix") + "64"
                    + venueCategories.getJSONObject(0).getJSONObject(Parser.ICO).getString("suffix");
        } catch (JSONException e) {
            e.printStackTrace();
            Logger.logE(e.getMessage());
        }
        return null;
    }
}
