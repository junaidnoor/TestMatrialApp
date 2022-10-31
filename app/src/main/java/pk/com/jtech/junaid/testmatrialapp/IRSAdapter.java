package pk.com.jtech.junaid.testmatrialapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Junaid on 10/4/2015.
 */
public class IRSAdapter extends ArrayAdapter<IRS> {

    Context context;
    public static int _male;
    public static int _female;
    private ArrayList<IRS> visitList = null;
    private ArrayList<IRS> arraylist;

    public IRSAdapter(Context _context, ArrayList<IRS> array_list) {

        super(_context, R.layout.row_irs, array_list);
        this.context = _context;

        visitList = array_list;
        this.arraylist = new ArrayList<IRS>();
        this.arraylist.addAll(visitList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IRS irs = getItem(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_irs, parent, false);

        TextView tvIrsNo = (TextView) rowView.findViewById(R.id.row_irsno);
        TextView tvTestCount = (TextView) rowView.findViewById(R.id.row_test_count);

        tvIrsNo.setText(irs.getIrs());
        tvTestCount.setText(irs.getTest_count());

        return rowView;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        visitList.clear();
        if (charText.length() == 0) {
            visitList.addAll(arraylist);
        } else {
            for (IRS wp : arraylist) {
                if (wp.getIrs().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    visitList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
