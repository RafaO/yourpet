//
//  PetsListErrorView.swift
//  iosApp
//
//  Created by Rafael Ortega on 23/05/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct PetsListErrorView: View {
    let message: String
    let onButtonClicked: () -> Void
    
    
    var body : some View{
        GeometryReader { geometry in
            VStack {
                Text(message).multilineTextAlignment(.center)
                Button("Retry") {
                    onButtonClicked()
                }
            }
            .padding(.all, CGFloat(20))
            .frame(width: geometry.size.width)
        }
    }
}
