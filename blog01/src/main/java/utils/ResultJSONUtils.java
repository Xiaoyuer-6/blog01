package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HuYu
 * Date: 2021-04-04
 * Time: 16:02
 */
public class ResultJSONUtils {

        /**
         * 给前端输出 json 数据     write类
         */
        public static void write(HttpServletResponse response,
                                 HashMap<String, Object> data) throws IOException {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json");

            PrintWriter writer = response.getWriter();
            ObjectMapper mapper = new ObjectMapper();
            writer.println(mapper.writeValueAsString(data));
        }

    }
