package br.com.sisgr.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.sisgr.dao.ReuniaoDAO;
import br.com.sisgr.model.Reuniao;
import br.com.sisgr.model.Usuario;

@Controller
public class ExportController {
	private ReuniaoDAO rdao;

	@Autowired
	public ExportController(ReuniaoDAO rdao) {
		this.rdao = rdao;

	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public String form() {
		return "export";

	}

	@RequestMapping(value = "/download", method = RequestMethod.POST)
	public void download(HttpSession session, HttpServletResponse response)
			throws IOException {
		try {
			Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
			ServletOutputStream out = response.getOutputStream();
			List<Reuniao> reuniaos = new ArrayList<Reuniao>();
			reuniaos = rdao.listar(usuario.getId());
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=agenda.csv");
			out.println("Subject, Start Date, Start Time, End Date, End Time, Private, All Day Event, Location");
			for (int i = 0; i < reuniaos.size(); i++) {
				out.println(reuniaos.get(i).getNome() + ", "
						+ reuniaos.get(i).getDia() + ", "
						+ reuniaos.get(i).getInicio() + ",, "
						+ reuniaos.get(i).getFim() + ", FALSE,,");

			}
			out.flush();
			out.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
