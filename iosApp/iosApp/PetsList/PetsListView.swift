//
//  PetsListView.swift
//  iosApp
//
//  Created by Rafael Ortega on 23/05/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct PetsListView: View {
    var content: PetsScreenState.Content
    
    var body : some View{
        NavigationView {
            List(content.pets, id: \.name) { pet in
                NavigationLink(destination: PetDetailsView(pet: pet)) {
                    PetRow(pet: pet)
                }
            }
            .customNavigationTitle(with: "Pets")
        }
    }
}
