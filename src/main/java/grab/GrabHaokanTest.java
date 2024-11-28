package grab;

import cn.hutool.http.HttpUtil;

public class GrabHaokanTest {
    public static void main(String[] args) {
        // 好看视频列表请求
        String url = "https://haokan.baidu.com/web/author/listall?app_id=1807268537544238&ctime=&rn=10&searchAfter=&_api=1";

        String res = HttpUtil.get(url);
        String res2 = HttpUtil.get(url);

        // 两次结果不一样
        //{"errno":101007,"errmsg":"\u7f51\u7edc\u9519\u8bef\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5\uff01","logid":"0918387494","fatal":0}
        System.out.println(res);

        //{"errno":0,"errmsg":"\u6210\u529f","logid":"0920019987","data":{"response_count":0,"has_more":false,"ctime":0,"results":[]}}
        System.out.println(res2);

    }
}
