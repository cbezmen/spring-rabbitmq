#!/bin/bash

function note() {
    local GREEN NC
    GREEN='\033[0;32m'
    NC='\033[0m' # No Color
    printf "\n${GREEN}$@  ${NC}\n" >&2
}

set -e


if [[ $@ == *"compile"* ]]
then
  note "Building rabbit-sender"
  cd rabbit-sender; gradle clean build -x test; cd -
  note "Building rabbit-receiver"
  cd rabbit-receiver; gradle clean build -x test; cd -
fi

if [[ $@ == *"start"* ]]
then
  note "Starting docker compose"
  docker-compose up --build -d
fi

if [[ $@ == *"stop"* ]]
then
  note "Stopping docker compose"
  docker-compose down
fi
