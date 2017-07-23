package cz.cvut.fit.mobchar.core_library.adapters.simple_line;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cz.cvut.fit.mobchar.core_library.R;
import cz.cvut.fit.mobchar.core_library.lists.McHolder;
import cz.cvut.fit.mobchar.core_library.lists.McListItem;

/**
 * Created by shanemat (FIT-CTU) on 23.7.2017 as holder for widgets
 * associated with simple list items.
 */

public class McSimpleHolder extends McHolder {

    /// widget for showing item's icon
    public ImageView mIconView;

    /// widget for showing text
    public TextView mTextView;

    /**
     * Classic constructor, which binds widgets to parent view
     * passed as argument.
     *
     * @param convertView Parent view of this widget
     */
    public McSimpleHolder(View convertView) {

        mIconView = (ImageView) convertView.findViewById(R.id.i_list_simple_icon);
        mTextView = (TextView) convertView.findViewById(R.id.i_list_simple_text);
    }

    @Override
    public void populate(Context context, McListItem item) {
        if (!item.getClass().isInstance(McSimpleItem.class)) {
            return;
        }

        McSimpleItem simpleItem = (McSimpleItem) item;

        mTextView.setText(simpleItem.getText());
        mIconView.setImageDrawable(simpleItem.getIcon());
    }
}
