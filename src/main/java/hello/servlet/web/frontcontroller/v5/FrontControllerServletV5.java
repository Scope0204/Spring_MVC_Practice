package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5" , urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    /**
     *  예전 코드) 한가지 방식의 인터페이스만 사용할 수 있다 (ControllerV3)
     *  private Map<String, ControllerV3> controllerMap = new HashMap<>();
     */
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5(){
        initHandlerMappingMap();
        initHandlerAdapters();

    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        //v4 추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());

        //v4추가
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 핸들러 호출
        Object handler = getHandler(request);

        // 핸들러가 조회가 되지 않을때,
        if ( handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
            return;
        }

        // 핸들러를 지원하는 어댑터를 찾음
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        // 핸들러 호출 -> 모델-뷰 반환
        ModelView mv = adapter.handle(request, response, handler);

        // 논리 뷰 이름에서 물리 뷰 경로 얻기
        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(),request,response);
    }


    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();

        // v3 : ControllerV3 controller = controllerMap.get(requestURI);
        return handlerMappingMap.get(requestURI); // Object 타입으로 반환
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            // 해당 핸들러에 대한 어댑터가 있는 경우
            if(adapter.supports(handler)){ // instanceOf를 통해 boolean 값 반환
                return adapter;
            }
        }
        // 어댑터가 없는 경우
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다= " + handler);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

}
