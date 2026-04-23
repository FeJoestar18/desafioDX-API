package br.com.duxusdesafio.Application.Interfaces.Repository;

import br.com.duxusdesafio.Domain.Entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITimeRepository extends JpaRepository<Time, Long> {

}
