package br.com.duxusdesafio.Infrastructure.Config.Data;

import br.com.duxusdesafio.Domain.Entity.ComposicaoTime;
import br.com.duxusdesafio.Domain.Entity.Integrante;
import br.com.duxusdesafio.Domain.Entity.Time;
import br.com.duxusdesafio.Application.Interfaces.Repository.IIntegranteRepository;
import br.com.duxusdesafio.Application.Interfaces.Repository.IComposicaoTimeRepository;
import br.com.duxusdesafio.Application.Interfaces.Repository.ITimeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner seedData(
            IIntegranteRepository integranteRepo,
            ITimeRepository timeRepo,
            IComposicaoTimeRepository composicaoRepo
    ) {
        return args -> {
            if (integranteRepo.count() > 0) return;

            // Integrantes - Futebol
            Integrante bangalore  = integranteRepo.save(new Integrante("Bangalore",  "Atacante", null));
            Integrante bloodhound = integranteRepo.save(new Integrante("BloodHound", "Meio-campo", null));
            Integrante crypto     = integranteRepo.save(new Integrante("Crypto",     "Defesa", null));
            Integrante wraith     = integranteRepo.save(new Integrante("Wraith",     "Atacante", null));
            Integrante lifeline   = integranteRepo.save(new Integrante("Lifeline",   "Suporte", null));
            Integrante pathfinder = integranteRepo.save(new Integrante("Pathfinder", "Meio-campo", null));
            Integrante octane     = integranteRepo.save(new Integrante("Octane",     "Atacante", null));
            Integrante gibraltar  = integranteRepo.save(new Integrante("Gibraltar",  "Defesa", null));
            Integrante caustic    = integranteRepo.save(new Integrante("Caustic",    "Defesa", null));
            Integrante mirage     = integranteRepo.save(new Integrante("Mirage",     "Meio-campo", null));

            // Times
            Time falcons1 = timeRepo.save(new Time("Falcons", LocalDate.of(2021, 1, 15), null));
            Time falcons2 = timeRepo.save(new Time("Falcons", LocalDate.of(2021, 1, 22), null));
            Time falcons3 = timeRepo.save(new Time("Falcons", LocalDate.of(2021, 1, 29), null));
            Time furia1   = timeRepo.save(new Time("FURIA",   LocalDate.of(2021, 2, 5),  null));
            Time furia2   = timeRepo.save(new Time("FURIA",   LocalDate.of(2021, 2, 12), null));
            Time dark1    = timeRepo.save(new Time("DarkZero Esports", LocalDate.of(2021, 2, 19), null));
            Time dark2    = timeRepo.save(new Time("DarkZero Esports", LocalDate.of(2021, 2, 26), null));
            Time dark3    = timeRepo.save(new Time("DarkZero Esports", LocalDate.of(2021, 3, 5),  null));

            // Composições
            composicaoRepo.saveAll(List.of(
                    new ComposicaoTime(falcons1, bangalore),
                    new ComposicaoTime(falcons1, bloodhound),
                    new ComposicaoTime(falcons1, crypto),

                    new ComposicaoTime(falcons2, wraith),
                    new ComposicaoTime(falcons2, lifeline),
                    new ComposicaoTime(falcons2, bangalore),

                    new ComposicaoTime(falcons3, bangalore),
                    new ComposicaoTime(falcons3, pathfinder),
                    new ComposicaoTime(falcons3, octane),

                    new ComposicaoTime(furia1, gibraltar),
                    new ComposicaoTime(furia1, caustic),
                    new ComposicaoTime(furia1, wraith),

                    new ComposicaoTime(furia2, mirage),
                    new ComposicaoTime(furia2, lifeline),
                    new ComposicaoTime(furia2, bloodhound),

                    new ComposicaoTime(dark1, crypto),
                    new ComposicaoTime(dark1, octane),
                    new ComposicaoTime(dark1, bangalore),

                    new ComposicaoTime(dark2, wraith),
                    new ComposicaoTime(dark2, pathfinder),
                    new ComposicaoTime(dark2, gibraltar),

                    new ComposicaoTime(dark3, caustic),
                    new ComposicaoTime(dark3, mirage),
                    new ComposicaoTime(dark3, lifeline)
            ));

            System.out.println("Seed de dados inserido com sucesso!");
        };
    }
}