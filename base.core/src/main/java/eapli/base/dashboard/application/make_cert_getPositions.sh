#!/bin/bash
rm -f getPositions.jks
echo -e "$(hostname -f)\nDEI\nISEP\nPORTO\nPORTO\nPT\nyes" | keytool -genkey -v -alias getPositions -keyalg RSA -keysize 2048 \
	-validity 365 -keystore getPositions.jks -storepass forgotten
####
#keytool -list -v -keystore getPositions.jks -storepass forgotten
####
