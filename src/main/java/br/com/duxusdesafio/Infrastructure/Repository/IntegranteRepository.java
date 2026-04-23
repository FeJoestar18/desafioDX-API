package br.com.duxusdesafio.Infrastructure.Repository;

import br.com.duxusdesafio.Application.Interfaces.Repository.IIntegranteRepository;
import br.com.duxusdesafio.Domain.Entity.Integrante;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class IntegranteRepository implements IIntegranteRepository {
    @Override
    public List<Integrante> findAll() {
        return List.of();
    }

    @Override
    public List<Integrante> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Integrante> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Integrante> findAllById(Iterable<Long> iterable) {
        return List.of();
    }

    @Override
    public <S extends Integrante> S save(S s) {
        return null;
    }

    @Override
    public <S extends Integrante> List<S> saveAll(Iterable<S> iterable) {
        return List.of();
    }

    @Override
    public Optional<Integrante> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Integrante> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public <S extends Integrante> List<S> saveAllAndFlush(Iterable<S> iterable) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<Integrante> iterable) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Integrante getOne(Long aLong) {
        return null;
    }

    @Override
    public Integrante getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Integrante> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Integrante> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Integrante> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Integrante> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Integrante> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Integrante> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Integrante integrante) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> iterable) {

    }

    @Override
    public void deleteAll(Iterable<? extends br.com.duxusdesafio.Domain.Entity.Integrante> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
