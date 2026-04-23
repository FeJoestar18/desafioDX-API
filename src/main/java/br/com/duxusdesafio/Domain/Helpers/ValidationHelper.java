package br.com.duxusdesafio.Domain.Helpers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class ValidationHelper {

    private ValidationHelper() {
    }

    public static boolean hasDuplicates(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return false;
        }

        Set<Long> uniqueIds = new HashSet<>();
        for (Long id : ids) {
            if (id == null) {
                continue;
            }
            if (!uniqueIds.add(id)) {
                return true;
            }
        }
        return false;
    }

    public static void requireValidIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }

        for (Long id : ids) {
            if (id == null) {
                throw new IllegalArgumentException("A lista de integrantes contém um valor inválido.");
            }
        }
    }
}
