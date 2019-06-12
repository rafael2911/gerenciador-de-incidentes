insert into role (nome) values('ADMIN');
insert into role (nome) values('USER');

insert into usuario (email, nome, senha, sobrenome, status) values('rafael@admin.com', 'Rafael', '$2a$10$8d4z2ydQARpNsOKVQZF4gOt57iZHhsDMBPqhS/b8GApumuGMZ1e46', 'Carvalho', 'ATIVO');

insert into usuario_roles(usuario_email, roles_nome) values ('rafael@admin.com', 'ADMIN');
insert into usuario_roles(usuario_email, roles_nome) values('rafael@admin.com', 'USER');