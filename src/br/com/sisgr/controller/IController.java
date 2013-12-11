package br.com.sisgr.controller;

import javax.servlet.http.HttpSession;

import br.com.sisgr.model.Usuario;

public interface IController {

	public Usuario getUserSession(HttpSession session);
	
}