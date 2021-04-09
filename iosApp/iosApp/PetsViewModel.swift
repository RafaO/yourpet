//
//  PetsViewModel.swift
//  iosApp
//
//  Created by Rafael Ortega on 02/04/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

enum PetsScreenState {
    
    struct Content {
        let textToDisplay: String
        let imageUrl: String
    }
    
    case loading
    case content(Content)
    case error(String)
}

class PetsViewModel: ObservableObject {
    @Published private(set) var state: PetsScreenState = PetsScreenState.loading
    
    private let getPetsUseCase: GetPetsUseCase
    
    init(getPetsUseCase: GetPetsUseCase) {
        self.getPetsUseCase = getPetsUseCase
    }
    
    func viewCreated() {
        getPetsUseCase.execute { (flow: CFlow<NSArray>?, _) in
            flow?.watch(block: { (pets: NSArray?) in
                if pets?.count ?? 0 > 0 {
                    let firstPet = pets![0] as! Pet
                    self.state = PetsScreenState.content(PetsScreenState.Content(textToDisplay: firstPet.name, imageUrl: firstPet.imageUrl))
                } else {
                    self.state = PetsScreenState.error("No pets")
                }
            })
        }
    }
}
