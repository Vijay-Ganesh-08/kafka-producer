# kafka-producer
Contains Basic Java SpringBoot Application as Kafka Producer

## Types of Topic Creation
### SpringBoot Creation
- Allowing SpringBoot to create the topic by itself with Default Configuration
- We just need to pass the topic name while pushing messages to topic via Kafka Template
- SpringBoot default configuration is Partition : 1 , ReplicaSet : 1
### Manual Topic Creation
- Creation of Topic with the below command
- bin/kafka-topics --bootstrap-server localhost:9092 --create --topic <NewTopicName> --partitions 3 --replication-factor 1
- Partition and the ReplicaSet is being defined by the user manually
### Topic Creation via Code
- NewTopic class can be used in configuration class to create the topic manually
- Partition and the ReplicaSet is being defined by the user in the code
### Portal Creation
- Some companies have a UI portal to create topic and managing the same
- That can be used to create topic with required configurations.