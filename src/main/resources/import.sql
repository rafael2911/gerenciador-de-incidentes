insert into role (nome) values('ADMIN');
insert into role (nome) values('USER');

insert into usuario (email, nome, senha, sobrenome, status) values('rafael@admin.com', 'Rafael', '$2a$10$8d4z2ydQARpNsOKVQZF4gOt57iZHhsDMBPqhS/b8GApumuGMZ1e46', 'Carvalho', 'ATIVO');

insert into usuario_roles(usuario_email, roles_nome) values ('rafael@admin.com', 'ADMIN');
insert into usuario_roles(usuario_email, roles_nome) values('rafael@admin.com', 'USER');

insert into categoria(descricao) values('Email');
insert into categoria(descricao) values('Relatórios');
insert into categoria(descricao) values('Backup');
insert into categoria(descricao) values('Permissões');

insert into origem(descricao) values('Telefone');
insert into origem(descricao) values('Presencial');
insert into origem(descricao) values('Email');

insert into sla(descricao, dias) values('1_DIA', 1);
insert into sla(descricao, dias) values('2_DIAS', 2);
insert into sla(descricao, dias) values('4_DIAS', 4);