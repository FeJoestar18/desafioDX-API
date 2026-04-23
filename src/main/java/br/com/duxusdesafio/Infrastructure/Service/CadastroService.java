package br.com.duxusdesafio.Infrastructure.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.duxusdesafio.Application.Dto.IntegranteDto;
import br.com.duxusdesafio.Application.Dto.IntegranteInputDto;
import br.com.duxusdesafio.Application.Dto.TimeDto;
import br.com.duxusdesafio.Application.Dto.TimeInputDto;
import br.com.duxusdesafio.Application.Interfaces.Repository.IComposicaoTimeRepository;
import br.com.duxusdesafio.Application.Interfaces.Repository.IIntegranteRepository;
import br.com.duxusdesafio.Application.Interfaces.Repository.ITimeRepository;
import br.com.duxusdesafio.Application.Interfaces.Services.ICadastroService;
import br.com.duxusdesafio.Domain.Entity.ComposicaoTime;
import br.com.duxusdesafio.Domain.Entity.Integrante;
import br.com.duxusdesafio.Domain.Entity.Time;
import br.com.duxusdesafio.Domain.Validator.TimeValidator;

@Service
@Transactional
public class CadastroService implements ICadastroService {

    private final ITimeRepository timeRepository;
    private final IIntegranteRepository integranteRepository;
    private final IComposicaoTimeRepository composicaoTimeRepository;

    public CadastroService(
            ITimeRepository timeRepository,
            IIntegranteRepository integranteRepository,
            IComposicaoTimeRepository composicaoTimeRepository) {
        this.timeRepository = timeRepository;
        this.integranteRepository = integranteRepository;
        this.composicaoTimeRepository = composicaoTimeRepository;
    }

    @Override
    public IntegranteDto cadastrarIntegrante(IntegranteInputDto integranteInput) {
        Integrante integrante = new Integrante();
        integrante.setNome(integranteInput.getNome());
        integrante.setFuncao(integranteInput.getFuncao());
        Integrante salvo = integranteRepository.save(integrante);
        return toIntegranteDto(salvo);
    }

    @Override
    public TimeDto cadastrarTime(TimeInputDto timeInput) {
        TimeValidator.validateUniqueTimeName(timeInput.getNomeDoClube(), null, timeRepository);
        TimeValidator.validateUniqueIntegranteIds(timeInput.getIntegranteIds());

        Time time = new Time();
        time.setNomeDoClube(timeInput.getNomeDoClube());
        time.setData(timeInput.getData());
        time.setComposicaoTime(List.of());

        Time timeSalvo = timeRepository.save(time);
        List<Integrante> integrantes = findIntegrantesByIds(timeInput.getIntegranteIds());

        List<ComposicaoTime> composicaoTime = integrantes.stream()
                .map(integrante -> new ComposicaoTime(timeSalvo, integrante))
                .collect(Collectors.toList());
        composicaoTimeRepository.saveAll(composicaoTime);

        timeSalvo.setComposicaoTime(composicaoTime);
        return toTimeDto(timeSalvo);
    }

    @Override
    public IntegranteDto atualizarIntegrante(long id, IntegranteInputDto integranteInput) {
        Integrante integrante = findIntegranteById(id);
        integrante.setNome(integranteInput.getNome());
        integrante.setFuncao(integranteInput.getFuncao());
        Integrante atualizado = integranteRepository.save(integrante);
        return toIntegranteDto(atualizado);
    }

    @Override
    public void deletarIntegrante(long id) {
        Integrante integrante = findIntegranteById(id);
        List<ComposicaoTime> composicoes = integrante.getComposicaoTime();
        if (composicoes != null && !composicoes.isEmpty()) {
            composicaoTimeRepository.deleteAll(composicoes);
        }
        integranteRepository.delete(integrante);
    }

    @Override
    public TimeDto atualizarTime(long id, TimeInputDto timeInput) {
        Time time = findTimeById(id);
        TimeValidator.validateUniqueTimeName(timeInput.getNomeDoClube(), id, timeRepository);
        TimeValidator.validateUniqueIntegranteIds(timeInput.getIntegranteIds());

        time.setNomeDoClube(timeInput.getNomeDoClube());
        time.setData(timeInput.getData());

        List<ComposicaoTime> existingComposition = time.getComposicaoTime();
        if (existingComposition != null && !existingComposition.isEmpty()) {
            composicaoTimeRepository.deleteAll(existingComposition);
        }

        List<Integrante> integrantes = findIntegrantesByIds(timeInput.getIntegranteIds());
        List<ComposicaoTime> newComposition = integrantes.stream()
                .map(integrante -> new ComposicaoTime(time, integrante))
                .collect(Collectors.toList());
        composicaoTimeRepository.saveAll(newComposition);
        time.setComposicaoTime(newComposition);

        Time atualizado = timeRepository.save(time);
        return toTimeDto(atualizado);
    }

    @Override
    public void deletarTime(long id) {
        Time time = findTimeById(id);
        timeRepository.delete(time);
    }

    @Override
    public TimeDto buscarTimePorId(long id) {
        return toTimeDto(findTimeById(id));
    }

    @Override
    public Page<TimeDto> listarTimes(Pageable pageable) {
        return timeRepository.findAll(pageable)
                .map(this::toTimeDto);
    }

    private List<Integrante> findIntegrantesByIds(List<Long> integranteIds) {
        if (integranteIds == null || integranteIds.isEmpty()) {
            return List.of();
        }
        return integranteIds.stream()
                .map(id -> integranteRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Integrante não encontrado: " + id)))
                .collect(Collectors.toList());
    }

    private Time findTimeById(long id) {
        return timeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Time não encontrado: " + id));
    }

    private Integrante findIntegranteById(long id) {
        return integranteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Integrante não encontrado: " + id));
    }

    private TimeDto toTimeDto(Time time) {
        return new TimeDto(
                time.getId(),
                time.getNomeDoClube(),
                time.getData(),
                time.getComposicaoTime().stream()
                        .map(c -> toIntegranteDto(c.getIntegrante()))
                        .toList());
    }

    private IntegranteDto toIntegranteDto(Integrante integrante) {
        return new IntegranteDto(
                integrante.getId(),
                integrante.getNome(),
                integrante.getFuncao());
    }
}
