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
        let pets: [Pet]
    }
    
    case loading
    case content(Content)
    case error(String)
}

class PetsViewModel: ObservableObject {
    @Published private(set) var state = PetsScreenState.loading
    
    private let getPetsUseCase: GetPetsUseCase
    
    init(getPetsUseCase: GetPetsUseCase) {
        self.getPetsUseCase = getPetsUseCase
    }
    
    func viewCreated(genders: [Gender_: Bool]) {
        let applicable = KotlinMutableSet<Gender_>(array: genders.filter { $0.value }.map {$0.key})
        getPetsUseCase.invoke(param: Filter_(genders: applicable)) { (flow: CFlow<FlowableUseCaseResult<NSArray>>?, _) in
            flow?.watch(block: {(result: FlowableUseCaseResult<NSArray>?) in
                switch result {
                case let success as FlowableUseCaseResultSuccess<NSArray>:
                    let pets = success.result
                    if pets?.count ?? 0 > 0 {
                        self.state = PetsScreenState.content(PetsScreenState.Content(pets: pets as! [Pet]))
                    } else {
                        self.state = PetsScreenState.error("No pets")
                    }
                case let error as FlowableUseCaseResultFailure<NSArray>:
                    switch self.state {
                    case .content: break
                    default:
                        self.state = PetsScreenState.error(error.error?.message ?? "something went wrong")
                    }
                default: break
                }
            })
        }
    }
}
