package cz.cvut.fit.mobchar.core_library.lists;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by shanemat (FIT-CTU) on 23.7.2017 as modified adapter, which shields out most
 * of common logic for array adapters.
 */

public abstract class McListAdapter<Item extends McListItem, Holder extends McHolder> extends ArrayAdapter<Item> {

    /// application context
    private Context mContext;

    /// items, which should be shown
    private ArrayList<Item> mItems;

    /// ID of layout, which should be used for items
    private int mLayoutID;

    /**
     * Constructor matching super, which also sets the collection of items,
     * which should be shown and pairs layout, which should be used.
     *
     * @param context Application context
     * @param items Collection of items, which should be shown
     * @param layoutID ID of layout, which should be used for items
     */
    public McListAdapter(Context context, @NonNull ArrayList<Item> items, int layoutID) {
        super(context, 0);

        mContext = context;
        mItems = items;
        mLayoutID = layoutID;
    }

    /**
     * One of two mandatory methods. Needs to know, how many items there are in the
     * list, which should be shown.
     *
     * @return Returns number of elements in the list.
     */
    @Override
    public int getCount() {
        return mItems.size();
    }

    /**
     * This method prepares the view of each item, which should be shown.
     *
     * @param position Position in the list
     * @param convertView View, which is already inflated and should be only updated
     * @param parent The parent view, in which the items' views are inflated
     * @return Returns updated View of one item.
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Item item = mItems.get(position);
        Holder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mLayoutID, null);

            holder = getNewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.populate(mContext, item);
        return convertView;
    }

    /**
     * This method should create new instance of Holder based on
     * given convert view.
     *
     * @return Returns new instance of Holder.
     */
    public abstract Holder getNewHolder(View convertView);

    /**
     * Classical setter.
     *
     * @param items Items, which should be set
     */
    public void setItems(ArrayList<Item> items) {
        mItems = items;
    }
}
