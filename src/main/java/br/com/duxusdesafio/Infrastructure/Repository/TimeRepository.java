package br.com.duxusdesafio.Infrastructure.Repository;

import br.com.duxusdesafio.Application.Interfaces.Repository.ITimeRepository;
import br.com.duxusdesafio.Domain.Entity.Time;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class TimeRepository implements ITimeRepository {
    @Override
    public List<Time> findAll() {
        return List.of();
    }

    @Override
    public List<Time> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Time> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Time> findAllById(Iterable<Long> iterable) {
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
    public void delete(Time time) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> iterable) {

    }

    @Override
    public void deleteAll(Iterable<? extends Time> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Time> S save(S s) {
        return null;
    }

    @Override
    public <S extends Time> List<S> saveAll(Iterable<S> iterable) {
        return List.of();
    }

    @Override
    public Optional<Time> findById(Long aLong) {
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
    public <S extends Time> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public <S extends Time> List<S> saveAllAndFlush(Iterable<S> iterable) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<Time> iterable) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Time getOne(Long aLong) {
        return null;
    }

    @Override
    public Time getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Time> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Time> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Time> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Time> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Time> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Time> boolean exists(Example<S> example) {
        return false;
    }
}
