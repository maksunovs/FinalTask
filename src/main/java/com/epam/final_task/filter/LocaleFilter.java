//package com.epam.final_task.filter;
//
//import com.epam.final_task.model.entity.enums.Language;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//public class LocaleFilter implements Filter {
//
//    private static final String LANGUAGE_ATTRIBUTE = "language";
//    private static final Language DEFAULT_LANGUAGE = Language.EN;
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpSession session = request.getSession();
//        Language language = (Language) session.getAttribute(LANGUAGE_ATTRIBUTE);
//        if (language == null) {
//            session.setAttribute(LANGUAGE_ATTRIBUTE, DEFAULT_LANGUAGE);
//        }
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//}
