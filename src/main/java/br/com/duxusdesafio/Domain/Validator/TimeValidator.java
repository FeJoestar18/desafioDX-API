package br.com.duxusdesafio.Domain.Validator;

import java.util.List;
import java.util.Optional;

import br.com.duxusdesafio.Application.Interfaces.Repository.ITimeRepository;
import br.com.duxusdesafio.Domain.Entity.Time;
import br.com.duxusdesafio.Domain.Helpers.ValidationHelper;

public final class TimeValidator {

    private TimeValidator() {
    }

    public static void validateUniqueTimeName(String nomeDoClube, Long currentTimeId, ITimeRepository timeRepository) {
        if (nomeDoClube == null || nomeDoClube.isBlank()) {
            return;
        }

        Optional<Time> existingTime = timeRepository.findByNomeDoClubeIgnoreCase(nomeDoClube.trim());
        if (existingTime.isPresent() && (currentTimeId == null || existingTime.get().getId() != currentTimeId)) {
            throw new IllegalArgumentException("Já existe um time com o nome '" + nomeDoClube.trim() + "'.");
        }
    }

    public static void validateUniqueIntegranteIds(List<Long> integranteIds) {
        ValidationHelper.requireValidIds(integranteIds);
        if (ValidationHelper.hasDuplicates(integranteIds)) {
            throw new IllegalArgumentException("O mesmo integrante não pode estar duplicado dentro do time.");
        }
    }
}
