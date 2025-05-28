package empresa; // Declaração do pacote onde a classe está localizada

// Classe que representa um projeto.
 
public class Projeto {
    private int id; // Identificador único do projeto
    private String nome; // Nome do projeto
    private String descricao; // Descrição do projeto
    private int idFuncionario; // ID do funcionário responsável pelo projeto


    // Construtor padrão
    public Projeto() {}


    // Construtor com parâmetros
    public Projeto(int id, String nome, String descricao, int idFuncionario) {
        this.id = id; // Inicializa o ID do projeto
        this.nome = nome; // Inicializa o nome do projeto
        this.descricao = descricao; // Inicializa a descrição do projeto
        this.idFuncionario = idFuncionario; // Inicializa o ID do funcionário responsável
    }


    // Getters e Setters
   
    public int getId() { return id; } // Retorna o ID do projeto
   
    public void setId(int id) { this.id = id; } // Define o ID do projeto
   
    public String getNome() { return nome; } // Retorna o nome do projeto
   
    public void setNome(String nome) { this.nome = nome; } // Define o nome do projeto
   
    public String getDescricao() { return descricao; } // Retorna a descrição do projeto
   
    public void setDescricao(String descricao) { this.descricao = descricao; } // Define a descrição do projeto
   
    public int getIdFuncionario() { return idFuncionario; } // Retorna o ID do funcionário responsável
    
    public void setIdFuncionario(int idFuncionario) { this.idFuncionario = idFuncionario; } // Define o ID do funcionário responsável
}