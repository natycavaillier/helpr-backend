/* Exercício */
/*
Passo 1) VVamos criar um SPS procedure para verificar por meio de segmento de datas 
se o funcionário retornou a seu deparatemento sem autorização por meio de uma correção,
assim devemos mostrar e testar estas movimentações com uso do trigger e do procedure feito 
por vocês. 
Tabela Temporária,
Procedure com entrega,
Ajuste para testes.
*/
USE empregados_db;
SELECT * FROM departamento;
SELECT * FROM empregado;
SELECT * FROM dependentes;
SELECT * FROM log_salario;
SELECT * FROM log_depto;

DELIMITER //

//
DELIMITER ;





