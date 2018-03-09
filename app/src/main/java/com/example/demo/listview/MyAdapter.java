package com.example.demo.listview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by 59246 on 2018/3/8.
 */

class MyAdapter extends BaseAdapter {
    private Context context;
    private List<JsonBean.ResultBean.DataBean> list;
    private ViewHolder vh;

    public MyAdapter(MainActivity mainActivity, List<JsonBean.ResultBean.DataBean> data) {
        context = mainActivity;
        list = data;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view=View.inflate(context,R.layout.content,null);
            vh = new ViewHolder();
            vh.img= view.findViewById(R.id.contentImg);
            vh.title= view.findViewById(R.id.title);
            vh.category = view.findViewById(R.id.category);
            view.setTag(vh);
        }else{
            vh= (ViewHolder) view.getTag();
        }
        Picasso.with(context).load(list.get(i).getThumbnail_pic_s()).into(vh.img);
        vh.title.setText(list.get(i).getTitle());
        vh.category.setText(list.get(i).getCategory());
        return view;
    }

    private class ViewHolder{
        ImageView img;
        TextView title;
        TextView category;
    }
}
