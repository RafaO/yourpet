//
//  MainViewModel.swift
//  iosApp
//
//  Created by Rafael Ortega on 10/7/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI

enum MenuOption: CaseIterable {
    case Pets
    case Settings
}

struct GlobalState {
    let colorScheme: ColorScheme
}

class MainViewModel: ObservableObject {
    @Published private(set) var state = GlobalState(colorScheme: .dark)
    
    func colorSchemeSelected(selected: ColorScheme) {
        self.state = GlobalState(colorScheme: selected)
    }
}
