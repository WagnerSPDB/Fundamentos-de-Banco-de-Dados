# FundamentosBancoDeDados
Projeto realizado na disciplina de Fundamentos de Banco de Dados, pela Universidade Federal do Ceará, em 2023.1

Descrição:
Parte 1 -
Crie a classe StudentManager contendo os métodos a seguir:
addStudent(Student student);
List<Student> getStudents();
updateStudent(Student student);
deleteStudent(Student student);
Implemente um programa que apresente ofereça uma interface ao usuário (pode ser no próprio console) com as funcionalidades: 1) adicionar estudante, 2) listar estudantes, 3) atualizar estudante e 4) remover estudante.

Parte 2 -
Crie um sistema de controle acadêmico para gerenciar alunos, disciplinas e histórico de alunos;
O Estudante deve ter como campos: matrícula, nome, email, telefone, data de nascimento (Date), sexo (boolean);
A Disciplina deve ter como campos: código, nome e quantidade de créditos;
Para cada vez que o aluno cursar uma disciplina deve-se armazenar o período, a nota e a frequência;
aluno(matricula, nome, email, telefone, data_nasc, sexo);
disciplina(codigo, nome, creditos);
aluno_disciplina(aluno_matr, disciplina_cod, periodo, nota, frequencia);

A aplicação deve fornecer interface gráfica ou de console que permita ao usuário executar as seguintes funções:
Adicionar estudante: informar os dados e adicionar ao banco (matrícula deve ser gerada automaticamente);
Atualizar estudante: informar apenas a matrícula e em seguida informar os demais dados a serem atualizados;
Remover estudante: informar apenas a matrícula do estudante  a ser removido;
Listar estudantes: exibir todos os dados cadastrais de todos os estudantes;
Buscar estudantes: informar uma string qualquer e o sistema deverá listar os estudante cuja a string informada seja parte do seu nome ou email (case INsensitive, não considerar se é maiúscula ou minúscula);
Listar, adicionar, atualizar e remover disciplina (similar às funcionalidades de estudante);
Adicionar resultado do estudante: informar a matrícula do aluno, o código da disciplina, o período (ex: 2021.1), a nota (de 0 a 10) e a frequência (de 0 a 100)
Exibir histórico do estudante: informar a matrícula do estudante e exibir: o nome das disciplinas que ele cursou com o respectivo período, nota, e frequência (exibir como porcentagem);
Exibir turma: informar o código da disciplina e o período e exibir: a matrícula, o nome, a nota e a freqûencia dos alunos daquela disciplina e período informados
Importante: 
Os dados das consultas 8 e 9 devem ser acessados através de uma única consulta;
Popule o banco de dados de maneira a facilitar os testes das funcionalidades.

Parte 3 - 
Popular o banco de dados.
Adicionar as seguintes funcionalidades:
Alterar a funcionalidade de exibir estudantes adicionando a média do estudante em cada estudante (o cálculo da média deve ser feito na consulta ao banco, e não na aplicação);
Listar turmas: exibir uma linha para cada disciplina e período com o nome da disciplina, o período, a quantidade de estudantes que cursaram a disciplina, a maior nota, a menor nota e a média das notas da turma (a contagem de alunos e cálculos relacionados à nota devem ser feitos na consulta ao banco, e não na aplicação);
Criar duas views:
alunos_info: definida pela consulta da questão 2;
turmas_info: definida pela consulta da questão 3;
Na tabela de aluno, acrescente uma coluna chamada ”distincao” do tipo char(20). Crie uma trigger (ou mais de uma, se julgar necessário) que, para cada inserção aluno_disciplina, atualiza o valor da coluna distincao da tabela aluno segundo as regras abaixo:
Se a média das notas do aluno for igual a 10, distincao deve ser atualizada para “Summa Cum Laude”;
Se a média das notas do aluno for entre 9 e 9.9, distincao deve ser atualizada para “Magna Cum Laude”.

