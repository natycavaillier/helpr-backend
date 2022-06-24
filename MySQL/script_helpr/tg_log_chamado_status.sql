DELIMITER //
CREATE TRIGGER tg_log_status_chamado BEFORE UPDATE
ON chamado
FOR EACH ROW
BEGIN 
	IF(OLD.status <> NEW.status) THEN
		INSERT INTO log_chamado_status(data_alteracao, dado_alterado, dado_novo, dev_correcao)
        VALUES(NOW(), OLD.status, NEW.status, NULL);
    END IF;
END //
DELIMITER ;

select * from chamado;

select * from log_chamado_status;