# deliverit
Project test using Spring, Java 8, JPA, Hibernate, PostgreSQL and Maven

Data Base config:

datasource.url=jdbc:postgresql://localhost:5432/core_app
datasource.username=deliverit
datasource.password=1234

Necessary scripts to correclty run the application:

//Rules for calculating percentage of delayed payments

insert into tb_multa (id,descricao,nivel,multa,juros_dia) values(1,'Até 3 dias', 'baixo',2.0,0.1);
insert into tb_multa (id,descricao,nivel,multa,juros_dia) values(2,'Superior a 3 dias', 'medio',3.0,0.2);
insert into tb_multa (id,descricao,nivel,multa,juros_dia) values(3,'Superior a 5 dias', 'alto',5.0,0.3);


Has two main operations:
Create Conta using the API Conta, who uses POST Method example of request:

Calling url with POST method: http://localhost:8080/api/conta
Body example:
    {
        "valorOriginal": 124.52,
        "dataVencimento": "05/02/2020",
        "dataPagamento": "15/02/2020",
        "nome":"Conta 01 atrasada 5 dias"
    }
    
    
And to return a LIST we have the method GET for url: http://localhost:8080/api/contas
That will bring all records on database
