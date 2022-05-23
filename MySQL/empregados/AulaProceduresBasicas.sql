USE empregados_db;
SELECT * FROM departamento;
USE empregados_db;
SELECT * FROM empregado;
SELECT * FROM dependentes;
SELECT * FROM log_salario;
SELECT * FROM log_depto;

ALTER TABLE `empregados_db`.`log_salario` 
ADD COLUMN `correcao_legal` BIT(1) NOT NULL AFTER `valor_novo`,
ADD COLUMN `id_correcao` INT NOT NULL AFTER `correcao_legal`;

SELECT * FROM log_salario WHERE valor_antigo>valor_novo;

UPDATE log_salario SET correcao_legal=1, id_correcao=16 WHERE cod_log_salario=5;
/*
ALTER TABLE `empregados_db`.`log_salario` 
ADD COLUMN `correcao_legal` BIT(1) NOT NULL AFTER `valor_novo`,
ADD COLUMN `id_correcao` INT NOT NULL AFTER `correcao_legal`;
*/
DELIMITER //
CREATE PROCEDURE sps_verifica_log_salario(
IN dt_busca_init DATETIME,
IN dt_busca_fim DATETIME
)
BEGIN
	CREATE TEMPORARY TABLE tmp_alteracao_irregular
    AS SELECT * , IF(valor_novo<valor_antigo AND correcao_legal =0, "Irregular", "Regular") AS regularidade
    FROM log_salario WHERE dt_alteracao BETWEEN dt_busca_init AND dt_busca_fim;
    SELECT * FROM tmp_alteracao_irregular;
    DROP TABLE tmp_alteracao_irregular;
END
//
DELIMITER ;

CALL sps_verifica_log_salario("2022-05-01","2022-06-01");
UPDATE empregado SET salario=5530.50 WHERE cod_empregado=4;
UPDATE log_salario SET correcao_legal=1, id_correcao=16 WHERE cod_log_salario=7;
