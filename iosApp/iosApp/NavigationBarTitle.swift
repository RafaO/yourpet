//
//  NavigationBarTitle.swift
//  iosApp
//
//  Created by Rafael Ortega on 18/04/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import SwiftUI

@available(iOS 14, *)
struct NavigationTitleViewModifier: ViewModifier {
    var text: String
    
    func body(content: Content) -> some View {
        content
            .navigationTitle(text)
    }
}
struct NavigationBarTitleViewModifier: ViewModifier {
    var text: String
    
    func body(content: Content) -> some View {
        content
            .navigationBarTitle(text)
    }
}

extension View {
    @ViewBuilder
    func customNavigationTitle(with text: String) -> some View {
        if #available(iOS 14, *) {
            self
                .modifier(NavigationTitleViewModifier(text: text))
                .navigationBarTitle(text, displayMode: .inline)
        }
        else {
            self.modifier(NavigationBarTitleViewModifier(text: text))
        }
    }
}
