package br.com.sisgr.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.sisgr.dao.UsuarioDAO;
import br.com.sisgr.model.Usuario;

@Controller
public class UsuarioController{

	private UsuarioDAO dao;
	
	@Autowired
	public UsuarioController(UsuarioDAO dao){
		this.dao = dao;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpSession session, Map<String, Object> model) {
		model.put("sucesso", session.getAttribute("sucesso"));
		return "usuario/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String fazerLogin(@RequestParam("email") String email, @RequestParam("senha") String senha, HttpSession session, Map<String, Object> model){
		Usuario usuario = dao.buscarPorLogin(email, senha);
		session.removeAttribute("sucesso");
		if (usuario == null){
			model.put("msg", "Email ou senha inválidos");
			return "usuario/login";
		}else{
			session.setAttribute("usuario",usuario);
			return "reuniao/agenda";
		}
	}
	
	@RequestMapping(value = "/registro", method = RequestMethod.GET)
	public String registro(Map<String, Object> model){
		model.put("usuario", new Usuario());
		return "usuario/registro";
	}
	
	@RequestMapping(value = "/registro", method = RequestMethod.POST)
	public String fazerRegistro(Usuario usuario, HttpSession session){
		Usuario us = dao.buscarPorEmail(usuario.getEmail());
		if (us == null){
			dao.adicionar(usuario);
			session.setAttribute("usuario", usuario);
			session.setAttribute("sucesso", "Seu cadastro foi criado com sucesso!");
			return "redirect:/login";			
		}else{
			return "usuario/erro_cadastro";
		}
	}
	
	@RequestMapping(value = "/sair")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}
