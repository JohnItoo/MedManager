package itoo.ohue.medmanager.base;



/**
 * Created by OHUE JOHN on 3/31/2018.
 */

public abstract class BaseFragment<T extends BasePresenter> extends android.support.v4.app.Fragment implements BaseView<T>{

    public BaseFragment(){}

    @Override
    public void makeToast(String message) {
        ((BaseActivity)getActivity()).makeToast(message);
    }

    @Override
    public void setPresenter(T presenter) {

    }

    @Override
    public void makeToast(int messageRes) {
        ((BaseActivity)getActivity()).makeToast(messageRes);
    }

    @Override
    public void showEditTextError(String message) {
        ((BaseActivity)getActivity()).showEditTextError(message);
    }

    @Override
    public void showEditTextError(int message) {
        ((BaseActivity)getActivity()).makeToast(message);
    }

    @Override
    public void setToolbarTitle( int message) {
        ((BaseActivity) getActivity()).setToolbarTitle(message);
    }


}
