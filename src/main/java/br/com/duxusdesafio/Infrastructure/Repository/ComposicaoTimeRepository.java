package br.com.duxusdesafio.Infrastructure.Repository;

import br.com.duxusdesafio.Application.Interfaces.Repository.IComposicaoTimeRepository;
import br.com.duxusdesafio.Domain.Entity.ComposicaoTime;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class ComposicaoTimeRepository implements IComposicaoTimeRepository {
    @Override
    public List<ComposicaoTime> findAll() {
        return List.of();
    }

    @Override
    public List<ComposicaoTime> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<ComposicaoTime> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<ComposicaoTime> findAllById(Iterable<Long> iterable) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(ComposicaoTime composicaoTime) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> iterable) {

    }

    @Override
    public void deleteAll(Iterable<? extends ComposicaoTime> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends ComposicaoTime> S save(S s) {
        return null;
    }

    @Override
    public <S extends ComposicaoTime> List<S> saveAll(Iterable<S> iterable) {
        return List.of();
    }

    @Override
    public Optional<ComposicaoTime> findById(Long aLong) {
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
    public <S extends ComposicaoTime> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public <S extends ComposicaoTime> List<S> saveAllAndFlush(Iterable<S> iterable) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<ComposicaoTime> iterable) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public ComposicaoTime getOne(Long aLong) {
        return null;
    }

    @Override
    public ComposicaoTime getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends ComposicaoTime> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends ComposicaoTime> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends ComposicaoTime> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends ComposicaoTime> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ComposicaoTime> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends ComposicaoTime> boolean exists(Example<S> example) {
        return false;
    }
}
