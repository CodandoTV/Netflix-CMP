#!/bin/bash

# Default configuration path
DETEKT_CONFIG="../../config/detekt/detekt.yml"
DETEKT_CLI="detekt"
INPUT_PATH="../../"

# Check if detekt is installed
if ! command -v $DETEKT_CLI &> /dev/null
then
    echo "Detekt CLI could not be found. Please install detekt first."
    exit 1
fi

# Run Detekt
# --config: path to config file
# --input: path(s) to scan
# --report: where to save output (xml, html, txt)
$DETEKT_CLI --input "$INPUT_PATH" --config "$DETEKT_CONFIG" --excludes "**/build/**" --debug