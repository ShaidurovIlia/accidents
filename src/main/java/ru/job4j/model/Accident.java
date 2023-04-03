package ru.job4j.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "accident")
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "accident_entity_graph",
                attributeNodes = {
                        @NamedAttributeNode(value = "rules")
                }
        )
})
public class Accident {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String text;

    private String address;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private AccidentType type;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "accident_rule",
            joinColumns = {@JoinColumn(name = "accident_id")},
            inverseJoinColumns = {@JoinColumn(name = "rule_id")}
    )
    private Set<Rule> rules = new HashSet<>();
}
