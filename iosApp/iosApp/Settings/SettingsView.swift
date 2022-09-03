//
//  SettingsView.swift
//  iosApp
//
//  Created by Rafael Ortega on 10/7/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct SettingsView: View {
    @State private var selectedTheme = 0
    
    var body: some View {
        return VStack(spacing: 10) {
                Text("Settings")
                HStack() {
                    Text("Theme")
                    Spacer()
                    Picker(selection: $selectedTheme, label: Text("Theme")) {
                        Text("light")
                        Text("dark")
                    }.pickerStyle(MenuPickerStyle())
                }
            }
    }
}
