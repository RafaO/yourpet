package com.keller.yourpet.androidApp.main.viewmodel

data class MainViewState(val themeColor: ThemeColor)

enum class ThemeColor {
    LIGHT,
    DARK,
    SYSTEM
}

fun ThemeColor.isDark(isSystemDark: Boolean) =
    this == ThemeColor.DARK || this == ThemeColor.SYSTEM && isSystemDark
