SELECT * FROM pessoa;
SELECT * FROM log_tecnico_update WHERE id_pessoa = 1;

CREATE TABLE log_tecnico_update(
id INT NOT NULL AUTO_INCREMENT,
dt_alteracao DATETIME NOT NULL,
dt_criacao DATE NOT NULL,
dado_alterado VARCHAR(250) NOT NULL,
novo_dado VARCHAR(250) NOT NULL,
id_pessoa INT NOT NULL,
PRIMARY KEY(id)
);

DELIMITER //
CREATE TRIGGER tg_log_alteracao_pessoa BEFORE UPDATE ON pessoa
FOR EACH ROW
BEGIN
	CASE
    WHEN NEW.dtype <> OLD.dtype THEN
		INSERT INTO log_tecnico_update(dt_alteracao, dt_criacao, dado_alterado, novo_dado, id_pessoa)
        VALUES(NOW(), NEW.data_criacao, OLD.dtype, NEW.dtype, NEW.id);
    WHEN NEW.cpf <> OLD.cpf THEN
		INSERT INTO log_tecnico_update(dt_alteracao, dt_criacao, dado_alterado, novo_dado, id_pessoa)
        VALUES(NOW(), NEW.data_criacao, OLD.cpf, NEW.cpf, NEW.id);
    WHEN NEW.email <> OLD.email THEN
		INSERT INTO log_tecnico_update(dt_alteracao, dt_criacao, dado_alterado, novo_dado, id_pessoa)
        VALUES(NOW(), NEW.data_criacao, OLD.email, NEW.email, NEW.id);
    WHEN NEW.nome <> OLD.nome THEN
		INSERT INTO log_tecnico_update(dt_alteracao, dt_criacao, dado_alterado, novo_dado, id_pessoa)
        VALUES(NOW(), NEW.data_criacao, OLD.nome, NEW.nome, NEW.id);
    WHEN NEW.senha <> OLD.senha THEN
		INSERT INTO log_tecnico_update(dt_alteracao, dt_criacao, dado_alterado, novo_dado, id_pessoa)
        VALUES(NOW(), NEW.data_criacao, OLD.senha, NEW.senha, NEW.id);
	END CASE;
END //
DELIMITER ;