package com.example.daotest.activity;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.daotest.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author tian on 2019/10/12
 */
public class XMLActivity extends AppCompatActivity {

    @InjectView(R.id.show)
    TextView show;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml);
        ButterKnife.inject(this);


    }

    @OnClick(R.id.bn)
    public void onViewClicked() {
        //根据XML资源的ID获取解析该资源的解析器
        //getXml方法返回XmlResourceParser对象
        //XmlResourceParser是XmlPullParser的子类
        XmlResourceParser xrp = getResources().getXml(R.xml.books);
        try {

            StringBuilder sb = new StringBuilder();
            //还没到XML文档的结尾处
            while(xrp.getEventType() != XmlResourceParser.END_DOCUMENT){
                //如果遇到开始标签
                if (xrp.getEventType() == XmlResourceParser.START_TAG){
                    //获取该标签的标签名
                    String tagName = xrp.getName();
                    //如果遇到book标签
                    if (tagName.equals("book")){
                        //根据属性名称获取属性值
                        String bookName = xrp.getAttributeValue(null,"price");
                        sb.append("价格: ");
                        sb.append(bookName);
                        //根据属性索引来获取属性值
                        String bookPrice = xrp.getAttributeValue(1);
                        sb.append("   出版日期: ");
                        sb.append(bookPrice);
                        sb.append("  书名: ");
                        //获取文本节点的值
                        sb.append(xrp.nextText());
                    }
                    sb.append("\n");
                }
                //获取解析器的下一个时间
                xrp.next();
            }

            show.setText(sb.toString());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
