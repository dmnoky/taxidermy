package com.dmnoky.taxidermy.interceptor;

import com.dmnoky.taxidermy.service.animal.subcategory.SubsidiaryService;
import com.dmnoky.taxidermy.service.animal.subcategory.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class HeaderViewInterceptor implements HandlerInterceptor {
    private SubsidiaryService subsidiaryService;
    private TypeService typeService;

    @Autowired
    public void setSubsidiaryService(SubsidiaryService subsidiaryService) {
        this.subsidiaryService = subsidiaryService;
    }
    @Autowired
    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    /**load list for template/header.jsp*/
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        request.setAttribute("subList", subsidiaryService.getSubsidiaries());
        request.setAttribute("typeList", typeService.getTypes());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}
