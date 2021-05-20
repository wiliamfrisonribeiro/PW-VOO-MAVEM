/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "Aeroporto")
public class Aeroporto implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_aeroporto", sequenceName = "seq_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_aeroporto", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotBlank(message = "O nome não pode ser em branco")
    @Length(max = 50, message = "o Nome ter mais que {max} caracqueter")
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;
    @Column(name = "operacaoNoturna", nullable = false)
    private Boolean operacaoNoturna;
    @ManyToOne
    @JoinColumn(name = "cidade", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_aeroporto_cidade"))
    private Cidade cidade;

    public Aeroporto() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getOperacaoNoturna() {
        return operacaoNoturna;
    }

    public void setOperacaoNoturna(Boolean operacaoNoturna) {
        this.operacaoNoturna = operacaoNoturna;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

   
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final Aeroporto other = (Aeroporto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
