package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "voo")
public class Voo implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_voo", sequenceName = "seq_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_voo", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotBlank(message = "A descrição não pode ser em branco")
    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;
    @Column(name = "tempoEstimado", nullable = false, precision = 2)
    private Double tempoEstimado;
    @Column(name = "ativo", nullable = false)
    private Boolean ativo;
    @NotBlank(message = "A periodicidade não pode ser em branco")
    @Length(max = 50, message = "o periodicidade tem mais que {max} caracqueter")
    @Column(name = "periodicidade", nullable = false, length = 50)
    private String periodicidade;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "escalas",
            joinColumns
            = @JoinColumn(name = "voo", referencedColumnName = "id",
                    nullable = false),
            inverseJoinColumns
            = @JoinColumn(name = "aeroporto", referencedColumnName = "id", nullable = false))
    private Set<Aeroporto> escalas = new HashSet<>();

    @OneToMany(mappedBy = "voo", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<VooAgendado> vooagendado = new ArrayList<>();

    public Voo() {

    }

    public void adicionarVooAgendado(VooAgendado obj) {
        obj.setVoo(this);
        this.vooagendado.add(obj);
    }

    public void removerVooAgendado(int index) {
        this.vooagendado.remove(index);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getTempoEstimado() {
        return tempoEstimado;
    }

    public void setTempoEstimado(Double tempoEstimado) {
        this.tempoEstimado = tempoEstimado;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(String periodicidade) {
        this.periodicidade = periodicidade;
    }

    public Set<Aeroporto> getEscalas() {
        return escalas;
    }

    public void setEscalas(Set<Aeroporto> escalas) {
        this.escalas = escalas;
    }

    public List<VooAgendado> getVooagendado() {
        return vooagendado;
    }

    public void setVooagendado(List<VooAgendado> vooagendado) {
        this.vooagendado = vooagendado;
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
        final Voo other = (Voo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
