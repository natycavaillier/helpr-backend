USE empregados_db;
/* Seleção em tabela temporária para obter o salário */
CREATE TEMPORARY TABLE tmp_por_dep AS
SELECT dep.cod_empregado, emp.nome AS empregado, emp.salario, dep.nome 
AS dependente, FLOOR(DATEDIFF(NOW(), dep.dt_nascimento)/365) AS idade_dep 
FROM dependentes dep, empregado emp WHERE emp.cod_empregado=dep.cod_empregado;

/* Seleção em tabela temporária para obter o numero dedependentes */
CREATE TEMPORARY TABLE num_dep_emp AS
SELECT dep.cod_empregado, COUNT(dep.cod_dependente) AS num_dep
FROM empregado emp, dependentes dep
WHERE emp.cod_empregado=dep.cod_empregado
GROUP BY dep.cod_empregado;

/* Filtro par obter a média de renda de cada um dos dependentes. */
SELECT empregado, num_dep, salario, dependente, idade_dep, (salario / (num_dep + 1)) AS renda_media 
FROM num_dep_emp ne, tmp_por_dep nd WHERE ne.cod_empregado = nd.cod_empregado;

/*Exclusão e teste a 1a tb temporária*/
SELECT * FROM tmp_por_dep;
drop table tmp_por_dep;

/*Exclusão e teste a 2a tb temporária*/
SELECT * FROM num_dep_emp;
drop table num_dep_emp;
