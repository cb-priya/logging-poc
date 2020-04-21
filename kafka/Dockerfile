FROM ubuntu:16.04

RUN apt-get update && \
    apt-get install -y openjdk-8-jdk && \
    apt-get install -y wget && \
    wget https://archive.apache.org/dist/kafka/2.2.1/kafka_2.12-2.2.1.tgz -P /tmp && \
    tar xf /tmp/kafka_2.12-2.2.1.tgz

COPY initialize_kafka.sh /tmp/initialize_kafka.sh

RUN chmod 755 /tmp/initialize_kafka.sh

CMD /tmp/initialize_kafka.sh
    


