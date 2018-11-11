package upiicsa.hackaton.becdmx.helper.holder;

import android.app.ProgressDialog;
import android.widget.Toast;

import upiicsa.hackaton.becdmx.R;

public class BaseActivity {
    private ProgressDialog mProgressDialog;

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
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

    public void showToast(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show();
    }
}
