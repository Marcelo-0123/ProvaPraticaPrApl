package empresa; // Declaração do pacote onde a classe está localizada

// Classe que representa um funcionário, que é uma especialização de Pessoa.
 
public class Funcionario extends Pessoa {
    private String matricula; // Matrícula do funcionário
    private String departamento; // Departamento onde o funcionário trabalha


    // Construtor padrão
    public Funcionario() {}


    // Construtor com parâmetros
    public Funcionario(int id, String nome, String email, String matricula, String departamento) {
        super(id, nome, email); // Chama o construtor da classe pai (Pessoa)
        this.matricula = matricula; // Inicializa a matrícula
        this.departamento = departamento; // Inicializa o departamento
   
    }

    // Getters e Setters
  
    public String getMatricula() { return matricula; } // Retorna a matrícula do funcionário
    
    public void setMatricula(String matricula) { this.matricula = matricula; } // Define a matrícula do funcionário
    
    public String getDepartamento() { return departamento; } // Retorna o departamento do funcionário
   
    public void setDepartamento(String departamento) { this.departamento = departamento; } // Define o departamento do funcionário
}