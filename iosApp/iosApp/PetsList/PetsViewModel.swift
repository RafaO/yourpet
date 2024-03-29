//
//  PetsViewModel.swift
//  iosApp
//
//  Created by Rafael Ortega on 02/04/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

enum PetsScreenState: Equatable {
    case loading
    case content([Pet])
    case error(String)
}

class PetsViewModel: ObservableObject {
    @Published private(set) var state = PetsScreenState.loading
    
    private let getPetsUseCase: GetPetsUseCaseInterface
    
    init(getPetsUseCase: GetPetsUseCaseInterface) {
        self.getPetsUseCase = getPetsUseCase
    }
    
    func viewCreated(genders: [Gender_: Bool]) {
        let applicable = KotlinMutableSet<Gender_>(array: genders.filter { $0.value }.map {$0.key})
        getPetsUseCase.invoke(param: Filter_(genders: applicable)) { (flow: CFlow<BaseFlowableUseCaseResult<AnyObject>>?, _) in
            flow?.watch(block: {(result: BaseFlowableUseCaseResult<AnyObject>?) in
                switch result {
                case let success as BaseFlowableUseCaseResultSuccess<NSArray>:
                    let pets = success.result
                    if pets?.count ?? 0 > 0 {
                        self.state = PetsScreenState.content(pets as! [Pet])
                    } else {
                        self.state = PetsScreenState.error("No pets")
                    }
                case let error as BaseFlowableUseCaseResultFailure<NSArray>:
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
