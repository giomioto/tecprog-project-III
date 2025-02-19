/*--------------------------
 * PROJETO FINAL II
 * -------------------------
 * ESTUDANTE: GIOVANNI MIOTO
 * RA: 2603454
 * -------------------------
 */

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;
        ArrayList<Livro> livros = new ArrayList<>();
        ArrayList<Usuario> usuarios = new ArrayList<>();

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
        while (opcao != 9) {
            System.out.println("********* MENU PRINCIPAL *********");
            System.out.println("[ 1 ] Cadastrar Livros");
            System.out.println("[ 2 ] Excluir Livros");
            System.out.println("[ 3 ] Listar Livros");
            System.out.println("[ 4 ] Emprestar Livros");
            System.out.println("[ 5 ] Devolver Livro");
            System.out.println("[ 6 ] Cadastrar Usuário");
            System.out.println("[ 7 ] Excluir Usuário");
            System.out.println("[ 8 ] Listar Usuários");
            System.out.println("[ 9 ] Sair");
            System.out.println("**********************************");
            opcao = scanner.nextInt();
            scanner.nextLine();
            if (opcao == 1) {
                // Função para cadastrar livros
                OpcoesLivro.entradaLivro(livros);
            } else if (opcao == 2) {
                // Função para excluir livros
                OpcoesLivro.excluirLivro(livros, usuarios);
            } else if (opcao == 3) {
                // Função para listar livros
                System.out.println("Entrou na função listar livros");
                OpcoesLivro.listarLivro(livros, usuarios);
            } else if (opcao == 4) {
                // Função para emprestar livros
                System.out.println("Entrou na função emprestar livros");
                OpcoesLivro.emprestarLivro(livros, usuarios);
            } else if (opcao == 5) {
                // Função para devolver livros
                System.out.println("Entrou na função devolver livros");
                OpcoesLivro.devolverLivro(livros, usuarios);
            } else if (opcao == 6) {
                // Função para cadastrar usuários
                System.out.println("Entrou na função cadastrar usuários");
                OpcoesUsuario.entradaUsuario(usuarios);
            } else if (opcao == 7) {
                // Função para excluir usuários
                System.out.println("Entrou na função excluir usuários");
                OpcoesUsuario.excluirUsuario(usuarios, livros);
            } else if (opcao == 8) {
                // Função para listar usuários
                System.out.println("Entrou na função listar usuários");
                OpcoesUsuario.listarUsuario(usuarios, livros);
            }
        }
        OpcoesLivro.exportarLivro(livros);
        OpcoesUsuario.exportarUsuario(usuarios);
        scanner.close();
    }
}
