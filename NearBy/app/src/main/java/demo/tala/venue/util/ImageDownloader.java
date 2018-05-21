package demo.tala.venue.util;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

/**
 * Created by admin on 20/05/18.
 */

public class ImageDownloader {

    public static void loadImage(final Context context, final String url, final ImageView icon) {
        Picasso.with(context).load(url).networkPolicy(NetworkPolicy.OFFLINE).fit().into(icon, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {
                Picasso.with(context)
                        .load(url)
                        .into(icon, new Callback() {
                            @Override
                            public void onSuccess() {
                            }

                            @Override
                            public void onError() {
                                Logger.log("Error while getting image : " + url);
                            }
                        });
            }
        });
    }
}
