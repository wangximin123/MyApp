package com.example.administrator.myapp;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NewsActicity extends AppCompatActivity {
    RecyclerView recyclerView;
    ViewPager viewPager;
    ListView listView;
    List<String> list=new ArrayList<String>();
    LinearLayout Linear_content;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_acticity);
        findViews();
        initList();
        RecyclerAdapter recyclerAdapter=new RecyclerAdapter(list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);
        initFragment(new NewsFragment());
    }
    void initFragment(Fragment fragment){
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Linear_content,fragment);
        fragmentTransaction.commit();
    }
    void findViews(){
        recyclerView=findViewById(R.id.Recycle_title);
        viewPager=findViewById(R.id.ViewPager_news);
        listView=findViewById(R.id.Listview_content);
        Linear_content=findViewById(R.id.Linear_content);
    }
    void initList(){
        list.add("要闻");
        list.add("军事");
        list.add("财经");
        list.add("社会");
        list.add("自然");
    }

    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
        List<String> list;
        public RecyclerAdapter(List<String> list){
            this.list=list;
        }
        @NonNull
        @Override
        public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int i) {
            View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_recycle_item,viewGroup,false);
            final ViewHolder viewHolder=new ViewHolder(view);

            viewHolder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int i=0;i<viewGroup.getChildCount();i++){
                        View v=viewGroup.getChildAt(i);
                        v.findViewById(R.id.button).setBackgroundResource(R.drawable.new_recycler_button_normal);
                    }
                    viewHolder.button.setBackgroundResource(R.drawable.news_recycler_button);

                    if (viewHolder.button.getText().equals("要闻")){
                        initFragment(new NewsFragment());
                    }else {
                        initFragment(new OtherNewsFragment());
                    }
                }
            });
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder viewHolder, int i) {
            String s=list.get(i);
            viewHolder.button.setText(s);
            if (i==0){
                viewHolder.button.setBackgroundResource(R.drawable.news_recycler_button);
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            Button button;
            View view;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                button=itemView.findViewById(R.id.button);
                view=itemView;
            }
        }
    }
}
