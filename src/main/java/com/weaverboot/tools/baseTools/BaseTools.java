package com.weaverboot.tools.baseTools;


import com.weaverboot.frame.dao.anno.Association;
import com.weaverboot.frame.dao.anno.Collection;
import com.weaverboot.frame.dao.anno.DisableColumn;
import com.weaverboot.tools.logTools.LogTools;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.hrm.HrmUserVarify;
import weaver.hrm.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * 基本工具类
 *
 * @Author : Jaylan Zhou
 *
 */

public class BaseTools {

    private BaseTools(){};

    /**
     * 首字母转大写
     * @param s
     * @return
     */

    public static String toUpperCaseFirstOne(String s){
        if(Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 首字母转小写
     * @param s
     * @return
     */

    public static String toLowerCaseFirstOne(String s){
        if(Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     *
     * 判断list是否为空
     *
     * @param list 检验的List
     * @return 不空为true，空为false
     */

    public static boolean notNullList(List list){

        boolean result = list != null && !list.isEmpty() && list.size() > 0;

        return result;

    }

    /**
     *
     * 判断String 字符串是否为空
     *
     * @param check 检验的String
     * @return 不空为true，空为false
     */
    public static boolean notNullString(String check){

        boolean result = check != null && !check.equals("");

        return result;

    }

    /**
     *
     * 创建CDK（18位字符串）
     *
     * @return
     */

    public static String creatCDK() {
        String chars = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String result = "";
        for (int i = 0; i < 18; i++) {
            result = result + chars.charAt((int) (36 * (Math.random())));
        }
        return result;//生成18位字符串
    }

    /**
     *
     * 生成id（生成规则：当前 年月日时分秒 加上6位随机数字）
     *
     * @return
     */

    public static String createId() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String time = dateFormat.format(date);
        String num = "";
        for (int i = 0; i < 6; i++) {
            num = num + (int) (10 * (Math.random()));
        }
        return time + num;// 生成ID，由当前系统时间+随机数字，要16位，用于所有表生产唯一主键
    }

    /**
     *
     * 获取当前登录用户ID
     *
     * @param request
     * @param response
     * @return
     */

    public static String getUserId(HttpServletRequest request, HttpServletResponse response){

        User users = HrmUserVarify.getUser (request , response);

        String userId = Integer.toString(users.getUID());

        return userId;

    }

    /**
     *
     * 获取当前登录用户实体类
     *
     * @param request
     * @param response
     * @return
     */

    public static User getUser(HttpServletRequest request, HttpServletResponse response){

        User user = HrmUserVarify.getUser (request , response);

        return user;

    }

    /**
     *
     * 获取当前人力资源表中最大的人员id
     *
     * @return 最大的人员id
     */

    public static int getHrmMaxId(){

        int maxId = 1;

        RecordSet recordSet = new RecordSet();

        try {

            recordSet.executeProc("HrmResourceMaxId_Get", "");

            if (recordSet.next()) {

                maxId = recordSet.getInt(1);

            }

        }catch (Exception e){

            BaseBean baseBean = new BaseBean();

            LogTools.writeLog("获取最大ID错误，原因为：" + e.getMessage());

        }

        return maxId;

    }

    public static boolean checkIsAbleColumn(Field field){
        
        boolean result = false;

        if (!field.isAnnotationPresent(DisableColumn.class) && !field.isAnnotationPresent(Association.class) && !field.isAnnotationPresent(Collection.class)) {

            result = true;
            
        }

        return result;

    }


}
