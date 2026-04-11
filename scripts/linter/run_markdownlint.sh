#!/bin/bash

# Default configuration path
MD_CONFIG="../../.markdownlint.json"
MD_CLI="markdownlint"
INPUT_PATH="../../"

# Check if detekt is installed
if ! command -v $MD_CLI &> /dev/null
then
    echo "Markdownlint CLI could not be found. Please install Markdownlint first."
    exit 1
fi

# Run Prettier
$MD_CLI --config "$MD_CONFIG" --fix "$INPUT_PATH"