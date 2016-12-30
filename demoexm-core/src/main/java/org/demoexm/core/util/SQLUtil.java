package org.demoexm.core.util;

import java.util.List;

public class SQLUtil {
    /**
     * 处理用于SQL查询的字符串，将特殊字符转换
     *
     * @param paramStr :原字符串
     * @return 转换后的字符串
     */
    public static String dealSQLStr(String paramStr) {
        if (paramStr == null || "".equals(paramStr.trim()))
            return "%";
        StringBuilder sb = new StringBuilder();
        sb.append("%");
        for (int i = 0; i < paramStr.length(); i++) {
            char ch = paramStr.charAt(i);
            if (ch == '[' || ch == ']' || ch == '%' || ch == '^' || ch == '_')
                sb.append("[" + ch + "]");
            else
                sb.append(ch);
        }
        sb.append("%");
        return sb.toString();
    }

    /**
     * Id列表转换ID 格式
     * @param ids
     * @return
     */
    public static String idList2Ids(List<Integer> ids){
        if (ids != null && ids.size() > 0){
            StringBuffer sb = new StringBuffer(50);
            for (Integer id : ids){
                sb.append(id).append(",");
            }
            String result = sb.toString();
            if (result.length() > 0){
                result = result.substring(0, result.length()-1);
            }
            return result;
        }
        return null;
    }
}
