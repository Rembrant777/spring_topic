#!/bin/sh
DB_PORT=54331
POSTGRES_DB=wiki_media_db
POSTGRES_PASSWORD=postgres
DB_CONTAINER_NAME=wiki_media_db_pg

docker run -d -p $DB_PORT:5432 \
  -e POSTGRES_PASSWORD=$POSTGRES_PASSWORD \
  -e POSTGRES_DB=$POSTGRES_DB \
  --name=$DB_CONTAINER_NAME  postgres:12.13-bullseye