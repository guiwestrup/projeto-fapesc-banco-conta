/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.AgenciaDao;
import Dao.ClienteDao;
import Dao.ContaDao;
import Model.ContaModel;

/**
 *
 * @author guilhermewestrup
 */
public class ContaController {
    
    
    public static void salvarConta(String numeroConta, float saldo, int cliente_id, int agencia_id) {
        var clienteDao = new ClienteDao();
        var clienteModel = clienteDao.findById(cliente_id);
        
        var agenciaDao = new AgenciaDao();
        var agenciaModel = agenciaDao.findById(agencia_id);
        
        var conta = new ContaModel(numeroConta, saldo, clienteModel, agenciaModel);
        
        var contaDao = new ContaDao();
        contaDao.insert(conta);
    }
}
