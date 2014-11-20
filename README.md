AMADEUS
=======
O AMADEUS é um sistema de gestão de aprendizagem desenvolvido pelo Ciências Cognitivas e Tecnologia Educacional do Centro de 
Informática da UFPE.

Documentação
------------
Acesse [aqui](http://www.abed.org.br/revistacientifica/Revista_PDF_Doc/2009/AMADEUS_NOVO_MODEL0_DE_SISTEMA_DE_GESTAO_DE_APRENDIZAGEMrbaad2009.pdf) a documentação.

Instalação
----------
O AmadeusLMS é um projeto Eclipse e utiliza as tecnologias Java 6, JSP, TomCat 6, Hibernate e PostgreSQL.

- Crie uma base de dados e configure os dados de conexão no [hibernate.cfg.xml](src/hibernate.cfg.xml).
- Execute o [dump](src/amadeuslms_web-FINAL.sql) para gerar da base de dados.
- Faça o deploy do projeto no TomCat.

Licença
-------
Veja o arquivo de [licença](LICENSE).
