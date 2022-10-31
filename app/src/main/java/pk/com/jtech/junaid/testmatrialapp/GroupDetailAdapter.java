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
 * Created by Junaid on 10/5/2015.
 */
public class GroupDetailAdapter extends ArrayAdapter<GroupDetail> {


    Context context;
    int imgView = R.drawable.ic_004;
    public GroupDetailAdapter(Context _context, ArrayList<GroupDetail> array_list) {

        super(_context, R.layout.row_group_detail, array_list);
        this.context = _context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GroupDetail groupDetail = getItem(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_group_detail, parent, false);

        //TextView tvPoCode = (TextView) rowView.findViewById(R.id.row_product_name);
        TextView tvTest = (TextView) rowView.findViewById(R.id.row_test_name);
        TextView tvLrs = (TextView) rowView.findViewById(R.id.row_lrs);
        TextView tvEntryTime = (TextView) rowView.findViewById(R.id.row_entery_time);


        tvTest.setText(groupDetail.getTest_name());
        tvLrs.setText(groupDetail.getLrs_no());
        tvEntryTime.setText(groupDetail.getEntry_time());

        return rowView;
    }
}
