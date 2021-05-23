//
//  PetRow.swift
//  iosApp
//
//  Created by Rafael Ortega on 09/04/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import Foundation
import shared

struct PetRow: View {
    var pet: Pet
    
    @State var image: UIImage = UIImage()
    
    var body: some View {
        Text(pet.name)
        Image(uiImage: image)
            .resizable()
            .aspectRatio(contentMode: .fit)
            .frame(width:100, height:100)
            .onReceive(ImageLoader(urlString: pet.imageUrl).didChange) { data in
                self.image = UIImage(data: data) ?? UIImage()
            }
    }
}
