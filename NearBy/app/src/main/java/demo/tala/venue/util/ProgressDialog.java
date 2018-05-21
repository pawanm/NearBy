package demo.tala.venue.util;

import android.content.Context;

/**
 * Created by admin on 18/05/18.
 */

public class ProgressDialog {
    private android.app.ProgressDialog loadingDialog;

    public ProgressDialog(Context context) {
        loadingDialog = new android.app.ProgressDialog(context);
        loadingDialog.setMessage(context.getResources().getString(demo.tala.venue.R.string.venue_search_progress));
        loadingDialog.setIndeterminate(false);
        loadingDialog.setCancelable(false);
    }

    public void show() {
        loadingDialog.show();
    }

    public void dismiss() {
        loadingDialog.dismiss();
    }
}
