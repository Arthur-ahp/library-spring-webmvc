package br.com.bpkedu.library_spring_webmvc.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    // Remova este atributo, pois os livros vêm via EmprestimoItem
    // private Livro livro;

    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;

    private Boolean devolvido;

    @OneToMany(mappedBy = "emprestimo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmprestimoItem> itens = new ArrayList<>(); // inicialize aqui

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /*
    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
    */

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public LocalDate getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public void setDataDevolucaoReal(LocalDate dataDevolucaoReal) {
        this.dataDevolucaoReal = dataDevolucaoReal;
    }

    public Boolean getDevolvido() {
        return devolvido;
    }

    public void setDevolvido(Boolean devolvido) {
        this.devolvido = devolvido;
    }

    public List<EmprestimoItem> getItens() {
        return itens;
    }

    public void setItens(List<EmprestimoItem> itens) {
        this.itens = itens;
    }
}