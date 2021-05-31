#!/usr/bin/env bash
#
# Script para iniciar la ejecucíon de la aplicacción java validadndo lo necesario
#
#


if [ $# -eq 2 ] ; then
  INPUT_FILE=$1
  OUTPUT_FILE=$2
  echo "parametros: "
  echo "** Archivo entrada; $INPUT_FILE"
  echo "** Archivo Salida; $OUTPUT_FILE"
else
  echo "Debe ingresar archivo de entrada y nombre archivo salida"
  exit 1
fi


function validateJava() {
  JAVA_MAJOR_VERSION=$(java -version 2>&1 | head -1 | cut -d'"' -f2 | sed 's/^1\.//' | cut -d'.' -f1)
  if [[ "$JAVA_MAJOR_VERSION" -ge "8" ]] ; then
    echo "JAVA_MAJOR_VERSION: $JAVA_MAJOR_VERSION"
  else
    echo "Valide su instalación de java"
    exit 1
  fi
}

function validateMaven() {
  MAVEN_VERSION_FIRST=$(mvn -version 2>&1  | head -1   | cut -d'"' -f2   | sed 's/^2\.//' | cut -d' ' -f3 | cut -d'.' -f1)
  MAVEN_VERSION_SECOND=$(mvn -version 2>&1  | head -1   | cut -d'"' -f2   | sed 's/^2\.//' | cut -d' ' -f3 | cut -d'.' -f2)
  if [[ "$MAVEN_VERSION_FIRST" -ge "3"  &&  "$MAVEN_VERSION_SECOND" -ge "6" ]] ; then
    echo "MAVEN_VERSION: $MAVEN_VERSION_FIRST.$MAVEN_VERSION_SECOND"
  else
    echo "Valide su instalación de Maven"
    exit 1
  fi
}


validateJava
validateMaven

USE_PROFILE=${PREVIRED_D1_NIVEL1_ENV:-local}
echo "PREVIRED_D1_NIVEL1_ENV=${USE_PROFILE}"


mvn -P${USE_PROFILE} -DskipTests spring-boot:run -Dspring-boot.run.profiles=${USE_PROFILE} -Dspring-boot.run.arguments="--file.input=$INPUT_FILE --file.output=$OUTPUT_FILE"
exit 0
