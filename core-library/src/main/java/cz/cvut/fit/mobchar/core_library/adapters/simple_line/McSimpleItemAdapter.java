package cz.cvut.fit.mobchar.core_library.adapters.simple_line;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import java.util.ArrayList;

import cz.cvut.fit.mobchar.core_library.R;
import cz.cvut.fit.mobchar.core_library.dialogs.McDialogItem;
import cz.cvut.fit.mobchar.core_library.lists.McHolder;
import cz.cvut.fit.mobchar.core_library.lists.McListAdapter;

/**
 * Created by shanemat (FIT-CTU) on 23.7.2017 as specified adapter for handling
 * lists with simple list items (one icon and one text line).
 */

public class McSimpleItemAdapter extends McListAdapter {

    /**
     * Constructor matching super, which also sets the collection of items,
     * which should be shown and pairs layout, which should be used.
     *
     * @param context   Application context
     * @param items     Collection of items, which should be shown
     */
    public McSimpleItemAdapter(Context context, @NonNull ArrayList items) {
        super(context, items, R.layout.i_list_simple);
    }

    @Override
    public McHolder getNewHolder(View convertView) {
        return new McSimpleHolder(convertView);
    }
}
