package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp"; // view 경로(JSP)
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);// 컨트롤러 -> 뷰로 이동시 사용하는 메서드
        dispatcher.forward(request, response); // 서블릿(컨트롤러) 에서 JSP(뷰) 호출


    }
}
