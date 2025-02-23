import java.util.List;
import java.util.Scanner;

public class Funcionario extends Pessoa {
    private String senhaFuncionario;
    private char sit;

    public Funcionario() {
    }

    public Funcionario(int id, String nome, char sexo, String[] dataNasc, String cpf, String senhaFuncionario, char sit) {
        setId(id);
        setNome(nome);
        setSexo(sexo);
        setDataNasc(new Data(Integer.parseInt(dataNasc[0]), Integer.parseInt(dataNasc[1]), Integer.parseInt(dataNasc[2])));
        setCpf(cpf);
        this.senhaFuncionario = senhaFuncionario;
        this.sit = sit;
    }
    public String getSenhaFuncionario() {
        return senhaFuncionario;
    }

    public void setSenhaFuncionario(String senhaFuncionario) {
        this.senhaFuncionario = senhaFuncionario;
    }

    public boolean login(List<Funcionario> lstFuncionario, Scanner scanner) {
        System.out.println("********* LOGIN FUNCIONÁRIO *********");
        System.out.println("INSIRA AS CREDENCIAIS PARA ACESSAR O SISTEMA");
        System.out.print("Identificacao: ");
        int id = scanner.nextInt();
        scanner.nextLine();
    
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        int pos = acharFuncionario(lstFuncionario.toArray(new Funcionario[lstFuncionario.size()]), id);
        
        if (pos != -1 && lstFuncionario.get(pos).getSenhaFuncionario().equals(senha)) {
            return true;
        }
    
        return false;
    }
    

    public char getSit() {
        return sit;
    }

    public void setSit(char sit) {
        this.sit = sit;
    }
    
    public static int acharFuncionario(Funcionario[] lstFuncionario, int id) {
        for (int i = 0; i < lstFuncionario.length; i++) {
            if (lstFuncionario[i].getId() == id) {
                return i;
            }
        }
        return -1;
    }
}