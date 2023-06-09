package ru.job4j.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.model.Accident;
import ru.job4j.model.AccidentType;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    private final AtomicInteger ids = new AtomicInteger(3);

    public AccidentMem() {
        accidents.put(1, new Accident(1, "John Smith", "Description 1", "Address 1",
                new AccidentType(), new RuleMem().findAll()));
        accidents.put(2, new Accident(2, "Sam Brown", "Description 2",
                "Address 2", new AccidentType(), new RuleMem().findAll()));
        accidents.put(3, new Accident(3, "Paul Pierce", "Description 3", "Address 3",
                new AccidentType(), new RuleMem().findAll()));
    }

    public List<Accident> findAll() {
        return accidents.values().stream().toList();
    }

    public boolean create(Accident accident) {
        if (accidents.containsKey(accident.getId())) {
            return false;
        }
        accident.setId(ids.incrementAndGet());
        accidents.put(accident.getId(), accident);
        return true;
    }

    public void update(Accident accident) {
        accidents.replace(accident.getId(), accident);
    }

    public Optional<Accident> findById(int id) {
        Accident accident = accidents.get(id);
        if (accident == null) {
            return Optional.empty();
        }
        return Optional.of(accident);
    }
}
