/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.AgenciaDao;
import Dao.ClienteDao;
import Dao.ContaDao; 
import Model.ClienteModel;

/**
 *
 * @author guilhermewestrup
 */
public class ClienteController {
    public static void salvarCliente(String nome, String telefone) {
        var cliente = new ClienteModel(nome, telefone);
        
        var clienteDao = new ClienteDao();
        int result = clienteDao.insert(cliente);
        
        System.out.println("result: " + result);
        
    }
    
    public static void mostrarClientePeloId(int id){
        var clienteDao = new ClienteDao();
        var cliente = clienteDao.findById(id);
        
        cliente.imprimeCliente();
        System.out.println("------");
    }
    
    public static void mostrarClienteComConta(int clienteId) {        
        var contaDao = new ContaDao();
        var conta = contaDao.findByClienteId(clienteId);
        
        System.out.println("---- Cliente e sua conta -----");
        System.out.println("Nome: " + conta.getCliente().getNome());
        System.out.println("Conta: " + conta.getNumeroConta() + " Agencia: " + conta.getAgencia().getCodigo());
        System.out.println("---- Fim ----");
    }
    
    public static void deletarCliente(int clienteId) {
        var clienteDao = new ClienteDao();
        var contaDao = new ContaDao();
        
        var conta = contaDao.findByClienteId(clienteId);
        
        if(conta != null) {
            contaDao.delete(conta.getId());
        }

        clienteDao.delete(clienteId);

    }
    
    public static void buscarPorTelefone(String telefone) {
        var clienteDao = new ClienteDao();
        
        var clientes = clienteDao.findByTelefone(telefone);
        
        for(var cliente : clientes) {
            cliente.imprimeCliente();
            System.out.println("Nome + Telefone " + cliente.getNome() + cliente.getTelefone());
        }
        
        System.out.println("------");
        
    }
}
