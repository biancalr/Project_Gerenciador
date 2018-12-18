package tads.bianca.gerenciador;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public class Constants {
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    public static final String ARGUMENT_PERMISSION_REQUEST_CODE = "requestCode";
    public static final String ARGUMENT_FINISH_ACTIVITY = "finish";
    public static final int PLACE_PICKER_REQUEST = 1;
    public static final float DEFAULT_ZOOM = 15f;
    public static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(
            new LatLng(-8.05428, -34.8813), new LatLng(-10.33333, -53.2));
}
