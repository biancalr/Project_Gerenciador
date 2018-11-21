package tads.bianca.gerenciador.Pickers;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;

import tads.bianca.gerenciador.R;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    private static final String TAG = "TimePickFragment";

    private String time;
    private Button mButtonDisplay;

    public OnInputSelected mOnInputSelected;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        mButtonDisplay = (Button) getActivity().findViewById(R.id.button_create_time);
        if (minute < 10){
            time = hourOfDay + " : 0" + minute;
        }else {
            time = hourOfDay + " : " + minute;
        }
        mButtonDisplay.setText(time);
    }

    public String getTime() {
        if (time != null){
            return time;
        }else {
            return null;
        }
    }

    public interface OnInputSelected{
        String getTime();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnInputSelected = (TimePickerFragment.OnInputSelected)getTargetFragment();
        }catch (ClassCastException e){
            Log.e(TAG, "TimePickerFragment onAttach: ClassCastException" + e.getMessage());
        }
    }
}
