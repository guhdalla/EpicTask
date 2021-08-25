package br.com.fiap.epictask.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "T_EPC_USUARIO")
@SequenceGenerator(name = "usuario", sequenceName = "SQ_T_EPC_USUARIO", allocationSize = 1)
public class Usuario {
	
	@Id
	@Column(name = "id_usuario")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario")
	private Long id;

	@NotBlank
	@Size(min = 8, max = 20)
	@Column(name = "nm_usuario", length = 20, nullable = false)
	private String nome;
	
	@NotBlank
	@Email
	@Size(max = 50)
	@Column(name = "ds_email", length = 50, nullable = false, unique = true)
	private String email;
	
	@NotBlank
	@Size(min = 8, max = 20)
	@Column(name = "ds_senha", length = 20, nullable = false)
	private String senha;
}