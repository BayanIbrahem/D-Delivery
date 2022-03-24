package com.example.qubit.d_delivery.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import com.example.qubit.d_delivery.BuildConfig;

/** ADVANCED INFO FOR ALERT DIALOG:
 *      Alert Dialog support 3 types of lists, ordinary, checkbox and radio box.
 *      - use setItems (String[] items, Listener) to put a list of choices
 *      - use setMultiChoiceItems() | setSingleChoiceItem(), to use the other types of lists
 *          Note: in MultiChoiceItems, in the event we use if/else clause, to add/remove the item form selected items
 *                  (something similar for the setSingleChoiceItem
 *      last thing; if we want to add a custom layout we use setView method
 *          but for the layout inflater we use "requireActivity().getLayoutInflater()"
 *          and we can use both the lists and custom layout.
 *  FOR MORE ADVANCED DIALOG, WE CAN DISPLAY AN ENTIRE ACTIVITY AS DIALOG BY MODIFY THIS IS THEME IN ANDROID MANIFEST FILE
 *      <activity android:theme="@android:style/Theme.Holo.Dialog" >
 *  """to show the dialog we use getSupportFragmentManager() as a Fragment manager parameter"""
 *
 *  MORE ABOUT DIALOG IN: https://developer.android.com/guide/topics/ui/dialogs
 * **/
public class AboutDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstance){
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("About");
        //TODO: put all BLAHs we need
        dialog.setMessage("D-Delivery App, Version: " +BuildConfig.VERSION_NAME);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TODO: may we need this: dialog.dismiss();
            }
        });
        return dialog.create();
    }
}
