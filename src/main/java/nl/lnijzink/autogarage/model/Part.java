package nl.lnijzink.autogarage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
public class Part {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    String name;
    Float price;
    Long quantity;

    @OneToMany(mappedBy = "part")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    Collection<WorkUnitPart> workUnitParts;

    public Part() {}

    public Part(String name, Float price, Long quantity) {
    }

}
