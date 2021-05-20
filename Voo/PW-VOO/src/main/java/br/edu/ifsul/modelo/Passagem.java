/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Calendar;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "Passagem")
public class Passagem implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_passagem", sequenceName = "seq_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_passagem", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataCompra;
    @Column(name = "bagagem", nullable = false, precision = 2)
    private Integer bagagem;

    @ManyToOne
    @JoinColumn(name = "pessoa", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_passagem_pessoa"))

    private Pessoa pessoa;
    
    @ManyToOne
    @JoinColumn(name = "classe", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_passagem_classe"))
    private Classe classe;
    
    @ManyToOne
    @JoinColumn(name = "vooagendado", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_passagem_vooagendado"))
    private VooAgendado vooagendado;
    
    
    public Passagem() {
      
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Calendar dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Integer getBagagem() {
        return bagagem;
    }

    public void setBagagem(Integer bagagem) {
        this.bagagem = bagagem;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }
    
    public VooAgendado getVooagendado() {
        return vooagendado;
    }

    public void setVooagendado(VooAgendado vooagendado) {
        this.vooagendado = vooagendado;
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
        final Passagem other = (Passagem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }



}
