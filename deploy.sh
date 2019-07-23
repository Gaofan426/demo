#!/usr/bin/env bash

if [[ $# -gt 0 ]]; then
    env=$1
else
    env="test"
fi

echo "==== git pull  ===="
git pull

echo "==== starting to build demo ===="
mvn package -DskipTests
echo "==== building hospital demo  ===="

./startup.sh $env
