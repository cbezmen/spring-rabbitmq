#!/bin/bash

projectArray=(rabbit-sender rabbit-receiver)
projectVersion=v1

function note() {
    local GREEN NC
    GREEN='\033[0;32m'
    NC='\033[0m' # No Color
    printf "\n${GREEN}$@  ${NC}\n" >&2
}

set -e


if [[ $@ == *"compile"* ]]
then
  for project in ${projectArray[*]}; do
    note "Compiling $project..."
    cd $project; gradle clean build -x test; docker build -f docker/Dockerfile -t $project:$projectVersion .; cd -
  done
fi

if [[ $@ == *"start-d9"* ]]
then
  note "Starting docker compose"
  docker-compose up --build -d
fi

if [[ $@ == *"stop-d9"* ]]
then
  note "Stopping docker compose"
  docker-compose down
fi

if [[ $@ == *"start-k8"* ]]
then
  eval $(minikube docker-env)
  note "Starting building yaml files..."
  note "Starting Rabbitmq Server..."
  kubectl create -f kubernetes/rabbitmq.yaml; sleep 3

  for project in ${projectArray[*]}; do
    note "Starting $project..."
    kubectl create -f kubernetes/$project.yaml; sleep 5
  done
fi


if [[ $@ == *"stop-k8"* ]]
then
  note "Stoping kubernetes..."
  kubectl delete service rabbitmq
  kubectl delete deployment rabbitmq

  kubectl delete service ${projectArray[*]}
  kubectl delete deployment ${projectArray[*]}

fi
#
