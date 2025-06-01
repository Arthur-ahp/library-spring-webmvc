package br.com.bpkedu.library_spring_webmvc.domain;


import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String senha;
    private String email;
    private String endereco;
    private String telefone;

<<<<<<< HEAD
    public Long getId() {
        return id;
=======
    public Usuario(Long id) {
        this.id = id;
    }

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
>>>>>>> 5ed4c5d (Criação inicial da tela de Empréstimo)
    }

    public Usuario() {
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    @Override
    public String toString() {
        return "Usuario{" +
            "id=" + id +
            ", nome='" + nome + '\'' +
            ", senha='" + senha + '\'' +
            ", email='" + email + '\'' +
            ", endereco='" + endereco + '\'' +
            ", telefone='" + telefone + '\'' +
            '}';
    }
}
