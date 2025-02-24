package Utils;
import java.io.*;
import java.util.*;

import Classes.Livro;
import Classes.Usuario;
import Classes.Funcionario;

public class OpcoesUsuario {
    private static Scanner scanner = new Scanner(System.in);

    public static void entradaUsuario(List<Usuario> lstUsuario, List<Funcionario> lstFuncionario) {
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
                System.out.println("<<<<< CADASTRAR USUARIOS >>>>>\n");
                System.out.print("Identificacao (0 para encerrar): ");
                int idUsuario = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer após next()

                if (idUsuario == 0) {
                    pause();
                    return;
                }

                if (acharUsuario(lstUsuario, idUsuario) != -1) {
                    System.out.println("Identificação já cadastrada!\n");
                    pause();
                    continue;
                }

                System.out.print("\nNome do Usuario.........: ");
                String nome = scanner.nextLine();

                char sexo;
                do {
                    System.out.print("Sexo do Usuario (M/F)....: ");
                    sexo = Character.toUpperCase(scanner.next().charAt(0));
                    scanner.nextLine(); // Limpa o buffer após next()
                } while (sexo != 'M' && sexo != 'F');

                System.out.print("Data de nascimento do Usuario (dd/mm/YY).......: ");
                String[] dataNasc = scanner.nextLine().split("/");

                System.out.print("CPF do Usuario.......: ");
                String cpf = scanner.next();
                scanner.nextLine(); // Limpa o buffer

                System.out.print("Senha do Usuario.......: ");
                String senha = scanner.nextLine();

                if (confirmou("Confirma o cadastro? (S/*): ", scanner)) {
                    Usuario usuario = new Usuario(idUsuario, nome, sexo, dataNasc, cpf, senha, 'X');
                    lstUsuario.add(usuario);
                    System.out.println("<<<< Confirmado >>>>\n\n\n");
                } else {
                    System.out.println("<<<< Não Confirmado >>>>\n\n\n\n");
                }
                pause();
            }
        } else {
            System.out.println("ACESSO NEGADO\n");
            pause();
        }
    }

    public static void excluirUsuario(List<Usuario> lstUsuario, List<Livro> lstLivro,
            List<Funcionario> lstFuncionario) {
                limpar();
        Funcionario funcionario = new Funcionario();
        if (funcionario.login(lstFuncionario, scanner)) {
            limpar();
            Scanner scanner = new Scanner(System.in);
            System.out.println("<<<<< EXCLUIR USUARIOS >>>>>\n");
            System.out.print("Identificacao do Usuario (0 para encerrar): ");
            int idUsuario = scanner.nextInt();
            if (idUsuario == 0) {
                pause();
                return;
            }
            ;

            int posicao = acharUsuario(lstUsuario, idUsuario);
            if (posicao == -1) {
                System.out.println("Usuario não encontrado!\n");
            } else {
                if (confirmou("Confirma a exclusão? (S/*): ", scanner)) {
                    Usuario usuario = lstUsuario.get(posicao);
                    System.out.printf("Id do usuario: %d | Posicao: %d | Nome: %s\n", idUsuario, posicao,
                            usuario.getNome());
                    usuario.setSit(' ');
                    for (Livro livro : lstLivro) {
                        if (livro.getIdUsuario() == idUsuario) {
                            livro.setIdUsuario(-1);
                        }
                    }
                    System.out.println("<<<< Confirmado >>>>\n\n\n");
                } else {
                    System.out.println("<<<< Não Confirmado >>>>\n\n\n\n");
                }
            }
            pause();
        } else {
            System.out.println("ACESSO NEGADO\n");
            pause();
        }
    }

    public static void listarUsuario(List<Usuario> lstUsuario, List<Livro> lstLivro, List<Funcionario> lstFuncionario) {
        limpar();
        Funcionario funcionario = new Funcionario();
        if (funcionario.login(lstFuncionario, scanner)) {
            limpar();
            System.out.println("<<<<< LISTAR USUARIOS >>>>>\n");
            for (Usuario usuario : lstUsuario) {
                if (usuario.getSit() == 'X') {
                    System.out.println("**********************************");
                    System.out.printf("Identificacao: %d\n", usuario.getId());
                    System.out.printf("Nome: %s\n", usuario.getNome());
                    System.out.printf("Sexo: %c\n", usuario.getSexo());
                    System.out.printf("Idade: %d\n", usuario.getIdade());
                    System.out.printf("CPF: %s\n", usuario.getCpf());
                    System.out.println("Livros emprestados: ");
                    boolean temLivrosEmprestados = false;
                    for (int livroId : usuario.getLivrosEmprestados()) {
                        if (livroId != -1) {
                            int posicaoLivro = acharLivro(lstLivro, livroId);
                            if (posicaoLivro != -1) {
                                Livro livro = lstLivro.get(posicaoLivro);
                                System.out.printf("id: %d - Titulo: %s\n", livro.getCodigo(), livro.getTitulo());
                                temLivrosEmprestados = true;
                            }
                        }
                    }
                    if (!temLivrosEmprestados) {
                        System.out.println("Nenhum livro emprestado");
                    }
                    System.out.printf("Situação: %c\n", usuario.getSit());
                }
            }
            System.out.println("**********************************");
            pause();
        } else {
            System.out.println("ACESSO NEGADO\n");
            pause();
        }
    }

    public static void informacoesUsuario(List<Usuario> lstUsuario, List<Livro> lstLivro) {
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

        System.out.println("<<<<< INFORMAÇÕES DO USUÁRIO >>>>>\n");
        System.out.printf("Identificacao: %d\n", usuario.getId());
        System.out.printf("Nome: %s\n", usuario.getNome());
        System.out.printf("Sexo: %c\n", usuario.getSexo());
        System.out.printf("Idade: %d\n", usuario.getIdade());
        System.out.printf("CPF: %s\n", usuario.getCpf());
        System.out.println("Livros emprestados: ");
        boolean temLivrosEmprestados = false;
        for (int livroId : usuario.getLivrosEmprestados()) {
            if (livroId != -1) {
                int posicaoLivro = acharLivro(lstLivro, livroId);
                if (posicaoLivro != -1) {
                    Livro livro = lstLivro.get(posicaoLivro);
                    System.out.printf("id: %d - Titulo: %s\n", livro.getCodigo(), livro.getTitulo());
                    temLivrosEmprestados = true;
                }
            }
        }
        if (!temLivrosEmprestados) {
            System.out.println("Nenhum livro emprestado");
        }
        System.out.printf("Situação: %c\n", usuario.getSit());
        pause();
    }

    public static void importarUsuarios(List<Usuario> usuarios) {
        try (BufferedReader br = new BufferedReader(new FileReader("data/usuarios.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                int id = Integer.parseInt(parts[0]);
                String nome = parts[1];
                char sexo = parts[2].charAt(0);
                String[] dataNasc = parts[3].split("/");
                String cpf = parts[4].isEmpty() ? "" : parts[4];
                String senha = parts[5];
                char situacao = parts[6].charAt(0);
                usuarios.add(new Usuario(id, nome, sexo, dataNasc, cpf, senha, situacao));
            }
        } catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo usuarios.txt");
        }
    }

    public static void exportarUsuario(List<Usuario> usuarios) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("data/usuarios.txt"))) {
            for (Usuario usuario : usuarios) {
                pw.printf("%d;%s;%c;%s;%s;%s;%c\n", usuario.getId(), usuario.getNome(), usuario.getSexo(),
                        usuario.getDataNasc(), usuario.getCpf(), usuario.getSenha(), usuario.getSit());
            }
        } catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo usuarios.txt");
        }
    }

    // Helper methods
    private static int acharUsuario(List<Usuario> lstUsuario, int idUsuario) {
        for (int i = 0; i < lstUsuario.size(); i++) {
            if (lstUsuario.get(i).getId() == idUsuario) {
                return i;
            }
        }
        return -1;
    }

    private static int acharLivro(List<Livro> lstLivro, int idLivro) {
        for (int i = 0; i < lstLivro.size(); i++) {
            if (lstLivro.get(i).getCodigo() == idLivro) {
                return i;
            }
        }
        return -1;
    }

    private static boolean confirmou(String mensagem, Scanner scanner) {
        System.out.print(mensagem);
        char resposta = Character.toUpperCase(scanner.next().charAt(0));
        return resposta == 'S';
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
