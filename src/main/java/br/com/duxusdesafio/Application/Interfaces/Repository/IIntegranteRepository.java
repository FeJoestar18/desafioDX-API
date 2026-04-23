package br.com.duxusdesafio.Application.Interfaces.Repository;

import br.com.duxusdesafio.Domain.Entity.Integrante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IIntegranteRepository extends JpaRepository<Integrante, Long> {
}
