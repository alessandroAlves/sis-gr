package br.com.sisgr.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.sisgr.dao.ReuniaoDAO;
import br.com.sisgr.dao.TarefaDAO;
import br.com.sisgr.model.Contato;
import br.com.sisgr.model.Reuniao;
import br.com.sisgr.model.Tarefa;
import br.com.sisgr.model.Usuario;

@Controller
public class TarefaController implements IController{

	TarefaDAO tarefaDAO;
	ReuniaoDAO reuniaoDAO;
	
	@Autowired
	public TarefaController(TarefaDAO tarefaDAO, ReuniaoDAO reuniaoDAO){
		this.tarefaDAO = tarefaDAO;
		this.reuniaoDAO = reuniaoDAO;
	}
	 
	@Override
	public Usuario getUserSession(HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@RequestMapping(value = "/removerTarefa/{id}", method = RequestMethod.GET)
	public String remover(@PathVariable Integer id, HttpSession session){
		Tarefa tarefa = tarefaDAO.carregar(id);
		tarefaDAO.remover(tarefa);
		return "redirect:/listaTarefas/" + session.getAttribute("reuniao");
	}
	
	@RequestMapping(value = "finalizaTarefa/{id}")
	public String finaliza(@PathVariable Integer id, HttpSession session) {
    Tarefa tarefa = tarefaDAO.carregar(id);
    tarefa.setStatus(true);
	tarefaDAO.atualizar(tarefa);
	return "redirect:/listaTarefas/" + session.getAttribute("reuniao");
	}

	@RequestMapping(value = "listarTarefas/{id}", method = RequestMethod.GET)
	public ModelAndView listar(@PathVariable Integer id){
		ModelAndView mav = new ModelAndView("tarefa/tarefas");
		List<Tarefa> tarefas = new ArrayList<>();
		List<Tarefa> tarefasForaDoPrazo = new ArrayList<>();
		Date date = new Date();
		
		for (Reuniao reuniao: reuniaoDAO.listar(id)){
			for(Contato contato: reuniao.getContatos()){
				tarefas.addAll(contato.getTarefas());
			}
		}
		
		for (Tarefa tarefa: tarefas){
			if(tarefa.getPrazo().compareTo(date) < 0 && tarefa.isStatus() == false){
				tarefasForaDoPrazo.add(tarefa);
			}
		}
		
		mav.addObject("tarefas", tarefas);
		mav.addObject("tarefas2", tarefasForaDoPrazo);
		return mav;
	}
}