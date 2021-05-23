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
        Text(message)
        Button("Retry") {
            onButtonClicked()
        }
    }
}
