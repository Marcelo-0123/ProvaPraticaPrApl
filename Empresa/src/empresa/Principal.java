package empresa; // Declaração do pacote onde a classe está localizada

import java.sql.Connection; // Importa a classe Connection do pacote java.sql
import java.sql.PreparedStatement; // Importa a classe PreparedStatement do pacote java.sql
import java.sql.ResultSet; // Importa a classe ResultSet do pacote java.sql
import java.sql.SQLException; // Importa a classe SQLException do pacote java.sql
import java.sql.Statement; // Importa a classe Statement do pacote java.sql
import java.util.Scanner; // Importa a classe Scanner do pacote java.util
import util.Conexao; // Importa a classe Conexao do pacote util

/**
 * Classe principal que gerencia as operações CRUD para Pessoa, Funcionario e Projeto.
 */
public class Principal {
    private static Scanner scanner = new Scanner(System.in); // Scanner para entrada de dados do usuário

    /**
     * Método principal que inicia a aplicação.
     * @param args - argumentos da linha de comando
     */
    public static void main(String[] args) {
        try (Connection con = Conexao.conectar()) { // Estabelece a conexão com o banco de dados
            System.out.println("Conexão bem-sucedida!"); // Mensagem de sucesso
            criarTabelas(con); // Cria as tabelas no banco de dados
            menu(con); // Exibe o menu para operações CRUD
        } catch (ClassNotFoundException | SQLException e) { // Captura exceções de classe não encontrada e SQL
            System.err.println("Erro: " + e.getMessage()); // Mensagem de erro
        }
    }

    //Método para criar tabelas no banco de dados.
    private static void criarTabelas(Connection con) throws SQLException {
        Statement stmt = con.createStatement(); // Cria um Statement para executar comandos SQL
        // Criação da tabela pessoa
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS pessoa (id INT PRIMARY KEY AUTO_INCREMENT, nome VARCHAR(100), email VARCHAR(100))");
        // Criação da tabela funcionario
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS funcionario (id INT PRIMARY KEY, matricula VARCHAR(20), departamento VARCHAR(50), FOREIGN KEY (id) REFERENCES pessoa(id))");
        // Criação da tabela projeto
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS projeto (id INT PRIMARY KEY AUTO_INCREMENT, nome VARCHAR(100), descricao TEXT, id_funcionario INT, FOREIGN KEY (id_funcionario) REFERENCES funcionario(id))");
        System.out.println("Tabelas criadas com sucesso!"); // Mensagem de sucesso
    }

    // Método para exibir o menu e realizar operações CRUD.
    
