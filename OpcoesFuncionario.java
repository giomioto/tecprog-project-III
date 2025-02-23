import java.io.*;
import java.util.*;

public class OpcoesFuncionario {
    private static Scanner scanner = new Scanner(System.in);

    public static void cadastrarFuncionario(List<Funcionario> lstFuncionario) {
        Funcionario funcionarioLogin = new Funcionario();
        if (funcionarioLogin.login(lstFuncionario, scanner)) {
            System.out.println("ACESSO PERMITIDO\n");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            limpar();

            while (true) {
                System.out.println("<<<<< CADASTRAR FUNCIONARIOS >>>>>\n");
                System.out.print("Identificacao (0 para encerrar): ");
                int idFuncionario = scanner.nextInt();
                scanner.nextLine();
                if (idFuncionario == 0) {
                    pause();
                    return;
                }

                if (acharFuncionario(lstFuncionario, idFuncionario) != -1) {
                    System.out.println("Identificação já cadastrada!\n");
                    pause();
                    continue;
                }

                System.out.print("\nNome do Funcionario.........: ");
                String nome = scanner.nextLine();

                char sexo;
                do {
                    System.out.print("Sexo do Funcionario (M/F)....: ");
                    sexo = Character.toUpperCase(scanner.next().charAt(0));
                } while (sexo != 'M' && sexo != 'F');

                System.out.print("Data de nascimento do Funcionario (dd/mm/YY).......: ");
                String[] dataNasc = scanner.nextLine().split("/");


                System.out.print("CPF do Funcionario.......: ");
                String cpf = scanner.next();
                System.out.print("Senha do Funcionario.......: ");
                String senha = scanner.next();

                if (confirmou("Confirma o cadastro? (S/*): ", scanner)) {
                    Funcionario funcionario = new Funcionario(idFuncionario, nome, sexo, dataNasc, cpf, senha, 'X');
                    lstFuncionario.add(funcionario);
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

    public static void excluirFuncionario(List<Funcionario> lstFuncionario) {
        System.out.println("<<<<< EXCLUIR FUNCIONARIOS >>>>>\n");
        System.out.print("Identificacao do Funcionario (0 para encerrar): ");
        int idFuncionario = scanner.nextInt();
        if (idFuncionario == 0) {
            pause();
            return;
        }

        int posicao = acharFuncionario(lstFuncionario, idFuncionario);
        if (posicao == -1) {
            System.out.println("Funcionario não encontrado!\n");
        } else {
            System.out.printf("Nome: %s\n", lstFuncionario.get(posicao).getNome());
            if (confirmou("Confirma a exclusão? (S/*): ", scanner)) {
                Funcionario funcionario = lstFuncionario.get(posicao);
                funcionario.setSit(' ');
                System.out.println("<<<< Confirmado >>>>\n\n\n");
            } else {
                System.out.println("<<<< Não Confirmado >>>>\n\n\n\n");
            }
        }
        pause();
    }

    public static void listarFuncionarios(List<Funcionario> lstFuncionario) {
        System.out.println("<<<<< LISTAR FUNCIONARIOS >>>>>\n");
        for (Funcionario funcionario : lstFuncionario) {
            System.out.printf("Identificacao: %d\n", funcionario.getId());
            System.out.printf("Nome: %s\n", funcionario.getNome());
            System.out.printf("Sexo: %c\n", funcionario.getSexo());
            System.out.printf("Idade: %d\n", funcionario.getIdade());
            System.out.printf("CPF: %s\n", funcionario.getCpf());
            System.out.println();
        }
        pause();
    }

    public static void importarFuncionarios(List<Funcionario> funcionarios) {
        try (BufferedReader br = new BufferedReader(new FileReader("funcionarios.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length < 7) {
                    System.out.println("Linha inválida no arquivo: " + line);
                    continue;
                }
                int id = parts[0].isEmpty() ? 0 : Integer.parseInt(parts[0]);
                String nome = parts[1];
                char sexo = parts[2].charAt(0);
                String[] dataNasc = parts[3].split("/");
                String cpf = parts[4].isEmpty() ? "" : parts[4];
                String senha = parts[5];
                char situacao = parts[6].charAt(0);
                funcionarios.add(new Funcionario(id, nome, sexo, dataNasc, cpf, senha, situacao));
            }
        } catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo usuarios.txt");
        }
    }

    public static void exportarFuncionarios(List<Funcionario> funcionarios) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("funcionarios.txt"))) {
            for (Funcionario funcionario : funcionarios) {
                pw.printf("%d;%s;%c;%s;%s;%s;%c\n", funcionario.getId(), funcionario.getNome(), funcionario.getSexo(),
                        funcionario.getDataNasc(), funcionario.getCpf(), funcionario.getSenhaFuncionario(), funcionario.getSit());
            }
        } catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo usuarios.txt");
        }
    }

    private static int acharFuncionario(List<Funcionario> lstFuncionario, int idFuncionario) {
        for (int i = 0; i < lstFuncionario.size(); i++) {
            if (lstFuncionario.get(i).getId() == idFuncionario) {
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
