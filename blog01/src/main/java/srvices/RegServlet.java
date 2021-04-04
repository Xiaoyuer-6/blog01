package srvices;

import dao.UserInfoDao;
import utils.ResultJSONUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HuYu
 * Date: 2021-04-04
 * Time: 11:42
 */
public class RegServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置通用参数
        //request.setCharacterEncoding("utf-8");
        //response.setContentType("application/json");//连接jQuery的ajax
        // 定义返回给前端的参数
        int succ = 0;//注册成功为0
        String msg = "";

        // 1.获取请求的参数
        String username = request.getParameter("username");//得到前端的username和password;
        String password = request.getParameter("password");
        // todo:非空效验 [msg="参数不全"]
        // 2.【业务逻辑处理】操作数据库 添加 用户
        UserInfoDao userInfoDao = new UserInfoDao();//调用dao库中的用户表

        try {//处理异常
            succ = userInfoDao.add(username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // 3.返回结果，存放在哈希表中
        HashMap<String, Object> result = new HashMap<>();//两个构造参数<String, Object>
        result.put("succ", succ);
        result.put("msg", msg);
        ResultJSONUtils.write(response, result);//调用write类
        //PrintWriter writer = response.getWriter();
        // {"succ":1,"msg":"msg"}容易出问题，使用JSON处理库（不需要人为拼接，和使用对象一样，设置完属性，调用转发方法，可以将对象转化为JSON字符串）
        //writer.println(String.format("{\"succ\":%d,\"msg\":\"%s\"}",succ, msg));

    }

}

