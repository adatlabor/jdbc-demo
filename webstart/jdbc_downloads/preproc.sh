#!/bin/bash

# WARNING!
#
# This is an _experimental_ shell script which can be used to automatically modify
# the name, title, description of the sample Java Web Start application in the
# appropriate (source) files. In addition, student name, neptun code and
# name of ojdbc driver can also be specified.
#
# Use it on your own risk!

# Edit here (DO NOT USE ANY SPEC. CHARACTERS):
# ==================================>%============================================
student_name='Hallgató';
neptun='aaa000'; # == Rapid user
title='JDBC mérés';
initpage_title='Minta kezdőoldal';
desc='Próbaalkalmazás a Szoftver laboratórium 5. c. tárgy JDBC méréséhez';
app_name='MyJwsApplication';
app_model='MyJwsApplicationModel';
ojdbc_driver='ojdbc6'; # Without extension
# ==================================>%============================================

# Sed patterns
declare -a SED_PATTERNS;
SED_PATTERNS=('\$\$student_name\$\$' '\$\$neptun\$\$' '\$\$title\$\$' '\$\$initpage_title\$\$' '\$\$desc\$\$' '\$\$app_name\$\$' '\$\$app_model\$\$' '\$\$ojdbc_driver\$\$');

# User-specified params
declare -a PARAMS;
PARAMS=("$student_name " "$neptun" "$title " "$initpage_title " "$desc " "$app_name" "$app_model" "$ojdbc_driver");

# File paths
declare -a FILEPATHS;
FILEPATHS=('./index.html' './jdbc/index.html' './jdbc/Makefile' './jdbc/MANIFEST.MF' './jdbc/application.java' './jdbc/applicationmodel.java' './jdbc/application.jnlp');

# Filenames
declare -a FILENAMES;
FILENAMES=('application.jnlp' 'application.jar' 'application.java' 'applicationmodel.java');
# Base directory
BASEDIR='./jdbc';

# Replace placeholders in files with the appropriate parameters
for FILEPATH in "${FILEPATHS[@]}"
do
    SED_PATTERN="";
    
    for i in {0..7}
    do
        SED_PATTERN+="s/"${SED_PATTERNS[$i]}"/"${PARAMS[$i]}"/g;";
    done
    
    #echo -e $SED_PATTERN'\n';
    cat "$FILEPATH" | sed -e "$SED_PATTERN" > "${FILEPATH}.tmp";
done

# Renaming temporary files
for FILEPATH in "${FILEPATHS[@]}"
do
    mv "${FILEPATH}.tmp" "$FILEPATH";
done

# Rename files
# JNLP file
mv "$BASEDIR/${FILENAMES[0]}" "$BASEDIR/$app_name.jnlp";
# JAR file
mv "$BASEDIR/${FILENAMES[1]}" "$BASEDIR/$app_name.jar";
# Application's main Java source file
mv "$BASEDIR/${FILENAMES[2]}" "$BASEDIR/$app_name.java";
# Application model's Java source file
mv "$BASEDIR/${FILENAMES[3]}" "$BASEDIR/$app_model.java";
