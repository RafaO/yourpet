//
//  PetsListView.swift
//  iosApp
//
//  Created by Rafael Ortega on 23/05/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import shared
import SwiftUI

struct PetsListView: View {
    var content: [Pet]
    
    var body : some View{
        VStack() {
            Text("Pets")
            List(content, id: \.name) { pet in
                NavigationLink(destination: PetDetailsView(pet: pet)) {
                    PetRow(pet: pet)
                }
            }
        }
    }
}
