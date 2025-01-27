package com.example.arcanite.uikit.helpfulFunctions


fun formatFileSize(size: Int): String {
    return when {
        size >= 1024 -> {
            "%.1f KB".format(size / 1024f)
        }
        else -> {
            "$size B"
        }
    }
}