#!/bin/bash
/usr/bin/mysqld_safe --skip-grant-tables &
sleep 5
mysql -u root -e "CREATE DATABASE habitissimo"
mysql -u root habitissimo < /tmp/habitissimo.sql
