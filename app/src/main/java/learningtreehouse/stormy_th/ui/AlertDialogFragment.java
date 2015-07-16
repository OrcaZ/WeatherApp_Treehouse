package learningtreehouse.stormy_th.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by OrcaZ on 2015/07/15.
 */
public class AlertDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle("Oops! Sorry!")
                .setMessage("There was an error.")
                .setPositiveButton("OK", null);

        Dialog dialog = builder.create();
        return dialog;
    }
}
