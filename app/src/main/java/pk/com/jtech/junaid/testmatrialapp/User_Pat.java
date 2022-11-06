package pk.com.jtech.junaid.testmatrialapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;




/**
 * Created by Junaid on 9/19/2015.
 */
public class User_Pat extends Fragment {

    public final static String TAG = User_Pat.class.getSimpleName();


    public static User_Pat newInstance() {
        return new User_Pat();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

        @SuppressLint("MissingInflatedId")
        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View userpat = inflater.inflate(R.layout.user_pat_frag, container, false);
        ((TextView)userpat.findViewById(R.id.textView)).setText("User Patient");

        return userpat;
    }
}