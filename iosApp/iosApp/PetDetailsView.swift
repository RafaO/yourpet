//
//  PetDetailsView.swift
//  iosApp
//
//  Created by Rafael Ortega on 18/04/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct PetDetailsView: View {
    var pet: Pet
    
    var body: some View {
        Text(pet.name)
    }
}
