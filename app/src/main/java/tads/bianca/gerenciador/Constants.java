package tads.bianca.gerenciador;

import android.Manifest;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public class Constants {
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    public static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    public static final String ARGUMENT_PERMISSION_REQUEST_CODE = "requestCode";
    public static final String ARGUMENT_FINISH_ACTIVITY = "finish";
    public static final int PLACE_PICKER_REQUEST = 1;
    public static final float DEFAULT_ZOOM = 15f;
    public static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(
            new LatLng(-40, -168), new LatLng(71, 136));
}
