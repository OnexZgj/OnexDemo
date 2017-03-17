package com.esint.demo.onexdemo.Activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.esint.demo.onexdemo.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Linsa on 2017/3/17:10:10.
 * des:
 */
public class ProductAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_product_info, null);
            holder=new ViewHolder(convertView);
            holder.ivIpiOutput= (ImageView) convertView.findViewById(R.id.iv_ipi_output);
            holder.tvIpiServer= (TextView) convertView.findViewById(R.id.tv_ipi_server);
            holder.tvIpiTime= (TextView) convertView.findViewById(R.id.tv_ipi_time);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }


        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.tv_ipi_server)
        TextView tvIpiServer;
        @InjectView(R.id.tv_ipi_time)
        TextView tvIpiTime;
        @InjectView(R.id.iv_ipi_output)
        ImageView ivIpiOutput;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
