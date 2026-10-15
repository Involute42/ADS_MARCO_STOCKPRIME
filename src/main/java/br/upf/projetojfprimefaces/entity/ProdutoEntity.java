package br.upf.projetojfprimefaces.entity;

import br.upf.projetojfprimefaces.enums.StatusProduto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class ProdutoEntity implements Serializable {
    private int id;
    private String codigo;
    private String nome;
    private String descricao;
    private BigDecimal preco = BigDecimal.ZERO;
    private int estoqueAtual;
    private int estoqueMinimo;
    private StatusProduto status = StatusProduto.ATIVO;
    private CategoriaEntity categoria;
    private FornecedorEntity fornecedor;

    public ProdutoEntity() {}

    public ProdutoEntity(int id, String codigo, String nome, String descricao, BigDecimal preco,
                         int estoqueAtual, int estoqueMinimo, StatusProduto status,
                         CategoriaEntity categoria, FornecedorEntity fornecedor) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoqueAtual = estoqueAtual;
        this.estoqueMinimo = estoqueMinimo;
        this.status = status;
        this.categoria = categoria;
        this.fornecedor = fornecedor;
    }

    public ProdutoEntity(ProdutoEntity other) {
        this(other.id, other.codigo, other.nome, other.descricao, other.preco, other.estoqueAtual,
             other.estoqueMinimo, other.status, other.categoria, other.fornecedor);
    }

    public boolean isEstoqueBaixo() { return estoqueAtual <= estoqueMinimo; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    public int getEstoqueAtual() { return estoqueAtual; }
    public void setEstoqueAtual(int estoqueAtual) { this.estoqueAtual = estoqueAtual; }
    public int getEstoqueMinimo() { return estoqueMinimo; }
    public void setEstoqueMinimo(int estoqueMinimo) { this.estoqueMinimo = estoqueMinimo; }
    public StatusProduto getStatus() { return status; }
    public void setStatus(StatusProduto status) { this.status = status; }
    public CategoriaEntity getCategoria() { return categoria; }
    public void setCategoria(CategoriaEntity categoria) { this.categoria = categoria; }
    public FornecedorEntity getFornecedor() { return fornecedor; }
    public void setFornecedor(FornecedorEntity fornecedor) { this.fornecedor = fornecedor; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProdutoEntity)) return false;
        ProdutoEntity that = (ProdutoEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
