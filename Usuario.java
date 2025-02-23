import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Usuario extends Pessoa {
    private String senha;
    private char sit;
    private int[] livrosEmprestados;
    private static final int MAXLIVROS = 5;

    public Usuario(int id, String nome, char sexo, String[] dataNasc, String cpf, String senha, char sit) {
        setId(id);
        setNome(nome);
        setSexo(sexo);
        setDataNasc(new Data(Integer.parseInt(dataNasc[0]), Integer.parseInt(dataNasc[1]), Integer.parseInt(dataNasc[2])));
        setCpf(cpf);
        this.senha = senha;
        this.sit = sit;
        this.livrosEmprestados = new int[MAXLIVROS];
        Arrays.fill(this.livrosEmprestados, 0);
    }

    public char getSit() {
        return sit;
    }

    public void setSit(char sit) {
        this.sit = sit;
    }

    public int[] getLivrosEmprestados() {
        return livrosEmprestados;
    }

    public void setLivrosEmprestados(int idLivro) {
        boolean flag = false;
        for (int i = 0; i < MAXLIVROS; i++) {
            if (livrosEmprestados[i] == 0) {
                livrosEmprestados[i] = idLivro;
                flag = true;
                System.out.println("<<<< Confirmado >>>>\n\n\n");
                break;
            } else if (livrosEmprestados[i] == idLivro) {
                System.out.println("Livro já emprestado!");
                break;
            }
        }
        if (!flag) {
            System.out.println("Usuário já atingiu o limite de livros emprestados!\n\n\n");
        }
    }

    public void excluirLivro(int idLivro) {
        for (int i = 0; i < MAXLIVROS; i++) {
            if (livrosEmprestados[i] == idLivro) {
                livrosEmprestados[i] = -1;
                break;
            }
        }
    }

    public static int acharUsuario(Usuario[] lstUsuario, int id) {
        for (int i = 0; i < lstUsuario.length; i++) {
            if (lstUsuario[i].getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean login(List<Usuario> lstUsuario, Scanner scanner) {
        System.out.print("Identificacao: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        int pos = acharUsuario(lstUsuario.toArray(new Usuario[lstUsuario.size()]), id);

        if (pos != -1 && lstUsuario.get(pos).getSenha().equals(senha)) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format("ID: %d\nNome: %s\nSexo: %s\nIdade: %d\nCPF: %s\nSituação: %c\n", getId(), getNome(), getSexo(), getIdade(), getCpf(), sit);
    }
}
