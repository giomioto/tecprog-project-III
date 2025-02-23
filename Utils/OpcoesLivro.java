package Utils;
import java.util.ArrayList;
import java.util.Scanner;

import Classes.Funcionario;
import Classes.Livro;
import Classes.Usuario;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class OpcoesLivro {
    private static Scanner scanner = new Scanner(System.in);

    public static void entradaLivro(ArrayList<Livro> lstLivro, ArrayList<Funcionario> lstFuncionario) {
        limpar();
        Funcionario funcionario = new Funcionario();
        if (funcionario.login(lstFuncionario, scanner)) {
            System.out.println("ACESSO PERMITIDO\n");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            limpar();
            while (true) {
                System.out.println("<<<<< CADASTRAR LIVROS >>>>>\n\n");
                System.out.print("Identificacao (0 para encerrar): ");
                int idLivro = scanner.nextInt();
                scanner.nextLine(); // consume newline
                if (idLivro == 0) {
                    pause();
                    return;
                }
                ;

                if (acharLivro(lstLivro, idLivro) != -1) {
                    System.out.println("Livro já cadastrado!\n\n");
                    pause();
                    continue;
                }

                System.out.print("\nTitulo do Livro.........: ");
                String titulo = scanner.nextLine();

                System.out.print("Autor do Livro..........: ");
                String autor = scanner.nextLine();

                System.out.print("Ano de Publicacao.......: ");
                int ano = scanner.nextInt();
                scanner.nextLine(); // consume newline

                if (confirmou("Confirma o cadastro? (S/*): ")) {
                    Livro livro = new Livro(idLivro, titulo, autor, ano, 'X');
                    lstLivro.add(livro);
                    System.out.println("<<<< Confirmado >>>>\n\n\n");
                } else {
                    System.out.println("<<<< Não Confirmado >>>>\n\n\n\n");
                }

                pause();
            }
        } else {
            System.out.println("ACESSO NEGADO\n");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void excluirLivro(ArrayList<Livro> lstLivro, ArrayList<Usuario> lstUsuario,
            ArrayList<Funcionario> lstFuncionario) {
        limpar();
        Funcionario funcionario = new Funcionario();
        if (funcionario.login(lstFuncionario, scanner)) {
            System.out.println("ACESSO PERMITIDO\n");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            limpar();
            System.out.println("<<<<< EXCLUIR LIVROS >>>>>\n\n");
            System.out.print("Identificacao do Livro (0 para encerrar): ");
            int idLivro = scanner.nextInt();
            scanner.nextLine(); // consume newline
            if (idLivro == 0) {
                pause();
                return;
            }
            ;

            int posicao = acharLivro(lstLivro, idLivro);
            if (posicao == -1) {
                System.out.println("Livro não encontrado!\n\n");
            } else {
                if (confirmou("Confirma a exclusão? (S/*): ")) {
                    Livro livro = lstLivro.get(posicao);
                    livro.setSit(' ');
                    int idUsuario = livro.getIdUsuario();
                    int posicaoUsuario = acharUsuario(lstUsuario, idUsuario);
                    if (posicaoUsuario != -1) {
                        Usuario usuario = lstUsuario.get(posicaoUsuario);
                        usuario.excluirLivro(idLivro);
                    }
                    System.out.printf("Id do Livro: %d | Posicao: %d | Nome: %s\n", idLivro, posicao,
                            livro.getTitulo());
                    System.out.println("<<<< Confirmado >>>>\n\n\n");
                } else {
                    System.out.println("<<<< Não Confirmado >>>>\n\n\n\n");
                }
            }
            pause();
        } else {
            System.out.println("ACESSO NEGADO\n");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void listarLivro(ArrayList<Livro> lstLivro, ArrayList<Usuario> lstUsuario) {
        limpar();
        System.out.println("<<<<< LISTAR LIVROS >>>>>\n\n");
        for (Livro livro : lstLivro) {
            if (livro.getSit() == 'X') {
                System.out.printf("Identificacao: %d\n", livro.getCodigo());
                System.out.printf("Titulo: %s\n", livro.getTitulo());
                System.out.printf("Autor: %s\n", livro.getAutor());
                System.out.printf("Ano: %d\n", livro.getAno());
                System.out.printf("ID Usuário: %d | ", livro.getIdUsuario());
                int posicaoUsuario = acharUsuario(lstUsuario, livro.getIdUsuario());
                if (posicaoUsuario != -1) {
                    System.out.printf("Usuário: %s\n", lstUsuario.get(posicaoUsuario).getNome());
                }
                System.out.printf("Situação: %c\n\n", livro.getSit());
            }
        }
        pause();

    }

    public static void emprestarLivro(ArrayList<Livro> lstLivro, ArrayList<Usuario> lstUsuario) {
        limpar();
        System.out.print("Identificacao do Usuario: ");
        int idUsuario = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        int posicaoUsuario = acharUsuario(lstUsuario, idUsuario);
        if (posicaoUsuario == -1 || !lstUsuario.get(posicaoUsuario).getSenha().equals(senha)) {
            System.out.println("Usuario não encontrado ou senha incorreta!\n\n");
            pause();
            return;
        }
        Usuario usuario = lstUsuario.get(posicaoUsuario);
        System.out.printf("Login no usuário: %s\n", usuario.getNome());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        limpar();
        System.out.println("<<<<< EMPRESTAR LIVRO >>>>>\n\n");
        System.out.print("Identificacao do Livro (0 para encerrar): ");
        int idLivro = scanner.nextInt();
        scanner.nextLine(); // consume newline
        if (idLivro == 0) {
            pause();
            return;
        }
        ;

        int posicaoLivro = acharLivro(lstLivro, idLivro);
        if (posicaoLivro == -1) {
            System.out.println("Livro não encontrado!\n\n");
        } else {
            Livro livro = lstLivro.get(posicaoLivro);
            if (livro.getIdUsuario() != -1) {
                System.out.println("Livro já emprestado!\n\n");
                pause();
                return;
            } else if (livro.getSit() == ' ') {
                System.out.println("Livro não está na biblioteca!\n\n");
                pause();
                return;
            } else {
                System.out.printf("Titulo: %s\n", livro.getTitulo());
            }
            if (idUsuario == 0)
                return;

            if (confirmou("Confirma requisição para empréstimo? (S/*): ")) {
                livro.setIdUsuario(idUsuario);
                usuario.setLivrosEmprestados(idLivro);
            } else {
                System.out.println("<<<< Não Confirmado >>>>\n\n\n\n");
            }
        }
        pause();
    }

    public static void devolverLivro(ArrayList<Livro> lstLivro, ArrayList<Usuario> lstUsuario) {
        limpar();
        System.out.print("Identificacao do Usuario: ");
        int idUsuario = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        int posicaoUsuario = acharUsuario(lstUsuario, idUsuario);
        if (posicaoUsuario == -1 || !lstUsuario.get(posicaoUsuario).getSenha().equals(senha)) {
            System.out.println("Usuario não encontrado ou senha incorreta!\n\n");
            pause();
            return;
        }
        Usuario usuario = lstUsuario.get(posicaoUsuario);
        System.out.printf("Login no usuário: %s\n", usuario.getNome());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        limpar();
        System.out.println("<<<<< DEVOLVER LIVRO >>>>>\n\n");
        System.out.print("Identificacao do Livro (0 para encerrar): ");
        int idLivro = scanner.nextInt();
        scanner.nextLine(); // consume newline
        if (idLivro == 0) {
            pause();
            return;
        }

        int posicaoLivro = acharLivro(lstLivro, idLivro);
        if (posicaoLivro == -1) {
            System.out.println("Livro não encontrado!\n\n");
        } else {
            Livro livro = lstLivro.get(posicaoLivro);
            if (livro.getIdUsuario() != idUsuario) {
                System.out.println("Livro não está emprestado para este usuário!\n\n");
                pause();
                return;
            }
            System.out.printf("Titulo: %s\n", livro.getTitulo());
            if (confirmou("Confirma a devolução? (S/*): ")) {
                livro.setIdUsuario(-1);
                usuario.excluirLivro(idLivro);
                System.out.println("<<<< Confirmado >>>>\n\n\n");
            } else {
                System.out.println("<<<< Não Confirmado >>>>\n\n\n\n");
            }
        }
        pause();
    }

    public static void importarLivros(ArrayList<Livro> lstLivro, ArrayList<Usuario> lstUsuario) {
        try (Scanner fileScanner = new Scanner(new File("data/livros.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(";");
                int codigo = Integer.parseInt(parts[0]);
                String titulo = parts[1];
                String autor = parts[2];
                int ano = Integer.parseInt(parts[3]);
                int idUsuario = Integer.parseInt(parts[4]);
                char sit = parts[5].charAt(0);

                Livro livro = new Livro(codigo, titulo, autor, ano, sit);
                livro.setIdUsuario(idUsuario);
                lstLivro.add(livro);

                if (idUsuario != -1) {
                    int posicaoUsuario = acharUsuario(lstUsuario, idUsuario);
                    if (posicaoUsuario != -1) {
                        Usuario usuario = lstUsuario.get(posicaoUsuario);
                        usuario.setLivrosEmprestados(codigo);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao abrir o arquivo livros.txt");
        }
    }

    public static void exportarLivro(ArrayList<Livro> lstLivro) {
        try (PrintWriter writer = new PrintWriter(new File("data/livros.txt"))) {
            for (Livro livro : lstLivro) {
                writer.printf("%d;%s;%s;%d;%d;%c\n",
                        livro.getCodigo(),
                        livro.getTitulo(),
                        livro.getAutor(),
                        livro.getAno(),
                        livro.getIdUsuario(),
                        livro.getSit());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao abrir o arquivo livros.txt");
        }
    }

    private static int acharLivro(ArrayList<Livro> lstLivro, int idLivro) {
        for (int i = 0; i < lstLivro.size(); i++) {
            if (lstLivro.get(i).getCodigo() == idLivro) {
                return i;
            }
        }
        return -1;
    }

    private static int acharUsuario(ArrayList<Usuario> lstUsuario, int idUsuario) {
        for (int i = 0; i < lstUsuario.size(); i++) {
            if (lstUsuario.get(i).getId() == idUsuario) {
                return i;
            }
        }
        return -1;
    }

    private static boolean confirmou(String mensagem) {
        System.out.print(mensagem);
        String resposta = scanner.nextLine();
        return resposta.equalsIgnoreCase("S");
    }

    private static void pause() {
        System.out.println("Pressione Enter para continuar...");
        scanner.nextLine();
        limpar();
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
