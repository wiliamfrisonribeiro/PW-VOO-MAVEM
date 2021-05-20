/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Aeroporto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteListarAeroporto {
       public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PW-VOO-PU");
        EntityManager em = emf.createEntityManager();
        List<Aeroporto> l = em.createQuery("from Aeroporto order by id").getResultList();
        for (Aeroporto a : l){
            System.out.println("ID: " + a.getId() + " Nome do Aeroporto: " + a.getNome() + " Operação: " + a.getOperacaoNoturna()+ " IdCidade: " + a.getCidade().getNome());
        }

        em.close();
        emf.close();
        
        
        
    }
}
