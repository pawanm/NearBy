package demo.tala.venue.core;

import android.view.View;

/**
 * Created by admin on 23/05/18.
 */

public interface IClickListener {
    void onClick(View view,int position);
    void onLongClick(View view, int position);
}