    private static void menu(Connection con) throws SQLException {
        int opcao; // Variável para armazenar a opção escolhida pelo usuário
        do {
            // Exibe o menu de opções
            System.out.println("\nMenu:");
            System.out.println("1. Gerenciar Pessoas");
            System.out.println("2. Gerenciar Funcionários");
            System.out.println("3. Gerenciar Projetos");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt(); // Lê a opção escolhida
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1: gerenciar(con, "pessoa"); break; // Chama o método para gerenciar pessoas
                case 2: gerenciar(con, "funcionario"); break; // Chama o método para gerenciar funcionários
                case 3: gerenciar(con, "projeto"); break; // Chama o método para gerenciar projetos
                case 4: System.out.println("Saindo..."); break; // Mensagem de saída
                default: System.out.println("Opção inválida! Tente novamente."); // Mensagem de erro
            }
        } while (opcao != 4); // Continua até que o usuário escolha sair
    }

    // Método para gerenciar operações CRUD para Pessoas, Funcionários ou Projetos.
    private static void gerenciar(Connection con, String tipo) throws SQLException {
        int opcao; // Variável para armazenar a opção escolhida pelo usuário
        do {
            // Exibe o menu de gerenciamento
            System.out.println("\nGerenciar " + tipo + ":");
            System.out.println("1. Adicionar " + tipo);
            System.out.println("2. Listar " + tipo + "s");
            System.out.println("3. Atualizar " + tipo);
            System.out.println("4. Deletar " + tipo);
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt(); // Lê a opção escolhida
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1: adicionar(con, tipo); break; // Chama o método para adicionar
                case 2: listar(con, tipo); break; // Chama o método para listar
                case 3: atualizar(con, tipo); break; // Chama o método para atualizar
                case 4: deletar(con, tipo); break; // Chama o método para deletar
                case 5: System.out.println("Voltando..."); break; // Mensagem de retorno
                default: System.out.println("Opção inválida! Tente novamente."); // Mensagem de erro
            }
        } while (opcao != 5); // Continua até que o usuário escolha voltar
    }

    // Método para adicionar uma nova entidade (Pessoa, Funcionário ou Projeto).
    
    private static void adicionar(Connection con, String tipo) throws SQLException {
        String sql = ""; // Inicializa a string SQL
        if (tipo.equals("pessoa")) { // Se o tipo for pessoa
            System.out.print("Nome: "); // Solicita o nome da pessoa
            String nome = scanner.nextLine(); // Lê o nome
            System.out.print("Email: "); // Solicita o email da pessoa
            String email = scanner.nextLine(); // Lê o email
            sql = "INSERT INTO pessoa (nome, email) VALUES (?, ?)"; // Comando SQL para inserir uma nova pessoa
            try (PreparedStatement stmt = con.prepareStatement(sql)) { // Prepara o comando SQL
                stmt.setString(1, nome); // Define o nome
                stmt.setString(2, email); // Define o email
                stmt.executeUpdate(); // Executa o comando SQL
                System.out.println("Pessoa adicionada com sucesso!"); // Mensagem de sucesso
            }
        } else if (tipo.equals("funcionario")) { // Se o tipo for funcionário
            System.out.print("ID da Pessoa: "); // Solicita o ID da pessoa
            int id = scanner.nextInt(); // Lê o ID
            scanner.nextLine(); // Limpa o buffer
            System.out.print("Matrícula: "); // Solicita a matrícula do funcionário
            String matricula = scanner.nextLine(); // Lê a matrícula
            System.out.print("Departamento: "); // Solicita o departamento do funcionário
            String departamento = scanner.nextLine(); // Lê o departamento
            sql = "INSERT INTO funcionario (id, matricula, departamento) VALUES (?, ?, ?)"; // Comando SQL para inserir um novo funcionário
            try (PreparedStatement stmt = con.prepareStatement(sql)) { // Prepara o comando SQL
                stmt.setInt(1, id); // Define o ID da pessoa
                stmt.setString(2, matricula); // Define a matrícula
                stmt.setString(3, departamento); // Define o departamento
                stmt.executeUpdate(); // Executa o comando SQL
                System.out.println("Funcionário adicionado com sucesso!"); // Mensagem de sucesso
            }
        } else if (tipo.equals("projeto")) { // Se o tipo for projeto
            System.out.print("Nome do Projeto: "); // Solicita o nome do projeto
            String nome = scanner.nextLine(); // Lê o nome do projeto
            System.out.print("Descrição do Projeto: "); // Solicita a descrição do projeto
            String descricao = scanner.nextLine(); // Lê a descrição do projeto
            System.out.print("ID do Funcionário Responsável: "); // Solicita o ID do funcionário responsável
            int idFuncionario = scanner.nextInt(); // Lê o ID do funcionário responsável
            sql = "INSERT INTO projeto (nome, descricao, id_funcionario) VALUES (?, ?, ?)"; // Comando SQL para inserir um novo projeto
            try (PreparedStatement stmt = con.prepareStatement(sql)) { // Prepara o comando SQL
                stmt.setString(1, nome); // Define o nome do projeto
                stmt.setString(2, descricao); // Define a descrição do projeto
                stmt.setInt(3, idFuncionario); // Define o ID do funcionário responsável
                stmt.executeUpdate(); // Executa o comando SQL
                System.out.println("Projeto adicionado com sucesso!"); // Mensagem de sucesso
            }
        }
    }

    //Método para listar todas as entidades (Pessoa, Funcionário ou Projeto).
   
    private static void listar(Connection con, String tipo) throws SQLException {
        String sql = "SELECT * FROM " + tipo; // Comando SQL para selecionar todas as entidades do tipo especificado
        try (PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) { // Executa o comando SQL e obtém os resultados
            System.out.println("\nLista de " + tipo + "s:"); // Mensagem de cabeçalho
            while (rs.next()) { // Itera sobre os resultados
                if (tipo.equals("pessoa")) { // Se o tipo for pessoa
                    // Exibe os dados de cada pessoa
                    System.out.println("ID: " + rs.getInt("id") + ", Nome: " + rs.getString("nome") + ", Email: " + rs.getString("email"));
                } else if (tipo.equals("funcionario")) { // Se o tipo for funcionário
                    // Exibe os dados de cada funcionário
                    System.out.println("ID: " + rs.getInt("id") + ", Matrícula: " + rs.getString("matricula") + ", Departamento: " + rs.getString("departamento"));
                } else if (tipo.equals("projeto")) { // Se o tipo for projeto
                    // Exibe os dados de cada projeto
                    System.out.println("ID: " + rs.getInt("id") + ", Nome: " + rs.getString("nome") + ", Descrição: " + rs.getString("descricao"));
                }
            }
        }
    }

    // Método para atualizar uma entidade (Pessoa, Funcionário ou Projeto).
    
    private static void atualizar(Connection con, String tipo) throws SQLException {
        System.out.print("ID do " + tipo + " a ser atualizado: "); // Solicita o ID da entidade
        int id = scanner.nextInt(); // Lê o ID
        scanner.nextLine(); // Limpa o buffer
        String sql = ""; // Inicializa a string SQL
        if (tipo.equals("pessoa")) { // Se o tipo for pessoa
            System.out.print("Novo Nome: "); // Solicita o novo nome
            String nome = scanner.nextLine(); // Lê o novo nome
            System.out.print("Novo Email: "); // Solicita o novo email
            String email = scanner.nextLine(); // Lê o novo email
            sql = "UPDATE pessoa SET nome = ?, email = ? WHERE id = ?"; // Comando SQL para atualizar a pessoa
            try (PreparedStatement stmt = con.prepareStatement(sql)) { // Prepara o comando SQL
                stmt.setString(1, nome); // Define o novo nome
                stmt.setString(2, email); // Define o novo email
                stmt.setInt(3, id); // Define o ID da pessoa a ser atualizada
                stmt.executeUpdate(); // Executa o comando SQL
                System.out.println("Pessoa atualizada com sucesso!"); // Mensagem de sucesso
            }
        } else if (tipo.equals("funcionario")) { // Se o tipo for funcionário
            System.out.print("Nova Matrícula: "); // Solicita a nova matrícula
            String matricula = scanner.nextLine(); // Lê a nova matrícula
            System.out.print("Novo Departamento: "); // Solicita o novo departamento
            String departamento = scanner.nextLine(); // Lê o novo departamento
            sql = "UPDATE funcionario SET matricula = ?, departamento = ? WHERE id = ?"; // Comando SQL para atualizar o funcionário
            try (PreparedStatement stmt = con.prepareStatement(sql)) { // Prepara o comando SQL
                stmt.setString(1, matricula); // Define a nova matrícula
                stmt.setString(2, departamento); // Define o novo departamento
                stmt.setInt(3, id); // Define o ID do funcionário a ser atualizado
                stmt.executeUpdate(); // Executa o comando SQL
                System.out.println("Funcionário atualizado com sucesso!"); // Mensagem de sucesso
            }
        } else if (tipo.equals("projeto")) { // Se o tipo for projeto
            System.out.print("Novo Nome: "); // Solicita o novo nome
            String nome = scanner.nextLine(); // Lê o novo nome
            System.out.print("Nova Descrição: "); // Solicita a nova descrição
            String descricao = scanner.nextLine(); // Lê a nova descrição
            sql = "UPDATE projeto SET nome = ?, descricao = ? WHERE id = ?"; // Comando SQL para atualizar o projeto
            try (PreparedStatement stmt = con.prepareStatement(sql)) { // Prepara o comando SQL
                stmt.setString(1, nome); // Define o novo nome
                stmt.setString(2, descricao); // Define a nova descrição
                stmt.setInt(3, id); // Define o ID do projeto a ser atualizado
                stmt.executeUpdate(); // Executa o comando SQL
                System.out.println("Projeto atualizado com sucesso!"); // Mensagem de sucesso
            }
        }
    }

    // Método para deletar uma entidade (Pessoa, Funcionário ou Projeto).
   
    private static void deletar(Connection con, String tipo) throws SQLException {
        System.out.print("ID do " + tipo + " a ser deletado: "); // Solicita o ID da entidade
        int id = scanner.nextInt(); // Lê o ID
        String sql = "DELETE FROM " + tipo + " WHERE id = ?"; // Comando SQL para deletar a entidade
        try (PreparedStatement stmt = con.prepareStatement(sql)) { // Prepara o comando SQL
            stmt.setInt(1, id); // Define o ID da entidade a ser deletada
            stmt.executeUpdate(); // Executa o comando SQL
            System.out.println(tipo.substring(0, 1).toUpperCase() + tipo.substring(1) + " deletado com sucesso!"); // Mensagem de sucesso
        }
    }
}
