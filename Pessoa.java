public class Pessoa {
    private int id;
    private String nome;
    private char sexo;
    private Data dataNasc;
    private int idade;
    private String cpf;

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
        int anoAtual = java.time.Year.now().getValue();
        int aniversario = dataNasc.getAno();
        this.idade = anoAtual - aniversario;
        java.time.LocalDate birthDate = java.time.LocalDate.of(dataNasc.getAno(), dataNasc.getMes(), dataNasc.getDia());
        if (java.time.LocalDate.now().isBefore(birthDate.plusYears(this.idade))) {
            this.idade--;
        }
        return idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public Data getDataNasc() {
        return dataNasc;
    }
    public void setDataNasc(Data dataNasc) {
        this.dataNasc = dataNasc;
    }
}
