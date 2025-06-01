package br.com.bpkedu.library_spring_webmvc.service;

import br.com.bpkedu.library_spring_webmvc.domain.Emprestimo;
import br.com.bpkedu.library_spring_webmvc.domain.EmprestimoItem;
import br.com.bpkedu.library_spring_webmvc.domain.Livro;
import br.com.bpkedu.library_spring_webmvc.domain.Usuario;
import br.com.bpkedu.library_spring_webmvc.repository.EmprestimoItemRepository;
import br.com.bpkedu.library_spring_webmvc.repository.EmprestimoRepository;
import br.com.bpkedu.library_spring_webmvc.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private EmprestimoItemRepository emprestimoItemRepository;

    public Emprestimo realizarEmprestimo(Long usuarioId, Long livroId) {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setUsuario(new Usuario(usuarioId));
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucaoPrevista(LocalDate.now().plusDays(7));

        List<EmprestimoItem> itens = new ArrayList<>();

        for (livroId : livroId;) {
            Livro livro = livroRepository.findById(livroId)
                    .orElseThrow(() -> new RuntimeException("Livro não encontrado: " + livroId));

            boolean emprestado = emprestimoItemRepository.existsByLivroIdAndDevolvidoFalse(livroId);
            if (emprestado) {
                throw new RuntimeException("Livro já emprestado: " + livro.getTitulo());
            }

            EmprestimoItem item = new EmprestimoItem();
            item.setLivro(livro);
            item.setEmprestimo(emprestimo);
            item.setDevolvido(false);

            itens.add(item);
        }

        emprestimo.setItens(itens);

        return emprestimoRepository.save(emprestimo);
    }

    public Emprestimo registrarDevolucao(Long emprestimoId) {
        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));

        emprestimo.setDevolvido(true);
        emprestimo.setDataDevolucaoReal(LocalDate.now());

        emprestimo.getItens().forEach(item -> {
            item.setDevolvido(true);
        });

        return emprestimoRepository.save(emprestimo);
    }

    public List<Emprestimo> listarTodos() {
        return emprestimoRepository.findAll();
    }
}