#!/bin/sh
# Build fuzzsmt.jar.  Needs nothing but a JDK: javac and jar are enough,
# ant is not required.  build.xml is still there for anyone who has ant.

set -e

cd "$(dirname "$0")"

rm -rf build
mkdir build
javac -d build *.java
jar cfm fuzzsmt.jar Manifest.txt -C build .

echo "built fuzzsmt.jar"
