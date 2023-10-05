## Notes of Kafka Integration with Spring Boot

We only create a Inter-Mediate class that wraps all the basic operations of Kafka as Producer/Consumer. 

And annotate it by annotation `@Service` so that it can be loaded, initialized into the spring container,
and use for multiple time by different components in the project 

If we were familiar with the CRUD operation's service defintion of the RDBMS(mysql, postgres and oracle), 
the `KafkaConsumer` and the `KafkaProducer` we declared and annotated by `@Service`. 

They play the same role in the project. 


And the topic name, default partitions declaration, cluster name, the group id for consumer side, 
and the serializer/deserializer use for both producer and consumer initialization can be loaded from the 
`application.propertis` by using the spring inner defined annotation like ` @Value("${spring.kafka.topic.name}")`.

I'm wondering about are there any more complicated scenraios like 
how to maintain the dataset's consistency during kafka stream based distributed env's transaction which involves lots of the 
operations that supported among multiple services in the spring system(or spring cloud).

And an other question I curious about is how to use the kafka streaming and how to integrate it into the microservice system(spring boot).   
Maybe try to explore the topic of spring-boot and kafka by refering this [project](https://github.com/ben-jamin-chen/springboot-kafka-streams-rest-api/tree/main/src/main/java/io/confluent/demo) in github.


## References 
Most of the codes from this repository is refering to the github repo
* [springboot-kafka-course](https://github.com/RameshMF/springboot-kafka-course)

* [kafka-images](https://github.com/confluentinc/kafka-images/blob/master/examples/kafka-single-node/docker-compose.yml)