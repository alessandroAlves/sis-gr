package br.com.sisgr.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.SimpleEmail;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.sisgr.dao.ReuniaoDAO;
import br.com.sisgr.model.Contato;
import br.com.sisgr.model.Reuniao;
import br.com.sisgr.model.Usuario;

@Controller
public class ReuniaoController implements IController{

	private ReuniaoDAO reuniaoDAO;
	
	@Autowired
	public ReuniaoController(ReuniaoDAO reuniaoDAO) {
		this.reuniaoDAO = reuniaoDAO;
	}
	
	@Override
	public Usuario getUserSession(HttpSession session) {
		return (Usuario) session.getAttribute("usuario");
	}
	
	@RequestMapping(value = "/agenda", method = RequestMethod.GET)
	public String mostraAgenda() {
		return "reuniao/agenda";
	}
	
	@RequestMapping(value = "/adicionarReuniao", method = RequestMethod.POST)
	public String adicionar(Reuniao reuniao, HttpSession session) {
		reuniao.setUsuario(getUserSession(session));
		reuniaoDAO.adicionar(reuniao);
		return "reuniao/agenda";
	}
	
	@RequestMapping(value = "/removerReuniao/{id}", method = RequestMethod.GET)
	public String remover(@PathVariable Integer id){
		Reuniao reuniao = reuniaoDAO.carregar(id);
		reuniaoDAO.remover(reuniao);
		return "redirect:/agenda";
	}
	
	@RequestMapping(value = "/editarReuniao/{id}", method = RequestMethod.GET)
	public ModelAndView atualizar(@PathVariable Integer id, HttpSession session) {
		ModelAndView mav = new ModelAndView("reuniao/editar");
		Reuniao reuniao = reuniaoDAO.carregar(id);		
		if (reuniao.getContatos().size() == 0){
			Contato contato = new Contato();
			Usuario usuario = getUserSession(session);
			contato.setNome(usuario.getNome());
			contato.setSobrenome(usuario.getSobrenome());
			contato.setEmail(usuario.getEmail());
			contato.setUsuario(getUserSession(session));
			reuniao.getContatos().add(contato);
			reuniaoDAO.atualizar(reuniao);
		}
		mav.addObject("reuniao", reuniao);
		session.setAttribute("reuniao", id);
		session.removeAttribute("salva");
		return mav;
	}

	@RequestMapping(value = "/editarReuniao", method = RequestMethod.PUT)
	public String atualizar(Reuniao reuniao, HttpSession session) {
		List<Contato> existentes = reuniaoDAO.carregar(reuniao.getId()).getContatos();
		for(Contato contato: reuniao.getContatos()){
			contato.setUsuario(getUserSession(session));
		}
		for (int j = 0; j < existentes.size(); j++) {
			for (int i = 0; i < reuniao.getContatos().size(); i++) {
				if (existentes.get(j).getEmail().equals(reuniao.getContatos().get(i).getEmail())) {
					reuniao.getContatos().get(i).setId(existentes.get(j).getId());
					reuniao.getContatos().get(i).setTarefas(existentes.get(j).getTarefas());
				}
			}
		}
			
		reuniao.setUsuario(getUserSession(session));
		reuniaoDAO.atualizar(reuniao);
		session.removeAttribute("reuniao");
		session.setAttribute("salva", "salva");
		return "reuniao/agenda";
	}
	
	@RequestMapping(value = "/listarReuniao", method = RequestMethod.GET)
	public ModelAndView listar(HttpSession session) {
		ModelAndView mav = new ModelAndView("reuniao/lista");
		mav.addObject("reunioes", reuniaoDAO.listar((getUserSession(session).getId())));
		return mav;
	}

	@RequestMapping(value = "/listaTarefas/{id}", method = RequestMethod.GET)
	public ModelAndView tarefas(@PathVariable Integer id, HttpSession session){
		ModelAndView mav = new ModelAndView("reuniao/tarefas");
		session.setAttribute("reuniao", id);
		mav.addObject("reuniao", reuniaoDAO.carregar(id));
		return mav;
	}
	
	@RequestMapping(value = "/listaJson", method = RequestMethod.GET)
	@ResponseBody
	public String listarToJson(HttpServletResponse response,HttpSession session){
		List<Reuniao> reunioes = reuniaoDAO.listar(getUserSession(session).getId());
		JSONArray array = new JSONArray();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
		for (Reuniao reuniao : reunioes) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", reuniao.getId());
			map.put("title", reuniao.getNome());
			map.put("start", dateFormat.format(reuniao.getDia()) + " " + hourFormat.format(reuniao.getInicio()));
			map.put("end", dateFormat.format(reuniao.getDia()) + " " + hourFormat.format(reuniao.getFim()));
			map.put("url", "/sis-gr/editarReuniao/" + reuniao.getId());
			map.put("allDay", false);
			array.put(map);
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		return array.toString();
	}
	
	@RequestMapping(value = "/enviarReuniao/{id}", method = RequestMethod.GET)
	public String enviarReuniao(@PathVariable Integer id, HttpSession session){
		session.setAttribute("reuniao", id);
		return "reuniao/enviar";
	}
	
	@RequestMapping(value = "/downloadPDF/{id}", method = RequestMethod.GET)
	public ModelAndView download(@PathVariable Integer id){
		Reuniao reuniao = reuniaoDAO.carregar(id); 
		return new ModelAndView("pdfView", "reuniao" , reuniao);
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/compartilhar/{id}", method = RequestMethod.GET)
	public String compartilhar(@PathVariable Integer id)
			throws Exception {
		Reuniao reuniao = (Reuniao) reuniaoDAO.carregar(id);
		try {
			for (Contato contato : reuniao.getContatos()) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
				SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
				if (contato != null) {
					SimpleEmail email = new SimpleEmail();
					email.setHostName("smtp.gmail.com");
					email.setSmtpPort(465);
					email.addTo(contato .getEmail());
					email.setFrom("projetosisgr@gmail.com");
					email.setSubject(reuniao.getNome());
					String msg = "<h1 class='muted'>SisGR</h1>"
							+ "<h3 class='muted'>" + contato.getNome() + " você foi convidado(a) para uma reuniao.</h2>"
							+ "<table style='margin: 15px' class='table-condensed'>"
							+ "<h3>Reuniao:</h3>"
							+ "<tr>"
							+ "<td width='83px'><label><strong>Título</strong></label></td>"
							+ "<td>" + reuniao.getNome() + "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<td><label><strong>Dia</strong></label></td>"
							+ "<td>" + dateFormat.format(reuniao.getDia()) + "<br /></td>"
							+ "</tr>"
							+ "<tr>"
							+ "<td><label><strong>Início</strong></label></td>"
							+ "<td>" + hourFormat.format(reuniao.getInicio()) + "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<td><label><strong>Fim</strong></label></td>"
							+ "<td>" + hourFormat.format(reuniao.getFim()) + "<br /></td>"
							+ "</tr>"
							+ "<tr>"
							+ "<td valign='top'><label><strong>Descrição</strong></label></td>"
							+ "<td>" + reuniao.getDescricao() + "</td>"
							+ "</tr>"
							+ "</table>";
					email.setContent("<html><head></head><body>" + msg + "</body></html>", "text/html");
					email.setSSL(true);
					email.setAuthentication("projetosisgr@gmail.com", "sisgr123456");
					email.send();
				}
			}
			return "redirect:/editarReuniao/" + id;
			
		} catch (Exception e) {
			return "agenda";			
		}
	}
}
