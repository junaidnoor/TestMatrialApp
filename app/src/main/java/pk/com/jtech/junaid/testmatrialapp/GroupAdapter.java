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
 * Created by Junaid on 8/26/2015.
 */
public class GroupAdapter extends ArrayAdapter<GroupList> {


    Context context;

    public GroupAdapter(Context _context, ArrayList<GroupList> array_list) {

        super(_context, R.layout.row_group, array_list);
        this.context = _context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GroupList groupList = getItem(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_group, parent, false);

        //TextView tvPoCode = (TextView) rowView.findViewById(R.id.row_product_name);
        //TextView tvPoAmt = (TextView) rowView.findViewById(R.id.row_product_price);

        ImageView iView = (ImageView) rowView.findViewById(R.id.row_group_imageView);
        TextView tvRowResult = (TextView) rowView.findViewById(R.id.row_result);
        TextView tvRowGroupCount = (TextView) rowView.findViewById(R.id.roe_group_count);


        iView.setImageResource(groupList.getImgReosurce());
        tvRowResult.setText(groupList.getGroup_name());
        tvRowGroupCount.setText(groupList.getTest_count());

        return rowView;
    }
}
