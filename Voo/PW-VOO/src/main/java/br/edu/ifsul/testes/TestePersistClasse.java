/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Classe;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestePersistClasse {
         public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PW-VOO-PU");
        EntityManager em = emf.createEntityManager();
        Classe c = new Classe();
        c.setNome("Classe 1");
        c.setValor(100.5);
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
