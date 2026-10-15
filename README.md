# StockPrime - Controle de Estoque

Projeto desenvolvido a partir da estrutura utilizada em aula, mantendo Jakarta EE 10, JSF, PrimeFaces 13, Template Manhattan e a configuração do NetBeans para GlassFish 7 (`gfv700ee10`).

## Tema
Sistema de Controle de Estoque.

## Funcionalidades
- Login administrativo com filtro de acesso.
- Dashboard com indicadores de estoque.
- CRUD de Categorias.
- CRUD de Fornecedores.
- CRUD de Produtos, relacionando Categoria e Fornecedor.
- Busca e paginação nas listagens.
- Enums `StatusProduto` e `TipoMovimentacao`.
- Entrada e saída de estoque com validação de saldo.
- Histórico de movimentações.
- Logout com invalidação da sessão.

## Login de demonstração
- E-mail: `admin@estoque.com`
- Senha: `123`

## Servidor
O arquivo `nb-configuration.xml` mantém a configuração do projeto-base para GlassFish 7 / Jakarta EE 10:

`gfv700ee10`

## Banco de dados
O projeto-base fornecido não contém `persistence.xml`, driver PostgreSQL, `EntityManager`, DataSource/JNDI nem configuração JDBC dentro do código-fonte. Ele armazena os dados em memória.

Por isso esta versão mantém o mesmo modelo de persistência da base recebida, centralizado em `EstoqueStore` (`@ApplicationScoped`), sem inventar credenciais ou alterar a configuração externa do ambiente da disciplina.

## Manhattan
O projeto mantém a dependência `org.primefaces.themes:manhattan:7.0.0` e a mesma estrutura de layout usada pelo projeto original. No ambiente da disciplina, deve ser usada a mesma instalação/recursos do Manhattan que já funcionam com o projeto-base.
