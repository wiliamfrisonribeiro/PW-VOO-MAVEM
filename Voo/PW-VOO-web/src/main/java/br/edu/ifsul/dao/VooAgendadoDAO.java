/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.VooAgendado;

import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class VooAgendadoDAO<TIPO> extends DAOGenerico<VooAgendado> implements Serializable {
    public VooAgendadoDAO(){
        super();
        classePersistente = VooAgendado.class;
        // definir as ordens possíveis
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("aeronave", "Aeronave", "like"));
        // difinir a ordem inicial
        ordemAtual = listaOrdem.get(1);
        // inicializar o conversor das ordens
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);   
    }

}
