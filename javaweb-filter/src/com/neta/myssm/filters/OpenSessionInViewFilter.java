package com.neta.myssm.filters;

import com.neta.myssm.trans.TransactionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter("*.do")
public class OpenSessionInViewFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            TransactionManager.beginTrans();

            filterChain.doFilter(servletRequest, servletResponse);
            TransactionManager.commit();

        } catch (Exception e) {
            e.printStackTrace();
            try {
                TransactionManager.rollback();

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }


    }

    @Override
    public void destroy() {

    }
}
