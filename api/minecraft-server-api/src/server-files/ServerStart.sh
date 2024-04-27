#!/bin/sh

# Don't edit these values unless you know what you are doing.
INSTALL_JAR="forge-1.12.2-14.23.5.2860-installer.jar"
SERVER_JAR="forge-1.12.2-14.23.5.2860.jar"

# You can edit these values if you wish.
MIN_RAM="2G"
MAX_RAM="15G"
JAVA_PARAMETERS="-XX:+UseG1GC -Dsun.rmi.dgc.server.gcInterval=2147483646 -XX:+UnlockExperimentalVMOptions -XX:G1NewSizePercent=20 -XX:G1ReservePercent=20 -XX:MaxGCPauseMillis=50 -XX:G1HeapRegionSize=32M -Dfml.readTimeout=180"

# Start the server.
start_server() {
   /home/nathan/.sdkman/candidates/java/8.0.402-tem/bin/java -server -Xms${MIN_RAM} -Xmx${MAX_RAM} ${JAVA_PARAMETERS} -jar ${SERVER_JAR} nogui & echo $!
}
start_server
