insert into role (nome) values('ROLE_ADMIN');
insert into role (nome) values('ROLE_TECNICO');
insert into role (nome) values('ROLE_USER');

insert into usuario (email, nome, senha, sobrenome, status) values('rafael@admin.com', 'Rafael', '$2a$10$8d4z2ydQARpNsOKVQZF4gOt57iZHhsDMBPqhS/b8GApumuGMZ1e46', 'Carvalho', 'ATIVO');

insert into usuario_roles(usuario_email, roles_nome) values ('rafael@admin.com', 'ROLE_ADMIN');
insert into usuario_roles(usuario_email, roles_nome) values('rafael@admin.com', 'ROLE_USER');

insert into categoria(descricao) values('Email');
insert into categoria(descricao) values('Relatórios');
insert into categoria(descricao) values('Backup');
insert into categoria(descricao) values('Office');

insert into origem(descricao) values('Telefone');
insert into origem(descricao) values('Presencial');
insert into origem(descricao) values('Email');

insert into sla(descricao, dias) values('1_DIA', 1);
insert into sla(descricao, dias) values('2_DIAS', 2);
insert into sla(descricao, dias) values('4_DIAS', 4);

insert into chamado (titulo, descricao, data_abertura, status, categoria, origem, requerente) values('Primeiro Chamado', 'Chamado realizado para testar a listagem', 'aced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c00007870770e05000007e306110c292400b71b0078', 'ABERTO', 4, 1, 'rafael@admin.com')
