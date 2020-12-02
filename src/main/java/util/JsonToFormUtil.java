package util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Scanner;

/**
 * jdd请求json转表单的key-value
 * 结果填入postman的Body的表单数据的Bulk Edit
 * @author cl
 */
public class JsonToFormUtil {
    public static void main(String[] args) {
        System.out.println("请输入json：");
        Scanner scanner = new Scanner(System.in);
        String json = scanner.next();

        StringBuilder sb = new StringBuilder();

        JSONObject jsonObject = JSON.parseObject(json);

        JSONObject header = jsonObject.getJSONObject("header");
        header.forEach((k, v) -> sb.append(k).append(":").append(v).append("\r\n"));

        JSONObject body = jsonObject.getJSONObject("body");
        body.forEach((k, v) -> sb.append(k).append(":").append(v).append("\r\n"));

        System.out.println("转换成postman的表单数据的Bulk Edit结果：");
        System.out.println(sb);
    }
}
