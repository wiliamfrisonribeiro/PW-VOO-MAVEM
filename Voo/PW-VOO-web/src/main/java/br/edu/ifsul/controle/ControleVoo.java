package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AeroportoDAO;

import br.edu.ifsul.dao.VooDAO;
import br.edu.ifsul.modelo.Aeroporto;
import br.edu.ifsul.modelo.Voo;
import br.edu.ifsul.modelo.VooAgendado;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "controleVoo")
@ViewScoped
public class ControleVoo implements Serializable {

    @EJB
    private VooDAO<Voo> dao;
    private Voo objeto;
    
    @EJB
    private AeroportoDAO<Aeroporto> daoAeroporto;
    private Aeroporto aeroporto;
    
    private VooAgendado vooAgendado;
    private Boolean novoVooAgendado;
    private int abaAtiva;

    public ControleVoo() {
    }

    public void removerAeroporto(Aeroporto obj) {
        objeto.getEscalas().remove(obj);
        Util.mensagemInformacao("Aeroporto removido com sucesso!");
    }

    public void adicionarAeroporto() {
        if (!objeto.getEscalas().contains(aeroporto)) {
            objeto.getEscalas().add(aeroporto);
            Util.mensagemInformacao("Aeroporto adicionada com sucesso!");
        } else {
            Util.mensagemInformacao("Escala jÃ¡ possui este aeroporto!");
        }
    }

    public void novoVooAgendado() {
        vooAgendado = new VooAgendado();
        novoVooAgendado = true;
    }

    public void alterarVooAgendado(int index) {
        vooAgendado = objeto.getVooagendado().get(index);
        novoVooAgendado = false;
    }

    public void salvarVooAgendado() {
        if (novoVooAgendado) {
            objeto.adicionarVooAgendado(vooAgendado);
        }
        Util.mensagemInformacao("Voo agendado adicionado ou alterado com sucesso!");
    }

    public void removerVooAgendado(int index){
        objeto.removerVooAgendado(index);
        Util.mensagemInformacao("VooAgendado removido com sucesso!");
    }

    public String listar() {
        return "/privado/Voo/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Voo();
        abaAtiva = 0;
    }

    public void alterar(Object id) {
        try {
            objeto = dao.getObjectById(id);
            abaAtiva = 0;
        } catch (Exception e) {
            Util.mensagemInformacao("Erro ao recuperar Objeto: " + Util.getMensagemErro(e));
        }
    }

    public void excluir(Object id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!!");
        } catch (Exception e) {
            Util.mensagemInformacao("Erro ao remover Objeto: " + Util.getMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e) {
            Util.mensagemInformacao("Erro ao salvar Objeto: " + Util.getMensagemErro(e));
        }
    }

    public VooDAO<Voo> getDao() {
        return dao;
    }

    public void setDao(VooDAO<Voo> dao) {
        this.dao = dao;
    }

    public Voo getObjeto() {
        return objeto;
    }

    public void setObjeto(Voo objeto) {
        this.objeto = objeto;
    }
    //pra funcionar a lista

    public AeroportoDAO<Aeroporto> getDaoAeroporto() {
        return daoAeroporto;
    }

    public VooAgendado getVooAgendado() {
        return vooAgendado;
    }

    public void setVoaoAgendado(VooAgendado voaoAgendado) {
        this.vooAgendado = voaoAgendado;
    }

    public Boolean getNovoVooAgendado() {
        return novoVooAgendado;
    }

    public void setNovoVooAgendado(Boolean novoVooAgendado) {
        this.novoVooAgendado = novoVooAgendado;
    }

    public int getAbaAtiva() {
        return abaAtiva;
    }

    public void setAbaAtiva(int abaAtiva) {
        this.abaAtiva = abaAtiva;
    }

    public Aeroporto getAeroporto() {
        return aeroporto;
    }

    public void setAeroporto(Aeroporto aeroporto) {
        this.aeroporto = aeroporto;
    }
}