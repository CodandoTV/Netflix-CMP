#!/bin/bash

# Default configuration path
DETEKT_CONFIG="../../config/detekt/detekt.yml"
DETEKT_CLI="detekt"

# Check if detekt is installed
if ! command -v $DETEKT_CLI &> /dev/null
then
    echo "Detekt CLI could not be found. Please run 'scripts/linter/install-detekt.sh' first."
    exit 1
fi

# Run Detekt
# --config: path to config file
# --input: path(s) to scan
# --report: where to save output (xml, html, txt)
detekt -i ../../ -c "../../config/detekt/detekt.yml" -ex "**/build/**"