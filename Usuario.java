import java.util.Arrays;

public class Usuario {
    private int id;
    private String nome;
    private char sexo;
    private int idade;
    private int cpf;
    private char sit;
    private int[] livrosEmprestados;
    private static final int MAXLIVROS = 5;

    public Usuario(int id, String nome, char sexo, int idade, int cpf, char sit) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        this.cpf = cpf;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
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

    @Override
    public String toString() {
        return String.format("ID: %d\nNome: %s\nSexo: %c\nIdade: %d\nCPF: %d\nSituação: %c\n", id, nome, sexo, idade, cpf, sit);
    }
}