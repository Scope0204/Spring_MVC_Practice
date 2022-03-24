package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4); // V4 를 구현한 경우 True, 아닌경우 False 반환
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4) handler;

        // FrontControllerServletV4와 거의 흡사 -> v5 : 어댑터에서 해당 역할을 대신 해주는 것
        Map<String, String> paramMap = createParamMap(request);
        HashMap<String, Object> model = new HashMap<>();

        String viewName = controller.process(paramMap, model); // v4는 ModelView를 사용하지않음. viewName만 가지고 뷰리졸브 실행

        // 어댑터 다운역할 -> 반환 타입에 맞게 새롭게 객체 생성
        ModelView mv = new ModelView(viewName);

        // 모델 뷰에 모델 설정
        mv.setModel(model);

        return mv;
    }


    private Map<String, String> createParamMap(HttpServletRequest request) {
        // paramMap
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName))); // 서블릿 기본 요청정보 참고
        return paramMap;
    }

}
