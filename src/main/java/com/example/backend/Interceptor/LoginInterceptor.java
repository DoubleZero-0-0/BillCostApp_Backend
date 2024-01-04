package com.example.backend.Interceptor;


import com.example.backend.Util.JwtUtil;
import com.example.backend.Util.ThreadLocalUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        String token = request.getHeader("Authorization");
        try
        {

            System.out.println("Token is  " + token );
            Map<String, Object> objectMap = JwtUtil.parseToken(token);

            ThreadLocalUtil.set(objectMap);


            return true;




        }catch (Exception e)

        {
            e.printStackTrace();
            response.setStatus(401);
            return false;

        }


    }
}
