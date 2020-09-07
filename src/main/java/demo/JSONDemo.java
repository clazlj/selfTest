package demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class JSONDemo {
    public static void main(String[] args) {
        List<DemoModel> modelList = new ArrayList<>();
        modelList.add(new DemoModel("red", "round"));
        modelList.add(new DemoModel("blue", "square"));

        String modelListStr = JSON.toJSONString(modelList);

        JSONArray jsonArray = JSON.parseArray(modelListStr);

        List<DemoModel> resultList = jsonArray.toJavaList(DemoModel.class);

        List<DemoModel> resultList2 = JSON.parseArray(modelListStr, DemoModel.class);

    }

    public static class DemoModel {
        public DemoModel(String color, String shape) {
            this.color = color;
            this.shape = shape;
        }

        public String color;

        public String shape;
    }
}
