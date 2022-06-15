#!/bin/bash
rm -f httpServer.jks
echo -e "$(hostname -f)\nDEI\nISEP\nPORTO\nPORTO\nPT\nyes" | keytool -genkey -v -alias http -keyalg RSA -keysize 2048 \
	-validity 365 -keystore httpServer.jks -storepass forgotten
#######
echo "############################################################################"
keytool -list -v -keystore httpServer.jks -storepass forgotten
echo "############################################################################"
#######
