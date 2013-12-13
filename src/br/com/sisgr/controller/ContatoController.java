package br.com.sisgr.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.sisgr.dao.ContatoDAO;
import br.com.sisgr.dao.ReuniaoDAO;
import br.com.sisgr.model.Contato;
import br.com.sisgr.model.Reuniao;
import br.com.sisgr.model.Tarefa;
import br.com.sisgr.model.Usuario;

@Controller
public class ContatoController implements IController{

	private ContatoDAO dao;
	private ReuniaoDAO rdao;
	
	@Autowired
	public ContatoController(ContatoDAO contatoDAO, ReuniaoDAO reuniaoDao) {
		this.dao = contatoDAO;
		this.rdao = reuniaoDao;
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model, HttpSession session) {
		Contato contato = new Contato();
		model.addAttribute("contato", contato);
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		List<Contato> contatos = dao.listar(usuario);
		model.addAttribute("contatos", contatos);
		return "contato/contato";
	}

	@RequestMapping(value = "/adicionar", method = RequestMethod.POST)
	public String adicionar(@ModelAttribute(value = "editar") Contato contato,
			Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		contato.setUsuario(usuario);
		Contato cont = dao.carregar(contato.getId());
		if (cont.getId() != null) {
			dao.atualizar(contato);
		} else {
			dao.salvar(contato);
		}
		model.addAttribute("contato", contato);
		return "redirect:/contato/listar";
	}

	@RequestMapping(value = "/deletar", method = RequestMethod.GET)
	public String deletar(@RequestParam("id") Integer id) {
		Contato contato = dao.carregar(id);
		dao.remover(contato);
		return "redirect:/contato/listar";
	}

	@RequestMapping(value = "/editarContato", method = RequestMethod.PUT)
	public String formEditar(Contato contato,HttpSession session) {
		Integer idReuniao = (Integer) session.getAttribute("reuniao");
		Reuniao reuniao = rdao.carregar(idReuniao);
		
		for(Tarefa tarefa: contato.getTarefas()){
		   tarefa.setContato(contato);
		   tarefa.setReuniao(reuniao);
		}
		
		contato.setUsuario(getUserSession(session));
		dao.atualizar(contato);
		
		return "redirect:/editarReuniao/" + idReuniao;
	}
	
	@RequestMapping(value = "/tarefas/{id}", method = RequestMethod.GET)
	public ModelAndView listaTarefas(@PathVariable Integer id){
		Contato contato = dao.carregar(id);
		
		ModelAndView mav = new ModelAndView("contato/tarefas");
		
		if(contato.getTarefas().size() == 0){
			contato.getTarefas().add(new Tarefa());
		}
		
		mav.addObject("contato", contato);
		return mav;
	}

	@Override
	public Usuario getUserSession(HttpSession session) {
		return (Usuario) session.getAttribute("usuario");
	}

}
