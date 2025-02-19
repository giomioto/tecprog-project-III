public class Livro {
    private int codigo;
    private String titulo;
    private String autor;
    private int ano;
    private char sit;
    private int idUsuario;

    public Livro(int codigo, String titulo, String autor, int ano, char sit) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.sit = sit;
        this.idUsuario = -1;
    }

    public char getSit() {
        return sit;
    }

    public void setSit(char sit) {
        this.sit = sit;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public static int acharLivro(Livro[] lstLivro, int codigo) {
        for (int i = 0; i < lstLivro.length; i++) {
            if (lstLivro[i].getCodigo() == codigo) {
                return i;
            }
        }
        return -1;
    }

    public int getCodigo() {
        return codigo;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + "\nTítulo: " + titulo + "\nAutor: " + autor + "\nAno: " + ano + "\nSituação: " + sit + "\nID Usuário: " + idUsuario + "\n";
    }
}
