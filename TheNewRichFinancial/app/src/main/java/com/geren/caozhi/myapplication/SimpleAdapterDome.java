package com.geren.caozhi.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by caozhi on 2016/6/29.
 * 动态页面
 */
public class SimpleAdapterDome extends Activity {

    private int[] images = {R.mipmap.security_press,R.mipmap.mine_press};
    private String[] names = {"沙龙|家庭资产如何最大化收益配置？","互金投资人风险教育公益活动成功举办"};
    private String[] contents= {"随着央行“双降”落地，一年斯定存基准利率降至1.5%，如果你的钱存银行，","本月26日，“稳追金融.明辨论未来”互联网金融行业风险教育公益活动在深圳"};
    private String[] dates = {"2016-06-29","2016-06-27"};
    private String[] particularss = {"详情","详情"};

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        ArrayList<Map<String, Object>> lists = new ArrayList();
        for (int i = 0; i < images.length; i++){
            Map<String, Object> map = new HashMap<>();
            map.put("image", images[i]);
            map.put("name", names[i]);
            map.put("content", contents[i]);
            map.put("date", dates[i]);
            map.put("particulars", particularss[i]);
            lists.add(map);

            SimpleAdapter simpleAdapter = new SimpleAdapter(this, lists, R.layout.listview,
                    new String[]{"image","name","content","date","particulars"},
                    new int[]{R.id.image,R.id.tibiao,R.id.neirong,R.id.riqi,R.id.xiangqing});

            ListView list = (ListView) findViewById(R.id.myListView);
            list.setAdapter(simpleAdapter);
        }
    }
}
