package cz.cvut.fit.mobchar.core_library.adapters.dual_line;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import java.util.ArrayList;

import cz.cvut.fit.mobchar.core_library.R;
import cz.cvut.fit.mobchar.core_library.lists.McHolder;
import cz.cvut.fit.mobchar.core_library.lists.McListAdapter;
import cz.cvut.fit.mobchar.core_library.lists.McListItem;

/**
 * Created by shanemat (FIT-CTU) on 24.7.2017 as specified adapter for handling
 * lists with dual list items (one icon and two text lines).
 */

public class McDualItemAdapter<Item extends McListItem> extends McListAdapter<Item, McHolder> {

    /**
     * Constructor matching super, which also sets the collection of items,
     * which should be shown and pairs layout, which should be used.
     *
     * @param context Application context
     * @param items   Collection of items, which should be shown
     */
    public McDualItemAdapter(Context context, @NonNull ArrayList<Item> items) {
        super(context, items, R.layout.i_list_dual);
    }

    @Override
    public McHolder getNewHolder(View convertView) {
        return new McDualHolder(convertView);
    }
}
