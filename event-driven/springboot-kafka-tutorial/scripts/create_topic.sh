#!/bin/sh

./kafka-topics --create --bootstrap-server localhost:9092 \
               --replication-factor 1 --partitions 1 --topic kakfa_tutorial_topic
# [appuser@0172347c8196 bin]$ ./kafka-topics --create --bootstrap-server localhost:9092 \
#>                --replication-factor 1 --partitions 1 --topic kakfa_tutorial_topic
#
# WARNING: Due to limitations in metric names, topics with a period ('.') or underscore ('_') could collide. To avoid issues it is best to use either, but not both.
# Created topic kakfa_tutorial_topic.


./kafka-topics --create --bootstrap-server localhost:9092 \
               --replication-factor 1 --partitions 1 --topic kafka_tutorial_json_topic

#[appuser@0172347c8196 bin]$ ./kafka-topics --create --bootstrap-server localhost:9092 \
#>                --replication-factor 1 --partitions 1 --topic kafka_tutorial_json_topic
# WARNING: Due to limitations in metric names, topics with a period ('.') or underscore ('_') could collide. To avoid issues it is best to use either, but not both.
# Created topic kafka_tutorial_json_topic.