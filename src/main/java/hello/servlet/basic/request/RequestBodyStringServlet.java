package hello.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream(); // 메시지바디의 내용을 byte코드로 받음
        // byte코드 -> String (stream에서 제공)
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);// 매개변수로 인코딩 정보를 알려줘야함 : UTF-8

        System.out.println("messageBody = " + messageBody);

        response.getWriter().write("ok");
    }
}
