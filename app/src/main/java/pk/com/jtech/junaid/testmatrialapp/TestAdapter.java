package pk.com.jtech.junaid.testmatrialapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Junaid on 8/29/2015.
 */
public class TestAdapter extends ArrayAdapter<TestList> {


    Context context;
    int imgView = R.drawable.ic_004;
    public TestAdapter(Context _context, ArrayList<TestList> array_list) {

        super(_context, R.layout.row_test, array_list);
        this.context = _context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TestList testList = getItem(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_test, parent, false);

        //TextView tvPoCode = (TextView) rowView.findViewById(R.id.row_product_name);
        //TextView tvPoAmt = (TextView) rowView.findViewById(R.id.row_product_price);

        ImageView iView = (ImageView) rowView.findViewById(R.id.row_test_imageView);
        TextView tvRowResult = (TextView) rowView.findViewById(R.id.row_test_result);
        TextView tvRowTestCount = (TextView) rowView.findViewById(R.id.roe_test_count);


        //iView.setImageBitmap(groupList.getGroupImage());
        iView.setImageResource(testList.getImgReosurce());
        tvRowResult.setText(testList.getTest_name());
        tvRowTestCount.setText(testList.getTest_count());

        return rowView;
    }
}
