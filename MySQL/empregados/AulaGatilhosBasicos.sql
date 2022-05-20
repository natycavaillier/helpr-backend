SELECT * FROM departamento;
SELECT * FROM empregado;
SELECT * FROM dependentes;
SELECT * FROM log_salario;

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
UPDATE empregado set salario=2560.30 WHERE cod_empregado=1;
UPDATE empregado set salario=4870.00 WHERE cod_empregado=14;
UPDATE empregado set salario=3820.25 WHERE cod_empregado=10;
UPDATE empregado set salario=8023.50 WHERE cod_empregado=5;
ROLLBACK;
COMMIT;