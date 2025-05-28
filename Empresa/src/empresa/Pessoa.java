package empresa; // Declaração do pacote onde a classe está localizada


// Classe que representa uma pessoa.
 
public class Pessoa {
    private int id; // Identificador único da pessoa
    private String nome; // Nome da pessoa
    private String email; // Email da pessoa
    
    // Construtor padrão
    public Pessoa() {}


    // Construtor com parâmetros
    public Pessoa(int id, String nome, String email) {
        this.id = id; // Inicializa o ID
        this.nome = nome; // Inicializa o nome
        this.email = email; // Inicializa o email
    }

    // Getters e Setters
   
    public int getId() { return id; } // Retorna o ID da pessoa
   
    public void setId(int id) { this.id = id; } // Define o ID da pessoa
    
    public String getNome() { return nome; } // Retorna o nome da pessoa
   
    public void setNome(String nome) { this.nome = nome; } // Define o nome da pessoa
   
    public String getEmail() { return email; } // Retorna o email da pessoa
   
    public void setEmail(String email) { this.email = email; } // Define o email da pessoa
}