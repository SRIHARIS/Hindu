package com.app.hindu.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;


import com.app.hindu.R;

import java.util.ArrayList;


/**
 * Created by 914893 on 3/17/15.
 */
public class FavouriteOptionDialog extends DialogFragment {

    private String tripId;
    private String PREFS_NAME = "DEFAULT_SECTION";
    private SharedPreferences default_section;

    public FavouriteOptionDialog(){

    }
    public FavouriteOptionDialog(SharedPreferences pref){
         default_section = pref;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        final ArrayList mSelectedItems = new ArrayList();  // Where we track the selected items
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Set the dialog title

        //Get last saved preference

         builder.setTitle(R.string.Fav_section)
                .setSingleChoiceItems(R.array.sections, default_section.getInt(PREFS_NAME,0), null)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                        // Do something useful withe the position of the selected radio button
                        Log.d("clicked", String.valueOf(selectedPosition));
                        SharedPreferences.Editor editor = default_section.edit();
                        editor.putInt(PREFS_NAME,selectedPosition);
                        editor.commit();
                    }
                });
        return builder.create();
    }

    public void removeDialog(){
        this.dismiss();
    }
}


