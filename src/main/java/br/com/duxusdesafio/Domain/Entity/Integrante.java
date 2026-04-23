package br.com.duxusdesafio.Domain.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "integrante")
public class Integrante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Column
	private String nome;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "cargo_id")
	private Cargo cargo;
	
	@OneToMany(mappedBy = "integrante")
	private List<ComposicaoTime> composicaoTime;


	public Integrante() {
	}

	public Integrante(String nome, Cargo cargo, List<ComposicaoTime> composicaoTime) {
		this.nome = nome;
		this.cargo = cargo;
		this.composicaoTime = composicaoTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public String getFuncao() {
		return cargo != null ? cargo.getNome() : null;
	}

	public List<ComposicaoTime> getComposicaoTime() {
		return composicaoTime;
	}

	public void setComposicaoTime(List<ComposicaoTime> composicaoTime) {
		this.composicaoTime = composicaoTime;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Integrante)) return false;
		Integrante that = (Integrante) o;
		return id == that.id && Objects.equals(nome, that.nome) && Objects.equals(cargo, that.cargo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome, cargo);
	}

	@Override
	public String toString() {
		return "Integrante{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", cargo=" + cargo +
				'}';
	}
}
