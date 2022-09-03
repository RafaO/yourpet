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
    @State private var selectedTheme = 0
    
    var body: some View {
        return VStack(spacing: 10) {
                Text("Settings")
                HStack() {
                    Text("Theme")
                    Spacer()
                    Picker(selection: $selectedTheme, label: Text("Theme")) {
                        ForEach (0..<ColorScheme.allCases.count, id: \.self) {
                            Text(ColorScheme.allCases[$0].name())
                        }
                    }.pickerStyle(MenuPickerStyle())
                }
            }
    }
}
