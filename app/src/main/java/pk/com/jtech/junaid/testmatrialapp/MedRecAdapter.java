package pk.com.jtech.junaid.testmatrialapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Junaid on 10/1/2015.
 */
public class MedRecAdapter extends ArrayAdapter<MedicalRecord> {

    Context context;
    CardView cv;
    public static int _male;
    public static int _female;
    private ArrayList<MedicalRecord> medirecList = null;
    private ArrayList<MedicalRecord> arraylist;

    public MedRecAdapter(Context _context, ArrayList<MedicalRecord> array_list,int male,int female) {

        super(_context, R.layout.row_medical1, array_list);
        this.context = _context;
        _male = male;
        _female = female;

        medirecList = array_list;
        this.arraylist = new ArrayList<MedicalRecord>();
        this.arraylist.addAll(medirecList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MedicalRecord medi = getItem(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_medical1, parent, false);

        TextView tvTitle = (TextView) rowView.findViewById(R.id.row_title);
        TextView tvMrData = (TextView) rowView.findViewById(R.id.row_mrdata);

        tvTitle.setText("[ "+medi.getTitle()+" ]");
        tvMrData.setText(medi.getMrdata());

        return rowView;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        medirecList.clear();
        if (charText.length() == 0) {
            medirecList.addAll(arraylist);
        } else {
            for (MedicalRecord wp : arraylist) {
                if (wp.getTitle().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    medirecList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}

