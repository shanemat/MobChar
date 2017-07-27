package cz.cvut.fit.mobchar.core_library.adapters.dual_line;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cz.cvut.fit.mobchar.core_library.R;
import cz.cvut.fit.mobchar.core_library.lists.McHolder;
import cz.cvut.fit.mobchar.core_library.lists.McListItem;

/**
 * Created by shanemat (FIT-CTU) on 24.7.2017 as holder for widgets
 * associated with items with two lines of text.
 */

public class McDualHolder extends McHolder {

    /// widget for showing item's icon
    public ImageView mIconView;

    /// widget for showing primary text
    public TextView mPrimaryView;

    /// widget for showing secondary text
    public TextView mSecondaryView;

    /**
     * Classic constructor, which binds widgets to parent view
     * passed as argument.
     *
     * @param convertView Parent view of this widget
     */
    public McDualHolder(View convertView) {

        mIconView = (ImageView) convertView.findViewById(R.id.i_list_dual_icon);
        mPrimaryView = (TextView) convertView.findViewById(R.id.i_list_dual_primary_text);
        mSecondaryView = (TextView) convertView.findViewById(R.id.i_list_dual_secondary_text);
    }

    @Override
    public void populate(Context context, McListItem item) {
        if (!item.getClass().isInstance(McDualItem.class)) {
            return;
        }

        McDualItem dualItem = (McDualItem) item;

        mPrimaryView.setText(dualItem.getPrimaryText());
        mSecondaryView.setText(dualItem.getSecondaryText());
        mIconView.setImageDrawable(dualItem.getIcon());
    }
}
