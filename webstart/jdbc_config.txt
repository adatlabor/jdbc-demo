#!/bin/bash

HOMEDIR=${HOME}
# HOMEDIR=/usr/orauser/${USER}
# HOMEDIR=~

WWWJDBC=https://www.db.bme.hu/sites/default/files
PACKJDBC=jdbc_downloads.tgz

# Preparing public_html
chmod 701 ${HOMEDIR}
mkdir ${HOMEDIR}/public_html
mkdir ${HOMEDIR}/public_html/jdbc
chmod 705 ${HOMEDIR}/public_html
chmod 705 ${HOMEDIR}/public_html/jdbc

# Downloading necessary files
wget --no-check-certificate ${WWWJDBC}/${PACKJDBC}
if [ "$?" != "0" ] ; then
  echo 
  echo !!! Failed to download files !!!
  echo
  exit 1
fi
tar xvf ${PACKJDBC}
rm ${PACKJDBC}

echo "Working environment was successfully set!"
