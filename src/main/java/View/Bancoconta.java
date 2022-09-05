package View;

import Controller.AgenciaController;
import Controller.ClienteController;
import Controller.ContaController;
import java.util.Scanner;
/**
 *
 * @author guilhermewestrup
 */
public class Bancoconta {

    public static void main(String[] args) {        
        int control = 3000;
        var digitado = new Scanner(System.in);
        
        while(control != 0) {
            System.out.println("Digite o que você deseja fazer:");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Cadastrar Agencia");
            System.out.println("3 - Procurar Cliente pelo ID");
            System.out.println("4 - Cadastrar conta");
            System.out.println("5 - Buscar cliente pelo ID com numero da conta");
            System.out.println("6 - Deletar cliente pelo ID");
            System.out.println("7 - buscar cliente pelo telefone");
            System.out.println("0 - Sair");
            control = digitado.nextInt();
            
            switch(control) {
                case 1 ->  {
                    mostrarCadastroCliente();
                }
                case 2 ->  {
                    mostrarCadastroAgencia();
                }
                case 3 -> {
                    mostrarBuscaClientePorId();
                }
                case 4 -> {
                    mostrarCadastroConta();
                }
                case 5 -> {
                    mostrarBuscaClienteComConta();
                }
                case 6 -> {
                    mostrarDeleteClienteId();
                }
                case 7 -> {
                    mostrarBuscarPorTelefone();
                }
            }
        }
    }
    
    private static void mostrarCadastroCliente() {
        var digitado = new Scanner(System.in);
        
        System.out.println("----- Cadastro de cliente -----");
        System.out.println("Digite o nome do cliente:");
        String nome = digitado.nextLine();
        System.out.println("Digite o telefone:");
        String telefone = digitado.next();
        
        ClienteController.salvarCliente(nome, telefone);
        
        System.out.println("---- Fim do cadastro -----");
    }
    
    private static void mostrarCadastroAgencia() {
        var digitado = new Scanner(System.in);
        
        System.out.println("----- Cadastro de Agencia -----");
        System.out.println("Digite o nome:");
        String nome = digitado.next();
        System.out.println("Digite o codigo:");
        String codigo = digitado.next();
        System.out.println("Digite a cidade:");
        String cidade = digitado.next();
        AgenciaController.salvarAgencia(codigo, nome, cidade);
        
        mostrarMensagemDeRetorno();
        
        System.out.println("---- Fim do cadastro de Agencia -----");
    }
    
    private static void mostrarBuscaClientePorId() {
        var digitado = new Scanner(System.in);
        System.out.println("---- Busca de cliente por ID ----");
        System.out.println("Digite o ID do cliente:");
        int idCliente = digitado.nextInt();
        
        ClienteController.mostrarClientePeloId(idCliente);
        
        mostrarMensagemDeRetorno();
    }
    
    private static void mostrarCadastroConta() {
        var digitado = new Scanner(System.in);
        
        System.out.println("----- Cadastro de Conta -----");
        System.out.println("Digite o numero da conta:");
        String numeroConta = digitado.next();
        System.out.println("Digite o saldo inicial:");
        float saldo = digitado.nextFloat();
        System.out.println("Digite o ID do cliente:");
        int idCliente = digitado.nextInt();
        System.out.println("Digite o ID da Agencia:");
        int idAgencia = digitado.nextInt();
        
        ContaController.salvarConta(numeroConta, saldo, idCliente, idAgencia);
        
        mostrarMensagemDeRetorno();
        
        System.out.println("----- Fim Cadastro de Conta -----");
    }
    
    private static void mostrarBuscaClienteComConta() {
        var digitado = new Scanner(System.in);
        System.out.println("----- Buscar cliente pelo ID com conta -----");
        System.out.println("Digite o ID do cliente:");
        int clienteId = digitado.nextInt();

        
        ClienteController.mostrarClienteComConta(clienteId);
        mostrarMensagemDeRetorno();
    }
    
    private static void mostrarMensagemDeRetorno() {
        var digitado = new Scanner(System.in);
        int controle = 1;
        
        while (controle != 0) {
            System.out.println("Digite 0 para à tela inicial.");
            controle = digitado.nextInt();
        }
    }
    
    private static void mostrarDeleteClienteId() {
        var digitado = new Scanner(System.in);
        
        System.out.println("----- Deletar cliente -----");
        System.out.println("Digite o ID do cliente:");
        int clienteId = digitado.nextInt();
        
        ClienteController.deletarCliente(clienteId);
        System.out.println("Cliente apagado");
        System.out.println("----- Fim Deletar -----");
    }
    
    private static void mostrarBuscarPorTelefone() {
        var digitado = new Scanner(System.in);
        int controle = 1;
        
        System.out.println("----- Buscar cliente pelo telefone -----");
        System.out.println("Digite o telefone:");
        String telefone = digitado.next();
        
        ClienteController.buscarPorTelefone(telefone);
        
        
        while (controle != 0) {
            System.out.println("Digite 0 para à tela inicial.");
            controle = digitado.nextInt();
        }
    }
}