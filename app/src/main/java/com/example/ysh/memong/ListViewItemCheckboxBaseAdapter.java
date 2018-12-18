package com.example.ysh.memong;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;


public class ListViewItemCheckboxBaseAdapter extends BaseAdapter {

    private List<ListViewItem> listViewItemList = null;

    private Context ctx = null;

    public ListViewItemCheckboxBaseAdapter(Context ctx, List<ListViewItem> listViewItemList) {
        this.ctx = ctx;
        this.listViewItemList = listViewItemList;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if(listViewItemList!=null)
        {
            ret = listViewItemList.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int itemIndex) {
        Object ret = null;
        if(listViewItemList!=null) {
            ret = listViewItemList.get(itemIndex);
        }
        return ret;
    }

    @Override
    public long getItemId(int itemIndex) {
        return itemIndex;
    }

    @Override
    public View getView(int itemIndex, View convertView, ViewGroup viewGroup) {

        ListViewItemViewHolder viewHolder = null;

        if(convertView!=null)
        {
            viewHolder = (ListViewItemViewHolder) convertView.getTag();
        }else
        {
            convertView = View.inflate(ctx, R.layout.activity_list_view_with_checkbox, null);

            CheckBox listItemCheckbox = (CheckBox) convertView.findViewById(R.id.list_view_item_checkbox);

            TextView listItemText = (TextView) convertView.findViewById(R.id.list_view_item_text);

            viewHolder = new ListViewItemViewHolder(convertView);

            viewHolder.setItemCheckbox(listItemCheckbox);

            viewHolder.setItemTextView(listItemText);

            convertView.setTag(viewHolder);
        }

        ListViewItem listViewItemDto = listViewItemList.get(itemIndex);
        viewHolder.getItemCheckbox().setChecked(listViewItemDto.isChecked());
        viewHolder.getItemTextView().setText(listViewItemDto.getItemText());

        return convertView;
    }
}