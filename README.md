# delivery-fast
Projeto Lanchonete Web

-- Maven Gerar artefato --
sudo mvn clean install

-- Docker API --
# Buildar imagem:
sudo docker build -f Dockerfile -t delivery-fast-spring-boot .

# Executar aplicacao
sudo docker run -p8085:8085 delivery-fast-spring-boot

# Configurar banco
-- Acessar terminal H2 --
http://localhost:8085/h2-console/

Copiar scripts do arquivo src/main/resources/schema.sql
Executa-los para criação de tabelas e constraints


# Subir frontend
cd delivery-fast/app
yarn start

Acessar http://localhost:3000/lanches


