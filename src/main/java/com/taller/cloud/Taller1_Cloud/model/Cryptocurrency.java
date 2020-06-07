package com.taller.cloud.Taller1_Cloud.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_Cryptocurrency")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cryptocurrency {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String symbol;
    private Long rank;

    @OneToMany(
            mappedBy = "cryptocurrency",
            cascade = CascadeType.ALL
    )
    private List<Quote> quote = new ArrayList<>();


}
