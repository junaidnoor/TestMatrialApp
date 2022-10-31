package pk.com.jtech.junaid.testmatrialapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Junaid on 9/30/2015.
 */
public class VisitAdapter extends ArrayAdapter<VisitDate> {

    Context context;
    public static int _male;
    public static int _female;
    private ArrayList<VisitDate> visitList = null;
    private ArrayList<VisitDate> arraylist;

    public VisitAdapter(Context _context, ArrayList<VisitDate> array_list,int male,int female) {

        super(_context, R.layout.row_visit, array_list);
        this.context = _context;
        _male = male;
        _female = female;

        visitList = array_list;
        this.arraylist = new ArrayList<VisitDate>();
        this.arraylist.addAll(visitList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VisitDate visitdate = getItem(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_visit, parent, false);

        TextView tvVisitDate = (TextView) rowView.findViewById(R.id.row_visit_date);
        TextView tvDocName = (TextView) rowView.findViewById(R.id.row_doctor);
        TextView tvSpecialty = (TextView) rowView.findViewById(R.id.row_specialty);
        //TextView tvPatAge = (TextView) rowView.findViewById(R.id.row_mrinfo_age);
        //ImageView imgGender = (ImageView) rowView.findViewById(R.id.row_mrinfo_gender_image);

        tvVisitDate.setText(visitdate.getVist_date());
        tvDocName.setText(visitdate.getDoctor_name());
        tvSpecialty.setText(visitdate.getSpecialty());
        //tvPatAge.setText(visitdate.getPat_age());
        //tvPatfName.setText(visitdate.getPat_fname());
        //imgGender.setImageResource(visitdate.getGenderImgReosurce());

        return rowView;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        visitList.clear();
        if (charText.length() == 0) {
            visitList.addAll(arraylist);
        } else {
            for (VisitDate wp : arraylist) {
                if (wp.getDoctor_name().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    visitList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
