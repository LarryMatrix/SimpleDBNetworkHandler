package tz.co.matrixhub.simple_db_network_handler.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkManager {

    private static int TYPE_MOBILE = 1;
    private static int TYPE_WIFI = 2;
    private static int TYPE_NOT_CONNECTED = 0;

    public static int getConnectivityStatus(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }
        if (networkInfo != null) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return TYPE_WIFI;
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                return TYPE_MOBILE;
            }
        }
        return TYPE_NOT_CONNECTED;
    }

    public static String getConnectivityStatusString(Context context) {
        int connectionType = NetworkManager.getConnectivityStatus(context);
        String status = null;

        if (connectionType == NetworkManager.TYPE_MOBILE) {
            status = "Mobile Data Enabled and Connected!";
        } else if (connectionType == NetworkManager.TYPE_WIFI) {
            status = "WiFi Enabled and Connected!";
        } else {
            status = "Not connected to Internet!";
        }
        return status;
    }

    public static boolean checkNetworkConnectionStatus(Context context) {
        int connectionType = NetworkManager.getConnectivityStatus(context);
        boolean network_status;

        if (connectionType == NetworkManager.TYPE_MOBILE) {
            network_status = true;
            return network_status;
        } else if (connectionType == NetworkManager.TYPE_WIFI) {
            network_status = true;
            return network_status;
        } else {
            network_status = false;
            return network_status;
        }
    }

    public static boolean checkNetworkConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return (networkInfo != null && networkInfo.isConnected());
    }


}
