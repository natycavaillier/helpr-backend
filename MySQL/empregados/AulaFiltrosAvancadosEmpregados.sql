USE empregados_db;

/*Concatenador de regras*/
SELECT * FROM empregado WHERE est_civil='C' OR est_civil='U' OR est_civil='D'; 

/* Filtros por meio de faixas*/
SELECT * FROM empregado WHERE cod_depto BETWEEN 3 and 5;

/* Filtros por proximidade de string */
SELECT * FROM empregado WHERE nome LIKE "An%";
SELECT * FROM empregado WHERE nome LIKE "%o";
SELECT * FROM empregado WHERE nome LIKE "%Julia%";

/* Amostra no espaço de um campo */
SELECT * FROM empregado WHERE dt_nascimento IN("1994-10-19","1990-01-25");
SELECT * FROM empregado WHERE dt_nascimento IN("1994-10-19","1990-01-25") AND dt_admissao LIKE "2009%";
SELECT * FROM empregado WHERE dt_nascimento > "1990-01-01";
SELECT * FROM empregado WHERE cod_empregado IN(SELECT cod_empregado FROM empregado WHERE dt_nascimento LIKE "198%");

/* Alterando Registros do Banco */
UPDATE empregado SET nome = "Maria Eduarda Di Senna" WHERE cod_empregado = 13;
UPDATE empregado SET nome = "Marcela Marcondes" WHERE cod_empregado = 14;
select * from empregado;

/* Dados Voláteis e os Dados Físicos */
SELECT nome, FLOOR(DATEDIFF(NOW(), dt_nascimento) / 365) AS idade FROM empregado;

/* Exercícos de Filtros e Cálculos*/
/* 1.Alterar a idade da Rosemay para  1992-02-15. */
UPDATE empregado SET dt_nascimento = "1992-02-15" WHERE cod_empregado=2;
/* 2.Calcular o tempo de empresa de todos os funcionários. */
SELECT nome, FLOOR(DATEDIFF(NOW(), dt_admissao)/365) AS tempo_depto from empregado;
/* 3.Calcular a idade de todos os dependentes. */
SELECT nome, FLOOR(DATEDIFF(NOW(), dt_nascimento) / 365) AS idade FROM dependentes;
/* Filtrar os dependentes idade igual ou menor que 3 anos. */ 
SELECT nome, FLOOR(DATEDIFF(NOW(), dt_nascimento) / 365) AS idade FROM dependentes WHERE FLOOR(DATEDIFF(NOW(), dt_nascimento) / 365) <=3;
/* 5.Filtar os funcionarios que ganham mais de 1 salários e que nasceram na decada de 90 */
SELECT cod_empregado FROM empregado WHERE dt_nascimento LIKE "199%";
SELECT * FROM empregado WHERE cod_empregado IN(SELECT cod_empregado FROM empregado WHERE dt_nascimento LIKE "199%") and salario>1212;