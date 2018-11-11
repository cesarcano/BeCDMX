package upiicsa.hackaton.becdmx.helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.Fragment;

import upiicsa.hackaton.becdmx.R;

public class BaseFragment extends Fragment {
    public ProgressDialog mProgressDialog;
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setMessage(getString(R.string.cargando));
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }
}
