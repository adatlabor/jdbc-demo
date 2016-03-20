#!/bin/bash

HOMEDIR=${HOME}
REPOSITORY_NAME=jdbc-demo
BRANCH_NAME=master
DOWNLOAD_BASE=https://github.com/adatlabor

DOWNLOAD_URL=${DOWNLOAD_BASE}/${REPOSITORY_NAME}/archive/${BRANCH_NAME}.zip
ARCHIVE_DIRNAME=${REPOSITORY_NAME}-${BRANCH_NAME}
TARGET_DIRNAME=jdbc
WEB_DIRNAME=jdbc


TEMPFILE=$( mktemp -t lab5jdbc.XXXXXXXXX )
wget ${DOWNLOAD_URL} -O $TEMPFILE
if [ "$?" != "0" ] ; then
  echo 
  echo !!! Failed to download files !!!
  echo
  exit 1
fi
unzip $TEMPFILE
mv ${ARCHIVE_DIRNAME} ${TARGET_DIRNAME}
chmod 701 ${TARGET_DIRNAME}
chmod 705 ${TARGET_DIRNAME}/web
rm $TEMPFILE

# Preparing public_html
chmod 701 ${HOMEDIR}
mkdir -p ${HOMEDIR}/public_html
chmod 705 ${HOMEDIR}/public_html

WEBDIR=$( readlink -f ./${TARGET_DIRNAME}/web )
ln -s ${WEBDIR} ${HOMEDIR}/public_html/${WEB_DIRNAME}

cat <<HERE
Working environment was successfully set!

Do not forget to:
* add Oracle JDBC driver to the lib/ subdirectory.
* create and edit conf/preproc.config.sh (by copying preproc.config.sh.sample)
* change dir to workdir (cd ${TARGET_DIRNAME})
* run ./util/preproc.sh
HERE
