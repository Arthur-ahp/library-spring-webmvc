package br.com.bpkedu.library_spring_webmvc.controller;

import br.com.bpkedu.library_spring_webmvc.domain.Emprestimo;
import br.com.bpkedu.library_spring_webmvc.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @GetMapping("/listar")
    public String listar(Model model) {
        try {
            List<Emprestimo> lista = emprestimoService.listarTodos();
            model.addAttribute("emprestimos", lista);
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao carregar empréstimos.");
            e.printStackTrace(); // Para debugar
        }
        return "emprestimos/listar";
    }


    @PostMapping("/realizar")
    public String realizarEmprestimo(@RequestParam Long usuarioId, @RequestParam Long livroId) {
        emprestimoService.realizarEmprestimo(usuarioId, livroId);
        return "redirect:/emprestimos/listar";
    }

    @PostMapping("/devolver")
    public String registrarDevolucao(@RequestParam Long emprestimoId) {
        emprestimoService.registrarDevolucao(emprestimoId);
        return "redirect:/emprestimos/listar";
    }
}