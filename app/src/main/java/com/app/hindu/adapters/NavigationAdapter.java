package com.app.hindu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.IconTextView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.app.hindu.R;
import com.joanzapata.android.iconify.Iconify;

import java.util.List;


/**
 * Created by 914893 on 1/4/15.
 */
public class NavigationAdapter extends BaseAdapter {

    private List<String> MenuItems;
    private List<String> menuIcons;
    private Context context;
    private LayoutInflater inflater;

    public NavigationAdapter(List<String> menuItems, List<String> menuIcons, Context context){
        this.MenuItems = menuItems;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.menuIcons = menuIcons;
    }

    @Override
    public int getCount() {
        return MenuItems.size();
    }

    @Override
    public Object getItem(int position) {
        return MenuItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_navigation_item,null);
        }

        TextView label = (TextView) convertView.findViewById(R.id.MenuLabel);
        IconTextView image = (IconTextView)convertView.findViewById(R.id.MenuIcon);

        label.setText(this.MenuItems.get(position));
        image.setText(this.menuIcons.get(position));
        Iconify.addIcons(image);

        return convertView;
    }
}
