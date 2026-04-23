package br.com.duxusdesafio.Application.Interfaces.Repository;

import br.com.duxusdesafio.Domain.Entity.ComposicaoTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IComposicaoTimeRepository extends JpaRepository<ComposicaoTime, Long> {
}
