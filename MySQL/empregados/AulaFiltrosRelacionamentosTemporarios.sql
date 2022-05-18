USE empregados_db;
/*Instancias de Relacionamento*/
SELECT dpto.nome AS departamento, dpto.cod_depto AS cod_dpto, emp.nome AS empregado, emp.cod_depto AS cod_dpto_emp 
FROM departamento dpto, empregado emp WHERE dpto.cod_depto=emp.cod_depto;

/* Utilização de Agrupamentos de Referências */
SELECT emp.nome AS empregado, COUNT(dep.cod_dependente) AS num_dependentes 
FROM empregado emp, dependentes dep WHERE emp.cod_empregado=dep.cod_empregado
GROUP BY emp.nome;

/* Criando e Atualizando Vizualizações */
CREATE OR REPLACE VIEW vw_num_dependetes AS
SELECT emp.nome AS empregado, COUNT(dep.cod_dependente) AS num_dependentes 
FROM empregado emp, dependentes dep WHERE emp.cod_empregado=dep.cod_empregado
GROUP BY emp.nome;

/* Filtros em Vizualizações de Dados Pré Montados */
SELECT * FROM vw_num_dependetes WHERE empregado LIKE "%Bonadio";

SELECT * FROM dependentes;

/* Exercícios de Relacionamento e View */
/* 1) Monte um filtro determine quantos empregados há em cada departamento.*/
/* 2) Filtre com ajustes que fique somente o nome do dpto e seu numero de funcionários.*/
SELECT dpto.nome AS departamento, COUNT(emp.cod_empregado) AS num_empregados FROM empregado emp, departamento dpto 
WHERE dpto.cod_depto=emp.cod_depto GROUP BY dpto.nome;
/* 3) Feito isso salve dentro de uma view dinâmica e adicione um funcionário com os dados a seguir e mostre se a view mudou.*/
CREATE OR REPLACE VIEW vw_num_empregados AS
SELECT dpto.nome AS departamento, COUNT(emp.cod_empregado) AS num_empregados FROM empregado emp, departamento dpto 
WHERE dpto.cod_depto=emp.cod_depto GROUP BY dpto.nome;

/* values(7, "Hélio Takawa", "1989-03-04", 'T', "2022-18-05", 'U', 3560.00)*/
INSERT INTO empregado(cod_depto, nome, dt_nascimento, sexo, dt_admissao, est_civil, salario)
values(7, "Hélio Takawa", "1989-03-04", 'T', "2022-05-18", 'U', 3560.00);

SELECT * FROM vw_num_empregados;

INSERT INTO dependentes(cod_empregado, nome, dt_nascimento, sexo)
values
(15, "Marcela Takawa", "2020-02-03", 'F'),
(15, "Henrique Takawa", "2018-06-17", 'M');

SELECT * FROM vw_num_dependetes WHERE empregado LIKE "%Helio%";

START TRANSACTION;
DELETE FROM dependentes WHERE cod_dependente=8;
ROLLBACK;
COMMIT;






