/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "VooAgendado")
public class VooAgendado implements Serializable{

    @Id
    @SequenceGenerator(name = "seq_vooagendado", sequenceName = "seq_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_seq_vooagendado", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotBlank(message = "A aeronave não pode ser em branco")
    @Length(max = 50, message = "o aeronave tem mais que {max} caracqueter")
    @Column(name = "aeronave", nullable = false, length = 50)
    private String aeronave;
    @NotNull
    @Column(name = "totalPassageiros", nullable = false)
    private Integer totalPassageiros;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data;
    
    @ManyToOne
    @JoinColumn(name = "voo", referencedColumnName = "id", nullable = false)
    private Voo voo;
    
    @OneToMany(mappedBy = "vooagendado", cascade = CascadeType.ALL, 
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Passagem> passagens = new ArrayList<>();
    
    public VooAgendado(){
    
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAeronave() {
        return aeronave;
    }

    public void setAeronave(String aeronave) {
        this.aeronave = aeronave;
    }

    public Integer getTotalPassageiros() {
        return totalPassageiros;
    }

    public void setTotalPassageiros(Integer totalPassageiros) {
        this.totalPassageiros = totalPassageiros;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Voo getVoo() {
        return voo;
    }

    public void setVoo(Voo voo) {
        this.voo = voo;
    }
   
        @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VooAgendado other = (VooAgendado) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
 
}
