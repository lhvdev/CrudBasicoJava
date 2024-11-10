public class Contato {
    private String nome;
    private String celular;
    private String email;
    private String descricao;

    public Contato(String nome, String celular, String email, String descricao) {
        this.nome = nome;
        this.celular = celular;
        this.email = email;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Contato [Nome=" + nome + ", Celular=" + celular + ", Email=" + email + ", Descrição=" + descricao + "]";
    }
}
