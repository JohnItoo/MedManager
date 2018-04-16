package itoo.ohue.medmanager.search;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import itoo.ohue.medmanager.R;
import itoo.ohue.medmanager.helper.Instantiater;

public class SearchPresenter implements SearchContract.Presenter {
    private SearchContract.VIew view;
    private Context context;

    public SearchPresenter(Context context, SearchContract.VIew view) {
            this.context = context;
            this.view = view;
    }

    @Override
    public void search(String nameQuery) {
        if(!TextUtils.isEmpty(nameQuery)) {
            view.makeToast(R.string.field_error);
        }
        else {
            searchQuery(nameQuery);
        }

    }

    public Cursor searchQuery(String nameQuery) {
        return Instantiater.getDBOps(context , Instantiater.getDBInstance(context)).queryName(nameQuery);
    }
}
