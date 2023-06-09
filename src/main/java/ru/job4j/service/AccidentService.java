package ru.job4j.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Accident;
import ru.job4j.model.AccidentType;
import ru.job4j.model.Rule;
import ru.job4j.repository.AccidentRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AccidentService {
    private final AccidentRepository accidentsRepository;
    private final TypeService typeService;
    private final RuleService ruleService;

    public List<Accident> findAll() {
        return (List<Accident>) accidentsRepository.findAll();
    }

    public boolean create(Accident accident, int typeId, String[] ids) {
        Optional<Accident> accidentOpt = loadTypeAndRules(accident, typeId, ids);
        if (accidentOpt.isEmpty()) {
            return false;
        }
        accidentsRepository.save(accidentOpt.get());
        return true;
    }

    public boolean update(Accident accident, int typeId, String[] ids) {
        Optional<Accident> accidentOpt = loadTypeAndRules(accident, typeId, ids);
        if (accidentOpt.isEmpty()) {
            return false;
        }
        accidentsRepository.save(accidentOpt.get());
        return true;
    }

    public Optional<Accident> findById(int id) {
        return accidentsRepository.findById(id);
    }

    private Optional<Accident> loadTypeAndRules(Accident accident, int typeId, String[] ids) {
        Set<Rule> rules = new HashSet<>();
        Optional<AccidentType> typeOpt = typeService.findById(typeId);
        if (typeOpt.isEmpty()) {
            return Optional.empty();
        }
        for (String id : ids) {
            Optional<Rule> optRule = ruleService.findById(Integer.parseInt(id));
            if (optRule.isEmpty()) {
                return Optional.empty();
            }
            rules.add(optRule.get());
        }
        accident.setType(typeOpt.get());
        accident.setRules(rules);
        return Optional.of(accident);
    }
}
