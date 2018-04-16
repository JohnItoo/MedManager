package itoo.ohue.medmanager.search;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import itoo.ohue.medmanager.R;


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Cursor cursor;
    private Context context;

    public SearchAdapter(Context context , Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_overview ,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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