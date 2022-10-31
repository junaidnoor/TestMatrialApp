package pk.com.jtech.junaid.testmatrialapp;

import android.content.Context;
import android.content.Intent;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Junaid Noor Siddiqui on 5/12/2016.
 */
public class ExceptionHanlder implements Thread.UncaughtExceptionHandler{

    private final Context context;
    private String LINE_SEPARATOR = "\n";

    public ExceptionHanlder(Context context){

        this.context = context;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable exception) {
        StringWriter stackTrace=new StringWriter();
        exception.printStackTrace(new PrintWriter(stackTrace));

        StringBuilder errorReport=new StringBuilder();
        errorReport.append("**********************CAUSE***********************");
        errorReport.append(stackTrace.toString());

        Intent intent=new Intent(context,TraceActivity.class);
        intent.putExtra("errorReport", errorReport.toString());
        context.startActivity(intent);

        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(10);
    }
}
