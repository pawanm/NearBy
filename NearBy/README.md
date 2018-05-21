## Foursquare api Android example

This implementation makes use of a SearchView in the toolbar that can be used to find what are the recommended venues around the location inserted in the search field.
The search results are then displayed in a vertically scrolling list, with the venue name, rating and icon.

The approach used for this implementation was as follows:

-Use a RecyclerView to display the fetched data from the Foursquare api

-The networking was handled using Google's Volley library, in this case, a JsonObjectRequest.

-The data returned from the Foursquare api is then parsed using Volley's Json helper functions and the org.json library into the VenueModel

-After the data is fully translated into the model it is used to populate a List that is passed to the RecyclerView adapter for presentation

-The images are adjusted, displayed and its cache handled using the [Picasso image library](http://square.github.io/picasso/).
 
