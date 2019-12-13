package com.weaverboot.tools.baseTools;
import com.weaverboot.tools.encryptTools.Base64Encrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * HTTP Basic 验证工具类
 *
 * @Author : Jaylan Zhou
 *
 */

public class HttpBasicCheck {

    private HttpBasicCheck(){



    }

    /**
     *
     * 检查HTTP Basic验证 - 主方法
     *
     * @param request
     * @param response
     * @param user
     * @param password
     * @return
     * @throws IOException
     */

    public static boolean checkHttpBasic(HttpServletRequest request , HttpServletResponse response , String user , String password) throws IOException {

        boolean result = true;

        String sessionAuth = (String) request.getSession().getAttribute("auth");

        if (sessionAuth == null) {

            if(!checkHeaderAuth(request, response,user,password)){

                result = false;

            }

        }

        return result;

    }

    /**
     *
     * 检查Http Basic验证 主逻辑
     *
     * @param request
     * @param response
     * @param user
     * @param password
     * @return
     * @throws IOException
     */

    private static boolean checkHeaderAuth(HttpServletRequest request, HttpServletResponse response,String user,String password) throws IOException {

        PrintWriter out = response.getWriter();

        String auth = request.getHeader("Authorization").replaceAll("Basic ","");

        if(auth==null||auth.equals("")){

            out.print("Basic 验证失败");

            return false;
        }

        String decodedAuth = Base64Encrypt.decodeBASE64(auth);

        request.getSession().setAttribute("auth", decodedAuth);

        String[] authParams =  decodedAuth.split(":");

        if(authParams.length < 2){

            out.print("Basic 验证失败");

            return false;

        }


        String userIn = authParams[0];

        String passIn = authParams[1];

        if(userIn.equals(user)&&passIn.equals(password)){

            request.getSession().setAttribute("auth", decodedAuth);

            return true;

        }else{

            response.setStatus(401);

            response.setHeader("WWW-authenticate","Basic realm=\"请输入管理员密码\"");

            out.print("Basic 验证失败");

            return false;
        }


    }



}
