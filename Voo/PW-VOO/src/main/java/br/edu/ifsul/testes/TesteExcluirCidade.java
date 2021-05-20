package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Cidade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteExcluirCidade {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PW-VOO-PU");
        EntityManager em = emf.createEntityManager();
        Cidade e = em.find(Cidade.class, 6);
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
        em.close();
        emf.close();

    }

}
