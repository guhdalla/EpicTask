package br.com.fiap.epictask.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.Data;

@Data
@Entity
@Table(name = "T_EPC_TASK")
@SequenceGenerator(name = "task", sequenceName = "SQ_T_EPC_TASK", allocationSize = 1)
public class Task {

	@Id
	@Column(name = "id_task")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task")
	private Long id;

	@NotBlank(message = "O titulo e Obrigatorio")
	@Column(name = "nm_task", length = 20, nullable = false)
	private String title;

	@Size(min = 10)
	@Column(name = "ds_task", length = 100, nullable = false)
	private String description;

	@Min(10)
	@Column(name = "pt_task", nullable = false)
	private String points;
}
