//package com.epam.final_task.filter;
//
//import com.epam.final_task.model.entity.enums.Currency;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//public class CurrencyFilter implements Filter {
//
//    private static final String CURRENCY_ATTRIBUTE = "currency";
//    private static final Currency DEFAULT_CURRENCY = Currency.USD;
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpSession session = request.getSession();
//        Currency currency = (Currency) session.getAttribute(CURRENCY_ATTRIBUTE);
//        if (currency == null){
//            session.setAttribute(CURRENCY_ATTRIBUTE,DEFAULT_CURRENCY);
//        }
//        filterChain.doFilter(servletRequest,servletResponse);
//    }
//}
