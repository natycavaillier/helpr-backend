DELIMITER //
CREATE TRIGGER tg_log_salario_update BEFORE UPDATE ON  empregado
FOR EACH ROW
BEGIN
	IF (NEW.salario <> OLD.salario) THEN
		INSERT INTO log_salario(cod_empregado, cod_depto, dt_alteracao, valor_antigo, valor_novo, correcao_legal, id_correcao)
		VALUES(NEW.cod_empregado, NEW.cod_depto, NOW(), OLD.salario, NEW.salario, 0, 0);
    END IF;
END //
DELIMITER ;
select * from log_salario;
DROP TRIGGER tg_log_salario_update;