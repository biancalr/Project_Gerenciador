package tads.bianca.gerenciador.Pickers;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import tads.bianca.gerenciador.R;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener  {
    private static final String TAG = "DatePickFragment";

    private TextView mDisplayDate;
    private String date;

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
        mDisplayDate = (TextView) getActivity().findViewById(R.id.dateFragment);
        date = month + "/" + day + "/" + year;
        if (mDisplayDate != null){
            mDisplayDate.setText(date);
        }

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
