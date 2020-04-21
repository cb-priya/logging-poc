cd  kafka_2.12-2.2.1
bin/zookeeper-server-start.sh config/zookeeper.properties  &
bin/kafka-server-start.sh config/server.properties &
bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic filebeatToKafka
bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic logstashToKafka
tail -f /dev/null