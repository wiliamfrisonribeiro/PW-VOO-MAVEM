/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Cidade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteAlterarCidade {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PW-VOO-PU");
        EntityManager em = emf.createEntityManager();
        Cidade e = em.find(Cidade.class, 6);
        e.setNome("Rio de Janeiro");
        e.setPais("Brasil");
        em.getTransaction().begin();
        em.merge(e);
        em.getTransaction().commit();
        em.close();
        emf.close();
       
    }
}
