package itoo.ohue.medmanager.overview;

import android.content.Context;
import android.database.Cursor;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import itoo.ohue.medmanager.R;
import itoo.ohue.medmanager.Utils;
import itoo.ohue.medmanager.database.MedDBContract;

public class OverviewAdapter extends RecyclerView.Adapter<OverviewAdapter.ViewHolder> {
    private Cursor cursor;

    private Context context;

    public OverviewAdapter (Context context , Cursor cursor) {
        this.cursor = cursor;
        this.context = context;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_overview ,parent,false);
        return  new  ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(!cursor.moveToPosition(position))
            return;
        long id = cursor.getLong(cursor.getColumnIndex(MedDBContract.MedicationColumns._ID));
        String title = cursor.getString(cursor.getColumnIndex(MedDBContract.MedicationColumns.MED_NAME));
        String details = cursor.getString(cursor.getColumnIndex(MedDBContract.MedicationColumns.COLUMN_MED_DETAIlS));
        String date = cursor.getString(cursor.getColumnIndex(MedDBContract.MedicationColumns.COLUMN_MED_DATE));
        holder.medTitle.setText(title);
        holder.medDetails.setText(details);
       String dateString =Utils.formatDate(Utils.dateToCalendar(date).getTime(), true);
        holder.medTime.setText(dateString);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public void swapCursor(Cursor newCursor){
       this.cursor = newCursor;
        notifyDataSetChanged();

    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.med_title_item)
        TextView medTitle;
        @BindView(R.id.med_details_item)
        TextView medDetails;
        @BindView(R.id.med_time_item)
        TextView medTime;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
