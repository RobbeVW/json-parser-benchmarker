package be.robbevw.jsonparser.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private BigDecimal totalAmount;

    private String companyName;

    @ElementCollection()
    @Column(length=500000)
    private List<String> comment;
}
