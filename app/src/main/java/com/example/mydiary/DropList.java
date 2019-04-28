package com.example.mydiary;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.ArrayList;

/**
* Created by 初中生 on 2018/12/4.
*/
public class DropList extends Activity {
    private ImageView imgDrop;
    private ListView lvList;
    private PopupWindow mPopup;

    public ArrayList<Integer> popup_images;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_imagedrop);

        imgDrop = (ImageView) findViewById(R.id.image_drop);

        imgDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDropDown();
            }
        });

        initView();

    }

    //初始化listview 作为pw 的视图
    private void initView(){
        lvList = new ListView(this);

        popup_images = new ArrayList();
        popup_images.add(R.drawable.happy);
        popup_images.add(R.drawable.soso);
        popup_images.add(R.drawable.sad);

        lvList.setAdapter(new MyAdapter());
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                imgDrop.setImageResource(popup_images.get(position));
                mPopup.dismiss();

            }
        });
    }

    // 下拉框的显示
    protected void showDropDown(){
        if (mPopup == null){
            mPopup = new PopupWindow(lvList,170,800,true);
            mPopup.setFocusable(true);
            mPopup.setOutsideTouchable(true);
            mPopup.setBackgroundDrawable(new ColorDrawable());
        }

        mPopup.showAsDropDown(imgDrop);

    }

    //ListView的适配器
    class MyAdapter extends BaseAdapter{
        @Override
        public int getCount(){
            return popup_images.size();
        }

        @Override
        public Object getItem(int position) {
            return popup_images.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(),
                        R.layout.my_imagelist, null);

                holder = new ViewHolder();
                holder.image_list = (ImageView) convertView.findViewById(R.id.image_list);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.image_list.setImageResource(popup_images.get(position));

            return convertView;
        }

    }

    static class ViewHolder {
        public ImageView image_list;
    }

}

