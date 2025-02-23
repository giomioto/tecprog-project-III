/*--------------------------
 * PROJETO FINAL III
 * -------------------------
 * ESTUDANTE: GIOVANNI MIOTO
 * RA: 2603454
 * -------------------------
 */

import java.util.Scanner;

import Classes.Funcionario;
import Utils.OpcoesFuncionario;
import Utils.OpcoesLivro;
import Utils.OpcoesUsuario;
import Classes.Usuario;
import Classes.Livro;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;
        ArrayList<Livro> livros = new ArrayList<>();
        ArrayList<Usuario> usuarios = new ArrayList<>();
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        OpcoesFuncionario.importarFuncionarios(funcionarios);
        OpcoesUsuario.importarUsuarios(usuarios);
        OpcoesLivro.importarLivros(livros, usuarios);

        // Limpar a tela
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (opcao != 3) {
            limpar();
            System.out.println("********* MENU PRINCIPAL *********");
            System.out.println("[ 1 ] Menu Usuário");
            System.out.println("[ 2 ] Menu Funcionário");
            System.out.println("[ 3 ] Sair");
            System.out.println("**********************************");
            opcao = scanner.nextInt();
            scanner.nextLine();
            if (opcao == 1) {
                menuUsuario(scanner, livros, usuarios);
            } else if (opcao == 2) {
                menuFuncionario(scanner, livros, usuarios, funcionarios);
            }
        }
        OpcoesLivro.exportarLivro(livros);
        OpcoesUsuario.exportarUsuario(usuarios);
        OpcoesFuncionario.exportarFuncionarios(funcionarios);
        scanner.close();
    }

    private static void menuUsuario(Scanner scanner, ArrayList<Livro> livros, ArrayList<Usuario> usuarios) {
        int opcao = 0;
        while (opcao != 5) {
            limpar();
            System.out.println("********* MENU USUÁRIO *********");
            System.out.println("[ 1 ] Listar Livros");
            System.out.println("[ 2 ] Emprestar Livros");
            System.out.println("[ 3 ] Devolver Livro");
            System.out.println("[ 4 ] Informações do Usuário");
            System.out.println("[ 5 ] Voltar");
            System.out.println("********************************");
            opcao = scanner.nextInt();
            scanner.nextLine();
            if (opcao == 1) {
                // Função para listar livros
                OpcoesLivro.listarLivro(livros, usuarios);
            } else if (opcao == 2) {
                // Função para emprestar livros
                OpcoesLivro.emprestarLivro(livros, usuarios);
            } else if (opcao == 3) {
                // Função para devolver livros
                OpcoesLivro.devolverLivro(livros, usuarios);
            } else if (opcao == 4) {
                // Função para mostrar informações do usuário
                OpcoesUsuario.informacoesUsuario(usuarios, livros);
            }            
        }
    }

    private static void menuFuncionario(Scanner scanner, ArrayList<Livro> livros, ArrayList<Usuario> usuarios, ArrayList<Funcionario> funcionarios) {
        int opcao = 0;
        while (opcao != 10) {
            limpar();
            System.out.println("********* MENU FUNCIONÁRIO *********");
            System.out.println("[ 1 ] Cadastrar Livros");
            System.out.println("[ 2 ] Excluir Livros");
            System.out.println("[ 3 ] Listar Livros");
            System.out.println("[ 4 ] Cadastrar Usuário");
            System.out.println("[ 5 ] Excluir Usuário");
            System.out.println("[ 6 ] Listar Usuários");
            System.out.println("[ 7 ] Cadastrar Funcionário");
            System.out.println("[ 8 ] Excluir Funcionário");
            System.out.println("[ 9 ] Listar Funcionários");
            System.out.println("[ 10 ] Voltar");
            System.out.println("************************************");
            opcao = scanner.nextInt();
            scanner.nextLine();
            if (opcao == 1) {
                // Função para cadastrar livros
                OpcoesLivro.entradaLivro(livros, funcionarios);
            } else if (opcao == 2) {
                // Função para excluir livros
                OpcoesLivro.excluirLivro(livros, usuarios, funcionarios);
            } else if (opcao == 3) {
                // Função para listar livros
                OpcoesLivro.listarLivro(livros, usuarios);
            } else if (opcao == 4) {
                // Função para cadastrar usuários
                OpcoesUsuario.entradaUsuario(usuarios, funcionarios);
            } else if (opcao == 5) {
                // Função para excluir usuários
                OpcoesUsuario.excluirUsuario(usuarios, livros, funcionarios);
            } else if (opcao == 6) {
                // Função para listar usuários
                OpcoesUsuario.listarUsuario(usuarios, livros, funcionarios);
            } else if (opcao == 7) {
                // Função para cadastrar funcionários
                OpcoesFuncionario.cadastrarFuncionario(funcionarios);
            } else if (opcao == 8) {
                // Função para excluir funcionários
                OpcoesFuncionario.excluirFuncionario(funcionarios);
            } else if (opcao == 9) {
                // Função para listar funcionários
                OpcoesFuncionario.listarFuncionarios(funcionarios);
            }
        }
    }
    public static void limpar() {
        try {
            System.out.println("\n".repeat(10));
            String sistema = System.getProperty("os.name").toLowerCase();
            if (sistema.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
