package com.keller.yourpet.androidApp.settings.viewmodel

data class SettingsViewState(val themeColor: ThemeColor)

enum class ThemeColor {
    LIGHT,
    DARK,
    SYSTEM
}
