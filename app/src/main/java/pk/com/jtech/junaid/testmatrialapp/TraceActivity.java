package pk.com.jtech.junaid.testmatrialapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TraceActivity extends AppCompatActivity {

    //public static String uid;
    //public static String pas;
    //public static String ipa;
    private TextView exceptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHanlder(this));
        setContentView(R.layout.activity_trace);

        String errorReport = getIntent().getStringExtra("errorReport");
        exceptionTextView = (TextView) findViewById(R.id.textTrace);
        exceptionTextView.setText(errorReport);

    }
}
