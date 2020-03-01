#!/bin/sh
# wait-for-mysql

set -e

until nc -z -v -w30 mysql 3306
do
  >&2 echo "Mysql is unavailable - sleeping"
  sleep 1
done

>&2 echo "Mysql is up - executing command"

mvn clean install

exec $@
