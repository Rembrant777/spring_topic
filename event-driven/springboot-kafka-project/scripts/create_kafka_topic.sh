#!/bin/sh

./kafka-topics --create --bootstrap-server localhost:9092 \
               --replication-factor 1 --partitions 1 --topic kakfa_tutorial_topic



./kafka-topics --create --bootstrap-server localhost:9092 \
               --replication-factor 1 --partitions 1 --topic kafka_tutorial_json_topic
