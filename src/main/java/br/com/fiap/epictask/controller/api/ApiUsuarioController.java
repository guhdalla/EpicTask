package br.com.fiap.epictask.controller.api;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.epictask.model.Usuario;
import br.com.fiap.epictask.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/usuario")
public class ApiUsuarioController {
	
	@Autowired
	private UsuarioRepository repository;

	@GetMapping
	public Page<Usuario> index(@RequestParam(required = false) String nome, @PageableDefault Pageable pageable) {
		if (nome == null)
			return repository.findAll(pageable);
		return repository.findByNomeContaining("%" + nome + "%", pageable);
	}

	@PostMapping
	public ResponseEntity<Usuario> create(@RequestBody @Valid Usuario usuario, UriComponentsBuilder uriBuilder) {
		repository.save(usuario);
		URI uri = uriBuilder.path("/api/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(usuario);
	}

	@GetMapping("{id}")
	public ResponseEntity<Usuario> detail(@PathVariable Long id) {
		return ResponseEntity.of(repository.findById(id));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Usuario> remove(@PathVariable Long id) {
		Optional<Usuario> usuario = repository.findById(id);
		if (usuario.isEmpty())
			return ResponseEntity.notFound().build();
		repository.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("{id}")
	public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody @Valid Usuario usuario) {
		return repository.findById(id).map(record -> {
			record.setNome(usuario.getNome());
			record.setEmail(usuario.getEmail());
			record.setSenha(usuario.getSenha());
			Usuario updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}
}
