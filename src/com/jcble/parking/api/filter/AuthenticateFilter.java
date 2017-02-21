package com.jcble.parking.api.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

/**
 * 请求安全认证过滤器
 * 
 * @author Administrator
 * 
 */
public class AuthenticateFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticateFilter.class);

    private String[] unchecked;

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        String tempParam = arg0.getInitParameter("unchecked");
        if (StringUtils.isNotBlank(tempParam)) {
            unchecked = tempParam.split(",");
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,
            ServletException {

        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("utf-8");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (this.isUnChecked(req)) {
            filterChain.doFilter(req, res);
            return;
        }

//        Enumeration<String> parameterNames = req.getParameterNames();
//
//        Map<String, String> paramNameMap = Maps.newHashMap();
//        String parameterName = null;
//        while (parameterNames.hasMoreElements()) {
//            parameterName = parameterNames.nextElement();
//            if (StringUtils.isBlank(parameterName)) {
//                continue;
//            }
//            paramNameMap.put(parameterName, req.getParameter(parameterName));
//        }
//        boolean flag = true;
//        try {
//            // 参数加密后摘要
//            String sign = EncryptUtil.signature(paramNameMap, CommonConstants.SECRET_CODE, true, false,
//                    CommonConstants.PARAMTER_SIGN);
//            String reqSign = req.getParameter(CommonConstants.PARAMTER_SIGN);
//            if (StringUtils.isBlank(reqSign)) {
//                flag = false;
//            } else {
//                // 摘要不匹配
//                if (!reqSign.equals(sign)) {
//                    flag = false;
//                }
//            }
//        } catch (Exception e) {
//            logger.error("A request from the GMIS, the encryption failure", e);
//            flag = false;
//        }
//        if (flag) {
//            filterChain.doFilter(req, res);
//        } else {
//            // 认证失败，返回提示信息
//            BaseRespDto resp = new BaseRespDto();
//
//            // 升级维护开关
//            String switchStatus = GetWebConfig.getValue("system.upgrade.maintenance.switch");
//            if ("true".equals(switchStatus)) {
//                resp.setRespCode(CommonEnum.RespCode.UPGRADE_ERROR.getCode());
//                resp.setRespMsg(CommonEnum.RespCode.UPGRADE_ERROR.getName());
//            } else {
//                resp.setRespCode(CommonEnum.RespCode.SIGN_ERRO.getCode());
//                resp.setRespMsg(CommonEnum.RespCode.SIGN_ERRO.getName());
//            }
//
//            String respstr = JacksonMapper.bean2Json(resp);
//            res.getWriter().print(respstr);
//        }
    }

    /**
     * 判断是否需要检查
     * 
     * @param request
     *            the request
     * @return 当不需要检查时返回true,否则返回false
     */
    private boolean isUnChecked(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String uri = request.getRequestURI();

        if (uri.length() - 1 <= contextPath.length()) {
            return true;
        }
        String temp = null;
        if (unchecked != null && unchecked.length > 0) {
            for (int i = 0; i < unchecked.length; i++) {
                temp = unchecked[i].trim();
                if (uri.startsWith(contextPath + temp)) {
                    return true;
                }
            }
        }
        return true;
    }

}
