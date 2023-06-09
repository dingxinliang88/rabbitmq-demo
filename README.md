# RabbitMQ Demo

## Project Structure

```
.
├── LICENSE	==> MIT LICENSE(100%)
├── README.md	==> Read this first
├── doc	==> install documentation
├── rabbitmq-consumer   ==> original rabbitmq consumer
├── rabbitmq-producer   ==> original rabbitmq producer
├── spring-rabbitmq-consumer    ==> Spring integration with RabbitMQ
├── spring-rabbitmq-producer    ==> Spring integration with RabbitMQ
├── springboot-rabbitmq-consumer    ==> SpringBoot integration with RabbitMQ
└── springboot-rabbitmq-producer    ==> SpringBoot integration with RabbitMQ

```

## How to Use?

### Step1: Install RabbitMQ
The specific steps can be found in the [official documentation](https://www.rabbitmq.com/download.html)

> If you just want to start quickly, you can read the [doc](./doc/RabbitMQ_install_doc.md)

### Step2: Modify the configuration
Before you start each module, you need to modify the configurations first, and the specific configurations is subject to the actual environment

### Step3: Just Run!!!

- start the consumer first
- then start the producer

=> This will allow you to see the consumer receiving messages and outputting them

