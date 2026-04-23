package br.com.duxusdesafio.Infrastructure.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.duxusdesafio.Application.Dto.CargoDto;
import br.com.duxusdesafio.Application.Dto.CargoInputDto;
import br.com.duxusdesafio.Application.Dto.IntegranteDto;
import br.com.duxusdesafio.Application.Dto.IntegranteInputDto;
import br.com.duxusdesafio.Application.Dto.TimeDto;
import br.com.duxusdesafio.Application.Dto.TimeInputDto;
import br.com.duxusdesafio.Application.Interfaces.Repository.ICargoRepository;
import br.com.duxusdesafio.Application.Interfaces.Repository.IComposicaoTimeRepository;
import br.com.duxusdesafio.Application.Interfaces.Repository.IIntegranteRepository;
import br.com.duxusdesafio.Application.Interfaces.Repository.ITimeRepository;
import br.com.duxusdesafio.Application.Interfaces.Services.ICadastroService;
import br.com.duxusdesafio.Domain.Entity.Cargo;
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
    private final ICargoRepository cargoRepository;

    public CadastroService(
            ITimeRepository timeRepository,
            IIntegranteRepository integranteRepository,
            IComposicaoTimeRepository composicaoTimeRepository,
            ICargoRepository cargoRepository) {
        this.timeRepository = timeRepository;
        this.integranteRepository = integranteRepository;
        this.composicaoTimeRepository = composicaoTimeRepository;
        this.cargoRepository = cargoRepository;
    }

    @Override
    public IntegranteDto cadastrarIntegrante(IntegranteInputDto integranteInput) {
        Cargo cargo = findCargoById(integranteInput.getCargoId());
        Integrante integrante = new Integrante();
        integrante.setNome(integranteInput.getNome());
        integrante.setCargo(cargo);
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
        Cargo cargo = findCargoById(integranteInput.getCargoId());
        integrante.setNome(integranteInput.getNome());
        integrante.setCargo(cargo);
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
    public java.util.List<IntegranteDto> listarIntegrantesSemTimes() {
        return integranteRepository.findByComposicaoTimeIsEmpty().stream()
                .map(this::toIntegranteDto)
                .collect(Collectors.toList());
    }

    @Override
    public CargoDto cadastrarCargo(CargoInputDto cargoInput) {
        validateUniqueCargoName(cargoInput.getNome(), null);

        Cargo cargo = new Cargo();
        cargo.setNome(cargoInput.getNome());
        Cargo salvo = cargoRepository.save(cargo);
        return toCargoDto(salvo);
    }

    @Override
    public java.util.List<CargoDto> listarCargos() {
        return cargoRepository.findAll().stream()
                .map(this::toCargoDto)
                .collect(Collectors.toList());
    }

    @Override
    public CargoDto atualizarCargo(long id, CargoInputDto cargoInput) {
        Cargo cargo = findCargoById(id);
        validateUniqueCargoName(cargoInput.getNome(), id);
        cargo.setNome(cargoInput.getNome());
        Cargo atualizado = cargoRepository.save(cargo);
        return toCargoDto(atualizado);
    }

    @Override
    public void deletarCargo(long id) {
        Cargo cargo = findCargoById(id);
        cargoRepository.delete(cargo);
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

    private Cargo findCargoById(long id) {
        return cargoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cargo não encontrado: " + id));
    }

    private Time findTimeById(long id) {
        return timeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Time não encontrado: " + id));
    }

    private Integrante findIntegranteById(long id) {
        return integranteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Integrante não encontrado: " + id));
    }

    private void validateUniqueCargoName(String nome, Long currentCargoId) {
        if (nome == null || nome.isBlank()) {
            return;
        }

        cargoRepository.findByNomeIgnoreCase(nome.trim())
                .filter(existing -> currentCargoId == null || existing.getId() != currentCargoId)
                .ifPresent(existing -> {
                    throw new IllegalArgumentException("Já existe um cargo com o nome '" + nome.trim() + "'.");
                });
    }

    private CargoDto toCargoDto(Cargo cargo) {
        return new CargoDto(cargo.getId(), cargo.getNome());
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
