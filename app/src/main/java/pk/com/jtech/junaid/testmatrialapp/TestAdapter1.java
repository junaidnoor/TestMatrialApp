package pk.com.jtech.junaid.testmatrialapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Junaid on 10/26/2015.
 */
public class TestAdapter1 extends RecyclerView.Adapter<MrListRowHolder> {


    private List<TestList> mrList;

    private Context mContext;
    public static int _male;
    public static int _female;


    public TestAdapter1(Context context, List<TestList> mrList,int male,int female) {
        this.mrList = mrList;
        this.mContext = context;
        _male = male;
        _female = female;
    }

    @Override
    public MrListRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_test, null);
        MrListRowHolder mh = new MrListRowHolder(v);

        return mh;
    }

    @Override
    public void onBindViewHolder(MrListRowHolder mrListRowHolder, int i) {
        TestList medi = mrList.get(i);
        //MedicalRecord medi = getItem(position);

        /*Picasso.with(mContext).load(feedItem.getThumbnail())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(mrListRowHolder.thumbnail);
        */
        mrListRowHolder.mr_test_imageView.setImageResource(medi.getImgReosurce());
        mrListRowHolder.mr_test_result.setText(Html.fromHtml(medi.getTest_name()));
        mrListRowHolder.mr_test_count.setText(Html.fromHtml(medi.getTest_count()));

    }

    @Override
    public int getItemCount() {
        return (null != mrList ? mrList.size() : 0);
    }
}

