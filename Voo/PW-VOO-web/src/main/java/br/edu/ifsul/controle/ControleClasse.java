package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ClasseDAO;
import br.edu.ifsul.modelo.Classe;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "controleClasse")
@ViewScoped
public class ControleClasse implements Serializable {
    @EJB
    private ClasseDAO<Classe> dao;
    private Classe objeto;
    
    public ControleClasse() {
    }
    
    public String listar(){
        return "/privado/classe/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Classe();
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
    public ClasseDAO<Classe> getDao() {
        return dao;
    }

    public void setDao(ClasseDAO<Classe> dao) {
        this.dao = dao;
    }

    public Classe getObjeto() {
        return objeto;
    }

    public void setObjeto(Classe objeto) {
        this.objeto = objeto;
    }
    
}
