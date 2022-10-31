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
 * Created by Junaid on 9/13/2015.
 */
public class MrAdapter extends ArrayAdapter<MrInfo> {

    Context context;
    public static int _male;
    public static int _female;
    private ArrayList<MrInfo> mrinfoList = null;
    private ArrayList<MrInfo> arraylist;

    public MrAdapter(Context _context, ArrayList<MrInfo> array_list,int male,int female) {

        super(_context, R.layout.row_pat, array_list);
        this.context = _context;
        _male = male;
        _female = female;

        mrinfoList = array_list;
        this.arraylist = new ArrayList<MrInfo>();
        this.arraylist.addAll(mrinfoList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MrInfo mrinfo = getItem(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_pat, parent, false);

        //TextView tvPoCode = (TextView) rowView.findViewById(R.id.row_product_name);
        //TextView tvPoAmt = (TextView) rowView.findViewById(R.id.row_product_price);

        TextView tvMrCode = (TextView) rowView.findViewById(R.id.row_mrinfo_mr);
        TextView tvPatName = (TextView) rowView.findViewById(R.id.row_mrinfo_pname);
        TextView tvPatfName = (TextView) rowView.findViewById(R.id.row_mrinfo_fname);
        TextView tvPatAge = (TextView) rowView.findViewById(R.id.row_mrinfo_age);
        ImageView imgGender = (ImageView) rowView.findViewById(R.id.row_mrinfo_gender_image);

        tvMrCode.setText(mrinfo.getMr_code());
        tvPatName.setText(mrinfo.getPat_name());
        tvPatAge.setText(mrinfo.getPat_age());
        tvPatfName.setText(mrinfo.getPat_fname());
        imgGender.setImageResource(mrinfo.getGenderImgReosurce());
        /*
        if(mrinfo.getPat_sex().equals("Male"))
        {
            imgGender.setImageResource(_male);
        }
        else
        {
            imgGender.setImageResource(_female);
        }
        */
        //tvPstSex.setText(mrinfo.getPat_sex());

        return rowView;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mrinfoList.clear();
        if (charText.length() == 0) {
            mrinfoList.addAll(arraylist);
        } else {
            for (MrInfo wp : arraylist) {
                if (wp.getPat_name().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    mrinfoList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}