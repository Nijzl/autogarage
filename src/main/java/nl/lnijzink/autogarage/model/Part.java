package nl.lnijzink.autogarage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "Part")
@Table(name = "parts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Part {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    String name;
    Double price;
    Long quantity;

    @OneToMany(mappedBy = "part")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    Collection<WorkUnitPart> workUnitParts;

    public Part(String name, Double price, Long quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

}
