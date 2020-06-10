package com.taller.cloud.Taller1_Cloud.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_Cryptocurrency")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cryptocurrency {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("currency_name")
    @NotEmpty(message = "El name no puede ser vacio")
    @Column(name = "currency_name", unique = true)
    private String name;
    @JsonProperty("currency_symbol")
    @NotEmpty(message = "El symbol no puede ser vacio")
    @Column(name = "currency_symbol", unique = true)
    private String symbol;
    @Column(name = "currency_rank")
    private long rank;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "cryptocurrency",
            cascade = CascadeType.ALL
    )
    private List<Quote> quote = new ArrayList<>();


    public Cryptocurrency(String nameP, String symbolp, Long rankp, List<Quote> quotep) {
        this.name=nameP;
        this.symbol=symbolp;
        this.rank=rankp;
        this.quote=quotep;

    }
}
