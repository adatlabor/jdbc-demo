#!/bin/bash

# WARNING!
#
# This is an _experimental_ shell script which can be used to automatically modify
# the name, title, description of the sample Java Web Start application in the
# appropriate (source) files. In addition, student name, neptun code and
# name of ojdbc driver can also be specified.
#
# Use it on your own risk!

# include local config, if exists, exit otherwise.
LOCAL_CONFIGFILE=`dirname $0`/preproc.config.sh
if [ -f ${LOCAL_CONFIGFILE} ]; then
  . ${LOCAL_CONFIGFILE}
else
  cat <<HERE

!!! ${LOCAL_CONFIGFILE} not found. !!!

Perhaps you forgot to create it by copying ${LOCAL_CONFIGFILE}.sample

Aborting preprocessing.

HERE

  exit 2
fi


# Sed patterns
declare -a SED_PATTERNS;
SED_PATTERNS=('\$\$student_name\$\$' '\$\$neptun\$\$' '\$\$title\$\$' '\$\$initpage_title\$\$' '\$\$desc\$\$' '\$\$app_name\$\$' '\$\$app_model\$\$' '\$\$ojdbc_driver\$\$');

# User-specified params
declare -a PARAMS;
PARAMS=("$student_name " "$neptun" "$title " "$initpage_title " "$desc " "$app_name" "$app_model" "$ojdbc_driver");

# File paths
declare -a FILEPATHS;
FILEPATHS=('./web/index.html' './conf/MANIFEST.MF' './src/application.java' './src/applicationmodel.java' './src/application.jnlp');

# Replace placeholders in files with the appropriate parameters
for FILEPATH in "${FILEPATHS[@]}"
do
    SED_PATTERN="";
    
    for i in {0..7}
    do
        SED_PATTERN+="s/"${SED_PATTERNS[$i]}"/"${PARAMS[$i]}"/g;";
    done
    
    #echo -e $SED_PATTERN'\n';
    sed -i -e "$SED_PATTERN" "${FILEPATH}";
done

# remove file renamings for now, jmarton, 20160319
## Filenames
#declare -a FILENAMES;
#FILENAMES=('application.jnlp' 'application.jar' 'application.java' 'applicationmodel.java');
## Base directory
#BASEDIR='./jdbc';
#
## Rename files
## JNLP file
#mv "$BASEDIR/${FILENAMES[0]}" "$BASEDIR/$app_name.jnlp";
## JAR file
#mv "$BASEDIR/${FILENAMES[1]}" "$BASEDIR/$app_name.jar";
## Application's main Java source file
#mv "$BASEDIR/${FILENAMES[2]}" "$BASEDIR/$app_name.java";
## Application model's Java source file
#mv "$BASEDIR/${FILENAMES[3]}" "$BASEDIR/$app_model.java";
