#!/bin/bash
echo Setting up environment ...
export EMULATOR_HOME=$PWD
export EMULATOR_ASSETS=$EMULATOR_HOME/assets
export EMULATOR_SRC=$EMULATOR_HOME/src
export EMULATOR_MODELS=$EMULATOR_SRC/main/java/com/ocka/emulator/model
alias b="gradle clean build"
