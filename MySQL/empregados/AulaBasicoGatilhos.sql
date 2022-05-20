use empregados_db;
SELECT * FROM empregado;

DELIMITER //
CREATE TRIGGER log_alteracao_salario BEFORE UPDATE ON  empregado
FOR EACH ROW
BEGIN
	IF (NEW.salario <> OLD.salario) THEN
		INSERT INTO log_salario(cod_empregado, cod_depto, dt_alteracao, valor_antigo, valor_novo)
		VALUES(NEW.cod_empregado, NEW.cod_depto, NOW(), NEW.salario, OLD.salario);
    END IF;
END
DELIMITER ;