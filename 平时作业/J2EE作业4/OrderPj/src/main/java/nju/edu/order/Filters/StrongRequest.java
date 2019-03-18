package nju.edu.order.Filters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class StrongRequest extends HttpServletRequestWrapper {

    HttpServletRequest request;

    public StrongRequest(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public String getParameter(String name) {

        String method = request.getMethod();
        String value=null;
        try {
            request.setCharacterEncoding("utf-8");

            value = request.getParameter(name);

            if("get".equalsIgnoreCase(method)){
                value = new String(value.getBytes("iso-8859-1"),"utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }


    @Override
    public String[] getParameterValues(String name) {

        String method = request.getMethod();
        String[] values=null;
        try {
            request.setCharacterEncoding("utf-8");

            values = request.getParameterValues(name);

            if("get".equalsIgnoreCase(method)){
                int i=0;
                for (String str : values) {
                    values[i++] = new String(str.getBytes("iso-8859-1"),"utf-8");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return values;
    }

}
