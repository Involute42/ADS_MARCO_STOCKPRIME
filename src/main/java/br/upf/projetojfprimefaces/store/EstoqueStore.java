package br.upf.projetojfprimefaces.store;

import br.upf.projetojfprimefaces.entity.CategoriaEntity;
import br.upf.projetojfprimefaces.entity.FornecedorEntity;
import br.upf.projetojfprimefaces.entity.MovimentacaoEntity;
import br.upf.projetojfprimefaces.entity.ProdutoEntity;
import br.upf.projetojfprimefaces.enums.StatusProduto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class EstoqueStore implements Serializable {
    private final List<CategoriaEntity> categorias = new ArrayList<>();
    private final List<FornecedorEntity> fornecedores = new ArrayList<>();
    private final List<ProdutoEntity> produtos = new ArrayList<>();
    private final List<MovimentacaoEntity> movimentacoes = new ArrayList<>();
    private int categoriaSequence = 1;
    private int fornecedorSequence = 1;
    private int produtoSequence = 1;
    private int movimentacaoSequence = 1;

    @PostConstruct
    public void init() {
        CategoriaEntity perifericos = new CategoriaEntity(nextCategoriaId(), "Periféricos", "Acessórios e periféricos de informática", true);
        CategoriaEntity hardware = new CategoriaEntity(nextCategoriaId(), "Hardware", "Componentes internos de computadores", true);
        CategoriaEntity escritorio = new CategoriaEntity(nextCategoriaId(), "Escritório", "Materiais de uso administrativo", true);
        categorias.add(perifericos);
        categorias.add(hardware);
        categorias.add(escritorio);

        FornecedorEntity tech = new FornecedorEntity(nextFornecedorId(), "Tech Supply Ltda.", "12.345.678/0001-90", "vendas@techsupply.com", "(54) 3311-1000", true);
        FornecedorEntity sul = new FornecedorEntity(nextFornecedorId(), "Distribuidora Sul S.A.", "98.765.432/0001-10", "contato@distribuidorasul.com", "(54) 3311-2000", true);
        fornecedores.add(tech);
        fornecedores.add(sul);

        produtos.add(new ProdutoEntity(nextProdutoId(), "PRD-001", "Mouse sem fio", "Mouse óptico USB 2.4 GHz", new BigDecimal("89.90"), 18, 5, StatusProduto.ATIVO, perifericos, tech));
        produtos.add(new ProdutoEntity(nextProdutoId(), "PRD-002", "Teclado mecânico", "Teclado ABNT2 com iluminação", new BigDecimal("249.90"), 4, 5, StatusProduto.ATIVO, perifericos, tech));
        produtos.add(new ProdutoEntity(nextProdutoId(), "PRD-003", "SSD 1 TB", "SSD SATA III 1 TB", new BigDecimal("399.00"), 9, 3, StatusProduto.ATIVO, hardware, sul));
    }

    public List<CategoriaEntity> getCategorias() { return categorias; }
    public List<FornecedorEntity> getFornecedores() { return fornecedores; }
    public List<ProdutoEntity> getProdutos() { return produtos; }
    public List<MovimentacaoEntity> getMovimentacoes() { return movimentacoes; }

    public int nextCategoriaId() { return categoriaSequence++; }
    public int nextFornecedorId() { return fornecedorSequence++; }
    public int nextProdutoId() { return produtoSequence++; }
    public int nextMovimentacaoId() { return movimentacaoSequence++; }

    public CategoriaEntity findCategoria(int id) {
        return categorias.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    public FornecedorEntity findFornecedor(int id) {
        return fornecedores.stream().filter(f -> f.getId() == id).findFirst().orElse(null);
    }

    public ProdutoEntity findProduto(int id) {
        return produtos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public List<ProdutoEntity> getProdutosEstoqueBaixo() {
        List<ProdutoEntity> resultado = new ArrayList<>();
        for (ProdutoEntity p : produtos) {
            if (p.isEstoqueBaixo()) resultado.add(p);
        }
        return Collections.unmodifiableList(resultado);
    }
}
