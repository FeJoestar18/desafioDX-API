package br.com.duxusdesafio.Application.Interfaces.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.duxusdesafio.Domain.Entity.Time;

public interface ITimeRepository extends JpaRepository<Time, Long> {
    Optional<Time> findByNomeDoClubeIgnoreCase(String nomeDoClube);
}
