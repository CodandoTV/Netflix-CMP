#!/bin/bash

# Default configuration path
PRETTIER_CLI="prettier"
INPUT_PATH="../../.github"

# Check if detekt is installed
if ! command -v $PRETTIER_CLI &> /dev/null
then
    echo "Prettier CLI could not be found. Please install prettier first."
    exit 1
fi

# Run Prettier
$PRETTIER_CLI --write "$INPUT_PATH"