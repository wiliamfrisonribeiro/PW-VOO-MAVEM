/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.utilrelatorios;

import br.edu.ifsul.modelo.Aeroporto;
import br.edu.ifsul.modelo.Cidade;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dueren
 */
public class FabricaObjetos {
    public static List<Aeroporto> carregaAeroporto(){
        List<Aeroporto> lista = new ArrayList<>();
        Aeroporto a = new Aeroporto();
        a.setId(1);
        a.setNome("Congonhas");
        a.setOperacaoNoturna(Boolean.TRUE);
        
        Cidade c = new Cidade();
        c.setId(1);
        c.setNome("Marau");
        c.setPais("Brasil");
        
        a.setCidade(c);
        
        lista.add(a);
        return lista;
    }
}
