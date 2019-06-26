insert into role (nome) values('ROLE_ADMIN');
insert into role (nome) values('ROLE_TECNICO');
insert into role (nome) values('ROLE_USER');

insert into usuario (email, nome, senha, sobrenome, status) values('rafael@admin.com', 'Rafael', '$2a$10$8d4z2ydQARpNsOKVQZF4gOt57iZHhsDMBPqhS/b8GApumuGMZ1e46', 'Carvalho', 'ATIVO');
insert into usuario (email, nome, senha, sobrenome, status) values('carlos@tecnico.com', 'Carlos', '$2a$10$Tv5MDY2QfBF6RUVL/7cxeOwOMf.POXUjEsTsa2S9F2x3JKCpk/6t6', 'Carvalho', 'ATIVO');
insert into usuario (email, nome, senha, sobrenome, status) values('ferreira@user.com', 'Ferreira', '$2a$10$fHwts2XXVPMbFU7RPM4xCO2A66sGbq0Fb3NGG6Cnrq5uYQaE3sDPe', 'Carvalho', 'ATIVO');

insert into usuario_roles(usuario_email, roles_nome) values ('rafael@admin.com', 'ROLE_ADMIN');
insert into usuario_roles(usuario_email, roles_nome) values('rafael@admin.com', 'ROLE_USER');
insert into usuario_roles(usuario_email, roles_nome) values('carlos@tecnico.com', 'ROLE_TECNICO');
insert into usuario_roles(usuario_email, roles_nome) values('ferreira@user.com', 'ROLE_USER');

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

insert into interacao (mensagem, data, usuario) values ('Teste primeira iteracao', 'aced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c00007870770e05000007e306110c292400b71b0078', 'rafael@admin.com')
insert into interacao (mensagem, data, usuario) values ('Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s.', 'aced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c00007870770e05000007e306110c292400b71b0078', 'rafael@admin.com')
insert into chamado_interacoes (chamado_id, interacoes_id) values(1,1);
insert into chamado_interacoes (chamado_id, interacoes_id) values(1,2);




