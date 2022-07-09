//
//  TextStyles.swift
//  iosApp
//
//  Created by Rafael Ortega on 9/7/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI

// extension function
extension Text {
    func textStyle<Style: ViewModifier>(_ style: Style) -> some View {
        ModifiedContent(content: self, modifier: style)
    }
}

// styles

struct MenuStyle: ViewModifier {
    func body(content: Content) -> some View {
        content
            .foregroundColor(.gray)
            .font(.headline)
    }
}
