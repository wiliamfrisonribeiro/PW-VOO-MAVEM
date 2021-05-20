package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AeroportoDAO;
import br.edu.ifsul.dao.CidadeDAO;
import br.edu.ifsul.modelo.Aeroporto;
import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "controleAeroporto")
@ViewScoped
public class ControleAeroporto implements Serializable {
    @EJB
    private AeroportoDAO<Aeroporto> dao;
    private Aeroporto objeto;
    @EJB
    private CidadeDAO<Cidade> daoCidade;
    
    public ControleAeroporto() {
    }
    
    public void imprimeAeroportos(){
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatoriosAeroporto", parametros, dao.getListaTodos());
    }
    
     public void imprimeAeroporto(Object id){
        try{
            objeto = dao.getObjectById(id);
            List<Aeroporto> lista = new ArrayList<>();
            lista.add(objeto);
            HashMap parametros = new HashMap();
            UtilRelatorios.imprimeRelatorio("relatoriosAeroporto", parametros, lista);
        } catch(Exception e){
            Util.mensagemInformacao("Erro ao imprimir: "+ Util.getMensagemErro(e));
        }
    }

    public String listar(){
        return "/privado/aeroporto/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Aeroporto();
    }
    public void alterar(Object id){
        try{
            objeto = dao.getObjectById(id);
        } catch(Exception e){
            Util.mensagemInformacao("Erro ao recuperar Objeto: "+ Util.getMensagemErro(e));
        }
    }
    
    public void excluir(Object id){
        try{
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!!");
        } catch(Exception e){
            Util.mensagemInformacao("Erro ao remover Objeto: "+ Util.getMensagemErro(e));
        }
    }
    public void salvar(){
        try{
            if(objeto.getId() == null){
                dao.persist(objeto);
            }else{
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch(Exception e){
            Util.mensagemInformacao("Erro ao salvar Objeto: "+ Util.getMensagemErro(e));
        }
    }
    public AeroportoDAO<Aeroporto> getDao() {
        return dao;
    }

    public void setDao(AeroportoDAO<Aeroporto> dao) {
        this.dao = dao;
    }

    public Aeroporto getObjeto() {
        return objeto;
    }

    public void setObjeto(Aeroporto objeto) {
        this.objeto = objeto;
    }
    //pra funcionar a lista
    public CidadeDAO<Cidade> getDaoCidade() {
        return daoCidade;
    }

    public void setDaoEstado(CidadeDAO<Cidade> daoCidade) {
        this.daoCidade = daoCidade;
    }
}