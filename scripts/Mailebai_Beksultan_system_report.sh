#!/bin/bash
set -euo pipefail

PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
LOG_FILE="$PROJECT_DIR/logs/Mailebai_Beksultan_system_report.log"

{
    echo "Student: Mailebai Beksultan"
    echo "Group: IT2-2313 | ID: 37691"
    echo "Date: $(date --iso-8601=seconds)"
    echo "Username: $(whoami)"

    echo "=== Operating system ==="
    cat /etc/os-release
    echo "Kernel: $(uname -r)"

    echo "=== CPU ==="
    lscpu

    echo "=== Memory ==="
    free -h

    echo "=== Disk usage and available space ==="
    df -h

    echo "=== Project directory size ==="
    du -sh "$PROJECT_DIR"

    echo "Report completed: Mailebai Beksultan"
} 2>&1 | tee "$LOG_FILE"
