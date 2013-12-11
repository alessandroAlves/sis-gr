package br.com.sisgr.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter{
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller) throws Exception{
		
		String uri = request.getRequestURI();
		if(uri.endsWith("registro") || uri.endsWith("login") || uri.endsWith("inicio") || uri.endsWith("sucesso") 
				|| uri.contains("resources") || uri.contains("google")){
			
						return true;
		}
		
		if(request.getSession().getAttribute("usuario")!=null) {
			return true;
		} else {
			response.sendRedirect("/sis-gr/login");
			return false;
		}
		
	}
}
