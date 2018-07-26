package com.example.administrator.myapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {
    List<ImageView> list=new ArrayList<ImageView>();
    List<News> newsList=new ArrayList<News>();
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.news_fragmeng_yaowen,container,false);
        listView = view.findViewById(R.id.Listview_content);
        initList();
        ViewPager viewPager=view.findViewById(R.id.ViewPager_news);

        viewPager.setAdapter(new MyAdapter(list));

        return view;
    }
    private void initList(){
        int[] a={R.drawable.news_viewpager_one,R.drawable.news_viewpager_two,R.drawable.news_viewpager_three,R.drawable.news_viewpager_four};
        for (int i=0;i<4;i++){
            ImageView imageView=new ImageView(getContext());
            imageView.setImageResource(a[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            list.add(imageView);
        }
        News news1=new News(R.drawable.news_one,
                "下半年中国经济怎么干？国务院用这六个词定调",
                "中新网客户端北京7月26日电 (谢艺观 程春雨)GDP增长6.8%，" +
                        "中国已经交出了亮丽的上半年经济成绩单。中国经济要继续稳中向好发展，" +
                        "接下来该怎么干？7月23日召开的国务院常务会议部署财政金融政策和确定围绕补短板" +
                        "、增后劲、惠民生推动有效投资的措施。");
        News news2=new News(R.drawable.news_two,
                "发改委：上半年全国企业员工平均涨薪10.1%",
                "据新华社电 就业是上半年中国经济运行中的一大亮点" +
                        "。在复杂的国内外经济环境下，就业形势稳中向好原因在哪？" +
                        "未来能否持续保障就业稳定？如何推动居民收入稳定增长？" +
                        "在国家发展改革委25日举行的新闻发布会上，" +
                        "多位负责人回应了就业和收入相关热点问题。");
        News news3=new News(0,
                "美国欧盟达成协议：将致力于零关税和消除贸易壁垒",
                "海外网7月26日电 当地时间周三，美国总统特朗普" +
                        "和欧盟委员会主席容克在共同会见记者时宣布，" +
                        "美国和欧盟就缓和当前紧张的贸易关系达成协议，" +
                        "双方表示，将致力于消除关税和贸易壁垒，避免当前一触即发的贸易战。" +
                        "美国同意不向欧洲汽车加征关税，欧盟则同意进口更多美国大豆和天然气。");
        News news4=new News(0,
                "腾讯小程序上线问题疫苗查询入口 建疫苗共享预警反馈渠道",
                "腾讯科技讯 7月25日，针对近期社会上出现的问题疫苗恐慌情况，" +
                        "为更方便用户查询和了解疫苗安全信息，微信小程序“腾讯安心计划”，" +
                        "今日紧急开通问题疫苗查询入口，用户可通过该入口查询疫苗批次是" +
                        "否已被监管部门列入“召回疫苗”。");
        newsList.add(news1);
        newsList.add(news2);
        newsList.add(news3);
        newsList.add(news4);
        ListAdapter listAdapter=new ListAdapter(getContext(),0,newsList);
        listView.setAdapter(listAdapter);
    }
    class ListAdapter extends ArrayAdapter<News>{
        List<News> list;
        public ListAdapter(@NonNull Context context, int resource, @NonNull List<News> objects) {
            super(context, resource, objects);
            list=objects;
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view=null;
            MyHolder myHolder=null;
            if (convertView==null){
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.news_listview_item,parent,false);
                myHolder=new MyHolder();
                myHolder.Himage=view.findViewById(R.id.news_listview_item_image);
                myHolder.Htitle=view.findViewById(R.id.news_listview_item_title);
                myHolder.Hcontent=view.findViewById(R.id.news_listview_item_content);
                view.setTag(myHolder);
            }else {
                view=convertView;
                myHolder= (MyHolder) view.getTag();
            }
            News news=list.get(position);

            if (news.getImage()==0){
                myHolder.Himage.setVisibility(View.GONE);
            }else {
                myHolder.Himage.setImageResource(news.getImage());
                myHolder.Himage.setScaleType(ImageView.ScaleType.FIT_XY);
            }

            myHolder.Htitle.setText(news.getTitle());
            TextPaint paint = myHolder.Htitle.getPaint();
            paint.setFakeBoldText(true);

            myHolder.Hcontent.setText(news.getContent());
            return view;
        }
        class MyHolder{
            ImageView Himage;
            TextView Htitle,Hcontent;
        }

    }

}
