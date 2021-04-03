//
//  PetsViewModel.swift
//  iosApp
//
//  Created by Rafael Ortega on 02/04/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

class PetsViewModel: ObservableObject {
    @Published private(set) var textToDisplay: String = "Loading..."
    
    private let getPetsUseCase: GetPetsUseCase
    
    init(getPetsUseCase: GetPetsUseCase) {
        self.getPetsUseCase = getPetsUseCase
    }
    
    func viewCreated() {
        getPetsUseCase.execute { (flow: CFlow<NSArray>?, _) in
            flow?.watch(block: { (pets: NSArray?) in
                self.textToDisplay = pets?.count ?? 0 > 0 ? (pets![0] as! Pet).name : "No pets"
            })
        }
    }
}
