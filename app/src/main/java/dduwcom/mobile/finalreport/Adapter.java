package dduwcom.mobile.finalreport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Book> myDataList;
    private LayoutInflater layoutInflater;


    public Adapter(Context context, int layout, ArrayList<Book> myDataList) {
        this.context = context;
        this.layout = layout;
        this.myDataList = myDataList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return myDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return myDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return myDataList.get(position).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final int pos = position;
        ViewHolder holder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(layout, viewGroup, false);

            holder = new ViewHolder();
            holder.tvTitle = convertView.findViewById(R.id.titleView);
            holder.tvAuthor = convertView.findViewById(R.id.authorView);
            holder.tvPublisher = convertView.findViewById(R.id.publisherView);
            holder.tvDate = convertView.findViewById(R.id.dateView);
            holder.tvImage = convertView.findViewById(R.id.bookImage);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvTitle.setText(String.valueOf(myDataList.get(pos).getTitle()));
        holder.tvAuthor.setText(myDataList.get(pos).getAuthor());
        holder.tvPublisher.setText(myDataList.get(pos).getPublisher());
        holder.tvDate.setText(myDataList.get(pos).getDate().toString());
        holder.tvImage.setImageResource(myDataList.get(pos).getBookImageResId());

        return convertView;
    }

    static class ViewHolder {
        TextView tvTitle;
        TextView tvAuthor;
        TextView tvPublisher;
        TextView tvDate;
        ImageView tvImage;
    }
}
