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
    @Published private(set) var imageUrl: String?
    
    private let getPetsUseCase: GetPetsUseCase
    
    init(getPetsUseCase: GetPetsUseCase) {
        self.getPetsUseCase = getPetsUseCase
        imageUrl = nil
    }
    
    func viewCreated() {
        getPetsUseCase.execute { (flow: CFlow<NSArray>?, _) in
            flow?.watch(block: { (pets: NSArray?) in
                if pets?.count ?? 0 > 0 {
                    self.textToDisplay = (pets![0] as! Pet).name
                    self.imageUrl = (pets![0] as! Pet).imageUrl
                } else {
                    self.textToDisplay = "No pets"
                }
            })
        }
    }
}
