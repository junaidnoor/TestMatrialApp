package pk.com.jtech.junaid.testmatrialapp;

/**
 * Created by Junaid on 8/22/2015.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Junaid on 5/17/2015.
 */
public class GCMReceiver extends BroadcastReceiver {
    public static String user;
    public static String ipa;
    public static String BROADCAST_ACTION = "com.jtech.junaid.investigationview.ACTION_BACKGROUND";
    public static String CATEGORY = "com.jtech.junaid.investigationview.CATEGORY_BACKGROUND";

    @Override
    public void onReceive(Context context, Intent intent) {

        intent.setClass(context, GCMService.class);
        GCMService.user=user;
        GCMService.ipa = ipa;
        //intent.setClass(context, BackgrondSer.class);
        //BackgrondSer.user=user;
        context.startService(intent);

    }
}