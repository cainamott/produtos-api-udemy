package io.github.mottacaina.produtosapi.controller;

import io.github.mottacaina.produtosapi.model.Produto;
import io.github.mottacaina.produtosapi.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private ProdutoRepository repo;

    public ProdutoController(ProdutoRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Produto salvar(@RequestBody Produto produto){
        System.out.println("Produto recebido" + produto);

        var id = UUID.randomUUID().toString();
        produto.setId(id);

        repo.save(produto);
        return produto;
    }

    @GetMapping("/{id}")
    public Produto obterPorId(@PathVariable("id") String id){

        return repo.findById(id).orElseThrow(null);
    }

    @DeleteMapping("/{id}")
    public void deletarPorId(@PathVariable("id") String id){

        repo.deleteById(id);
    }

    @PutMapping("/{id}")
    public void atualizaProduto(@PathVariable("id") String id, @RequestBody Produto produto){

        produto.setId(id);
        repo.save(produto);

    }

    @GetMapping
    public List<Produto> buscar(@RequestParam("nome") String nome){

       return repo.findByNome(nome);
    }
}