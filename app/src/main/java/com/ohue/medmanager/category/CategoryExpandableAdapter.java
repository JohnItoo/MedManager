package com.ohue.medmanager.category;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.ohue.medmanager.database.MedDBContract;
import com.ohue.medmanager.miscellanous.Utils;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

import itoo.ohue.medmanager.R;

public class CategoryExpandableAdapter extends BaseExpandableListAdapter{
    private Context context;
    private List<String> listOfHeaders;
    private HashMap<String, Cursor> listChildren;

    public CategoryExpandableAdapter(Context context, List<String> listOfHeaders,
                                     HashMap<String, Cursor> listChildren) {
        this.context = context;
        this.listChildren = listChildren;
        this.listOfHeaders = listOfHeaders;
    }
    @Override
    public int getGroupCount() {
        return listOfHeaders.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
       return listChildren.get(listOfHeaders.get(groupPosition)).getCount();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listOfHeaders.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        if(!listChildren.get(listOfHeaders.get(groupPosition)).moveToPosition(childPosition)){
            return null;
        }
        else{
            return listChildren.get(listOfHeaders.get(groupPosition)).moveToPosition(childPosition);
        }
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.category_header_item, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.list_header_category);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Cursor cursor = (Cursor) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.item_overview, null);
        }
        long id = cursor.getLong(cursor.getColumnIndex(MedDBContract.MedicationColumns._ID));
        String title = cursor.getString(cursor.getColumnIndex(MedDBContract.MedicationColumns.MED_NAME));
        String details = cursor.getString(cursor.getColumnIndex(MedDBContract.MedicationColumns.COLUMN_MED_DETAIlS));
        String date = cursor.getString(cursor.getColumnIndex(MedDBContract.MedicationColumns.COLUMN_MED_DATE));

        TextView medTitle = convertView.findViewById(R.id.med_title_item);
        TextView medDetails = convertView.findViewById(R.id.med_details_item);
        TextView medTime = convertView.findViewById(R.id.med_time_item);
       medTitle.setText(title);
      medDetails.setText(details);
        String dateString = Utils.formatDate(Utils.dateToCalendar(date).getTime(), true);
        medTime.setText(dateString);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
