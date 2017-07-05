package util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ResponUtil {
    public static void writeUTF(HttpServletResponse response, String content) {
        response.setContentType("text/html;charset=UTF-8");
        try {
            response.getWriter().print(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
