package c4q.nyc.bookstore;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * Created by justiceo on 12/18/17.
 */

public class RSSPullService extends IntentService {

    private static String TAG = "RSSPullService";

    // Defines a custom Intent action
    public static final String BROADCAST_ACTION = "nyc.c4q.android.bookstore.BROADCAST";

    // Defines the key for the status "extra" in an Intent
    public static final String EXTENDED_DATA_STATUS = "nyc.c4q.android.bookstore.STATUS";

    // This is for type-checking for android in the manifest.
    public RSSPullService() {
        super(null);
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public RSSPullService(String name) {
        super(name);
    }

    /**
     * This method is invoked on the worker thread with a request to process.
     * Only one Intent is processed at a time, but the processing happens on a
     * worker thread that runs independently from other application logic.
     * So, if this code takes a long time, it will hold up other requests to
     * the same IntentService, but it will not hold up anything else.
     * When all requests have been handled, the IntentService stops itself,
     * so you should not call {@link #stopSelf}.
     *
     * @param intent The value passed to {@link
     *               Context#startService(Intent)}.
     *               This may be null if the service is being restarted after
     *               its process has gone away; see
     *               {@link Service#onStartCommand}
     *               for details.
     */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String data = intent.getDataString();
        Log.d(TAG, "service has been started and is running. data: " + data);

        // Simulate background work... like downloading the file via network
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Intent localIntent = new Intent(BROADCAST_ACTION);
        // Puts the status into the Intent
        localIntent.putExtra(EXTENDED_DATA_STATUS, data);
        // Broadcasts the Intent to receivers in this app.
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
    }

}
