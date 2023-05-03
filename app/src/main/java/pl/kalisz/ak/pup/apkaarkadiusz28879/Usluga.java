package pl.kalisz.ak.pup.apkaarkadiusz28879;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.util.Timer;
import java.util.TimerTask;

public class Usluga extends Service {
    public static final String EXTRA_MESSAGE = "message";
    public static final String CHANNEL_ID = "8888";
    public static final int NOTIFICATION_ID = 4567;
    private NotificationManager notificationManager;
    private String tekst;
    private Toast myToast;
    private ConnectivityManager conMgr;
    private NetworkInfo netInfo;
    //    private ConnectivityManager.NetworkCallback netInfo;
    public static final String NEW_MESSAGE = "pl.kalisz.ak.pup.oskar.apkaoskara21569.NEW_MSG";
    public static final String MSG_FIELD = "message";
    Intent intent = new Intent(NEW_MESSAGE);
    private Timer updatingTimer;

    private TimerTask notify = new TimerTask() {
        @Override
        public void run() {
            conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            netInfo = conMgr.getActiveNetworkInfo();
            netInfo = conMgr.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected())
                tekst = "Sieć działa";
            else
                tekst = "Brak sieci";
            intent.putExtra(MSG_FIELD, tekst);
            sendBroadcast(intent);
            wyslijPowiadomienie(tekst);
        }
    };

    public Usluga(){}

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();

        updatingTimer = new Timer();
        myToast = Toast.makeText(getApplicationContext(), "Usługa została uruchomiona", Toast.LENGTH_SHORT);
        myToast.show();

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String channelId = CHANNEL_ID;
            CharSequence channelName = "Akademia Kaliska - info";
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        updatingTimer.scheduleAtFixedRate(notify, 2500, 4000);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        updatingTimer.cancel();
        myToast.setText("Usługa została zatrzymana");
        myToast.show();
    }

    private void wyslijPowiadomienie(final String tekst){
        Intent actionIntent = new Intent(this, MainActivity.class);
        PendingIntent actionPendingIntent = PendingIntent. getActivity(
                this,
                0,
                actionIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setContentTitle("Akademia Kaliska")
                .setContentText(tekst)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(actionPendingIntent)
                .setVibrate(new long[]{0, 1000})
                .addAction(android.R.drawable.ic_dialog_info, "Info-1", actionPendingIntent)
                .addAction(android.R.drawable.ic_dialog_email, "Info-2", actionPendingIntent)
                .setAutoCancel(true);
        notificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}