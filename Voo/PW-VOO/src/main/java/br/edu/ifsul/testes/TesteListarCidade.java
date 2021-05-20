/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Cidade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class TesteListarCidade {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PW-VOO-PU");
        EntityManager em = emf.createEntityManager();
        List<Cidade> lista = em.createQuery("from Cidade order by id").getResultList();
        for (Cidade e : lista){
            System.out.println("ID: " + e.getId() + " Nome: " + e.getNome() + " Pais: " + e.getPais());
        }


        em.close();
        emf.close();
        
        
        
    }
}
