# Gerenciamento de Livros

Este projeto demonstra uma aplicação Android para gerenciamento de livros, implementada com uma arquitetura em três camadas que separa claramente a apresentação, as regras de negócio e o acesso a dados.

## Descrição

A aplicação permite:
- **Inserção** de novos livros
- **Listagem** completa dos registros com detalhes como título, autor, número de páginas, editora e data de lançamento
- **Atualização** dos dados dos livros cadastrados
- **Exclusão** de registros

A persistência dos dados é realizada utilizando SQLite, e a arquitetura foi organizada para facilitar a manutenção e escalabilidade do projeto.

## Arquitetura do Projeto

O projeto está estruturado em três camadas principais:

- **Camada de Apresentação:**  
  Composta pelas Activities e layouts XML, responsáveis pela interface com o usuário. Exemplo: `MainActivity`, `InsertBookActivity`, `ListBooksActivity` e `UpdateBookActivity`.

- **Camada de Negócio:**  
  Implementada através de classes de serviço, como a `BookService`, que contêm as regras de negócio e validam os dados antes de interagir com a camada de acesso a dados.

- **Camada de Acesso a Dados:**  
  Inclui o `DatabaseHelper` e o `BookDAO`, que encapsulam as operações CRUD (inserção, atualização, exclusão e consulta) no banco de dados SQLite.

## Tecnologias Utilizadas

- **Android Studio Meerkat | 2024.3.1 Patch 1**
- **Java**
- **SQLite**

## Funcionalidades

- **Cadastro de Livros:**  
  Cadastro de livros com atributos: título, autor, número de páginas, editora e data de lançamento.  
  (O identificador do livro é gerado automaticamente e não é exibido ao usuário.)

- **Listagem de Livros:**  
  Visualização completa dos registros (exceto o ID) com todas as informações relevantes de cada livro.

- **Atualização e Exclusão:**  
  Ao selecionar um item na listagem, o usuário pode atualizar os dados ou excluir o registro.

## Considerações Finais

Este projeto demonstra uma abordagem modular e robusta para o desenvolvimento de aplicações Android, utilizando uma arquitetura em três camadas que promove a separação de responsabilidades. Com a implementação das operações CRUD e a utilização do SQLite, o projeto serve como um exemplo prático de como organizar e evoluir um sistema de gerenciamento de dados sem comprometer a integridade dos registros existentes. Essa estrutura facilita a manutenção, o teste e a escalabilidade do aplicativo.

## Requisitos para Execução

- **Sistema Operacional:** Windows 11 (ou compatível)
- **IDE:** Android Studio Meerkat (versão 2024.3.1 Patch 1 ou superior)
- **Dispositivo/Emulador:** Android com versão mínima definida no projeto

## Como Executar

1. Clone este repositório:
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
