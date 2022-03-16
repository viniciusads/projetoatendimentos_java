package br.com.cotiinformatica.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table
public class Atendimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer idAtendimento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date dataHora;

	@ManyToOne
	@JoinColumn(name = "idMedico", nullable = false)
	private Medico medico;

	@ManyToOne
	@JoinColumn(name = "idPaciente", nullable = false)
	private Paciente paciente;

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name = "atendimento_receitaMedica",
			joinColumns = @JoinColumn(name = "idAtendimento", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "idReceitaMedica", nullable = false)
	)
	private List<ReceitaMedica> receitas;

}



