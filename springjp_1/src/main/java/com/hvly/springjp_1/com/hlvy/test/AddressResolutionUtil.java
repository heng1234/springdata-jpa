package com.hvly.springjp_1.com.hlvy.test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressResolutionUtil {

    /**
     * 解析地址
     * @author lin
     * @param address
     * @return
     */
    public static List<Map<String,String>> addressResolution(String address){
        String regex="(?<city>[^市]+自治州|.*?地区|.*?区|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.*?镇|.+海域|.+岛)?(?<village>.*)|(?<getcyv>^[\\u2E80-\\u9FFF]+$)";
        Matcher m=Pattern.compile(regex).matcher(address);
        String city=null,county=null,town=null,village=null,getcyv=null;
        List<Map<String,String>> table=new ArrayList<Map<String,String>>();
        Map<String,String> row=null;
        while(m.find()){
            row=new LinkedHashMap<String,String>();
            city=m.group("city");
            row.put("city", city==null?"":city.trim());
            county=m.group("county");
            row.put("county", county==null?"":county.trim());
            village=m.group("village");
            row.put("village", village==null?"":village.trim());
            getcyv=m.group("getcyv");
            row.put("getcyv", getcyv==null?"":getcyv.trim());
            table.add(row);
        }
        return table;
    }

    public static void main(String[] args) {
        System.out.println(addressResolution("上海市"));
    }

}
