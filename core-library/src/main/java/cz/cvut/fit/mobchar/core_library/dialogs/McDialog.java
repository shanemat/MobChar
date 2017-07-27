package cz.cvut.fit.mobchar.core_library.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;

import cz.cvut.fit.mobchar.core_library.R;
import cz.cvut.fit.mobchar.core_library.adapters.simple_line.McSimpleItemAdapter;

/**
 * Created by shanemat (FIT-CTU) on 23.7.2017 as common dialog being used throughout the app.
 *
 * Base use of those Dialogs is, that you define its title and collection of items, which
 * should be shown (and are of McDialogItem class).
 *
 * Once those are declared, your dialog should implement method prepareItems, in which you
 * specify, how the displayed items will look and be ordered like.
 *
 * After that, you should assign (in Fragment) a DialogClickListener, which will handle events
 * from options of this dialog being chosen. Note that this is the time to make use of option
 * number intel stored in McDialogItems.
 */

public abstract class McDialog extends DialogFragment {

    /// constant for obtaining title of fragment from Bundle
    protected static final String ARGS_TITLE = "title";

    /// title to be shown in header of this dialog
    protected String mTitle;

    /// items, which should be shown
    private ArrayList<McDialogItem> mItems;

    /// listener defining behavior of this dialog towards specific clicks
    private McDialogClickListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mItems = prepareItems();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String cancelText = getString(R.string.cancel);
        McSimpleItemAdapter adapter = new McSimpleItemAdapter<>(getActivity(), mItems);

        return new AlertDialog.Builder(getActivity())
                .setTitle(mTitle)
                .setNegativeButton(cancelText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // intentionally left blank - dialog will be dismissed automatically
                    }
                })
                .setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {

                        McDialogItem item = mItems.get(position);
                        mListener.onClick(item);
                    }
                }).create();
    }

    /**
     * In this method subclasses should prepare the list of items as they see fit.
     * This method is being called when dialog is being created and cannot be called
     * since then on purpose.
     *
     * @return Returns prepared arrayList of items to be shown.
     */
    protected abstract ArrayList<McDialogItem> prepareItems();

    public void setListener(McDialogClickListener listener) {
        mListener = listener;
    }
}
