package com.example.nguyenhuydong_ktra2_bai2;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestHolder>{
    private Activity activity;
    private ArrayList<Test> list;

    public TestAdapter(Activity activity) {
        this.activity = activity;
    }

    public void  setData(ArrayList<Test> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = activity.getLayoutInflater().inflate(R.layout.card_test, null, false);
        return new TestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestHolder holder, int position) {
        Test test = list.get(position);
        holder.idCard.setText(test.getId() + "");
        holder.nameCard.setText(test.getName());
        holder.dayCard.setText(test.getDate());
        holder.timeCard.setText(test.getTime());
        holder.writeCard.setChecked(test.isWrite());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, TestActivity.class);
                intent.putExtra("thi", list.get(position));
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    class TestHolder extends RecyclerView.ViewHolder {

        private TextView idCard, nameCard, dayCard, timeCard;
        private CheckBox writeCard;

        public TestHolder(@NonNull View view) {
            super(view);
            idCard = view.findViewById(R.id.idCard);
            nameCard = view.findViewById(R.id.nameCard);
            dayCard =view.findViewById(R.id.dayCard);
            timeCard =view.findViewById(R.id.timeCard);
            writeCard = view.findViewById(R.id.writeCard);
        }
    }
}
