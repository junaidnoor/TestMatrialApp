package pk.com.jtech.junaid.testmatrialapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Created by Junaid on 9/19/2015.
 */
public class Help_Pat extends Fragment {

    public final static String TAG = Help_Pat.class.getSimpleName();
    TextView mTextView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View helppat = inflater.inflate(R.layout.user_pat_frag, container, false);
        mTextView = (TextView) helppat.findViewById(R.id.textView);
        return helppat;
    }
}