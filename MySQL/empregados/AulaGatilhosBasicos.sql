USE empregados_db;
SELECT * FROM departamento;
SELECT * FROM empregado;
SELECT * FROM dependentes;
SELECT * FROM log_salario;
SELECT * FROM log_depto;
/*
CREATE TABLE `empregados_db`.`log_salario` (
  `cod_log_salario` INT NOT NULL AUTO_INCREMENT,
  `cod_empregado` INT NOT NULL,
  `cod_depto` INT NOT NULL,
  `dt_alteracao` DATETIME NOT NULL,
  `valor_antigo` DECIMAL(10,2) NOT NULL,
  `valor_novo` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`cod_log_salario`));
  */

DELIMITER //
CREATE TRIGGER tg_log_salario_update BEFORE UPDATE ON empregado
FOR EACH ROW 
BEGIN
	IF(NEW.salario <> OLD.salario) THEN
		INSERT log_salario(cod_empregado, cod_depto, dt_alteracao, valor_antigo, valor_novo)
        VALUES(NEW.cod_empregado, NEW.cod_depto, NOW(), OLD.salario, NEW.salario);
    END IF;
END //
DELIMITER ;

START TRANSACTION;
UPDATE empregado set salario=2584.30 WHERE cod_empregado=1;
UPDATE empregado set salario=4872.00 WHERE cod_empregado=14;
UPDATE empregado set salario=3830.25 WHERE cod_empregado=10;
UPDATE empregado set salario=8053.50 WHERE cod_empregado=5;
ROLLBACK;
COMMIT;

START TRANSACTION;
UPDATE empregado set salario=2560.30 WHERE cod_empregado=1;
ROLLBACK;
COMMIT;
/* Tabela de Log_Depto*/
START TRANSACTION;
CREATE TABLE `empregados_db`.`log_depto` (
  `cod_log_depto` INT NOT NULL AUTO_INCREMENT,
  `cod_empregado` INT NOT NULL,
  `emp_troca` INT NOT NULL,
  `dt_troca_depto` DATETIME NOT NULL,
  `depto_antigo` INT NOT NULL,
  `depto_novo` INT NOT NULL,
  `correcao_log` BIT(1) NOT NULL,
  PRIMARY KEY (`cod_log_depto`));
  ROLLBACK;
  COMMIT;
  /* Gatilho de empregado troca depto */
DELIMITER //
CREATE TRIGGER tg_log_depto_update BEFORE UPDATE ON empregado
FOR EACH ROW 
BEGIN
	IF(NEW.cod_depto <> OLD.cod_depto) THEN
		INSERT log_depto(cod_empregado, emp_troca, dt_troca_depto, depto_antigo, depto_novo, correcao_log)
        VALUES(NEW.cod_empregado, 0, NOW(), OLD.cod_depto, NEW.cod_depto, 0);
    END IF;
END //
DELIMITER ;
/* Trasação de Updates */
START TRANSACTION;
UPDATE empregado set cod_depto=2 WHERE cod_empregado=1;
UPDATE empregado set cod_depto=2 WHERE cod_empregado=3;
UPDATE empregado set cod_depto=4 WHERE cod_empregado=10;
UPDATE empregado set cod_depto=3 WHERE cod_empregado=11;
UPDATE empregado set cod_depto=3 WHERE cod_empregado=13;
UPDATE empregado set cod_depto=3 WHERE cod_empregado=14;
ROLLBACK;
COMMIT;
/* Exercício Gatilhos */
/*
1) Criar uma tabela para gerenciar as trocas de departamento. 
(cod_log_depto AI, cod_empregado, emp_troca, dt_troca_depto, depto_antigo, depto_novo, correcao_log BIT)
CREATE TABLE `empregados_db`.`log_deptp` (
  `cod_log_depto` INT NOT NULL AUTO_INCREMENT,
  `cod_empregado` INT NOT NULL,
  `emp_troca` INT NOT NULL,
  `dt_troca_depto` DATETIME NOT NULL,
  `depto_antigo` INT NOT NULL,
  `depto_novo` INT NOT NULL,
  `correcao_log` BIT(1) NOT NULL,
  PRIMARY KEY (`cod_log_depto`));
2) Criar gatilho ver se o departamento é diferente, caso sim insert na tabela log_depto.
DELIMITER //
CREATE TRIGGER tg_log_depto_update BEFORE UPDATE ON empregado
FOR EACH ROW 
BEGIN
	IF(NEW.cod_depto <> OLD.cod_depto) THEN
		INSERT log_depto(cod_empregado, emp_troca, dt_troca_depto, depto_antigo, depto_novo, correcao_log)
        VALUES(NEW.cod_empregado, 0, NOW(), OLD.cod_depto, NEW.cod_depto, 0);
    END IF;
END //
DELIMITER ;
3) Transação trocando os depatamentos dos empregados 
1. Marcos Aparecido 1 -> 2
3. Antônio Silva 1 -> 2
10. Felipe Januário 1 -> 4
11. Alexandra Pereira 1 -> 3
13. Maria Eduarda Di Senna 1 -> 3
14. Marcela Marcondes 1 -> 3

START TRANSACTION;
UPDATE empregado set cod_depto=2 WHERE cod_empregado=1;
UPDATE empregado set cod_depto=2 WHERE cod_empregado=3;
UPDATE empregado set cod_depto=4 WHERE cod_empregado=10;
UPDATE empregado set cod_depto=3 WHERE cod_empregado=11;
UPDATE empregado set cod_depto=3 WHERE cod_empregado=13;
UPDATE empregado set cod_depto=3 WHERE cod_empregado=14;
ROLLBACK;
COMMIT;
*/