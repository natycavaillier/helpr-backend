USE empregados_db;

SELECT * FROM empregado emp, dependentes dep WHERE emp.cod_empregado = dep.cod_empregado;
UPDATE dependentes SET dt_nascimento="2004-05-25" WHERE cod_dependente=13;

SELECT emp.nome,
IFNULL(dep.nome,"SEM DEPENDENTES") AS dependentes,
IF(dep.dt_nascimento < "2005-01-01","Quase maior","Dep Menor") AS estatus
FROM  dependentes dep 
RIGHT JOIN empregado emp ON emp.cod_empregado=dep.cod_empregado;

/*Exercício JOINs*/
/*Monte um filtro que liste todos os empregados da empresa,
neste filtro deverá ter uma coluna onde mostre o numero de dependentes, os
empregados sem dependetes deve aparecer 0 e em um próxima coluna
deve aparecer o IRPF onde os empregdos com dependentes tem mensagem Com Abatimento e os
sem Sem Abatimento.
*/
SELECT emp.nome AS empregado,
IFNULL(COUNT(dep.cod_dependente),0) AS num_dependente,
IF(COUNT(dep.cod_dependente)<=0,"Integral","Abatimento") AS IRPF
FROM empregado emp
LEFT JOIN dependentes dep ON emp.cod_empregado = dep.cod_empregado
GROUP BY emp.nome;


