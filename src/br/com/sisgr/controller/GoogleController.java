package br.com.sisgr.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sisgr.dao.UsuarioDAO;
import br.com.sisgr.model.GoogleOauthHelper;
import br.com.sisgr.model.Usuario;

@Controller
public class GoogleController 
{

    private GoogleOauthHelper helper = new GoogleOauthHelper(); 
	
    private UsuarioDAO dao;
    private Usuario usuario;
    
    
    @Autowired
    public GoogleController(UsuarioDAO usuarioDao)
    {
    	this.usuario = new Usuario();
    	this.dao = usuarioDao;
    }
    
    @RequestMapping("/google")
    public void home(HttpSession session,
                                    HttpServletRequest request,
                                             HttpServletResponse response) throws IOException
    {       
    	
         if((request.getParameter("code") == null) ||
                   (request.getParameter("state") == null))
         {
             response.sendRedirect(helper.buildLoginUrl());
             
             session.setAttribute("state", helper.getStateToken());   
         } 
         
         
         else if((request.getParameter("code") != null) ||
                    (request.getParameter("state") != null) && 
                        (request.getParameter("state").equals(session.getAttribute("state"))))
         {
             session.removeAttribute("state");
             
             JSONObject jObj = new JSONObject(helper.
            		 getUsuarioInfoJson(request.getParameter("code")));
         
            usuario.setNome(jObj.getString("given_name"));
            usuario.setSobrenome(jObj.getString("family_name"));
            usuario.setEmail(jObj.getString("email"));
            usuario.setSenha("senha"); 
            
            if (dao.buscarPorEmail(usuario.getEmail())!= null)
            {
            	usuario = dao.buscarPorEmail(usuario.getEmail());
            	session.setAttribute("usuario", usuario);
            }else 
            {
            	dao.adicionar(usuario);
            	session.setAttribute("usuario", usuario);
            }
            
            response.sendRedirect("agenda");
        }
    }
}
