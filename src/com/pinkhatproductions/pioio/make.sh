#!/bin/sh

LIB="../../../../library"
PROCESSING_CLASSPATH="/Users/spike/boxdocs/Applications/Processing-2.0b8.app/Contents/Resources/Java/core/library/core.jar"
CLASSPATH="$PROCESSING_CLASSPATH:$LIB/ioiolibpc.jar"
DEST="../../../../bin"

javac -classpath $CLASSPATH -d $DEST *.java
