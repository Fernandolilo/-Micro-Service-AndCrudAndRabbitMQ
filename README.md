# -Micro-Service-AndCrudAndRabbitMQ

Api de micro serviços, fazendo uso de gateway zuul, o gateway zuul, atualmente encrontra-se descontinuado, caso alguem queira fazer uso deste modelo para estudo, fazer um pull, no gateway.

temos 3 Apis se relacionando através de mesnsageria RabbitMQ.
para fazer uso do RabbitMQ é necessário ter o docker instalado, 
para instalar o docker no windows -> https://docs.docker.com/desktop/windows/install/
para inicializar uma imagem do RabbirMQ -> https://carledwinti.wordpress.com/2020/03/28/como-instalar-imagem-do-rabbitmq-no-docker/

bom temos neste serviço um user o mesmo ao logar entrega um token, token sendo valido, libera as transações nas apis, para fazer um pagamento, cad de vendas enfim, é apenas um simulado.

