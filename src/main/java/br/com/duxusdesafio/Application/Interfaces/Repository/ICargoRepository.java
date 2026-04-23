package br.com.duxusdesafio.Application.Interfaces.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.duxusdesafio.Domain.Entity.Cargo;

public interface ICargoRepository extends JpaRepository<Cargo, Long> {
    Optional<Cargo> findByNomeIgnoreCase(String nome);
}
