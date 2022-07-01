package com.keller.yourpet.androidApp.home.viewmodel

import com.keller.yourpet.androidApp.home.view.menu.MenuOption

data class HomeUiState(val optionSelected: MenuOption, val filterUpdated: Boolean)
