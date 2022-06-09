#!/bin/bash
rm -f server.jks
echo -e "$(hostname -f)\nDEI\nISEP\nPORTO\nPORTO\nPT\nyes" | keytool -genkey -v -alias http -keyalg RSA -keysize 2048 \
	-validity 365 -keystore server.jks -storepass forgotten
####
#keytool -list -v -keystore server.jks -storepass forgotten
####
