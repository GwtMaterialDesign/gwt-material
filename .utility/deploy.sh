#!/bin/bash
set -ev
if [ "$TRAVIS_JDK_VERSION" == "oraclejdk8" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then
  echo "<settings><servers><server><id>ossrh</id><username>\${env.OSSRH_USER}</username><password>\${env.OSSRH_PASS}</password></server></servers></settings>" > ~/settings.xml
  mvn deploy --settings ~/settings.xml
fi