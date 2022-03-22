package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, String> paraMap); // 프레임워크에 종속적이고, 서블릿에 종속적이지 않은 형태
}
