package tads.bianca.gerenciador.Pickers;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

import tads.bianca.gerenciador.R;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener  {
    private static final String TAG = "DatePickFragment";

    private String date;
    private Button mButtonDisplay;

    public OnInputSelected mOnInputSelected;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        month++;
        mButtonDisplay = (Button) getActivity().findViewById(R.id.button_create_date);
        if (day < 10){
            date = "0" + day;
        }else{
            date = "" + day;
        }
        if (month < 10) {
            date = date + "/0" + month;
        }else {
            date = date + "/" + month;
        }
        date = date + "/" + year;
        mButtonDisplay.setText(date);

    }

    public String getDate(){
        if (date != null){
            return date;
        }else {
            return null;
        }
    }

    public interface OnInputSelected{
        String getDate();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnInputSelected = (OnInputSelected)getTargetFragment();
        }catch (ClassCastException e){
            Log.e(TAG, "DatePickerFragment onAttach: ClassCastException" + e.getMessage());
        }
    }
}
