package com.fastexpo.recyclerviewexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.DemoViewHolder>{

    List<DemoModel> demoValues;
    DemoOnClickListener clickListener;
    public DemoAdapter(DemoOnClickListener clickListener) {
        this.clickListener = clickListener;
        demoValues = new ArrayList<>();
    }
    public void setData(List<DemoModel> demoValues){
        this.demoValues.clear();
        this.demoValues.addAll(demoValues);
        notifyDataSetChanged();
    }

    
    @Override
    public DemoViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contactsview,parent,false);
        return new DemoViewHolder(v);
    }

    @Override
    public void onBindViewHolder( DemoAdapter.DemoViewHolder holder, int position) {
        DemoModel value = demoValues.get(position);
        holder.txtText1.setText("Id : "+value.getS1());
        holder.txtText2.setText("Name : "+value.getS2());
        holder.txtText3.setText("Mail : "+value.getS3());
        holder.txtText4.setText("Address : "+value.getS4());
        holder.txtText5.setText("Gender : "+value.getS5());
        holder.txtText6.setText("Phone : "+value.getS6());
        holder.txtText7.setText("Home : "+value.getS7());
        holder.txtText8.setText("Office : "+value.getS8());

    }

    @Override
    public int getItemCount() {
        return demoValues.size();
    }

    public class DemoViewHolder extends RecyclerView.ViewHolder{

        TextView txtText1;
        TextView txtText2;
        TextView txtText3;
        TextView txtText4;
        TextView txtText5;
        TextView txtText6;
        TextView txtText7;
        TextView txtText8;


        public DemoViewHolder(View itemView) {
            super(itemView);
            txtText1 = itemView.findViewById(R.id.textView1);
            txtText2 = itemView.findViewById(R.id.textView2);
            txtText3 = itemView.findViewById(R.id.textView3);
            txtText4 = itemView.findViewById(R.id.textView4);
            txtText5 = itemView.findViewById(R.id.textView5);
            txtText6 = itemView.findViewById(R.id.textView6);
            txtText7 = itemView.findViewById(R.id.textView7);
            txtText8 = itemView.findViewById(R.id.textView8);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.OnTestClick(getAdapterPosition());
                }
            });

        }
    }
}
