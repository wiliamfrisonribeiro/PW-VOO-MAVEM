package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Cidade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class TestePersistCidade {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PW-VOO-PU");
        EntityManager em = emf.createEntityManager();
       Cidade c = new Cidade();
        c.setNome("Passo Fundo");
        c.setPais("Brasil");
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
 
}