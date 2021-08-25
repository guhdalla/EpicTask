package br.com.fiap.epictask.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.epictask.model.Task;
import br.com.fiap.epictask.model.Usuario;
import br.com.fiap.epictask.repository.UsuarioRepository;

@Controller
public class SessionController {
	
	@Autowired
	private UsuarioRepository repository;

	@RequestMapping("/login")
	public String indexLogin() {
		return "login";
	}
	
	@GetMapping("/register")
	public String indexRegister(Usuario usuario) {
		return "register";
	}
	
	@GetMapping("/showuser")
	public ModelAndView showUser() {
		ModelAndView modelAndView = new ModelAndView("showuser");
		List<Usuario> usuarios = repository.findAll();
		modelAndView.addObject("usuarios", usuarios);
		return modelAndView;
	}
	
	@PostMapping("/register")
	public String register(@Valid Usuario usuario, BindingResult result) {
		if (result.hasErrors()) return "register";
		repository.save(usuario);
		return "login";
	}
}
