//
//  SettingsView.swift
//  iosApp
//
//  Created by Rafael Ortega on 10/7/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI

extension ColorScheme {
    public func name() -> String {
        switch(self) {
        case .dark:
            return "dark"
        case .light:
            return "light"
        @unknown default:
            return ""
        }
    }
}

struct SettingsView: View {
    private let mainViewModel: MainViewModel
    @State private var selectedTheme: ColorScheme
    
    init(mainViewModel: MainViewModel) {
        self.mainViewModel = mainViewModel
        self._selectedTheme = State(initialValue: mainViewModel.state.colorScheme)
    }
    
    var body: some View {
        return VStack(spacing: 10) {
                Text("Settings")
                HStack() {
                    Text("Theme")
                    Spacer()
                    Picker(selection: $selectedTheme, label: Text("Theme")) {
                        ForEach(ColorScheme.allCases, id: \.self) {
                            Text($0.name())
                        }
                    }
                    .pickerStyle(MenuPickerStyle())
                    .onChange(of: selectedTheme) { new in mainViewModel.colorSchemeSelected(selected: new) }
                }
            }
    }
}
