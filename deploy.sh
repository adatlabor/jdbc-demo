#!/bin/bash

HOMEDIR=${HOME}
WWWJDBC=https://github.com/adatlabor/jdbc-demo/archive/
PACKJDBC=master.zip


TEMPFILE=$( mktemp -t lab5jdbc.XXXXXXXXX )
wget ${WWWJDBC}/${PACKJDBC} -O $TEMPFILE
if [ "$?" != "0" ] ; then
  echo 
  echo !!! Failed to download files !!!
  echo
  exit 1
fi
unzip $TEMPFILE
mv jdbc-demo-master jdbc
chmod 701 jdbc
chmod 705 jdbc/web
rm $TEMPFILE

# Preparing public_html
chmod 701 ${HOMEDIR}
mkdir -p ${HOMEDIR}/public_html
chmod 705 ${HOMEDIR}/public_html

WEBDIR=$( readlink -f ./jdbc/web )
ln -s ${WEBDIR} ${HOMEDIR}/public_html/jdbc

cat <<HERE
Working environment was successfully set!

Do not forget to:
* add Oracle JDBC driver to the lib/ subdirectory.
* create and edit preproc.config.sh (by copying preproc.config.sh.sample)
* run ./preproc.sh
HERE
