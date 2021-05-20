package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class DAOGenerico<TIPO> implements Serializable{
    private List<TIPO> listaObjetos;
    private List<TIPO> listaTodos;
    @PersistenceContext(unitName = "PW-VOO-WEB-PU")
    protected EntityManager em;
    protected Class classePersistente;
    protected String filtro = "";
    protected List<Ordem> listaOrdem = new ArrayList<>();
    protected Ordem ordemAtual;
    protected ConverterOrdem converterOrdem;
    protected Integer maximoObjetos = 5;
    protected Integer posicaoAtual = 0;
    protected Integer totalObjetos = 0;

    public DAOGenerico() {
    }
    

    public List<TIPO> getListaObjetos() {
        String jpql = "from " + classePersistente.getSimpleName();
        String where = "";
        filtro = filtro.replaceAll("[';-]", "");
        if (filtro.length() > 0) {
            switch (ordemAtual.getOperador()) {
                case "=":
                    if (ordemAtual.getAtributo().equals("id")) {
                        try {
                            Integer.parseInt(filtro);
                        } catch (Exception e) {
                            filtro = "0";
                        }
                    }
                    where += " where " + ordemAtual.getAtributo() + " = '" + filtro + "' ";
                    break;
                case "like":
                    where += " where upper(" + ordemAtual.getAtributo() + ") like '"
                            + filtro.toUpperCase() + "%' ";
                    break;
            }
        }
        jpql += where;
        jpql += " order by " + ordemAtual.getAtributo();
        System.out.println("JPQL: " + jpql);
        totalObjetos = em.createQuery(jpql).getResultList().size();
        return em.createQuery(jpql).setFirstResult(posicaoAtual).
                setMaxResults(maximoObjetos).getResultList();
    }
    
    public void primeiro() {
        posicaoAtual = 0;
    }
    
    public void anterior() {
        posicaoAtual -= maximoObjetos;
        if (posicaoAtual < 0) {
            posicaoAtual = 0;
        }
    }
    public void proximo() {
        if (posicaoAtual + maximoObjetos < totalObjetos) {
            posicaoAtual += maximoObjetos;
        }
    }

    public void ultimo() {
        int resto = totalObjetos % maximoObjetos;
        if (resto > 0) {
            posicaoAtual = totalObjetos - resto;
        } else {
            posicaoAtual = totalObjetos - maximoObjetos;
        }
    }
    public String getMensagemNavegacao() {
        int ate = posicaoAtual + maximoObjetos;
        if (ate > totalObjetos) {
            ate = totalObjetos;
        }
        if (totalObjetos > 0) {
            return "Listando de " + (posicaoAtual + 1) + " até " + ate + " de "
                    + totalObjetos + " registros";
        } else {
            return "Nenhum registro encontrado";
        }
    }
    
    public List<TIPO> getListaTodos() {
       String jpql = "from " + classePersistente.getSimpleName()
                + " order by " + ordemAtual.getAtributo();
        return em.createQuery(jpql).getResultList();
    }

    public void setListaObjetos(List<TIPO> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }
    
    @RolesAllowed("ADMINISTRADOR")
    public void persist(TIPO obj) throws Exception{
        em.persist(obj);
    }
    public void merge(TIPO obj) throws Exception{
        em.merge(obj);
    }
    
    @RolesAllowed("ADMINISTRADOR")
    public void remove(TIPO obj) throws Exception{
        obj = em.merge(obj);
        em.remove(obj);
    }
    public TIPO getObjectById(Object id) throws Exception{
       return (TIPO) em.find(classePersistente, id);
    }

    public void setListaTodos(List<TIPO> listaTodos) {
        this.listaTodos = listaTodos;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Class getClassePersistente() {
        return classePersistente;
    }

    public void setClassePersistente(Class classePersistente) {
        this.classePersistente = classePersistente;
    }

    /**
     * @return the filtro
     */
    public String getFiltro() {
        return filtro;
    }

    /**
     * @param filtro the filtro to set
     */
    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    /**
     * @return the listaOrdem
     */
    public List<Ordem> getListaOrdem() {
        return listaOrdem;
    }

    /**
     * @param listaOrdem the listaOrdem to set
     */
    public void setListaOrdem(List<Ordem> listaOrdem) {
        this.listaOrdem = listaOrdem;
    }

    /**
     * @return the ordemAtual
     */
    public Ordem getOrdemAtual() {
        return ordemAtual;
    }

    /**
     * @param ordemAtual the ordemAtual to set
     */
    public void setOrdemAtual(Ordem ordemAtual) {
        this.ordemAtual = ordemAtual;
    }

    /**
     * @return the converterOrdem
     */
    public ConverterOrdem getConverterOrdem() {
        return converterOrdem;
    }

    /**
     * @param converterOrdem the converterOrdem to set
     */
    public void setConverterOrdem(ConverterOrdem converterOrdem) {
        this.converterOrdem = converterOrdem;
    }

    /**
     * @return the maximoObjetos
     */
    public Integer getMaximoObjetos() {
        return maximoObjetos;
    }

    /**
     * @param maximoObjetos the maximoObjetos to set
     */
    public void setMaximoObjetos(Integer maximoObjetos) {
        this.maximoObjetos = maximoObjetos;
    }

    /**
     * @return the posicaoAtual
     */
    public Integer getPosicaoAtual() {
        return posicaoAtual;
    }

    /**
     * @param posicaoAtual the posicaoAtual to set
     */
    public void setPosicaoAtual(Integer posicaoAtual) {
        this.posicaoAtual = posicaoAtual;
    }

    /**
     * @return the totalObjetos
     */
    public Integer getTotalObjetos() {
        return totalObjetos;
    }

    /**
     * @param totalObjetos the totalObjetos to set
     */
    public void setTotalObjetos(Integer totalObjetos) {
        this.totalObjetos = totalObjetos;
    }

   
}